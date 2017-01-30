package com.alenmalik.autobusibih;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener{
    EditText ime;
    EditText prezime;
    EditText redoslijedVoznje;
    Button posalji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ime = (EditText) findViewById(R.id.imeEdit);
        prezime = (EditText) findViewById(R.id.prezimeEdit);
        redoslijedVoznje = (EditText) findViewById(R.id.redoslijedVoznjeEdit);
        posalji = (Button) findViewById(R.id.posaljiBtn);
        posalji.setOnClickListener(this);





    }

    @Override
    public void onClick(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND, Uri.fromParts("mailto","alenmalik43@gmail.com",null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Hello");
        emailIntent.putExtra(Intent.EXTRA_TEXT,  ime.getText().toString() + "\n" + prezime.getText().toString() + "\n" + redoslijedVoznje.getText().toString());
        startActivity(Intent.createChooser(emailIntent,"Send email..."));
    }
}
