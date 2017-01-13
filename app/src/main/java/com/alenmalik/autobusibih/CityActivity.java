package com.alenmalik.autobusibih;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CityActivity extends AppCompatActivity implements View.OnClickListener {

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter3;
    ArrayList<String> nameList;
    HashSet<String> hashSet;
    static double newLng;
    static double newLat;
    Button search;
    ListView cityview;
    ArrayAdapter<String> adapter;
    ArrayList<String> listCity;
    Intent intent;
    static String nameCity;
    static Double secondCityLAT;
    static Double secondCityLNG;
    String chooseCityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        nameList = new ArrayList<String>();
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        hashSet = new HashSet<String>();
        autoCompleteSuggesstion();
        search = (Button) findViewById(R.id.search_city_btn);
        //listCityNameto();

        cityview = (ListView) findViewById(R.id.listView34);
        listCity = new ArrayList<String>();
        listCity.add("Waiting...");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listCity);
        cityview.setAdapter(adapter);

        search.setOnClickListener(this);

        gotNext();


    }

        public void gotNext() {


            cityview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    double latitude = 0;
                    double longitude = 0;
                    ParseGeoPoint location = new ParseGeoPoint(latitude, longitude);
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("CityLocation");
                    query.whereNear("Location", location);
                    query.whereEqualTo("Name", listCity.get(position));
                    query.setLimit(10);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> list, ParseException e) {
                            if (e == null) {

                                if (list.size() > 0) {

                                    for (ParseObject object : list) {
                                        secondCityLAT = object.getParseGeoPoint("Location").getLatitude();
                                        secondCityLNG = object.getParseGeoPoint("Location").getLongitude();

                                    }

                                }


                            }
                        }
                    });

                    Intent intent1 = new Intent(CityActivity.this, DetailsActivity.class);
                    intent1.putExtra("selectCity", listCity.get(position));
                    startActivity(intent1);
                }


            });
        }


            public void onClick(View view) {
                if (view.getId() == R.id.search_city_btn) {

                    chooseCityName = String.valueOf(autoCompleteTextView.getText());

                    double latitude = 0;
                    double longitude = 0;
                    ParseGeoPoint location = new ParseGeoPoint(latitude, longitude);
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("CityLocation");
                    query.whereNear("Location", location);
                    query.whereEqualTo("Name", chooseCityName);
                    query.setLimit(10);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> list, ParseException e) {
                            if (e == null) {

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
                    query2.whereEqualTo("fromCity", chooseCityName);

                    query2.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> list, ParseException e) {
                            if (e == null){

                                if (list.size() > 0){

                                    listCity.clear();

                                    for (ParseObject object : list){

                                        listCity.add(String.valueOf(object.get("toCity")));

                                    }

                                    adapter.notifyDataSetChanged();

                                }

                            }
                        }
                    });

                }
            }
    public void listCityNameto(){

    }

            public void autoCompleteSuggesstion() {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Cities");
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {

                        if (e == null) {
                            if (list.size() > 0) {

                                for (ParseObject object : list) {

                                    nameList.add(String.valueOf(object.get("fromCity")));


                                }


                                Log.i("City", String.valueOf(nameList.size()));

                                hashSet.addAll(nameList);
                                nameList.clear();
                                nameList.addAll(hashSet);
                                adapter3 = new ArrayAdapter<String>(CityActivity.this, android.R.layout.simple_dropdown_item_1line, nameList);
                                autoCompleteTextView.setThreshold(1);

                                autoCompleteTextView.setAdapter(adapter3);
                                adapter3.notifyDataSetChanged();


                            }
                        }
                    }
                });
            }

            }