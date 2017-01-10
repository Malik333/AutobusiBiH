package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button city;
    Button route;
    Button busState;
    Button about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = (Button) findViewById(R.id.city_btn);
        route = (Button) findViewById(R.id.route_btn);
        busState = (Button) findViewById(R.id.butState_btn);
        about = (Button) findViewById(R.id.about_btn);

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cityIntent = new Intent(MainActivity.this, CityActivity.class);
                startActivity(cityIntent);
            }
        });
        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cityIntent = new Intent(MainActivity.this, RouteActivity.class);
                startActivity(cityIntent);
            }
        });

        busState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cityIntent = new Intent(MainActivity.this, BusStateActivity.class);
                startActivity(cityIntent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cityIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(cityIntent);
            }
        });

    }
}
