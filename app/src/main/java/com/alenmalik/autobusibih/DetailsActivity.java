package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {


    TextView fromCty, toCity;
    Button openMap;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        fromCty = (TextView) findViewById(R.id.fromCityId_txt);
        toCity = (TextView) findViewById(R.id.toCityId_txt);
        openMap = (Button) findViewById(R.id.open_map2);
        listView = (ListView) findViewById(R.id.listView_hours2);

        Intent i = getIntent();
        String toCityString = i.getStringExtra("selectCity");
        list = new ArrayList<String>();
        list.add("Waiting");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Cities");
        query.whereEqualTo("fromCity", CityActivity.chooseCityName);
        query.whereEqualTo("toCity", toCityString);

        fromCty.setText(CityActivity.chooseCityName);
        toCity.setText(toCityString);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> listt, ParseException e) {

                if (e == null) {

                    if (list.size() > 0) {
                        list.clear();
                        for (ParseObject object : listt) {

                            list.add(String.valueOf(object.get("Hours")));
                        }

                        adapter.notifyDataSetChanged();

                    }
                }

            }
        });

        openMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.open_map2){

            Intent intent = new Intent(DetailsActivity.this, MapRouteActivity.class);
            startActivity(intent);
        }
    }
}