package com.example.cobaskripsi.PengelolaUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.detail.LapanganModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import de.hdodenhof.circleimageview.CircleImageView;


public class AdapterDataLapangan extends FirebaseRecyclerAdapter<LapanganModel,AdapterDataLapangan.myviewholder> {
    private DatabaseReference mDatabase;

    public AdapterDataLapangan(@NonNull FirebaseRecyclerOptions<LapanganModel> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull LapanganModel model) {

    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datalapangan_row,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView namatempat, marker;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView)itemView.findViewById(R.id.basketImageView);
            namatempat = (TextView)itemView.findViewById(R.id.basketText1);
            marker = (TextView)itemView.findViewById(R.id.basketText2);
        }
    }
}
