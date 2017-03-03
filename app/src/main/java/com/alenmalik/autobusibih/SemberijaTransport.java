package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import static android.R.attr.value;

public class SemberijaTransport extends AppCompatActivity {
    String webAddress;
    String value;
    WebView myWebView;
    WebSettings webSettings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semberija_transport);
        myWebView = (WebView) findViewById(R.id.myWebView);

        Intent intent = getIntent();
       value = intent.getStringExtra("prijevoznikweb");

        webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(String.valueOf(value));
        myWebView.setWebViewClient(new WebViewClient());

        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Prijevoznici");
        query.whereEqualTo("Prijevoznik",value);
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
        }); */



    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()){
            myWebView.goBack();
        }else {

            super.onBackPressed();
        }

    }
}
