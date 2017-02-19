package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InternacionalTraffic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internacional_traffic);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(InternacionalTraffic.this, MainActivity.class));
    }
}
