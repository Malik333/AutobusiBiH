package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class InternacionalTraffic extends AppCompatActivity {
    Spinner stateSpinner;
    Spinner daySpinner;
    Spinner fromCitySpinner;
    Spinner toCitySpinner;
    ArrayList<String> stateList;
    ArrayList<String> daysList;
    ArrayList<String> fromCityList;
    ArrayList<String> toCityList;

    ArrayAdapter<String> stateAdapter;
    ArrayAdapter<String> fromCityAdapter;
    ArrayAdapter<String> toCityAdapter;
    ArrayAdapter<String> dayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internacional_traffic);

        stateSpinner = (Spinner)findViewById(R.id.izaberDrzavuSpinner);
        daySpinner = (Spinner) findViewById(R.id.internationalSpinnerDan);
        fromCitySpinner = (Spinner) findViewById(R.id.internationalSpinnerOdGrada);
        toCitySpinner = (Spinner) findViewById(R.id.internationalSpinnerDoGrada);
        stateAdapter = new ArrayAdapter<String>(InternacionalTraffic.this,android.R.layout.simple_spinner_dropdown_item,stateList);
        dayAdapter = new ArrayAdapter<String>(InternacionalTraffic.this,android.R.layout.simple_spinner_dropdown_item,daysList);
        stateAdapter = new ArrayAdapter<String>(InternacionalTraffic.this,android.R.layout.simple_spinner_dropdown_item,stateList);
        stateAdapter = new ArrayAdapter<String>(InternacionalTraffic.this,android.R.layout.simple_spinner_dropdown_item,stateList);
    }




    public void toCityInternationalTraffic(String name) {

        ParseQuery<ParseObject> toCityQuery = new ParseQuery<ParseObject>("");
        toCityQuery.whereEqualTo("odGrada", name);
        toCityQuery.setLimit(10000);
        toCityQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    if (list.size() > 0) {

                        for (ParseObject object : list) {
                            toCityList.add(String.valueOf(object.get("doGrada")));

                        }
                     /*   hashSet2.addAll(toCityList);
                        toCityList.clear();
                        toCityList.addAll(hashSet2);
                        toCityAdapter = new ArrayAdapter<String>(CityTraffic.this, android.R.layout.simple_spinner_dropdown_item, toCityList);
                        toCityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                        toCitySpinner.setAdapter(toCityAdapter);

                        toCityAdapter.notifyDataSetChanged();*/

                    }

                }
            }
        });
    }

    public void chooseDayInternationalTraffic() {

        ParseQuery<ParseObject> chooseDayQuery = new ParseQuery<ParseObject>("");
        chooseDayQuery.addAscendingOrder("createdAt");
        chooseDayQuery.setLimit(10000);
        chooseDayQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    if (list.size() > 0) {

                        for (ParseObject object : list) {
                            daysList.add(String.valueOf(object.get("Dan")));


                        }
/*                        hashSet3.addAll(daysList);
                        daysList.clear();
                        daysList.addAll(hashSet3);
                        daysAdapter = new ArrayAdapter<String>(CityTraffic.this, android.R.layout.simple_spinner_dropdown_item, daysList);


                        daysSpinner.setAdapter(daysAdapter);

                        daysAdapter.notifyDataSetChanged();
*/


                    }


                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(InternacionalTraffic.this, MainActivity.class));
    }
}
