package com.alenmalik.autobusibih;


import android.app.ProgressDialog;
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
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class PriceActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ArrayList<String> priceList;
    ListView priceListView;
    ProgressDialog dialog;
    String cityName;
//ponovo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        dialog = new ProgressDialog(this);
        priceListView = (ListView) findViewById(R.id.priceListView);
        priceList = new ArrayList<>();
        priceList.add("Waiting");
        adapter = new ArrayAdapter<String>(PriceActivity.this,android.R.layout.simple_list_item_1,priceList);


        priceListView.setAdapter(adapter);


        ParseQuery<ParseObject> query10 = ParseQuery.getQuery("Cijene");
        query10.addAscendingOrder("fromCity");
        dialog.getProgress();
        dialog.setMessage("Searching...");
        dialog.show();

        query10.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                        priceList.clear();

                    for (ParseObject object : list) {

                        cityName = object.get("fromCity") +" - "+ object.get("toCity");

                        priceList.add(String.valueOf(cityName));

                    }

                    adapter.notifyDataSetChanged();
                    dialog.dismiss();

                }
            }
        });

        priceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String[] split = priceList.get(position).split("-");

                String city1 = split[0].trim();
                String city2 = split[1].trim();

                Log.i("cityOne", city1);
                Log.i("cityTwo", city2);

                Intent intent = new Intent(PriceActivity.this, PriceInfoActivitiy.class);
                intent.putExtra("fromcity", city1);
                intent.putExtra("tocity", city2);
                startActivity(intent);

            }
        });

    }


}
