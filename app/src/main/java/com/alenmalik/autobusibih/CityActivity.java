package com.alenmalik.autobusibih;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity {

    EditText cityName;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;
    ArrayList<String> nameList;
    String[] name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        nameList = new ArrayList<String>();
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);


        // cityName = (EditText) findViewById(R.id.city_EditText);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
      /*  name = new String[]{"Bugojno", "Bihac", "Sarajevo"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, name);
        autoCompleteTextView.setThreshold(1);
        adapter.notifyDataSetChanged();*/

        //  autoCompleteTextView.setAdapter(adapter);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Cities");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                if (e == null){
                    if (list.size()> 0){

                       /* ParseObject[] data = list.toArray(new ParseObject[list.size()]);
                        name = new String[data.length];

                        for (int i = 0; i < data.length; i++) {
                            name[i] = String.valueOf(data[i].get("fromCity"));
                        } */

                        for (ParseObject object : list){

                            nameList.add(String.valueOf(object.get("fromCity")));


                        }
                        name = new String[nameList.size()];
                        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, nameList);
                        autoCompleteTextView.setThreshold(1);

                        autoCompleteTextView.setAdapter(adapter);

                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }


}
