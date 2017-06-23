package com.alenmalik.autobusibih;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;

public class BusTransport extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TransportAdapter prijevozniciAdapter;
    List<Transport> prijevozniciList;
    Vibrator vibrator;
    ImageView goBack;

    RecyclerView prijevoznici;
  ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_transport);
        prijevoznici = (RecyclerView) findViewById(R.id.prijevozniciListView);
        goBack = (ImageView) findViewById(R.id.backGo);
         progressDialog = new ProgressDialog(this,R.style.AppTheme_Dark);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusTransport.this,MainPage.class);
                startActivity(intent);
            }
        });


        prijevoznici.setLayoutManager(new LinearLayoutManager(this));

        prijevozniciList = new ArrayList<>();
         progressDialog.setMessage("Loading...");
        progressDialog.show();
        final ParseQuery<ParseObject> prevoznici = ParseQuery.getQuery("Prijevoznici");

        prevoznici.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                if (e == null) {

                    for (int i = 0; i < list.size(); i++) {
                        Transport tr = new Transport();


                        tr.name = list.get(i).getString("Prijevoznik");
                        tr.address = list.get(i).getString("Adresa");
                        tr.phoneNumber = list.get(i).getString("Telefon");
                        tr.website = list.get(i).getString("webAdresa");
                        ParseFile file = (ParseFile) (list.get(i).get("logo"));

                        String url = file.getUrl();
                        tr.setLogo(url);

                        prijevozniciList.add(tr);
                        progressDialog.dismiss();

                    }

                }


                prijevozniciAdapter = new TransportAdapter(prijevozniciList,BusTransport.this);
                prijevoznici.setAdapter(prijevozniciAdapter);
                prijevozniciAdapter.notifyDataSetChanged();





            }
        });


        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(BusTransport.this, MainPage.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_stanice){
           Intent busStateIntent = new Intent(BusTransport.this,BusStateListActivity.class);
            vibrator.vibrate(100);
            startActivity(busStateIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
