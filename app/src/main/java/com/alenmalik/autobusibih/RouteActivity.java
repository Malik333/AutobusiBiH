package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;
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
    HashSet<String> hashSet = new HashSet<String>();
    HashSet<String> hashSet2 = new HashSet<String>();
    Button search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        fromCity = (AutoCompleteTextView) findViewById(R.id.fromCityId);
        toCity = (AutoCompleteTextView) findViewById(R.id.toCityId);
        search = (Button) findViewById(R.id.button2);
        search.setOnClickListener(this);

        fromCityList = new ArrayList<>();
        toCityList = new ArrayList<>();

        fromCityRoute();
        toCityRoute();



    }

    public void fromCityRoute(){


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
                        adapter = new ArrayAdapter<String>(RouteActivity.this,android.R.layout.simple_dropdown_item_1line,fromCityList);
                        fromCity.setThreshold(1);

                        fromCity.setAdapter(adapter);

                        adapter.notifyDataSetChanged();

                    }




                }
            }
        });

    }

    public void toCityRoute(){


        ParseQuery<ParseObject> toCityQuery = new ParseQuery<ParseObject>("Cities");
        toCityQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null){
                    if (list.size() > 0){

                        for (ParseObject object : list){
                            toCityList.add(String.valueOf(object.get("toCity")));

                        }
                        hashSet2.addAll(toCityList);
                        toCityList.clear();
                        toCityList.addAll(hashSet2);
                        adapter2 = new ArrayAdapter<String>(RouteActivity.this,android.R.layout.simple_dropdown_item_1line,toCityList);
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

        String fromCityString = String.valueOf(fromCity.getText());
        String toCityString = String.valueOf(toCity.getText());

        Intent i = new Intent(RouteActivity.this,DetailsActivity.class);
        i.putExtra("fromCity",fromCityString);
        i.putExtra("toCity",toCityString);

        startActivity(i);

    }
}
