package com.alenmalik.autobusibih;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class PrijevozniciPopUp extends AppCompatActivity implements View.OnClickListener {

    TextView prijevoznikTxt;
    TextView gradTxt;
    TextView adresaTxt;
    TextView telTxt;
    TextView emailTxt;
    TextView webAdresaTxt;

    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijevoznici_pop_up);
        getSupportActionBar().setTitle("O PRIJEVOZNIKU");
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        prijevoznikTxt = (TextView) findViewById(R.id.prijevoznikIspis);
        gradTxt = (TextView) findViewById(R.id.gradIspis);
        adresaTxt = (TextView) findViewById(R.id.adressaIspis);
        telTxt = (TextView) findViewById(R.id.telIspis);
        emailTxt = (TextView) findViewById(R.id.emailIspis);
        webAdresaTxt = (TextView) findViewById(R.id.webAdresaIspis);

        webAdresaTxt.setOnClickListener(this);
        telTxt.setOnClickListener(this);

        Intent intent = getIntent();
        value = intent.getStringExtra("prijevoznik");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Prijevoznici");
        query.whereEqualTo("Prijevoznik",value);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                if (e == null){

                    for (ParseObject object : list){

                        prijevoznikTxt.setText(String.valueOf(object.get("Prijevoznik")));
                        gradTxt.setText(String.valueOf(object.get("Grad")));
                        adresaTxt.setText(String.valueOf(object.get("Adresa")));
                        telTxt.setText(String.valueOf(object.get("Telefon")));
                        emailTxt.setText(String.valueOf(object.get("email")));
                        webAdresaTxt.setText(String.valueOf(object.get("webAdresa")));
                    }


                }


            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.webAdresaIspis) {

            Intent intent2 = new Intent(PrijevozniciPopUp.this, SemberijaTransport.class);
            String webAdresa = String.valueOf(webAdresaTxt.getText());
            intent2.putExtra("prijevoznikweb", webAdresa);
            startActivity(intent2);

        } else if (view.getId() == R.id.telIspis) {

            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:+" + telTxt.getText().toString().trim()));
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                return;
            }

            startActivity(callIntent);
        }
    }
}
