package com.alenmalik.autobusibih;

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

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class BusStateActivity extends AppCompatActivity{

    ArrayAdapter adapter;
    ArrayList<String> listCityName;
    ListView listView;

    static   double busLat, busLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_state);
        listView = (ListView) findViewById(R.id.busName_list);

        listCityName = new ArrayList<String>();
        listCityName.add("Waiting...");
        adapter = new ArrayAdapter<String>(BusStateActivity.this, android.R.layout.simple_list_item_1, listCityName);
        listView.setAdapter(adapter);


        final ParseQuery<ParseObject> query = ParseQuery.getQuery("BusAddress");
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, final long l) {

                Intent intent = new Intent(BusStateActivity.this,BusMapActivity.class);

                double latitude=0, longitude=0;


                ParseGeoPoint location = new ParseGeoPoint(latitude, longitude);
                ParseQuery<ParseObject> query = ParseQuery.getQuery("BusAddress");
                query.whereEqualTo("CityName", listCityName.get(position));

                query.whereNear("Location", location);
                query.setLimit(10);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        if (e == null) {

                            if (list.size() > 0) {

                                for (ParseObject object : list) {
                                    busLat = object.getParseGeoPoint("Location").getLatitude();
                                    busLng = object.getParseGeoPoint("Location").getLongitude();

                                    Log.i("buslatituda", String.valueOf(busLat));
                                    Log.i("buslongituda", String.valueOf(busLng));
                                }

                            }


                        }
                    }
                });


                intent.putExtra("city",String.valueOf(listCityName.get(position)));
                intent.putExtra("busLat", busLat);
                intent.putExtra("busLng", busLng);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
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
