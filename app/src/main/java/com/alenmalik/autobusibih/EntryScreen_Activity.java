package com.alenmalik.autobusibih;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EntryScreen_Activity extends AppCompatActivity {

    private static int splash_time_out = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_screen_);

        PinObject object = new PinObject();
        object.pinAllobject();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent homeIntent = new Intent(EntryScreen_Activity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();

            }
        }, splash_time_out);
    }
}
