package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class InternacionalTraffic extends AppCompatActivity {
    Spinner stateSpinner;
    ArrayAdapter<String> stateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internacional_traffic);

        stateSpinner = (Spinner) findViewById(R.id.drzavaSpinner);
        stateAdapter = new ArrayAdapter<String>(InternacionalTraffic.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.drzave));
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(InternacionalTraffic.this, MainActivity.class));
    }
}
