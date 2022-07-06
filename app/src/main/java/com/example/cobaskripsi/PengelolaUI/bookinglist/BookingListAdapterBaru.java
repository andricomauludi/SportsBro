package com.example.cobaskripsi.PengelolaUI.bookinglist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.detail.PemesananModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BookingListAdapterBaru extends RecyclerView.Adapter<BookingListAdapterBaru.myviewholder> {
    public Context c;
    public ArrayList<PemesananModel> arrayList;

    public BookingListAdapterBaru(Context c, ArrayList<PemesananModel> arrayList) {
        this.c=c;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookinglist_row,parent,false);
        return new myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        PemesananModel pemesananModel = arrayList.get(position);
        if (pemesananModel.getStatuspemesanan().equals("Booked")){
            String sudahdipesan;
            String simpen;
            ArrayList<String> simpenlist = new ArrayList();
            sudahdipesan = (pemesananModel.getWaktupemesanan());
            String sudahdipesanbaru = String.valueOf(sudahdipesan.replaceAll("[\\[\\]\\(\\)]", ""));
            ArrayList<String> myList = new ArrayList<String>(Arrays.asList(sudahdipesanbaru.split(",")));
            for (int i=0; i<myList.size();i++){
                simpen=myList.get(i);
                simpenlist.add(jamdictionary(simpen));
            }

            holder.itemView.setVisibility(View.VISIBLE);
            holder.namapemesan.setText(pemesananModel.getNamapemesan());
            holder.tanggalpemesanan.setText(pemesananModel.getTanggalpemesanan());
            holder.waktupemesanan.setText(simpenlist.toString());
            holder.detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailBookingList.class);
                    intent.putExtra("NAMAPEMESAN", pemesananModel.getNamapemesan());
                    intent.putExtra("TIMESTAMP",pemesananModel.getTimestamp());
                    intent.putExtra("NAMALAPANGAN", pemesananModel.getNamalapangan());
                    intent.putExtra("TANGGALPEMESANAN", pemesananModel.getTanggalpemesanan());
                    intent.putExtra("WAKTUPEMESANAN", pemesananModel.getWaktupemesanan());
                    intent.putExtra("STATUSPEMESANAN", pemesananModel.getStatuspemesanan());
                    intent.putExtra("IDPEMESANAN", pemesananModel.getIdpemesanan());
                    intent.putExtra("NOMORTELP",pemesananModel.getNomortelppemesan());
                    v.getContext().startActivity(intent);
                }
            });
        }
        else{
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));

        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder{

        TextView namapemesan, tanggalpemesanan,waktupemesanan;
        Button detail;

        public myviewholder(View view) {
            super(view);
            namapemesan = view.findViewById(R.id.namapemesanbookinglist);
            tanggalpemesanan = view.findViewById(R.id.tanggalpemesananbookinglist);
            waktupemesanan = view.findViewById(R.id.waktupemesananbookinglist);
            detail=view.findViewById(R.id.detailpemesanbookinglist);
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
