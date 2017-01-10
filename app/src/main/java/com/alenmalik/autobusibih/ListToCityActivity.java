package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ListToCityActivity extends AppCompatActivity {

    ListView cityview;
    ArrayAdapter<String> adapter;
    ArrayList<String> listCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_to_city);

        cityview = (ListView) findViewById(R.id.listView);
        listCity = new ArrayList<String>();
        listCity.add("Waiting...");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listCity);

        cityview.setAdapter(adapter);
        Intent intent = getIntent();

        String nameCity = intent.getStringExtra("cityName");

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
