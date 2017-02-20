package com.alenmalik.autobusibih;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by korisnik on 18/02/2017.
 */

public class RelacijaTab extends Fragment {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = getActivity().getLayoutInflater().inflate(R.layout.relacija_city_traffic, container, false);
        fromCitySpinner = (Spinner) rootView.findViewById(R.id.odGradaSpinner);
        toCitySpinner = (Spinner) rootView.findViewById(R.id.doGradaSpinner);
        daysSpinner = (Spinner) rootView.findViewById(R.id.danSpinner);
        fromCityList = new ArrayList<>();
        toCityList = new ArrayList<>();
        daysList = new ArrayList<>();


        relacijaTextView = (TextView) rootView.findViewById(R.id.relacijaIspis);
        danTextView = (TextView) rootView.findViewById(R.id.danIspis);
        duzinaPutaTextView = (TextView) rootView.findViewById(R.id.duzinaPutaIspis);
        linijaTextView = (TextView) rootView.findViewById(R.id.linijaIspis);
        prijevoznikTextView = (TextView) rootView.findViewById(R.id.prevoznikIspis);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {


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
                Log.i("doGrada", doGrada);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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





        super.onActivityCreated(savedInstanceState);

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
                        fromCityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, fromCityList);
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
                        toCityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, toCityList);
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

                        daysAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, daysList);


                        daysSpinner.setAdapter(daysAdapter);

                        daysAdapter.notifyDataSetChanged();


                    }


                }
            }
        });


    }


}
