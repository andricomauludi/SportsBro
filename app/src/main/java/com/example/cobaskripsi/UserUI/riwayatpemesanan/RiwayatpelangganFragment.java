package com.example.cobaskripsi.UserUI.riwayatpemesanan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.detail.PemesananModel;
import com.example.cobaskripsi.preferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RiwayatpelangganFragment extends Fragment {
    private RecyclerView recyclerView;
    ConstraintLayout constraintLayout;
    String username,userid;
    TextView nonedata, pilihan;
    Button semuariwayat,sudahselesai,menunggukonfirmasi,booked,ditolak,pembatalan;
    ArrayList<PemesananModel> arrayList;
    DatabaseReference databaseReference;
    AdapterRiwayatPemesanan myadapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_riwayatuser, container, false);

        username= preferences.getUsername(getActivity());
        userid=preferences.getUserID(getContext());
        semuariwayat=root.findViewById(R.id.semuariwayat);
        sudahselesai=root.findViewById(R.id.sudahselesai);
        menunggukonfirmasi=root.findViewById(R.id.menunggukonfirmasi);
        booked=root.findViewById(R.id.booked);
        ditolak=root.findViewById(R.id.ditolak);
        pilihan=root.findViewById(R.id.pilihantext);
        pembatalan=root.findViewById(R.id.pengajuanpembatalan);

        arrayList= new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("pemesanan");


        recyclerView = root.findViewById(R.id.recyclerviewriwayat);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String todayString = formatter.format(todayDate);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        pilihan.setText("Menunggu Konfirmasi");
        recyclerView.setItemAnimator(null);
        Query query=databaseReference.orderByChild("iduser").equalTo(userid);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    arrayList.clear();
                    for (DataSnapshot snap : snapshot.getChildren()){
                        final PemesananModel pemesananModel = snap.getValue(PemesananModel.class);
                        String tanggalpesan = pemesananModel.getTanggalpemesanan();

                        Date tanggal = null;
                        try {
                            tanggal = sdf.parse(tanggalpesan);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date today = null;
                        try {
                            today = sdf.parse(todayString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (tanggal.compareTo(today)>=0){
                            if (pemesananModel.getStatuspemesanan().contains("Menunggu Konfirmasi")){
                                arrayList.add(pemesananModel);
                            }
                        }

                    }

                    myadapter =  new AdapterRiwayatPemesanan(getActivity().getApplicationContext(),arrayList);
                    recyclerView.setAdapter(myadapter);
                    myadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        semuariwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihan.setText("Semua Riwayat");
                recyclerView.setItemAnimator(null);
                Query query=databaseReference.orderByChild("iduser").equalTo(userid);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()){
                            arrayList.clear();
                            for (DataSnapshot snap : snapshot.getChildren()){
                                final PemesananModel pemesananModel = snap.getValue(PemesananModel.class);
                                arrayList.add(pemesananModel);

                            }

                            myadapter =  new AdapterRiwayatPemesanan(getActivity().getApplicationContext(),arrayList);
                            recyclerView.setAdapter(myadapter);
                            myadapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        sudahselesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihan.setText("Expired");
                recyclerView.setItemAnimator(null);
                Query query=databaseReference.orderByChild("iduser").equalTo(userid);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()){
                            arrayList.clear();
                            for (DataSnapshot snap : snapshot.getChildren()){
                                final PemesananModel pemesananModel = snap.getValue(PemesananModel.class);
                                String tanggalpesan = pemesananModel.getTanggalpemesanan();

                                Date tanggal = null;
                                try {
                                    tanggal = sdf.parse(tanggalpesan);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Date today = null;
                                try {
                                    today = sdf.parse(todayString);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                if (tanggal.compareTo(today)<0){
                                    arrayList.add(pemesananModel);
                                }

                            }

                            myadapter =  new AdapterRiwayatPemesanan(getActivity().getApplicationContext(),arrayList);
                            recyclerView.setAdapter(myadapter);
                            myadapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        menunggukonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihan.setText("Menunggu Konfirmasi");
                recyclerView.setItemAnimator(null);
                Query query=databaseReference.orderByChild("iduser").equalTo(userid);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()){
                            arrayList.clear();
                            for (DataSnapshot snap : snapshot.getChildren()){
                                final PemesananModel pemesananModel = snap.getValue(PemesananModel.class);
                                String tanggalpesan = pemesananModel.getTanggalpemesanan();

                                Date tanggal = null;
                                try {
                                    tanggal = sdf.parse(tanggalpesan);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Date today = null;
                                try {
                                    today = sdf.parse(todayString);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                if (tanggal.compareTo(today)>=0){
                                    if (pemesananModel.getStatuspemesanan().contains("Menunggu Konfirmasi")){
                                        arrayList.add(pemesananModel);
                                    }
                                }

                            }

                            myadapter =  new AdapterRiwayatPemesanan(getActivity().getApplicationContext(),arrayList);
                            recyclerView.setAdapter(myadapter);
                            myadapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        booked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihan.setText("Booked");
                recyclerView.setItemAnimator(null);
                Query query=databaseReference.orderByChild("iduser").equalTo(userid);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()){
                            arrayList.clear();
                            for (DataSnapshot snap : snapshot.getChildren()){
                                final PemesananModel pemesananModel = snap.getValue(PemesananModel.class);
                                String tanggalpesan = pemesananModel.getTanggalpemesanan();

                                Date tanggal = null;
                                try {
                                    tanggal = sdf.parse(tanggalpesan);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Date today = null;
                                try {
                                    today = sdf.parse(todayString);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                if (tanggal.compareTo(today)>=0){
                                    if (pemesananModel.getStatuspemesanan().contains("Booked")){
                                        arrayList.add(pemesananModel);
                                    }

                                }

                            }

                            myadapter =  new AdapterRiwayatPemesanan(getActivity().getApplicationContext(),arrayList);
                            recyclerView.setAdapter(myadapter);
                            myadapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        ditolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihan.setText("Ditolak");
                recyclerView.setItemAnimator(null);
                Query query=databaseReference.orderByChild("iduser").equalTo(userid);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()){
                            arrayList.clear();
                            for (DataSnapshot snap : snapshot.getChildren()){
                                final PemesananModel pemesananModel = snap.getValue(PemesananModel.class);
                                String tanggalpesan = pemesananModel.getTanggalpemesanan();

                                Date tanggal = null;
                                try {
                                    tanggal = sdf.parse(tanggalpesan);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Date today = null;
                                try {
                                    today = sdf.parse(todayString);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if (pemesananModel.getStatuspemesanan().contains("Ditolak")||pemesananModel.getStatuspemesanan().contains("Batal Pesan")){
                                    arrayList.add(pemesananModel);
                                }

                            }

                            myadapter =  new AdapterRiwayatPemesanan(getActivity().getApplicationContext(),arrayList);
                            recyclerView.setAdapter(myadapter);
                            myadapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        pembatalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihan.setText("Pengajuan Pembatalan");
                recyclerView.setItemAnimator(null);
                Query query=databaseReference.orderByChild("iduser").equalTo(userid);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()){
                            arrayList.clear();
                            for (DataSnapshot snap : snapshot.getChildren()){
                                final PemesananModel pemesananModel = snap.getValue(PemesananModel.class);
                                String tanggalpesan = pemesananModel.getTanggalpemesanan();

                                Date tanggal = null;
                                try {
                                    tanggal = sdf.parse(tanggalpesan);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Date today = null;
                                try {
                                    today = sdf.parse(todayString);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                if (tanggal.compareTo(today)>=0){
                                    if (pemesananModel.getStatuspemesanan().contains("Pengajuan Pembatalan")){
                                        arrayList.add(pemesananModel);
                                    }

                                }

                            }

                            myadapter =  new AdapterRiwayatPemesanan(getActivity().getApplicationContext(),arrayList);
                            recyclerView.setAdapter(myadapter);
                            myadapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return root;
    }


}