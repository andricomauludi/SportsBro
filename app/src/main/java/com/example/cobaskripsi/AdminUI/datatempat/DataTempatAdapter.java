package com.example.cobaskripsi.AdminUI.datatempat;

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
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.TempatModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class DataTempatAdapter extends FirebaseRecyclerAdapter<TempatModel, DataTempatAdapter.ViewHolder> {

    Context context;
    public DataTempatAdapter(@NonNull FirebaseRecyclerOptions<TempatModel> options) {
        super(options);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.datatempat_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull TempatModel model) {
        holder.namatempat.setText(model.getNamatempat());
        holder.jenisolahraga.setText("Olahraga "+model.getJenisolahraga());
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailDataTempat.class);
                intent.putExtra("NAMATEMPAT", model.getNamatempat());
                intent.putExtra("JENISOLAHRAGA",model.getJenisolahraga());
                intent.putExtra("ALAMAT", model.getAlamattempat());
                intent.putExtra("NOTELP", model.getNotelptempat());
                intent.putExtra("MITRA", "dummy");
                context.startActivity(intent);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView namatempat, jenisolahraga;
        Button detail;

        public ViewHolder(View view) {
            super(view);
            context=context=view.getContext();
            namatempat=view.findViewById(R.id.namatempatdatatempat);
            detail =view.findViewById(R.id.detailtempatdatatempat);
            jenisolahraga= view.findViewById(R.id.jenisolahragadatatempat);

        }

    }
}