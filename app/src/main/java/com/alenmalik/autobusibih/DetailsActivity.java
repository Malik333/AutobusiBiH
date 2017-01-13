package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    Intent intent;
    String toCityListActivity;
    Intent getRouteIntent;
    Intent getLocationIntent;
    double latitude = 0;
    double longitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        detailedCityListActivity();
        getRoute();

    }


    public void getRoute(){
        getRouteIntent = getIntent();
        final String getFromCity =getRouteIntent.getStringExtra("fromCity");
        String getToCity= getRouteIntent.getStringExtra("toCity");
        Toast.makeText(getApplicationContext(),getFromCity + getToCity,Toast.LENGTH_LONG).show();
        ParseQuery<ParseObject> routeQuery = new ParseQuery<ParseObject>("Cities");
        routeQuery.whereEqualTo("fromCity",getFromCity);
        routeQuery.whereEqualTo("toCity",getToCity);
        routeQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null){

                    if (list.size() > 0){

                        for (ParseObject object : list){

                            Toast.makeText(getApplicationContext(),String.valueOf(object.get("Hours")),Toast.LENGTH_LONG).show();
                        }

                    }

                }
            }
        });

    }


    public void detailedCityListActivity(){
        intent = getIntent();
        toCityListActivity = intent.getStringExtra("selectCity");

        String fromCityListActivity = CityActivity.nameCity;

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Cities");
        query.whereEqualTo("fromCity", fromCityListActivity);
        query.whereEqualTo("toCity", toCityListActivity);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                if (e == null){

                    if (list.size() > 0){

                        for (ParseObject object : list) {

                            Log.i("Hours to city: ", String.valueOf(object.get("Hours")));
                        }

                    }
                }

            }
        });
    }

    public void queryThroughLocationFromCity(){
        getLocationIntent = getIntent();
        String getFromCity =getLocationIntent.getStringExtra("fromCity");
        ParseGeoPoint location = new ParseGeoPoint(latitude,longitude);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("CityLocation");
        query.whereEqualTo("Name",getFromCity);

        query.whereNear("Location", location);
        query.setLimit(10);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null){

                    if (list.size() > 0){

                        for (ParseObject object : list){
                            double newLat = object.getParseGeoPoint("Location").getLatitude();
                            double newLng = object.getParseGeoPoint("Location").getLongitude();

                            Log.i("latituda",String.valueOf(newLat));
                            Log.i("longituda",String.valueOf(newLng));
                        }

                    }


                }
            }
        });


    }

    public void queryThroughLocationtoCity(){
        getLocationIntent = getIntent();
        String getToCity =getLocationIntent.getStringExtra("toCity");
        ParseGeoPoint location = new ParseGeoPoint(latitude,longitude);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("CityLocation");
        query.whereEqualTo("Name",getToCity);

        query.whereNear("Location", location);
        query.setLimit(10);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null){

                    if (list.size() > 0){

                        for (ParseObject object : list){
                            double newLat = object.getParseGeoPoint("Location").getLatitude();
                            double newLng = object.getParseGeoPoint("Location").getLongitude();

                            Log.i("latituda",String.valueOf(newLat));
                            Log.i("longituda",String.valueOf(newLng));
                        }

                    }


                }
            }
        });


    }

    public void open(View view){

        Intent it = new Intent(DetailsActivity.this, MapRouteActivity.class);
        getRoute();
        queryThroughLocationFromCity();
        queryThroughLocationtoCity();
        startActivity(it);
    }
}