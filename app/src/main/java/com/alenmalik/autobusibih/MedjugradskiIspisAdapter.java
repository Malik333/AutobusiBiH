package com.alenmalik.autobusibih;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.alenmalik.autobusibih.CityTraffic.busLat;
import static com.alenmalik.autobusibih.CityTraffic.busLng;

/**
 * Created by korisnik on 19/05/2017.
 */

public class MedjugradskiIspisAdapter extends RecyclerView.Adapter<MedjugradskiIspisAdapter.ViewHolder> {

    List<MedjugradskiIspisModel> listData;
    LayoutInflater inflater;
    Context c;

    public MedjugradskiIspisAdapter(List<MedjugradskiIspisModel> listData, Context c) {
        this.listData = listData;
        this.inflater = LayoutInflater.from(c);
        this.c = c;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.medjugradski_ispis_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        MedjugradskiIspisModel item = listData.get(position);
        holder.satnicaTextview.setText(item.getVrijemePolaska());
        holder.danTextView.setText(item.getDan());
        holder.duzinaPutaTextView.setText(item.getDuzinaPuta());
        holder.linijaTextView.setText(item.getLinija());
        holder.cijenaTextView.setText(item.getCijena());
        holder.prijevoznikTextView.setText(item.getPrijevoznik());
        holder.stanicaTextview.setText(item.getStanica());
        holder.relacijaTextView.setText(item.getRelacija());

        holder.stanicaTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = CityTraffic.odGrada;
                Log.i("city", city);
                CityTraffic.stanicaCity = true;
                double latitude = 0, longitude = 0;
                BusStateListActivity.busTran = false;
                Intent openStanica = new Intent(c, PopUpActivitiy.class);
                openStanica.putExtra("gradStanica", city);
                ParseGeoPoint location = new ParseGeoPoint(latitude, longitude);
                ParseQuery<ParseObject> query = ParseQuery.getQuery("BusAddress");
                query.whereEqualTo("CityName", city);

                query.whereNear("Location", location);
                query.setLimit(10);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        if (e == null) {


                            if (list.size() > 0) {

                                for (ParseObject object : list) {
                                    busLat = object.getParseGeoPoint("Location").getLatitude();
                                    busLng = object.getParseGeoPoint("Location").getLongitude();

                                    Log.i("buslatituda", String.valueOf(busLat));
                                    Log.i("buslongituda", String.valueOf(busLng));


                                }


                            }


                        }
                    }
                });
                c.startActivity(openStanica);
            }
        });

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
        TextView relacijaTextView;


        public ViewHolder(View itemView) {
            super(itemView);

            danTextView = (TextView) itemView.findViewById(R.id.danIspisRow);
            duzinaPutaTextView = (TextView) itemView.findViewById(R.id.duzinaPutaIspisRow);
            prijevoznikTextView = (TextView) itemView.findViewById(R.id.prijevoznikIspisRow);
            satnicaTextview = (TextView) itemView.findViewById(R.id.vrijemePolaskaIspisRow);
            stanicaTextview = (TextView) itemView.findViewById(R.id.adresaStaniceIspisRow);
            linijaTextView = (TextView) itemView.findViewById(R.id.linijaIspisRow);
            cijenaTextView = (TextView) itemView.findViewById(R.id.cijenaIspisRow);
            relacijaTextView = (TextView) itemView.findViewById(R.id.relacijaIspisRow);

        }
    }
}
