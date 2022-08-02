package com.example.cobaskripsi.AdminUI.userlist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.cobaskripsi.UserModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UserListAdapter extends FirebaseRecyclerAdapter<UserModel, UserListAdapter.ViewHolder> {


    Context context;
    public UserListAdapter(@NonNull FirebaseRecyclerOptions<UserModel> options) {
        super(options);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.userlist_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull UserModel model) {
        holder.namauser.setText(model.getUsername());
        holder.roleuser.setText("Role "+model.getRole());
        if (model.getRole().equals("mitra")){
            FirebaseDatabase.getInstance().getReference().child("tempatcontributors")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot childSnapshot : snapshot.getChildren()){
                                TempatcontributorsModel tempatcontributorsModel = childSnapshot.getValue(TempatcontributorsModel.class);
                                if (tempatcontributorsModel.getIduser().equals(model.getIduser())){
                                    holder.tempat.setText(tempatcontributorsModel.getNamatempat());
                                    holder.simpantempat=tempatcontributorsModel.getNamatempat();
                                }
                        }}

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
            }
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailUserList.class);
                intent.putExtra("NAMAUSER", model.getUsername());
                intent.putExtra("ROLE",model.getRole());
                intent.putExtra("EMAIL", model.getEmailuser());
                intent.putExtra("NOTELP", model.getNomortelpuser());
                intent.putExtra("NAMATEMPAT",holder.simpantempat);
                context.startActivity(intent);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditUserList.class);
                intent.putExtra("IDUSER",model.getIduser());
                intent.putExtra("NAMAUSER", model.getUsername());
                intent.putExtra("ROLE",model.getRole());
                intent.putExtra("EMAIL", model.getEmailuser());
                intent.putExtra("NOTELP", model.getNomortelpuser());
                intent.putExtra("NAMATEMPAT",holder.simpantempat);
                context.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.namauser.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Hapus "+model.getUsername()+" ?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("user")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namauser, roleuser,tempat;
        Button detail, delete,edit;
        String simpantempat="";

        public ViewHolder(View view) {
            super(view);
            context=view.getContext();
            namauser=view.findViewById(R.id.namauseruserlist);
            roleuser=view.findViewById(R.id.roleuseruserlist);
            detail=view.findViewById(R.id.detailuseruserlist);
            edit = view.findViewById(R.id.edituseruserlist);
            delete = view.findViewById(R.id.deleteuseruserlist);
            tempat = view.findViewById(R.id.tempatuseruserlist);

        }

    }
}