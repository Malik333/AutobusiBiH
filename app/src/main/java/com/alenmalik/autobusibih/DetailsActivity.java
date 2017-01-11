package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    Intent intent;
    String toCityListActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        detailedCityListActivity();


    }


    public void detailedCityListActivity(){
        intent = getIntent();
        toCityListActivity = intent.getStringExtra("selectCity");

        String fromCityListActivity = ListToCityActivity.nameCity;

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Cities");
        query.whereEqualTo("fromCity", fromCityListActivity);
        query.whereEqualTo("toCity", toCityListActivity);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                if (e == null){

                    if (list.size() > 0){

                        for (ParseObject object : list) {

                            Log.i("Hours to city: ", String.valueOf(object.get("Hours")));
                        }

                    }
                }

            }
        });
    }
}
