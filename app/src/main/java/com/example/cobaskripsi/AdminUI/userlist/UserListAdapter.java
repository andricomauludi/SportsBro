package com.example.cobaskripsi.AdminUI.userlist;

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
import com.example.cobaskripsi.UserModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


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
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailUserList.class);
                intent.putExtra("NAMAUSER", model.getUsername());
                intent.putExtra("ROLE",model.getRole());
                intent.putExtra("EMAIL", model.getEmailuser());
                intent.putExtra("NOTELP", model.getNomortelpuser());
                context.startActivity(intent);
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namauser, roleuser;
        Button detail;

        public ViewHolder(View view) {
            super(view);
            context=view.getContext();
            namauser=view.findViewById(R.id.namauseruserlist);
            roleuser=view.findViewById(R.id.roleuseruserlist);
            detail=view.findViewById(R.id.detailuseruserlist);

        }

    }
}