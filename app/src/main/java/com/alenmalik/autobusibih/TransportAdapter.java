package com.alenmalik.autobusibih;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by korisnik on 16/05/2017.
 */

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.ViewHolder> {

    List<Transport> listData;
    LayoutInflater inflater;
    Context c;

    public TransportAdapter(List<Transport> listData, Context c) {
        this.listData = listData;
        this.inflater = LayoutInflater.from(c);
        this.c = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.bus_transport_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Transport item = listData.get(position);
        ProgressDialog dialog = new ProgressDialog(c);
        dialog.setMessage("Loading...");
        dialog.show();
        Picasso.with(c).load(item.getLogo()).into(holder.logo);
        holder.name.setText(item.getName());
        holder.address.setText(item.getAddress());
        holder.phone.setText(item.getPhoneNumber());
        holder.webSite.setText(item.getWebsite());
        dialog.dismiss();


        final String prijevoznik = (String) holder.name.getText();

        final ArrayList<String> onlineStatus = new ArrayList<String>();
        Log.i("adaper", prijevoznik);


        final DatabaseReference checkDatabase = FirebaseDatabase.getInstance().getReference().child("Pracenje");
        checkDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(prijevoznik).exists()) {

                    DatabaseReference relacijaRef = checkDatabase.child(prijevoznik);
                    relacijaRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                            for (DataSnapshot daj : dataSnapshot.getChildren()){
                                Log.i("proba",daj.getValue().toString());
                                onlineStatus.add(String.valueOf(daj.getValue()));
                                if (onlineStatus.contains("true")){
                                    holder.checkStatus.setEnabled(true);
                                }else {
                                    holder.checkStatus.setEnabled(false);
                                }


                            }
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        holder.checkStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMap = new Intent(c, DriversMapActivity.class);
                openMap.putExtra("prijevoznik", prijevoznik);
                c.startActivity(openMap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView logo;
        TextView name;
        TextView address;
        TextView phone;
        TextView webSite;
        Button checkStatus;

        View line;

        public ViewHolder(View itemView) {
            super(itemView);

            logo = (ImageView) itemView.findViewById(R.id.logo_prevoznik);
            name = (TextView) itemView.findViewById(R.id.ime_prevoznik);
            address = (TextView) itemView.findViewById(R.id.adresa_prevoznik);
            phone = (TextView) itemView.findViewById(R.id.br_tel_prevoznik);
            webSite = (TextView) itemView.findViewById(R.id.web_prevoznik);
            checkStatus = (Button) itemView.findViewById(R.id.offline);


        }
    }
}
