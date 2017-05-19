package com.alenmalik.autobusibih;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by korisnik on 19/05/2017.
 */

public class MedjugradskiIspisAdapter extends RecyclerView.Adapter<MedjugradskiIspisAdapter.ViewHolder> {

    List<MedjugradskiIspisModel> listData;
    LayoutInflater inflater;

    public MedjugradskiIspisAdapter(List<MedjugradskiIspisModel> listData, Context c) {
        this.listData = listData;
        this.inflater = LayoutInflater.from(c);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.medjugradski_ispis_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MedjugradskiIspisModel item = listData.get(position);
        holder.satnicaTextview.setText(item.getVrijemePolaska());
        holder.danTextView.setText(item.getDan());
        holder.duzinaPutaTextView.setText(item.getVrijemePolaska());
        holder.linijaTextView.setText(item.getLinija());
        holder.cijenaTextView.setText(item.getCijena());
        holder.prijevoznikTextView.setText(item.getPrijevoznik());
        holder.stanicaTextview.setText(item.getStanica());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView danTextView;
        TextView duzinaPutaTextView;
        TextView satnicaTextview;
        TextView prijevoznikTextView;
        TextView stanicaTextview;
        TextView linijaTextView;
        TextView cijenaTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            danTextView = (TextView) itemView.findViewById(R.id.danIspisRow);
            duzinaPutaTextView = (TextView) itemView.findViewById(R.id.duzinaPutaIspisRow);
            prijevoznikTextView = (TextView) itemView.findViewById(R.id.prijevoznikIspisRow);
            satnicaTextview = (TextView) itemView.findViewById(R.id.vrijemePolaskaIspisRow);
            stanicaTextview = (TextView) itemView.findViewById(R.id.adresaStaniceIspisRow);
            linijaTextView = (TextView) itemView.findViewById(R.id.linijaIspisRow);
            cijenaTextView = (TextView) itemView.findViewById(R.id.cijenaIspisRow);
        }
    }
}
