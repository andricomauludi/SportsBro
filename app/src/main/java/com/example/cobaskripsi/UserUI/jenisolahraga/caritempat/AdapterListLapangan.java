package com.example.cobaskripsi.UserUI.jenisolahraga.caritempat;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.detail.DetailLapangan;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterListLapangan extends RecyclerView.Adapter<AdapterListLapangan.myviewholder> {

    public Context c;
    public ArrayList<TempatModel> arrayList;

    public AdapterListLapangan(Context c, ArrayList<TempatModel> arrayList){
        this.c=c;
        this.arrayList=arrayList;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tempat_row,parent,false);
        return new myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        TempatModel tempatModel = arrayList.get(position);
        holder.nama.setText(tempatModel.getNamatempat());

        String str =tempatModel.getMarker();

        String[] latlong = str.split(",");
        String lat = latlong[0];
        String lng = latlong[1];
        double latitudeTujuan = Double.valueOf(lat.toString());
        double longitudeTujuan = Double.valueOf(lng.toString());


        double jarak = getDistance(latitudeTujuan, longitudeTujuan, holder.latitudeSaya, holder.longitudeSaya);
        jarak = Math.ceil(jarak / 1000);
        String stringjarak = jarak+"";

        holder.jarak.setText(stringjarak + " km");
        Glide.with(holder.img.getContext())
                .load(tempatModel.getGambar())
                .placeholder(R.drawable.basket_bucketlist)
                .circleCrop()
                .error(R.drawable.basket4)
                .into(holder.img);

        holder.listlapangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity)v.getContext();

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new DetailLapangan(tempatModel.getNamatempat(),tempatModel.getMarker(),tempatModel.getGambar(),tempatModel.getAlamattempat(),tempatModel.getIdtempat())).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public class myviewholder extends RecyclerView.ViewHolder{

        TextView nama, jarak;
        CircleImageView img;
        CardView listlapangan;
        double latitudeSaya, longitudeSaya;
        public myviewholder(View itemView){
            super(itemView);
            nama=itemView.findViewById(R.id.basketText1);
            jarak=itemView.findViewById(R.id.basketText2);
            img=itemView.findViewById(R.id.basketImageView);
            listlapangan=itemView.findViewById(R.id.cardviewlistlapangan);
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
