package com.alenmalik.autobusibih;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CityTraffic extends AppCompatActivity {
    Spinner fromCitySpinner;
    Spinner toCitySpinner;
    Spinner daysSpinner;
    ArrayList<String> fromCityList;
    ArrayList<String> toCityList;
    ArrayList<String> daysList;
    ArrayAdapter<String> fromCityAdapter;
    ArrayAdapter<String> toCityAdapter;
    ArrayAdapter<String> daysAdapter;
    HashSet<String> hashSet = new HashSet<String>();
    HashSet<String> hashSet2 = new HashSet<String>();
    TextView relacijaTextView;
    String odGrada;
    String doGrada;
    String dan;
    TextView danTextView;
    TextView duzinaPutaTextView;
    TextView linijaTextView;
    TextView prijevoznikTextView;
    String duzinaPuta;
    String linija;
    String prijevoznik;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_traffic);
        fromCitySpinner = (Spinner) findViewById(R.id.odGradaSpinner);
        toCitySpinner = (Spinner) findViewById(R.id.doGradaSpinner);
        daysSpinner = (Spinner) findViewById(R.id.danSpinner);
        fromCityList = new ArrayList<>();
        toCityList = new ArrayList<>();
        daysList = new ArrayList<>();

        relacijaTextView = (TextView) findViewById(R.id.relacijaIspis);
        danTextView = (TextView) findViewById(R.id.danIspis);
        duzinaPutaTextView = (TextView) findViewById(R.id.duzinaPutaIspis);
        linijaTextView = (TextView) findViewById(R.id.linijaIspis);
        prijevoznikTextView = (TextView) findViewById(R.id.prevoznikIspis);

        fromCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                odGrada = (String) adapterView.getItemAtPosition(i);

                Log.i("toCity", odGrada);


                hashSet2.clear();
                toCityList.clear();
                toCity(odGrada);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        toCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                doGrada = (String) adapterView.getItemAtPosition(i);
                relacijaTextView.setText(odGrada +"-"+ doGrada);
                relacijaTextView.setText(odGrada + "-" + doGrada);
                danTextView.setText(dan);
                duzinaPutaTextView.setText(duzinaPuta);
                linijaTextView.setText(linija);
                prijevoznikTextView.setText(prijevoznik);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                relacijaTextView.setText(odGrada + "-" +  doGrada);
            }
        });


        daysSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dan = (String) adapterView.getItemAtPosition(i);
                Log.i("dan", dan);
                relacijaTextView.setText(odGrada + "-" + doGrada);
                danTextView.setText(dan);
                duzinaPutaTextView.setText(duzinaPuta);
                linijaTextView.setText(linija);
                prijevoznikTextView.setText(prijevoznik);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                relacijaTextView.setText(odGrada + "-" + doGrada);
                danTextView.setText(dan);
                duzinaPutaTextView.setText(duzinaPuta);
                linijaTextView.setText(linija);
                prijevoznikTextView.setText(prijevoznik);
            }
        });
        fromCity();
        chooseDay();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Relacija");
        query.whereEqualTo("odGrada", "Bijeljina");
        query.whereEqualTo("doGrada", "Brčko");
        query.whereEqualTo("Dan", "Utorak");

        query.setLimit(10000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    if (list.size() > 0) {

                        for (ParseObject object : list) {
                            Log.i("proba",String.valueOf(object.get("DuzinaPuta")));
                            Log.i("proba",String.valueOf(object.get("Linija")));
                            Log.i("proba",String.valueOf(object.get("Prijevoznik")));
                            duzinaPuta = String.valueOf(object.get("DuzinaPuta"));
                            linija = String.valueOf(object.get("Linija"));
                            prijevoznik = String.valueOf(object.get("Prijevoznik"));

                        }

                    }


                }
            }
        });




    }

    public void fromCity() {

        ParseQuery<ParseObject> fromCityQuery = new ParseQuery<ParseObject>("Relacija");
        fromCityQuery.setLimit(10000);
        fromCityQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    if (list.size() > 0) {

                        for (ParseObject object : list) {
                            fromCityList.add(String.valueOf(object.get("odGrada")));

                        }
                        hashSet.addAll(fromCityList);
                        fromCityList.clear();
                        fromCityList.addAll(hashSet);
                        fromCityAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, fromCityList);
                        fromCityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        fromCitySpinner.setAdapter(fromCityAdapter);

                        fromCityAdapter.notifyDataSetChanged();


                    }


                }
            }
        });


    }


    public void toCity(String name) {

        ParseQuery<ParseObject> toCityQuery = new ParseQuery<ParseObject>("Relacija");
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
                        hashSet2.addAll(toCityList);
                        toCityList.clear();
                        toCityList.addAll(hashSet2);
                        toCityAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, toCityList);
                        toCityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                        toCitySpinner.setAdapter(toCityAdapter);

                        toCityAdapter.notifyDataSetChanged();

                    }

                }
            }
        });
    }

    public void chooseDay() {

        ParseQuery<ParseObject> chooseDayQuery = new ParseQuery<ParseObject>("Relacija");

        chooseDayQuery.setLimit(10000);
        chooseDayQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    if (list.size() > 0) {

                        for (ParseObject object : list) {
                            daysList.add(String.valueOf(object.get("Dan")));

                        }

                        daysAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, daysList);


                        daysSpinner.setAdapter(daysAdapter);

                        daysAdapter.notifyDataSetChanged();


                    }


                }
            }
        });

    }

        @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(CityTraffic.this, MainActivity.class));
    }

   /* ParseQuery<ParseObject> query = ParseQuery.getQuery("Relacija");
    query.whereEqualTo("odGrada", "Bijeljina");
    query.whereEqualTo("doGrada", "Brčko");
    query.whereEqualTo("Dan", "Utorak");
    query.findInBackground(new FindCallback<ParseObject>() {
        @Override
        public void done(List<ParseObject> list, ParseException e) {
            if (e == null) {
                for (ParseObject object : list) {
                    satnica = String.valueOf(object.get("Satnica"));
                }
            }
            String[] split = satnica.split("/");

            for (int i = 0; i < split.length; i++) {
                lista.add(split[i]);
                adapter.notifyDataSetChanged();
            }
        }
    });*/
}
