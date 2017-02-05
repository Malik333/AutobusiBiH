package com.alenmalik.autobusibih;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton city;
    ImageButton route;
    ImageButton busState;
    ImageButton about;

    static Boolean cityActivityActive = false;
    static Boolean routeActivityActive = false;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        SharedPreferences prefs = this.getSharedPreferences(
                "com.your.app", Context.MODE_PRIVATE);
        boolean hasVisisted = prefs.getBoolean("HAS_VISISTED_BEFORE", false);
        if(!hasVisisted) {
            Toast.makeText(MainActivity.this, "Potrebno je da internet bude uključen da se podaci skinu", Toast.LENGTH_LONG).show();
            prefs.edit().putBoolean("HAS_VISISTED_BEFORE", true).commit();
        }

        city = (ImageButton) findViewById(R.id.cityIde);
        route = (ImageButton) findViewById(R.id.routeId);
        busState = (ImageButton) findViewById(R.id.busStateId);
        about = (ImageButton) findViewById(R.id.aboutIde);

        city.setOnClickListener(this);
        route.setOnClickListener(this);
        busState.setOnClickListener(this);
        about.setOnClickListener(this);
        onStop();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cityIde) {


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
        } else if (view.getId() == R.id.aboutIde) {
            Intent cityIntent = new Intent(MainActivity.this, PriceActivity.class);
            startActivity(cityIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_o_nama:
                Intent i = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_pomozite_nam:
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
