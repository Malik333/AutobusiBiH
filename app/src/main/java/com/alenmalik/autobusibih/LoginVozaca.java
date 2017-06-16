package com.alenmalik.autobusibih;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import static com.alenmalik.autobusibih.R.id.view;

public class LoginVozaca extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText code;
    TextView errorUsername;
    TextView errorCode;
    Button login;

    ArrayList<String> listUsername;
    ArrayList<String> listCode;

    boolean valid = true;

    String serialUsername = "1";
    String serialCode = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_vozaca);

        username = (EditText) findViewById(R.id.inputUsername);
        code = (EditText) findViewById(R.id.input_kode);
        errorUsername = (TextView) findViewById(R.id.infoUsernameError);
        errorCode = (TextView) findViewById(R.id.codeError);
        login = (Button) findViewById(R.id.btn_login_vozac);
        login.setOnClickListener(this);

        listUsername = new ArrayList<>();
        listCode = new ArrayList<>();

        ParseQuery<ParseObject> getData = ParseQuery.getQuery("LoginVozaca");
        getData.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects != null) {
                    for (ParseObject object : objects) {
                        listUsername.add(String.valueOf(object.get("username")));
                        listCode.add(String.valueOf(object.get("kod")));
                    }
                }

            }
        });
    }

    public boolean validate() {
        valid = true;

        if (TextUtils.isEmpty(username.getText().toString())) {
            errorUsername.setText("Polje ne može biti prazno");
            errorUsername.setVisibility(View.VISIBLE);
            valid = false;
        } else if (!listUsername.contains(username.getText().toString().trim())) {
            errorUsername.setText("Unjeli ste nepostojeći username");
            errorUsername.setVisibility(View.VISIBLE);
            valid = false;
        } else {
            errorUsername.setText("");
            errorUsername.setVisibility(View.GONE);

        }

        if (TextUtils.isEmpty(code.getText().toString().trim())) {
            errorCode.setText("Polje ne može biti prazno");
            errorCode.setVisibility(View.VISIBLE);
            valid = false;
        } else if (!listCode.contains(code.getText().toString().trim())) {
            errorCode.setText("Unjeli ste nepostojeći kod");
            errorCode.setVisibility(View.VISIBLE);
            valid = false;
        } else {
            errorCode.setText("");
            errorCode.setVisibility(View.GONE);

        }

        return valid;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login_vozac) {

            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setMessage("Provjera podataka...");
            dialog.show();
            usernameSerial();
            codeSerial();

            new CountDownTimer(2000, 1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {

                    if (!serialUsername.equals(serialCode)) {
                        Toast.makeText(LoginVozaca.this, "Username i kod se ne poklapaju", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                    if (validate() && serialUsername.equals(serialCode)) {

                        ParseQuery<ParseObject> getInfo = ParseQuery.getQuery("LoginVozaca");
                        getInfo.whereEqualTo("username", username.getText().toString().trim());
                        getInfo.whereEqualTo("kod", code.getText().toString().trim());
                        getInfo.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> objects, ParseException e) {
                                if (objects != null) {
                                    for (ParseObject object : objects) {
                                        String prijevoznik = String.valueOf(object.get("prijevoznik"));
                                        String relacija = String.valueOf(object.get("relacija"));
                                        String username = String.valueOf(object.get("username"));

                                        Intent openLocationActivity = new Intent(LoginVozaca.this, VozacLogovan.class);
                                        openLocationActivity.putExtra("prijevoznik", prijevoznik);
                                        openLocationActivity.putExtra("relacija", relacija);
                                        openLocationActivity.putExtra("username", username);
                                        startActivity(openLocationActivity);
                                    }
                                }

                            }
                        });
                        dialog.dismiss();

                    } else {
                        Toast.makeText(LoginVozaca.this, "failed", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }
            }.start();
        }
    }

    public void usernameSerial() {
        if (!TextUtils.isEmpty(username.getText().toString().trim())) {
            ParseQuery<ParseObject> getSerialCodeUsername = ParseQuery.getQuery("LoginVozaca");
            getSerialCodeUsername.whereEqualTo("username", username.getText().toString().trim());
            getSerialCodeUsername.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (objects != null) {
                        for (ParseObject object : objects) {
                            serialUsername = String.valueOf(object.get("serialCode"));
                        }
                    }

                }
            });
        }
    }

    public void codeSerial() {
        if (!TextUtils.isEmpty(code.getText().toString().trim())) {
            ParseQuery<ParseObject> getSerialCodeKod = ParseQuery.getQuery("LoginVozaca");
            getSerialCodeKod.whereEqualTo("kod", code.getText().toString().trim());
            getSerialCodeKod.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (objects != null) {
                        for (ParseObject object : objects) {
                            serialCode = String.valueOf(object.get("serialCode"));
                        }
                    }

                }
            });
        }
    }
}
