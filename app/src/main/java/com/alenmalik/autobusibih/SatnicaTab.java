package com.alenmalik.autobusibih;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by korisnik on 18/02/2017.
 */

public class SatnicaTab extends Fragment {

    ListView satnicaListView;
    ArrayAdapter adapter;
    ArrayList<String> lista;
    String satnica;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.satnica_city_traffic, container, false);
        satnicaListView = (ListView) rootView.findViewById(R.id.satnicaLista);
        lista = new ArrayList<>();
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, lista);
        satnicaListView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Relacija");
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
        });




    }
}
