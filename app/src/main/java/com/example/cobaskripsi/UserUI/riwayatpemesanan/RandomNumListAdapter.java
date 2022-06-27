package com.example.cobaskripsi.UserUI.riwayatpemesanan;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.detail.PemesananModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RandomNumListAdapter extends FirebaseRecyclerAdapter<PemesananModel,RandomNumListAdapter.myviewholder> {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


    public RandomNumListAdapter(@NonNull FirebaseRecyclerOptions<PemesananModel> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull PemesananModel model) {
        String sudahdipesan;
        String simpen,simpen2;
        ArrayList<String> simpenlist = new ArrayList();
        sudahdipesan = (model.getWaktupemesanan());
        String sudahdipesanbaru = String.valueOf(sudahdipesan.replaceAll("[\\[\\]\\(\\)]", ""));
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(sudahdipesanbaru.split(",")));
        for (int i=0; i<myList.size();i++){
            simpen=myList.get(i);
            simpenlist.add(jamdictionary(simpen));
        }

        holder.namatempat.setText((model.getNamatempat()).substring(0, 1).toUpperCase() + (model.getNamatempat()).substring(1).toLowerCase());
        holder.jenisolahraga.setText(model.getJenisolahraga().substring(0, 1).toUpperCase() + model.getJenisolahraga().substring(1).toLowerCase());
        holder.namalapangan.setText(model.getNamalapangan());
        holder.waktupemesanan.setText(simpenlist.toString());
        holder.tanggalpemesanan.setText(model.getTanggalpemesanan());

        holder.lihatriwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailRiwayatPelanggan.class);
                Bundle extras = new Bundle();
                extras.putString("NAMAPEMESAN", model.getNamapemesan());
                extras.putString("NOTELPPEMESAN", model.getNomortelppemesan());
                extras.putString("TANGGALPEMESANAN", model.getTanggalpemesanan());
                extras.putString("TIMESTAMP", model.getTimestamp());
                extras.putString("JENISOLAHRAGA", model.getJenisolahraga());
                extras.putString("NAMATEMPAT", model.getNamatempat());
                extras.putString("NAMALAPANGAN", model.getNamalapangan());
                extras.putString("STATUSPEMESANAN", model.getStatuspemesanan());
                extras.putString("WAKTUPEMESANAN", simpenlist.toString());
                intent.putExtras(extras);
                v.getContext().startActivity(intent);

                }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.riwayatuser_row, parent, false);
        return new myviewholder(view);
    }


    public class myviewholder extends RecyclerView.ViewHolder{

        TextView namatempat, namalapangan,tanggalpemesanan,waktupemesanan, jenisolahraga;
        Button lihatriwayat;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            namatempat = (TextView)itemView.findViewById(R.id.namatempatriwayat);
            namalapangan = (TextView)itemView.findViewById(R.id.namalapanganriwayat);
            tanggalpemesanan = (TextView)itemView.findViewById(R.id.tanggalpemesananriwayat);
            waktupemesanan = (TextView)itemView.findViewById(R.id.waktupemesananriwayat);
            lihatriwayat = (Button)itemView.findViewById(R.id.lihatdetailriwayat);
            jenisolahraga = (TextView)itemView.findViewById(R.id.jenisolahragariwayat);

        }
    }

    private String jamdictionary(String str){
        Map<String, String> m = new HashMap<>();
        m.put("0","07:00 - 08:00");
        m.put("1","08:00 - 09:00");
        m.put("2","09:00 - 10:00");
        m.put("3","10:00 - 11:00");
        m.put("4","11:00 - 12:00");
        m.put("5","12:00 - 13:00");
        m.put("6","13:00 - 14:00");
        m.put("7","14:00 - 15:00");
        m.put("8","15:00 - 16:00");
        m.put("9","16:00 - 17:00");
        m.put("10","17:00 - 18:00");
        m.put("11","18:00 - 19:00");
        m.put("12","19:00 - 20:00");
        m.put("13","20:00 - 21:00");
        m.put("14","21:00 - 22:00");
        m.put("15","22:00 - 23:00");
        m.put("16","23:00 - 24:00");
        m.put("17","24:00 - 01:00");
        m.put("18","01:00 - 02:00");
        m.put("19","02:00 - 03:00");
        m.put("20","03:00 - 04:00");
        m.put("21","04:00 - 05:00");
        m.put("22","05:00 - 06:00");
        m.put("23","06:00 - 07:00");
        String dict = m.get(str);
        return dict;
    }


}