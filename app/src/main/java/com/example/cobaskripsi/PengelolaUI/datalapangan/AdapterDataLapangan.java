package com.example.cobaskripsi.PengelolaUI.datalapangan;

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

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.detail.LapanganModel;
import com.example.cobaskripsi.preferences;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AdapterDataLapangan extends FirebaseRecyclerAdapter<LapanganModel,AdapterDataLapangan.myviewholder> {
    private DatabaseReference mDatabase;
    private Context context;

    public AdapterDataLapangan(@NonNull FirebaseRecyclerOptions<LapanganModel> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull LapanganModel model) {
        holder.namalapangan.setText(model.getNamalapangan());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("lapangan").orderByChild("namalapangan")
                        .equalTo(model.getNamalapangan()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()){
                            LapanganModel lapanganModel = childSnapshot.getValue(LapanganModel.class);
                            String idlapangan = childSnapshot.getKey();
                            Intent intent = new Intent(context, editdatalapangan.class);
                            intent.putExtra("IDTEMPAT", preferences.getIdtempatmitra(context));
                            intent.putExtra("NAMALAPANGAN",holder.namalapangan.getText().toString());
                            intent.putExtra("JAMTERSEDIA", model.getJamtersedia());
                            intent.putExtra("DESKRIPSILAPANGAN", model.getDeskripsilapangan());
                            intent.putExtra("IDLAPANGAN", idlapangan);
                            context.startActivity(intent);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.namalapangan.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Hapus "+model.getNamalapangan()+" ?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("lapangan")
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


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datalapangan_row,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder{

        TextView namalapangan;
        Button edit, delete;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            namalapangan = (TextView)itemView.findViewById(R.id.namalapangandatamitra);
            edit = itemView.findViewById(R.id.editdatalapanganmitra);
            delete= itemView.findViewById(R.id.deletedatalapanganmitra);
        }
    }
}
