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

public class SemberijaTransport extends AppCompatActivity {
    String webAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semberija_transport);

        Intent intent = getIntent();
        intent.getStringExtra("prijevoznik");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Prijevoznici");
        query.whereEqualTo("Prijevoznik","SEMBERIJA TRANSPORT");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                if (e == null){

                 for (ParseObject object : list){
                     webAddress = String.valueOf(object.get("webAdresa"));

                     Log.i("proba",webAddress);
                 }

                }


            }
        });


    }
}
