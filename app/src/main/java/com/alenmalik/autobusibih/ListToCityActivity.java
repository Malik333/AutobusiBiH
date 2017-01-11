package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ListToCityActivity extends AppCompatActivity {

    ListView cityview;
    ArrayAdapter<String> adapter;
    ArrayList<String> listCity;
    Intent intent;
    static String nameCity;
    static Double secondCityLAT;
    static Double secondCityLNG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_to_city);

        cityview = (ListView) findViewById(R.id.listView);
        listCity = new ArrayList<String>();
        listCity.add("Waiting...");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listCity);

        cityview.setAdapter(adapter);
        intent = getIntent();

        cityview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                double latitude = 0;
                double longitude = 0;
                ParseGeoPoint location = new ParseGeoPoint(latitude,longitude);
                ParseQuery<ParseObject> query = ParseQuery.getQuery("CityLocation");
                query.whereNear("Location", location);
                query.whereEqualTo("Name", listCity.get(position));
                query.setLimit(10);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        if (e == null){

                            if (list.size() > 0){

                                for (ParseObject object : list){
                                    secondCityLAT = object.getParseGeoPoint("Location").getLatitude();
                                    secondCityLNG = object.getParseGeoPoint("Location").getLongitude();

                                }

                            }


                        }
                    }
                });

                Intent intent1 = new Intent(ListToCityActivity.this, DetailsActivity.class);
                intent1.putExtra("selectCity", listCity.get(position));
                startActivity(intent1);

            }
        });

        listCityNameto();




    }


    public void listCityNameto(){


        nameCity = intent.getStringExtra("cityName");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Cities");
        query.whereEqualTo("fromCity", nameCity);

        query.findInBackground(new FindCallback<ParseObject>() {
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
