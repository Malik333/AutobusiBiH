package com.alenmalik.autobusibih;

import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ZaReklameActivity extends AppCompatActivity {
    Button posaljiEmail;
    Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_za_reklame);
        posaljiEmail = (Button) findViewById(R.id.posaljiEmail);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        posaljiEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(100);
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "alenmalik43@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Za reklame");

                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
    }
}
