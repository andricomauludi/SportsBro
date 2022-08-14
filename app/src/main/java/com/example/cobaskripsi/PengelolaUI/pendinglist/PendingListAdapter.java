package com.example.cobaskripsi.PengelolaUI.pendinglist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class PendingListAdapter extends FirebaseRecyclerAdapter<PemesananModel, PendingListAdapter.ViewHolder> {

    Context context;


    public PendingListAdapter(@NonNull FirebaseRecyclerOptions<PemesananModel> options) {
        super(options);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pendinglist_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull PemesananModel model) {
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String todayString = formatter.format(todayDate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String tanggalpesan = model.getTanggalpemesanan();

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
            if (model.getStatuspemesanan().equals("Menunggu Konfirmasi") ||model.getStatuspemesanan().equals("Pengajuan Pembatalan")){
                holder.itemView.setVisibility(View.VISIBLE);
                holder.namapemesan.setText(model.getNamapemesan());
                holder.tanggal.setText(model.getTanggalpemesanan());
                if (model.getStatuspemesanan().equals("Menunggu Konfirmasi")){
                    holder.statuspemesanan.setTextColor(Color.rgb(255,165,0));
                    holder.statuspemesanan.setText(model.getStatuspemesanan().substring(0, 1).toUpperCase() + model.getStatuspemesanan().substring(1).toLowerCase());
                }else if (model.getStatuspemesanan().equals("Pengajuan Pembatalan")){
                    holder.statuspemesanan.setTextColor(Color.BLUE);
                    holder.statuspemesanan.setText(model.getStatuspemesanan().substring(0, 1).toUpperCase() + model.getStatuspemesanan().substring(1).toLowerCase());
                }
                holder.statuspemesanan.setText(model.getStatuspemesanan());

                holder.detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailPendingList.class);
                        intent.putExtra("NAMAPEMESAN", model.getNamapemesan());
                        intent.putExtra("TIMESTAMP",model.getTimestamp());
                        intent.putExtra("NAMALAPANGAN", model.getNamalapangan());
                        intent.putExtra("TANGGALPEMESANAN", model.getTanggalpemesanan());
                        intent.putExtra("WAKTUPEMESANAN", model.getWaktupemesanan());
                        intent.putExtra("STATUSPEMESANAN", model.getStatuspemesanan());
                        intent.putExtra("IDPEMESANAN", model.getIdpemesanan());
                        intent.putExtra("NOMORTELEPON", model.getNomortelppemesan());
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
        }else{
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));

        }


    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namapemesan,statuspemesanan, tanggal;
        Button detail;

        public ViewHolder(View view) {
            super(view);
            context=view.getContext();
            namapemesan = view.findViewById(R.id.namapemesanpendinglist);
            tanggal=view.findViewById(R.id.tanggalpemesananpendinglist);
            statuspemesanan=view.findViewById(R.id.statuspemesananpendinglist);
            detail=view.findViewById(R.id.detailpemesanpendinglist);
        }
    }
}