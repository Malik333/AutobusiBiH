package com.alenmalik.autobusibih;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RouteActivity extends AppCompatActivity {
    AutoCompleteTextView fromCity;
    AutoCompleteTextView toCity;
    ArrayList<String> fromCityList;
    ArrayList<String> toCityList;
    ArrayAdapter<String> fromCityAdapter;
    ArrayAdapter<String> toCityAdapter;
    final HashSet<String> hashSet = new HashSet<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        fromCity = (AutoCompleteTextView) findViewById(R.id.fromCityId);
        toCity = (AutoCompleteTextView) findViewById(R.id.toCityId);

        fromCityList = new ArrayList<>();
        toCityList = new ArrayList<>();


        ParseQuery<ParseObject> fromCityQuery = new ParseQuery<ParseObject>("Cities");
        fromCityQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null){
                 if (list.size() > 0){

                     for (ParseObject object : list){
                         fromCityList.add(String.valueOf(object.get("fromCity")));

                     }
                     hashSet.addAll(fromCityList);
                     fromCityList.clear();
                     fromCityList.addAll(hashSet);
                     fromCityAdapter = new ArrayAdapter<String>(RouteActivity.this,android.R.layout.simple_dropdown_item_1line,fromCityList);
                     fromCity.setThreshold(1);

                     fromCity.setAdapter(fromCityAdapter);

                     fromCityAdapter.notifyDataSetChanged();

                 }




                }
            }
        });


        ParseQuery<ParseObject> toCityQuery = new ParseQuery<ParseObject>("Cities");
        toCityQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null){
                    if (list.size() > 0){

                        for (ParseObject object : list){
                            toCityList.add(String.valueOf(object.get("toCity")));

                        }
                        hashSet.addAll(toCityList);
                        toCityList.clear();
                        toCityList.addAll(hashSet);
                        toCityAdapter = new ArrayAdapter<String>(RouteActivity.this,android.R.layout.simple_dropdown_item_1line,toCityList);
                        toCity.setThreshold(1);

                        toCity.setAdapter(toCityAdapter);

                        toCityAdapter.notifyDataSetChanged();

                    }




                }
            }
        });



    }
}
