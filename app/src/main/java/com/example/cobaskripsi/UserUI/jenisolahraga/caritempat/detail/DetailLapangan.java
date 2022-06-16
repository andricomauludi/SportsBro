package com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.RecListLapangan;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.TempatModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailLapangan extends Fragment {

    private DatabaseReference mDatabase;
    Button lapangan1,newbtn;
    ListView listView;
    TextView cobain;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    String namatempat,marker,gambar,idtempat,jenisolahraga;

    public DetailLapangan() {

    }

    public DetailLapangan(String namatempat, String marker, String gambar) {
        this.namatempat = namatempat;
        this.marker = marker;
        this.gambar = gambar;
    }


    public static DetailLapangan newInstance(String param1, String param2) {
        DetailLapangan fragment = new DetailLapangan();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        View view= inflater.inflate(R.layout.fragment_detail_lapangan, container, false);

        ImageView imageholder= view.findViewById(R.id.gambarlapangan);
        TextView nameholder= view.findViewById(R.id.namalapangan);
        TextView cobain= view.findViewById(R.id.cobain);
        //TextView markerholder= view.findViewById(R.id.markerlapangan);

        nameholder.setText(namatempat);

        //markerholder.setText(stringjarak);
        Glide.with(getContext()).load(R.drawable.basket_bucketlist).into(imageholder);


       // listView = view.findViewById(R.id.listView);

       // final ArrayList<String> list = new ArrayList<>();
        final ArrayList<String> namalapangan = new ArrayList<>();
        final ArrayList<String> idlapangan = new ArrayList<>();
        final ArrayList<String> jamtersedia = new ArrayList<>();
       // final ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, list);
       // listView.setAdapter(adapter);

        LinearLayout linearLayout = view.findViewById(R.id.rootlayout);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("tempat")
                .orderByChild("namatempat")
                .equalTo(namatempat)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childSnapshot: snapshot.getChildren()){
                            String clubkey = childSnapshot.getKey();
                            TempatModel tempatModel = childSnapshot.getValue(TempatModel.class);
                            jenisolahraga=tempatModel.getJenisolahraga();
                            idtempat=clubkey;
                        }
                        mDatabase.child("lapangan")
                                //.child("lapangan1")
                                .orderByChild("idtempat")
                                .equalTo(idtempat)
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                       // list.clear();
                                        namalapangan.clear();
                                        idlapangan.clear();
                                        jamtersedia.clear();
                                        for (DataSnapshot childSnapshot : snapshot.getChildren()){
                                            LapanganModel lapanganModel = childSnapshot.getValue(LapanganModel.class);
                                            //list.add(lapanganModel.getNamalapangan());
                                            namalapangan.add(lapanganModel.getNamalapangan());
                                            jamtersedia.add(lapanganModel.getJamtersedia());
                                            String clubkey = childSnapshot.getKey();
                                            idlapangan.add(clubkey);

                                        }

                                        Button[] addlapangan = new Button[namalapangan.size()];
                                        for (int i=0;i<namalapangan.size();i++) {
                                            Button addbtn = new Button (getActivity());
                                            if (linearLayout!=null){
                                                linearLayout.addView(addbtn);
                                                addbtn.setText(namalapangan.get(i));
                                                addbtn.setId(i);

                                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                                );
                                                params.setMargins(20,0,10,0);
                                                addbtn.setLayoutParams(params);


                                                //addlapangan[i]=view.findViewById(R.id.lapangan1);

                                                String abc = String.valueOf(i);

                                                int resID = getResources().getIdentifier(abc, "id", getActivity().getPackageName());
                                                addlapangan[i] = (Button) view.findViewById(resID );
                                                addlapangan[i].setText(namalapangan.get(i));
                                                String jenislapangan = namalapangan.get(i);
                                                String idlapanganpesan = idlapangan.get(i);
                                                String jamtersediapesan = jamtersedia.get(i);
                                                addlapangan[i].setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Intent intent = new Intent(getActivity(), addpemesanan.class);
                                                        Bundle extras = new Bundle();
                                                        extras.putString("IDTEMPAT", idtempat);
                                                        extras.putString("NAMATEMPAT", namatempat);
                                                        extras.putString("JENISLAPANGAN", jenislapangan);
                                                        extras.putString("IDLAPANGAN", idlapanganpesan);
                                                        extras.putString("JAMTERSEDIA", jamtersediapesan);
                                                        extras.putString("JENISOLAHRAGA", jenisolahraga);
                                                        intent.putExtras(extras);
                                                        startActivity(intent);



                                                    }
                                                });


                                            }
                                        }

                                      //  adapter.notifyDataSetChanged();
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                                            }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });


        /**listView = view.findViewById(R.id.listView);

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, list);
        listView.setAdapter(adapter);
**/
        return view;


    }
    public void onBackPressed(){
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new RecListLapangan()).addToBackStack(null).commit();

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

    /**public void addButton(LayoutInflater inflater, ViewGroup container){
        View view= inflater.inflate(R.layout.fragment_detail_lapangan, container, false);
        LinearLayout layout =(LinearLayout) view.findViewById(R.id.rootlayout);
        newbtn= new Button(getActivity());
        newbtn.setText("new button");
        layout.addView(newbtn);
    }**/

































}