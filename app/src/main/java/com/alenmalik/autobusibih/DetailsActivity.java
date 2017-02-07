package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.model.LatLng;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {


    TextView fromCty, toCity;
    ImageView cijena;
    Button openMap;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter adapter;
    static String toCityString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        fromCty = (TextView) findViewById(R.id.fromCityId_txt);
        toCity = (TextView) findViewById(R.id.toCityId_txt);
        openMap = (Button) findViewById(R.id.open_map2);
        listView = (ListView) findViewById(R.id.listView_hours2);
        cijena = (ImageView) findViewById(R.id.cijenaImageView);
        cijena.setOnClickListener(this);

        Intent i = getIntent();
        toCityString = i.getStringExtra("selectCity");
        list = new ArrayList<String>();
        list.add("Waiting");
        adapter = new ArrayAdapter<String>(this, R.layout.itemlistview, list);
        listView.setAdapter(adapter);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Cities");
        query.whereEqualTo("fromCity", CityActivity.chooseCityName);
        query.whereEqualTo("toCity", toCityString);
        query.addAscendingOrder("createdAt");

        fromCty.setText(CityActivity.chooseCityName);
        toCity.setText(toCityString);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> listt, ParseException e) {

                if (e == null) {

                    if (list.size() > 0) {
                        list.clear();
                        for (ParseObject object : listt) {

                            String dateHours = object.get("Day") + ":  " + object.get("Hours");
                            list.add(dateHours);
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
        if (view.getId() == R.id.open_map2) {

            Intent intent = new Intent(DetailsActivity.this, MapRouteActivity.class);
            startActivity(intent);
        }else if (view.getId() == R.id.cijenaImageView){
            Intent intent = new Intent(DetailsActivity.this, PriceInfoActivitiy.class);
            intent.putExtra("fromCity", CityActivity.chooseCityName);
            intent.putExtra("toCity", toCityString);
            startActivity(intent);
        }
    }
}