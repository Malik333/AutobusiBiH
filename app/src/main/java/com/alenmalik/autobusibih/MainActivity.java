package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Collections;
import java.util.List;


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

        pinObject();
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        city = (ImageButton) findViewById(R.id.cityIde);
        route = (ImageButton) findViewById(R.id.routeId);
        busState = (ImageButton) findViewById(R.id.busStateId);
        about = (ImageButton) findViewById(R.id.aboutIde);

        city.setOnClickListener(this);
        route.setOnClickListener(this);
        busState.setOnClickListener(this);
        about.setOnClickListener(this);

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
            Intent cityIntent = new Intent(MainActivity.this, AboutActivity.class);
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

    public void pinObject() {

        ParseGeoPoint location = new ParseGeoPoint(0, 0);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("CityLocation");

        query.whereNear("Location", location);
        query.whereEqualTo("Name", CityActivity.chooseCityName);
        // query.setLimit(10);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground(list);


                    if (list.size() > 0) {

                        for (ParseObject object : list) {

                        }

                    }

                }
            }
        });

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Cities");
        query2.whereEqualTo("fromCity", CityActivity.chooseCityName);
        query2.setLimit(10000);


        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground(list);

                    if (list.size() > 0) {


                        for (ParseObject object : list) {

                        }


                    }

                }
            }
        });

        ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Cities");
        query3.setLimit(10000);

        query3.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {


                if (e == null) {
                    if (list.size() > 0) {

                        ParseObject.pinAllInBackground(list);
                        for (ParseObject object : list) {

                        }
                    }
                }

            }
        });

        ParseGeoPoint location2 = new ParseGeoPoint(0, 0);
        ParseQuery<ParseObject> query4 = ParseQuery.getQuery("CityLocation");

        query4.whereNear("Location", location2);

        //query.setLimit(10);
        query4.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground(list);

                    if (list.size() > 0) {

                        for (ParseObject object : list) {

                        }

                    }


                }
            }
        });




    }
}
