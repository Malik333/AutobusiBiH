package com.alenmalik.autobusibih;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class VozacLogovan extends AppCompatActivity {
Button startGps;
    Button stopGps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vozac_logovan);
        startGps = (Button) findViewById(R.id.start_gps);
        stopGps = (Button) findViewById(R.id.stop_gps);
        startGps.setEnabled(true);
        stopGps.setEnabled(false);
        stopGps.setClickable(false);

        startGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGps.setEnabled(false);
                stopGps.setEnabled(true);
                startGps.setClickable(false);
                stopGps.setClickable(true);
                Toast.makeText(VozacLogovan.this,"Uspješno ste pokrenuli pračenje autobusa",Toast.LENGTH_LONG).show();
            }
        });

        stopGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGps.setEnabled(true);
                startGps.setClickable(true);
                stopGps.setEnabled(false);
                stopGps.setClickable(false);
                Toast.makeText(VozacLogovan.this,"Uspješno ste zasutavili pračenje autobusa",Toast.LENGTH_LONG).show();
            }
        });
    }
}
