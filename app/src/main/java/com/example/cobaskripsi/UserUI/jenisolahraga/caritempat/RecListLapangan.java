package com.example.cobaskripsi.UserUI.jenisolahraga.caritempat;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaskripsi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RecListLapangan extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    RecyclerView recview;
    AdapterListLapanganLama adapter;
    AdapterListLapangan myadapter;
    ArrayList<TempatModel> arrayList;
    DatabaseReference databaseReference;

    String jenisolahragaa;
    Spinner change;
    double latitudeSaya, longitudeSaya;

    public RecListLapangan() {

    }


    public static RecListLapangan newInstance(String param1, String param2) {
        RecListLapangan fragment = new RecListLapangan();
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
        View view= inflater.inflate(R.layout.fragment_rec_list_lapangan, container, false);

        recview=(RecyclerView)view.findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));
        change=view.findViewById(R.id.change);

        arrayList= new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tempat");


        jenisolahragaa= ((MainListLapangan)getActivity()).jenisolahraga1;

        //coba.setText(jenisolahraga);



        ArrayList<String> spinnerArray = new ArrayList<String>(24);
        spinnerArray.add("5 Km");
        spinnerArray.add("10 Km");
        spinnerArray.add("25 Km");
        spinnerArray.add("50 Km");
        spinnerArray.add("Semua tempat");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        change.setAdapter(spinnerArrayAdapter);

        change.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String roleterpilih = spinnerArray.get(position);
                if (spinnerArray.get(position).equals("5 Km")){
                    recview.setItemAnimator(null);
                    Query query=databaseReference.orderByChild("jenisolahraga").equalTo(jenisolahragaa.toLowerCase());
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChildren()){
                                arrayList.clear();
                                for (DataSnapshot snap : snapshot.getChildren()){
                                    final TempatModel tempatModel = snap.getValue(TempatModel.class);
                                    String marker = tempatModel.getMarker();

                                    GetLocation getLocation = new GetLocation(getActivity());
                                    Location location = getLocation.getLocation();
                                    if (location != null){
                                        latitudeSaya = location.getLatitude();
                                        longitudeSaya = location.getLongitude();
                                    }
                                    String str = marker;

                                    String[] latlong = str.split(",");
                                    String lat = latlong[0];
                                    String lng = latlong[1];
                                    double latitudeTujuan = Double.valueOf(lat.toString());
                                    double longitudeTujuan = Double.valueOf(lng.toString());


                                    double jarak = getDistance(latitudeTujuan, longitudeTujuan, latitudeSaya, longitudeSaya);
                                    jarak = (jarak / 1000);
                                    String stringjarak = jarak+"";
                                    if (jarak<=5){
                                        arrayList.add(tempatModel);
                                    }

                                }

                                myadapter =  new AdapterListLapangan(getActivity().getApplicationContext(),arrayList);
                                recview.setAdapter(myadapter);
                                myadapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else if (spinnerArray.get(position).equals("10 Km")){
                    recview.setItemAnimator(null);
                    Query query=databaseReference.orderByChild("jenisolahraga").equalTo(jenisolahragaa.toLowerCase());
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChildren()){
                                arrayList.clear();
                                for (DataSnapshot snap : snapshot.getChildren()){
                                    final TempatModel tempatModel = snap.getValue(TempatModel.class);
                                    String marker = tempatModel.getMarker();

                                    GetLocation getLocation = new GetLocation(getActivity());
                                    Location location = getLocation.getLocation();
                                    if (location != null){
                                        latitudeSaya = location.getLatitude();
                                        longitudeSaya = location.getLongitude();
                                    }
                                    String str = marker;

                                    String[] latlong = str.split(",");
                                    String lat = latlong[0];
                                    String lng = latlong[1];
                                    double latitudeTujuan = Double.valueOf(lat.toString());
                                    double longitudeTujuan = Double.valueOf(lng.toString());


                                    double jarak = getDistance(latitudeTujuan, longitudeTujuan, latitudeSaya, longitudeSaya);
                                    jarak = Math.ceil(jarak / 1000);
                                    String stringjarak = jarak+"";
                                    if (jarak<=10){
                                        arrayList.add(tempatModel);

                                    }
                                }

                                myadapter =  new AdapterListLapangan(getActivity().getApplicationContext(),arrayList);
                                recview.setAdapter(myadapter);
                                myadapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else if (spinnerArray.get(position).equals("25 Km")){
                    recview.setItemAnimator(null);
                    Query query=databaseReference.orderByChild("jenisolahraga").equalTo(jenisolahragaa.toLowerCase());
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChildren()){
                                arrayList.clear();
                                for (DataSnapshot snap : snapshot.getChildren()){
                                    final TempatModel tempatModel = snap.getValue(TempatModel.class);
                                    String marker = tempatModel.getMarker();

                                    GetLocation getLocation = new GetLocation(getActivity());
                                    Location location = getLocation.getLocation();
                                    if (location != null){
                                        latitudeSaya = location.getLatitude();
                                        longitudeSaya = location.getLongitude();
                                    }
                                    String str = marker;

                                    String[] latlong = str.split(",");
                                    String lat = latlong[0];
                                    String lng = latlong[1];
                                    double latitudeTujuan = Double.valueOf(lat.toString());
                                    double longitudeTujuan = Double.valueOf(lng.toString());


                                    double jarak = getDistance(latitudeTujuan, longitudeTujuan, latitudeSaya, longitudeSaya);
                                    jarak = Math.ceil(jarak / 1000);
                                    String stringjarak = jarak+"";
                                    if (jarak<=25){
                                        arrayList.add(tempatModel);
                                    }
                                }

                                myadapter =  new AdapterListLapangan(getActivity().getApplicationContext(),arrayList);
                                recview.setAdapter(myadapter);
                                myadapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else if (spinnerArray.get(position).equals("50 Km")){
                    recview.setItemAnimator(null);
                    Query query=databaseReference.orderByChild("jenisolahraga").equalTo(jenisolahragaa.toLowerCase());
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChildren()){
                                arrayList.clear();
                                for (DataSnapshot snap : snapshot.getChildren()){
                                    final TempatModel tempatModel = snap.getValue(TempatModel.class);
                                    String marker = tempatModel.getMarker();

                                    GetLocation getLocation = new GetLocation(getActivity());
                                    Location location = getLocation.getLocation();
                                    if (location != null){
                                        latitudeSaya = location.getLatitude();
                                        longitudeSaya = location.getLongitude();
                                    }
                                    String str = marker;

                                    String[] latlong = str.split(",");
                                    String lat = latlong[0];
                                    String lng = latlong[1];
                                    double latitudeTujuan = Double.valueOf(lat.toString());
                                    double longitudeTujuan = Double.valueOf(lng.toString());


                                    double jarak = getDistance(latitudeTujuan, longitudeTujuan, latitudeSaya, longitudeSaya);
                                    jarak = Math.ceil(jarak / 1000);
                                    String stringjarak = jarak+"";
                                    if (jarak<=50){
                                        arrayList.add(tempatModel);
                                    }
                                }

                                myadapter =  new AdapterListLapangan(getActivity().getApplicationContext(),arrayList);
                                recview.setAdapter(myadapter);
                                myadapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else if (spinnerArray.get(position).equals("Semua tempat")){
                    recview.setItemAnimator(null);
                    Query query=databaseReference.orderByChild("jenisolahraga").equalTo(jenisolahragaa.toLowerCase());
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChildren()){
                                arrayList.clear();
                                for (DataSnapshot snap : snapshot.getChildren()){
                                    final TempatModel tempatModel = snap.getValue(TempatModel.class);
                                    String marker = tempatModel.getMarker();

                                    GetLocation getLocation = new GetLocation(getActivity());
                                    Location location = getLocation.getLocation();
                                    if (location != null){
                                        latitudeSaya = location.getLatitude();
                                        longitudeSaya = location.getLongitude();
                                    }
                                    String str = marker;

                                    String[] latlong = str.split(",");
                                    String lat = latlong[0];
                                    String lng = latlong[1];
                                    double latitudeTujuan = Double.valueOf(lat.toString());
                                    double longitudeTujuan = Double.valueOf(lng.toString());


                                    double jarak = getDistance(latitudeTujuan, longitudeTujuan, latitudeSaya, longitudeSaya);
                                    jarak = Math.ceil(jarak / 1000);
                                    String stringjarak = jarak+"";
                                    arrayList.add(tempatModel);

                                }

                                myadapter =  new AdapterListLapangan(getActivity().getApplicationContext(),arrayList);
                                recview.setAdapter(myadapter);
                                myadapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
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