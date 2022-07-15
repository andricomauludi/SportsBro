package com.example.cobaskripsi.PengelolaUI.datatempatmitra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.PengelolaUI.datalapangan.TempatcontributorsModel;
import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.TempatModel;
import com.example.cobaskripsi.preferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class datatempatmitra extends AppCompatActivity {

    TextView namatempat,jenisolahraga,alamat,marker,notelp;
    String tempatcont,idtempat;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datatempatmitra);

        namatempat=findViewById(R.id.namatempatdatatempatmitra);
        jenisolahraga=findViewById(R.id.jenisolahragadatatempatmitra);
        alamat=findViewById(R.id.alamattempatdatatempatmitra);
        marker=findViewById(R.id.markerdatatempatmitra);
        notelp=findViewById(R.id.nomortelpdatatempatmitra);
        edit=findViewById(R.id.editdatatempatmitra);

        FirebaseDatabase.getInstance().getReference().child("tempatcontributors")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            TempatcontributorsModel tempatcontributorsModel = childSnapshot.getValue(TempatcontributorsModel.class);
                            if (tempatcontributorsModel.getIduser().contains(preferences.getUserID(datatempatmitra.this))){
                                tempatcont = tempatcontributorsModel.getIdtempat();
                            }

                        }
                        idtempat=tempatcont;
                        insert();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        FirebaseDatabase.getInstance().getReference().child("tempat")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()){
                            TempatModel tempatModel = childSnapshot.getValue(TempatModel.class);
                            if (tempatModel.getIdtempat().contains(idtempat)){
                                namatempat.setText(tempatModel.getNamatempat());
                                jenisolahraga.setText(tempatModel.getJenisolahraga());
                                alamat.setText(tempatModel.getAlamattempat());
                                marker.setText(tempatModel.getMarker());
                                notelp.setText(tempatModel.getNotelptempat());
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(datatempatmitra.this, editdatatempatmitra.class);
                intent.putExtra("idtempat",idtempat);
                intent.putExtra("namatempat",namatempat.getText().toString());
                intent.putExtra("jenisolahraga", jenisolahraga.getText().toString());
                intent.putExtra("alamat",alamat.getText().toString());
                intent.putExtra("marker", marker.getText().toString());
                intent.putExtra("notelp", notelp.getText().toString());
                startActivity(intent);
            }
        });







    }
    public void insert(){
        namatempat.setText(idtempat);
    }
}