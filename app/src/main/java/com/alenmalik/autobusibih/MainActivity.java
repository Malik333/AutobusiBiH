package com.alenmalik.autobusibih;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView city;
    ImageView route;
    ImageView busState;
    ImageView about;

    static Boolean cityActivityActive = false;
    static Boolean routeActivityActive = false;
    private AdView mAdView;

    Vibrator vibe;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        city = (ImageView) findViewById(R.id.cityIde);
        route = (ImageView) findViewById(R.id.routeId);
        busState = (ImageView) findViewById(R.id.busStateId);
        about = (ImageView) findViewById(R.id.priceId);

        anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_click_button);


        city.setOnClickListener(this);
        route.setOnClickListener(this);
        busState.setOnClickListener(this);
        about.setOnClickListener(this);
        onStop();
//nesghgfnbvcsadasda
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cityIde) {

            city.startAnimation(anim);
            vibe.vibrate(150);
            Intent cityIntent = new Intent(MainActivity.this, CityActivity.class);
            startActivity(cityIntent);
            cityActivityActive = true;
            routeActivityActive = false;

        } else if (view.getId() == R.id.routeId) {
            route.setAnimation(anim);
            Intent routeIntent = new Intent(MainActivity.this, RouteActivity.class);
            startActivity(routeIntent);
            routeActivityActive = true;
            cityActivityActive = false;
            vibe.vibrate(150);
        } else if (view.getId() == R.id.busStateId) {
            busState.startAnimation(anim);
            Intent cityIntent = new Intent(MainActivity.this, BusStateActivity.class);
            startActivity(cityIntent);
            vibe.vibrate(150);
        } else if (view.getId() == R.id.priceId) {
            about.startAnimation(anim);
            Intent cityIntent = new Intent(MainActivity.this, PriceActivity.class);
            startActivity(cityIntent);
            vibe.vibrate(150);
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
    public void onPause() {

            if (mAdView != null) {
                mAdView.pause();
            }
            super.onPause();

        super.onPause();
        overridePendingTransition(0, 0);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
