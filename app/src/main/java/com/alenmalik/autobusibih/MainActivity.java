package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton city;
    ImageButton route;
    ImageButton busState;
    ImageButton about;

    static Boolean cityActivityActive = false;
    static Boolean routeActivityActive = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = (ImageButton) findViewById(R.id.cityId);
        route = (ImageButton) findViewById(R.id.routeId);
        busState = (ImageButton) findViewById(R.id.busStateId);
        about = (ImageButton) findViewById(R.id.aboutId);

        city.setOnClickListener(this);
        route.setOnClickListener(this);
        busState.setOnClickListener(this);
        about.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cityId) {
            Intent cityIntent = new Intent(MainActivity.this, CityActivity.class);
            startActivity(cityIntent);
            cityActivityActive = true;
            routeActivityActive = false;
        } else if (view.getId() == R.id.routeId) {
            Intent routeIntent = new Intent(MainActivity.this, RouteActivity.class);
            startActivity(routeIntent);
            routeActivityActive = true;
            cityActivityActive = false;
        } else if (view.getId() == R.id.busStateId) {
            Intent cityIntent = new Intent(MainActivity.this, BusStateActivity.class);
            startActivity(cityIntent);
        } else if (view.getId() == R.id.aboutId) {
            Intent cityIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(cityIntent);
        }
    }
}
