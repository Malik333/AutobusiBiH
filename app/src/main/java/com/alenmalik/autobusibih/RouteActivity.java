package com.alenmalik.autobusibih;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RouteActivity extends AppCompatActivity implements View.OnClickListener {
    AutoCompleteTextView fromCity;
    AutoCompleteTextView toCity;
    ArrayList<String> fromCityList;
    ArrayList<String> toCityList;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    ArrayList<String> hours_list;
    ArrayAdapter adapter_Hours;
    HashSet<String> hashSet = new HashSet<String>();
    HashSet<String> hashSet2 = new HashSet<String>();
    Button search;
    Button openMap;
    ListView hoursListView;
    static double routeLatFromCIty;
    static double routeLngFromCity;
    static double routeLatToCIty;
    static double routeLngToCity;
    ImageButton clearto, clearfrom;
    private ProgressDialog dialog;

    String chooseCityName;

    static String fromCityString;
    static String toCityString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        fromCity = (AutoCompleteTextView) findViewById(R.id.fromCityId);
        toCity = (AutoCompleteTextView) findViewById(R.id.toCityId_txt);
        search = (Button) findViewById(R.id.searchroute_bn);
        search.setOnClickListener(this);
        hours_list = new ArrayList<String>();
        openMap = (Button) findViewById(R.id.open_map);
        clearfrom = (ImageButton) findViewById(R.id.clearfromCity);
        clearto = (ImageButton) findViewById(R.id.cleartoCity);

        clearfrom.setOnClickListener(this);
        clearto.setOnClickListener(this);

        dialog = new ProgressDialog(this);

        hours_list.add("Waiting");
        adapter_Hours = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hours_list);
        hoursListView = (ListView) findViewById(R.id.listView_hours1);
        hoursListView.setAdapter(adapter_Hours);
        fromCityList = new ArrayList<>();
        toCityList = new ArrayList<>();

        openMap.setOnClickListener(this);
        fromCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                chooseCityName = (String) adapterView.getItemAtPosition(i);
                Log.i("cityName autoco", chooseCityName);
                hashSet2.clear();
                toCityList.clear();
                toCityRoute(chooseCityName);

            }
        });


        fromCityRoute();



    }

    public void fromCityRoute() {

        ParseQuery<ParseObject> fromCityQuery = new ParseQuery<ParseObject>("Cities");
        fromCityQuery.setLimit(10000);
        fromCityQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    if (list.size() > 0) {

                        for (ParseObject object : list) {
                            fromCityList.add(String.valueOf(object.get("fromCity")));

                        }
                        hashSet.addAll(fromCityList);
                        fromCityList.clear();
                        fromCityList.addAll(hashSet);
                        adapter = new ArrayAdapter<String>(RouteActivity.this, android.R.layout.simple_dropdown_item_1line, fromCityList);
                        fromCity.setThreshold(1);

                        fromCity.setAdapter(adapter);

                        adapter.notifyDataSetChanged();



                    }


                }
            }
        });

    }

    public void toCityRoute(String name) {

        ParseQuery<ParseObject> toCityQuery = new ParseQuery<ParseObject>("Cities");
        toCityQuery.fromLocalDatastore();
        toCityQuery.whereEqualTo("fromCity", name);
        
        toCityQuery.setLimit(10000);
        toCityQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    if (list.size() > 0) {

                        for (ParseObject object : list) {
                            toCityList.add(String.valueOf(object.get("toCity")));

                        }
                        hashSet2.addAll(toCityList);
                        toCityList.clear();
                        toCityList.addAll(hashSet2);
                        adapter2 = new ArrayAdapter<String>(RouteActivity.this, android.R.layout.simple_dropdown_item_1line, toCityList);
                        toCity.setThreshold(1);

                        toCity.setAdapter(adapter2);

                        adapter2.notifyDataSetChanged();

                    }

                }
            }
        });

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.searchroute_bn) {
            fromCityString = String.valueOf(fromCity.getText());
            toCityString = String.valueOf(toCity.getText());

            double latitude = 0;
            double longitude = 0;
            ParseGeoPoint fromCityLocation = new ParseGeoPoint(latitude, longitude);
            ParseQuery<ParseObject> fromCityQuery = ParseQuery.getQuery("CityLocation");
            fromCityQuery.whereNear("Location", fromCityLocation);
            fromCityQuery.whereEqualTo("Name", fromCityString);
            fromCityQuery.setLimit(10);
            fromCityQuery.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {

                        if (list.size() > 0) {

                            for (ParseObject object : list) {
                                routeLatFromCIty = object.getParseGeoPoint("Location").getLatitude();
                                routeLngFromCity = object.getParseGeoPoint("Location").getLongitude();

                                Log.i("rutalatituda", String.valueOf(routeLatFromCIty));
                                Log.i("rutalongituda", String.valueOf(routeLngFromCity));
                            }

                        }


                    }
                }
            });


            ParseGeoPoint toCityLocation = new ParseGeoPoint(latitude, longitude);
            ParseQuery<ParseObject> toCityQuery = ParseQuery.getQuery("CityLocation");
            toCityQuery.whereNear("Location", toCityLocation);
            toCityQuery.whereEqualTo("Name", toCityString);
            toCityQuery.setLimit(10);
            toCityQuery.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {

                        if (list.size() > 0) {

                            for (ParseObject object : list) {
                                routeLatToCIty = object.getParseGeoPoint("Location").getLatitude();
                                routeLngToCity = object.getParseGeoPoint("Location").getLongitude();

                                Log.i("rutalatituda", String.valueOf(routeLatToCIty));
                                Log.i("rutalongituda", String.valueOf(routeLngToCity));
                            }

                        }


                    }
                }
            });
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Cities");
            query.whereEqualTo("fromCity", fromCityString);
            query.whereEqualTo("toCity", toCityString);

            dialog.setMessage("Loading...");
            dialog.show();
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {

                    if (e == null) {

                        if (list.size() > 0) {
                            hours_list.clear();
                            for (ParseObject object : list) {

                                String hoursDay = object.get("Day") + ":  " + object.get("Hours");

                                hours_list.add(hoursDay);
                            }

                            adapter_Hours.notifyDataSetChanged();
                            dialog.dismiss();

                        }
                    }

                }
            });
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } else if (view.getId() == R.id.open_map) {


            Intent intent = new Intent(RouteActivity.this, MapRouteActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.clearfromCity){
            fromCity.setText("");
        } else if (view.getId() == R.id.cleartoCity){
            toCity.setText("");
        }


    }
}
