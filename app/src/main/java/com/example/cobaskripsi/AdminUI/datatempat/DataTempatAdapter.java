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

import com.example.cobaskripsi.PengelolaUI.datalapangan.TempatcontributorsModel;
import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.TempatModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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
        FirebaseDatabase.getInstance().getReference().child("tempatcontributors")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()){
                            TempatcontributorsModel tempatcontributorsModel = childSnapshot.getValue(TempatcontributorsModel.class);
                            if (tempatcontributorsModel.getIdtempat().equals(model.getIdtempat())){
                                holder.simpanmitra=tempatcontributorsModel.getUsername();
                            }
                        }}

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailDataTempat.class);
                intent.putExtra("NAMATEMPAT", model.getNamatempat());
                intent.putExtra("JENISOLAHRAGA",model.getJenisolahraga());
                intent.putExtra("ALAMAT", model.getAlamattempat());
                intent.putExtra("NOTELP", model.getNotelptempat());
                intent.putExtra("MITRA", holder.simpanmitra);
                context.startActivity(intent);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,editdatatempat.class));
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView namatempat, jenisolahraga;
        Button detail,delete,edit;
        String simpanmitra ="";
        public ViewHolder(View view) {
            super(view);
            context=context=view.getContext();
            namatempat=view.findViewById(R.id.namatempatdatatempat);
            detail =view.findViewById(R.id.detailtempatdatatempat);
            jenisolahraga= view.findViewById(R.id.jenisolahragadatatempat);
            delete=view.findViewById(R.id.deletedatatempat);
            edit=view.findViewById(R.id.editdatatempat);


        }

    }
}