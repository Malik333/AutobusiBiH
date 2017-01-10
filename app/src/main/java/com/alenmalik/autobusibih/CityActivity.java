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

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;
    ArrayList<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        nameList = new ArrayList<String>();
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);



        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Cities");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                if (e == null){
                    if (list.size()> 0){

                        for (ParseObject object : list){

                            nameList.add(String.valueOf(object.get("fromCity")));


                        }


                        Log.i("City", String.valueOf(nameList.size()));
                        adapter = new ArrayAdapter<String>(CityActivity.this, android.R.layout.simple_dropdown_item_1line, nameList);
                        autoCompleteTextView.setThreshold(1);

                        autoCompleteTextView.setAdapter(adapter);

                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }


}
