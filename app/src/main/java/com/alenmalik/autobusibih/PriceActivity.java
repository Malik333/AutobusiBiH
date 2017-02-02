package com.alenmalik.autobusibih;


import android.app.ProgressDialog;
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


public class PriceActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ArrayList<String> priceList;
    ListView priceListView;
    ProgressDialog dialog;
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

        query10.fromLocalDatastore();
        dialog.getProgress();
        dialog.setMessage("Searching...");
        dialog.show();

        query10.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                        priceList.clear();

                    for (ParseObject object : list) {

                        String cityName = object.get("fromCity") +" - "+ object.get("toCity");

                        priceList.add(String.valueOf(cityName));

                    }

                    adapter.notifyDataSetChanged();
                    dialog.dismiss();

                }
            }
        });

    }


}
