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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class BookingListAdapter extends FirebaseRecyclerAdapter<PemesananModel, BookingListAdapter.ViewHolder> {
    Context context;


    public BookingListAdapter(@NonNull FirebaseRecyclerOptions<PemesananModel> options) {
        super(options);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookinglist_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull PemesananModel model) {


        if (model.getStatuspemesanan().equals("Booked")){
            String sudahdipesan;
            String simpen;
            ArrayList<String> simpenlist = new ArrayList();
            sudahdipesan = (model.getWaktupemesanan());
            String sudahdipesanbaru = String.valueOf(sudahdipesan.replaceAll("[\\[\\]\\(\\)]", ""));
            ArrayList<String> myList = new ArrayList<String>(Arrays.asList(sudahdipesanbaru.split(",")));
            for (int i=0; i<myList.size();i++){
                simpen=myList.get(i);
                simpenlist.add(jamdictionary(simpen));
            }

            holder.itemView.setVisibility(View.VISIBLE);
            holder.namapemesan.setText(model.getNamapemesan());
            holder.tanggalpemesanan.setText(model.getTanggalpemesanan());
            holder.waktupemesanan.setText(simpenlist.toString());
            holder.detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailBookingList.class);
                    intent.putExtra("NAMAPEMESAN", model.getNamapemesan());
                    intent.putExtra("TIMESTAMP",model.getTimestamp());
                    intent.putExtra("NAMALAPANGAN", model.getNamalapangan());
                    intent.putExtra("TANGGALPEMESANAN", model.getTanggalpemesanan());
                    intent.putExtra("WAKTUPEMESANAN", model.getWaktupemesanan());
                    intent.putExtra("STATUSPEMESANAN", model.getStatuspemesanan());
                    intent.putExtra("IDPEMESANAN", model.getIdpemesanan());
                    intent.putExtra("NOMORTELP",model.getNomortelppemesan());
                    FirebaseDatabase.getInstance().getReference().child("lapangan")
                            .child(getRef(position).getKey());
                    context.startActivity(intent);
                }
            });
        }
        else{
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namapemesan, tanggalpemesanan,waktupemesanan;
        Button detail;

        public ViewHolder(View view) {
            super(view);
            context=view.getContext();
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