package com.alenmalik.autobusibih;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by korisnik on 16/05/2017.
 */

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.ViewHolder> {

    List<Transport> listData;
    LayoutInflater inflater;

    public TransportAdapter(List<Transport> listData, Context c) {
        this.listData = listData;
        this.inflater = LayoutInflater.from(c);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.bus_transport_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transport item = listData.get(position);
        holder.name.setText(item.getName());
        holder.address.setText(item.getAddress());
        holder.phone.setText(item.getPhoneNumber());
        holder.webSite.setText(item.getWebsite());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView address;
        TextView phone;
        TextView webSite;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.ime_prevoznik);
            address = (TextView) itemView.findViewById(R.id.adresa_prevoznik);
            phone = (TextView) itemView.findViewById(R.id.br_tel_prevoznik);
            webSite = (TextView) itemView.findViewById(R.id.web_prevoznik);
        }
    }
}
