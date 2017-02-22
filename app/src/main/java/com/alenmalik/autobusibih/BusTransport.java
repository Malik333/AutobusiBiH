package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BusTransport extends AppCompatActivity {
    ListView prijevozniciListView;
    ArrayAdapter<String> prijevozniciAdapter;
    ArrayList<String> prijevozniciList;
    HashSet<String> hashSet = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_transport);
        prijevozniciListView = (ListView) findViewById(R.id.prijevozniciListView);
        prijevozniciList = new ArrayList<>();
        getPrijevoznik();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(BusTransport.this, MainActivity.class));
    }


    public void getPrijevoznik(){
        ParseQuery<ParseObject> prijevozniciQuery = new ParseQuery<ParseObject>("Relacija");
        prijevozniciQuery.setLimit(10000);
        prijevozniciQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    if (list.size() > 0) {

                        for (ParseObject object : list) {
                            prijevozniciList.add(String.valueOf(object.get("Prijevoznik")));

                        }
                        hashSet.addAll(prijevozniciList);
                        prijevozniciList.clear();
                        prijevozniciList.addAll(hashSet);
                        prijevozniciAdapter = new ArrayAdapter<String>(BusTransport.this, android.R.layout.simple_list_item_1, prijevozniciList);

                        prijevozniciListView.setAdapter(prijevozniciAdapter);

                        prijevozniciAdapter.notifyDataSetChanged();


                    }


                }
            }
        });



    }
}
