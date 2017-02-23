package com.alenmalik.autobusibih;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class PopUpActivitiy extends AppCompatActivity implements View.OnClickListener{

    TextView grad, adresa, broj;
    Intent infoIntent;
    String cityName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_activitiy);
        getSupportActionBar().setTitle("INFORMACIJE");

        grad = (TextView) findViewById(R.id.gradIspis);
        adresa = (TextView) findViewById(R.id.adresaIspis);
        broj = (TextView) findViewById(R.id.telefonIspis);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        infoIntent = getIntent();
        cityName = infoIntent.getStringExtra("gradStanica");


        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)( width * .8),(int)(height * .6));
        broj.setOnClickListener(this);
        if (CityTraffic.stanicaCity == true){
            stanicaIspisCity();
        }
    }

    public void stanicaIspisCity(){

            ParseQuery<ParseObject> query = ParseQuery.getQuery("BusAddress");
            query.whereEqualTo("CityName", cityName);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null){
                        for (ParseObject object : list){
                            grad.setText(cityName);
                            adresa.setText(String.valueOf(object.get("Address")));
                            broj.setText(String.valueOf(object.get("PhoneNumber")));
                        }
                    }
                }
            });
        }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.telefonIspis){
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:+" + broj.getText().toString().trim()));
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                return;
            }

            startActivity(callIntent);

        }
    }


}
