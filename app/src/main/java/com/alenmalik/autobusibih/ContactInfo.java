package com.alenmalik.autobusibih;

import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactInfo extends AppCompatActivity implements View.OnClickListener {

    EditText imeIPrezime;
    EditText redoslijedVoznje;
    Button posalji;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        imeIPrezime = (EditText) findViewById(R.id.imeIPrezimeEdit);
        redoslijedVoznje = (EditText) findViewById(R.id.redoslijedVoznjeEdit);
        posalji = (Button) findViewById(R.id.posaljiBtn);
        posalji.setOnClickListener(this);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.posaljiBtn) {
            vibrator.vibrate(100);
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "alenmalik43@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
            emailIntent.putExtra(Intent.EXTRA_TEXT, imeIPrezime.getText().toString() + "\n\n" + redoslijedVoznje.getText().toString());
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(ContactInfo.this, MainPage.class));
    }
}
