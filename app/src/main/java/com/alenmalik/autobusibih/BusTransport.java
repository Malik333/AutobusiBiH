package com.alenmalik.autobusibih;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BusTransport extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TransportAdapter prijevozniciAdapter;
   List<Transport> prijevozniciList;
    HashSet<String> hashSet = new HashSet<>();
    Vibrator vibrator;
    String webAdress;
    private BitmapDrawable obwer;

    RecyclerView prijevoznici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_transport);
        prijevoznici = (RecyclerView) findViewById(R.id.prijevozniciListView);


        prijevoznici.setLayoutManager(new LinearLayoutManager(this));

        prijevozniciList = new ArrayList<>();

        final ParseQuery<ParseObject> prevoznici = ParseQuery.getQuery("Prijevoznici");

        prevoznici.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                if (e == null) {

                    for (int i = 0; i < list.size(); i++) {
                        Transport tr = new Transport();
                        ProgressDialog dialog = new ProgressDialog(BusTransport.this);
                        dialog.setMessage("Loading...");
                        dialog.show();
                        tr.name = list.get(i).getString("Prijevoznik");
                        tr.address = list.get(i).getString("Adresa");
                        tr.phoneNumber = list.get(i).getString("Telefon");
                        tr.website = list.get(i).getString("webAdresa");
                        ParseFile file = (ParseFile) (list.get(i).get("logo"));

                        String url = file.getUrl();
                        tr.setLogo(url);

                        prijevozniciList.add(tr);
                        dialog.dismiss();

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


    /*public void getPrijevoznik(){
        ParseQuery<ParseObject> prijevozniciQuery = new ParseQuery<ParseObject>("Prijevoznici");
        prijevozniciQuery.setLimit(10000);
        prijevozniciQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    if (list.size() > 0) {

                        for (ParseObject object : list) {
                            prijevozniciList.add(String.valueOf(object.get("Prijevoznik")));

                        }
                        hashSet.addAll(prijevozniciList);
                        prijevozniciList.clear();
                        prijevozniciList.addAll(hashSet);
                        prijevozniciAdapter = new ArrayAdapter<String>(BusTransport.this, android.R.layout.simple_list_item_1, prijevozniciList);

                        prijevozniciListView.setAdapter(prijevozniciAdapter);

                        prijevozniciAdapter.notifyDataSetChanged();


                    }


                }
            }
        });
        prijevozniciListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value = prijevozniciAdapter.getItem(i);

                Intent prijevoznikIntent = new Intent(BusTransport.this, PrijevozniciPopUp.class);
                prijevoznikIntent.putExtra("prijevoznik", value);
                startActivity(prijevoznikIntent);
            }
        });


    }
**/
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
