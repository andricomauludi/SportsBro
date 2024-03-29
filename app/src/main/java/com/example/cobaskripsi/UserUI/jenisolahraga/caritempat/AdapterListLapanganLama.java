package com.example.cobaskripsi.UserUI.jenisolahraga.caritempat;

import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.detail.DetailLapangan;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterListLapanganLama extends FirebaseRecyclerAdapter<TempatModel, AdapterListLapanganLama.myviewholder> {
    private DatabaseReference mDatabase;

    public AdapterListLapanganLama(@NonNull FirebaseRecyclerOptions<TempatModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull TempatModel model) {
        holder.namatempat.setText(model.getNamatempat());
        Glide.with(holder.img.getContext())
                .load(model.getGambar())
                .placeholder(R.drawable.basket_bucketlist)
                .circleCrop()
                .error(R.drawable.basket1)
                .into(holder.img);

                holder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppCompatActivity activity=(AppCompatActivity)v.getContext();

                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new DetailLapangan(model.getNamatempat(),model.getMarker(),model.getGambar(),model.getAlamattempat(),model.getIdtempat(),model.jenisolahraga,model.getNotelptempat())).addToBackStack(null).commit();
                    }
                });


        String str = model.marker;

        String[] latlong = str.split(",");
        String lat = latlong[0];
        String lng = latlong[1];
        double latitudeTujuan = Double.valueOf(lat.toString());
        double longitudeTujuan = Double.valueOf(lng.toString());


        double jarak = getDistance(latitudeTujuan, longitudeTujuan, holder.latitudeSaya, holder.longitudeSaya);
        jarak = Math.ceil(jarak / 1000);
        String stringjarak = jarak+"";

        holder.marker.setText(stringjarak + " km");

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tempat_row,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView namatempat, marker;
        double latitudeSaya, longitudeSaya;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView)itemView.findViewById(R.id.basketImageView);
            namatempat = (TextView)itemView.findViewById(R.id.basketText1);
            marker = (TextView)itemView.findViewById(R.id.basketText2);

            GetLocation getLocation = new GetLocation(itemView.getContext());
            Location location = getLocation.getLocation();
            if (location != null){
                 latitudeSaya = location.getLatitude();
                 longitudeSaya = location.getLongitude();
            }
        }
    }
    private double getDistance(Double latitudeTujuan, Double longitudeTujuan, Double latitudeUser, Double longitudeUser){
        /*VARIABEL */
        Double pi =3.14159265358979;
        Double lat1 = latitudeTujuan;
        Double lon1 = longitudeTujuan;
        Double lat2 = latitudeUser;
        Double lon2 = longitudeUser;
        Double R = 6371e3;

        Double latRad1 = lat1 * (pi / 180);
        Double latRad2 = lat2 * (pi / 180);
        Double deltaLatRad = (lat2 - lat1) * (pi / 180);
        Double deltaLonRad = (lon2 - lon1) * (pi / 180);

        /* RUMUS HAVERSINE*/
        Double a = Math.sin(deltaLatRad / 2) * Math.sin(deltaLatRad / 2) + Math.cos(latRad1) * Math.cos(latRad2) * Math.sin(deltaLonRad / 2) * Math.sin(deltaLonRad / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double s = R * c; // hasil jarak dalam meter
        return s;
    }
}
