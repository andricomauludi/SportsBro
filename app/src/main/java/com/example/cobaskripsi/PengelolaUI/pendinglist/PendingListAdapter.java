package com.example.cobaskripsi.PengelolaUI.pendinglist;

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
        holder.namapemesan.setText(model.getNamapemesan());
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
                FirebaseDatabase.getInstance().getReference().child("lapangan")
                        .child(getRef(position).getKey());
                context.startActivity(intent);
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namapemesan;
        Button detail;

        public ViewHolder(View view) {
            super(view);
            context=view.getContext();
            namapemesan = view.findViewById(R.id.namapemesanpendinglist);
            detail=view.findViewById(R.id.detailpemesanpendinglist);
        }
    }
}