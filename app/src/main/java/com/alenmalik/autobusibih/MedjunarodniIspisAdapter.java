package com.alenmalik.autobusibih;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import static com.alenmalik.autobusibih.CityTraffic.busLat;
import static com.alenmalik.autobusibih.CityTraffic.busLng;

/**
 * Created by korisnik on 03/06/2017.
 */

public class MedjunarodniIspisAdapter extends RecyclerView.Adapter<MedjunarodniIspisAdapter.ViewHolder> {

    List<MedjunarodniIspisModel> listData;
    LayoutInflater inflater;
    Context c;

    public MedjunarodniIspisAdapter(List<MedjunarodniIspisModel> listData, Context c) {
        this.listData = listData;
        this.inflater = LayoutInflater.from(c);
        this.c = c;
    }
    @Override
    public MedjunarodniIspisAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.international_traffic_row, parent, false);

        return new MedjunarodniIspisAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MedjunarodniIspisAdapter.ViewHolder holder, int position) {
        MedjunarodniIspisModel item = listData.get(position);
        holder.satnicaTextview.setText(item.getVrijemePolaska());
        holder.duzinaPutaTextView.setText(item.getDuzinaPuta());
        holder.linijaTextView.setText(item.getLinija());
        holder.cijenaTextView.setText(item.getCijena());
        holder.prijevoznikTextView.setText(item.getPrijevoznik());
        holder.relacijaTextView.setText(item.getRelacija());
        holder.dan.setText(item.getDan());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView duzinaPutaTextView;
        TextView satnicaTextview;
        TextView prijevoznikTextView;
        TextView dan;
        TextView linijaTextView;
        TextView cijenaTextView;
        TextView relacijaTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            duzinaPutaTextView = (TextView) itemView.findViewById(R.id.duzinaPutaIspis_international);
            prijevoznikTextView = (TextView) itemView.findViewById(R.id.prijevoznikIspis_international);
            satnicaTextview = (TextView) itemView.findViewById(R.id.vrijemePolaskaIspis_international);
            linijaTextView = (TextView) itemView.findViewById(R.id.linijaIspis_international);
            cijenaTextView = (TextView) itemView.findViewById(R.id.cijenaIspis_international);
            relacijaTextView = (TextView) itemView.findViewById(R.id.relacijaIspis_international);
            dan = (TextView) itemView.findViewById(R.id.danIspis_international);
        }
    }
}
