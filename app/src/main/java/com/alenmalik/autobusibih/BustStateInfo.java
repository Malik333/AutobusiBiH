package com.alenmalik.autobusibih;

import android.*;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class BustStateInfo extends AppCompatActivity implements View.OnClickListener {

    TextView name, address, phone;
    static String nameCity;
    Intent inte;
    Button openMap;

    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bust_state_info);

        name = (TextView) findViewById(R.id.cityName_txt);
        address = (TextView) findViewById(R.id.address_txt);
        phone = (TextView) findViewById(R.id.phone_number);
        dialog = new ProgressDialog(this);

        openMap = (Button) findViewById(R.id.openMapStateBus);

        openMap.setOnClickListener(this);

        inte = getIntent();

        nameCity = inte.getStringExtra("city");

        name.setText(nameCity);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("BusAddress");
        query.whereEqualTo("CityName", nameCity);
        dialog.setMessage("Searching...");
        dialog.show();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    for (ParseObject object : list) {

                        address.setText(object.get("Address").toString());

                        phone.setText(object.get("PhoneNumber").toString());

                        phone.setOnClickListener(BustStateInfo.this);

                    }

                    dialog.dismiss();

                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.openMapStateBus){
            Intent intent = new Intent(BustStateInfo.this, BusMapAct.class);
            startActivity(intent);

        }else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:+" + phone.getText().toString().trim()));
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                return;
            }

            startActivity(callIntent);
        }
    }


}
