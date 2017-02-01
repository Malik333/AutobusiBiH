package com.alenmalik.autobusibih;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
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

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class CityActivityOffline extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    AutoCompleteTextView cityAutocomplete;
    ArrayAdapter<String> adapter3;
    ArrayList<String> nameList;
    HashSet<String> hashSet, hashSettoCity;
    static double newLng;
    static double newLat;
    Button search;
    ImageButton clear;
    ListView cityView;
    ArrayAdapter<String> adapter;
    ArrayList<String> listCity;
    static Double secondCityLAT;
    static Double secondCityLNG;
    static String chooseCityName;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_offline);
        nameList = new ArrayList<String>();
        cityAutocomplete = (AutoCompleteTextView) findViewById(R.id.autoCompleteCityId_offline);
        hashSet = new HashSet<String>();
        hashSettoCity = new HashSet<String>();
        autoCompleteSuggestion();
        search = (Button) findViewById(R.id.search_city_btn_offline);
        cityView = (ListView) findViewById(R.id.cityListViewoffline);
        listCity = new ArrayList<String>();
        dialog = new ProgressDialog(this);
        clear = (ImageButton) findViewById(R.id.clearCityAct_offline);
        listCity.add("Waiting...");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listCity);
        cityView.setAdapter(adapter);
        search.setOnClickListener(this);
        cityView.setOnItemClickListener(this);
        clear.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.search_city_btn_offline) {
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

            chooseCityName = String.valueOf(cityAutocomplete.getText());

            double latitude = 0;
            double longitude = 0;
            ParseGeoPoint location = new ParseGeoPoint(latitude, longitude);
            ParseQuery<ParseObject> query = ParseQuery.getQuery("CityLocation");
            query.fromLocalDatastore();
            query.whereNear("Location", location);
            query.whereEqualTo("Name", chooseCityName);
            // query.setLimit(10);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {

                        ParseObject.pinAllInBackground(list);
                        if (list.size() > 0) {

                            for (ParseObject object : list) {
                                newLat = object.getParseGeoPoint("Location").getLatitude();
                                newLng = object.getParseGeoPoint("Location").getLongitude();

                                Log.i("latituda", String.valueOf(newLat));
                                Log.i("longituda", String.valueOf(newLng));
                            }

                        }


                    }
                }
            });

            ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Cities");
            query2.fromLocalDatastore();
            query2.whereEqualTo("fromCity", chooseCityName);
            query2.setLimit(10000);

            dialog.setMessage("Loading...");
            dialog.show();
            query2.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {
                        ParseObject.pinAllInBackground(list);

                        if (list.size() > 0) {

                            listCity.clear();
                            int i = 0;

                            for (ParseObject object : list) {

                                Log.i("City", i++ + " " + String.valueOf(object.get("toCity")));
                                listCity.add(String.valueOf(object.get("toCity")));

                            }

                            hashSettoCity.addAll(listCity);
                            listCity.clear();
                            listCity.addAll(hashSettoCity);
                            Collections.sort(listCity);

                            adapter.notifyDataSetChanged();
                            dialog.dismiss();

                        }

                    }
                }
            });

            hashSettoCity.clear();

        } else if (view.getId() == R.id.cityListViewoffline) {


        } else if (view.getId() == R.id.clearCityAct_offline) {
            cityAutocomplete.setText("");
        }
    }

    public void autoCompleteSuggestion() {


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Cities");
        query.setLimit(10000);
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {


                if (e == null) {
                    if (list.size() > 0) {
                        ParseObject.pinAllInBackground(list);
                        for (ParseObject object : list) {


                            nameList.add(String.valueOf(object.get("fromCity")));


                        }


                        Log.i("City", String.valueOf(nameList.size()));

                        hashSet.addAll(nameList);
                        nameList.clear();
                        nameList.addAll(hashSet);
                        adapter3 = new ArrayAdapter<String>(CityActivityOffline.this, android.R.layout.simple_dropdown_item_1line, nameList);
                        cityAutocomplete.setThreshold(1);

                        cityAutocomplete.setAdapter(adapter3);
                        adapter3.notifyDataSetChanged();


                    }
                }

            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (adapterView.getId() == R.id.cityListViewoffline) {

            if (listCity.get(position).equals("Waiting...")) {


            } else {
                double latitude = 0;
                double longitude = 0;
                ParseGeoPoint location = new ParseGeoPoint(latitude, longitude);
                ParseQuery<ParseObject> query = ParseQuery.getQuery("CityLocation");
                query.fromLocalDatastore();
                query.whereNear("Location", location);
                query.whereEqualTo("Name", listCity.get(position));
                //query.setLimit(10);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        if (e == null) {
                            ParseObject.pinAllInBackground(list);

                            if (list.size() > 0) {

                                for (ParseObject object : list) {
                                    secondCityLAT = object.getParseGeoPoint("Location").getLatitude();
                                    secondCityLNG = object.getParseGeoPoint("Location").getLongitude();

                                }

                            }


                        }
                    }
                });

                Intent intent1 = new Intent(CityActivityOffline.this, DetailsActivity.class);
                intent1.putExtra("selectCity", listCity.get(position));
                startActivity(intent1);
            }


        }

    }
}