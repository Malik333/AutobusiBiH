package com.alenmalik.autobusibih;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class BusStateActivity extends AppCompatActivity {

    ArrayAdapter adapter;
    ArrayList<String> listCityName;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_state);
        listView = (ListView) findViewById(R.id.busName_list);

        listCityName = new ArrayList<String>();
        listCityName.add("Waiting...");
        adapter = new ArrayAdapter<String>(BusStateActivity.this, android.R.layout.simple_list_item_1, listCityName);
        listView.setAdapter(adapter);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("BusAddress");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null){

                    listCityName.clear();
                    for (ParseObject object : list){

                        listCityName.add(String.valueOf(object.get("CityName")));

                    }

                    adapter.notifyDataSetChanged();

                }
            }
        });
    }
}
