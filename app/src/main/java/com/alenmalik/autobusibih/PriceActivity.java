package com.alenmalik.autobusibih;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
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
        adapter = new ArrayAdapter<String>(PriceActivity.this, R.layout.itemlistview, priceList);




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

                String[] split = adapter.getItem(position).split("-");

                String city1 = split[0].trim();
                String city2 = split[1].trim();

                Log.i("cityOne", city1);
                Log.i("cityTwo", city2);

                Intent intent = new Intent(PriceActivity.this, PriceInfoActivitiy.class);
                intent.putExtra("fromcity", city1);
                intent.putExtra("tocity", city2);
                MainActivity.routeActivityActive = false;
                MainActivity.cityActivityActive = false;
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menu_search);

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);

                return false;
            }

        });

        return super.onCreateOptionsMenu(menu);
    }


}
