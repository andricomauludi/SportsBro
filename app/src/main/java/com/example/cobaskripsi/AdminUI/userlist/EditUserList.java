package com.example.cobaskripsi.AdminUI.userlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.AdminUI.HomeadminActivity;
import com.example.cobaskripsi.PengelolaUI.datalapangan.TempatcontributorsModel;
import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.TempatModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditUserList extends AppCompatActivity {

    EditText username,email,notelp;
    String username1,email1,notelp1,role1,rolebaru,iduser, tempatterpilih, namaterpilih, ada,tempatcont,tempatcontjadi,tempatrolesebelum;
    Button back;
    Spinner spinnerrole;
    DatabaseReference reference;
    TextView role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_list);




        username=findViewById(R.id.namauseredituserlist);
        email=findViewById(R.id.emailedituserlist);
        notelp=findViewById(R.id.notelpedituserulist);
        back=findViewById(R.id.backedituserlist);
        role=findViewById(R.id.rolesebelum);
        TextView tempat = findViewById(R.id.tempatedituser);
        LinearLayout layout =findViewById(R.id.rootlayoutedituser3);
        reference = FirebaseDatabase.getInstance().getReference("user");

        Intent intent = getIntent();
        username1 = intent.getStringExtra("NAMAUSER");
        email1 = intent.getStringExtra("EMAIL");
        notelp1 = intent.getStringExtra("NOTELP");
        role1 = intent.getStringExtra("ROLE");
        iduser = intent.getStringExtra("IDUSER");
        tempatrolesebelum = intent.getStringExtra("NAMATEMPAT");

        username.setText(username1);
        email.setText(email1);
        notelp.setText(notelp1);

        if (tempatrolesebelum.equals("")){
            role.setText(role1);
        }
        else{
            role.setText(role1+" untuk "+tempatrolesebelum);
        }




        ArrayList<String> spinnerArray = new ArrayList<String>(24);
        spinnerArray.add("pelanggan");
        spinnerArray.add("mitra");
        spinnerArray.add("admin");

        spinnerrole=findViewById(R.id.spinneredituser);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinnerrole.setAdapter(spinnerArrayAdapter);

        spinnerrole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String roleterpilih = spinnerArray.get(position);
                layout.removeAllViews();
                tempat.setVisibility(View.GONE);
                if (roleterpilih.equals("mitra")){
                    ArrayList<String> arraytempat = new ArrayList<String>();
                    ArrayList<String> arraynamatempat = new ArrayList<String>();
                    FirebaseDatabase.getInstance().getReference("tempat")
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                        TempatModel tempatModel = childSnapshot.getValue(TempatModel.class);

                                        arraytempat.add(tempatModel.getNamatempat());
                                        arraynamatempat.add(tempatModel.getIdtempat());
                                    }

                                        Spinner spinnertempat = new Spinner(EditUserList.this);
                                        ArrayAdapter<String> spinnerTempatAdapter = new ArrayAdapter<String>(EditUserList.this, android.R.layout.simple_spinner_dropdown_item, arraytempat);
                                        spinnertempat.setAdapter(spinnerTempatAdapter);
                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                LinearLayout.LayoutParams.MATCH_PARENT,
                                                LinearLayout.LayoutParams.MATCH_PARENT
                                        );
                                        spinnertempat.setLayoutParams(params);

                                        layout.addView(spinnertempat);
                                        tempat.setVisibility(View.VISIBLE);

                                        spinnertempat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                tempatterpilih=arraynamatempat.get(position);
                                                namaterpilih = arraytempat.get(position);
                                                FirebaseDatabase.getInstance().getReference().child("tempatcontributors")
                                                        .addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                                                    TempatcontributorsModel tempatcontributorsModel = childSnapshot.getValue(TempatcontributorsModel.class);
                                                                    if (tempatcontributorsModel.getIduser().contains(iduser)
                                                                    ) {
                                                                        ada="ada";
                                                                        tempatcont= tempatcontributorsModel.getIdtempatcontributors();

                                                                    }else {
                                                                        ada="tidak ada";
                                                                    }
                                                                }
                                                                tempatcontjadi=tempatcont;
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });


                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                }
                rolebaru = roleterpilih;
    }



    @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditUserList.this,UserList.class));
                finish();
            }
        });
    }

    public void updateuser(View view){
        if (isUsernameChanged() || isEmailChanged() || isNoTelpChanged() || isRoleChanged()){
            Toast.makeText(this, "Data sudah diperbaharui",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, HomeadminActivity.class));
            finish();
        }
        else {
            Toast.makeText(this, "Data tidak ada yang berubah",Toast.LENGTH_LONG).show();
        }

    }

    private boolean isRoleChanged() {
            reference.child(iduser).child("role").setValue(rolebaru);
            if (rolebaru.equals("mitra")){
                if (ada.equals("ada")){
                    FirebaseDatabase.getInstance().getReference("tempatcontributors").child(tempatcontjadi).child("idtempat").setValue(tempatterpilih);
                }
                else{
                    String key = FirebaseDatabase.getInstance().getReference().child("tempatcontributors").push().getKey();
                    Map<String,Object> map = new HashMap<>();
                    map.put("idtempat",tempatterpilih);
                    map.put("iduser", iduser);
                    map.put("namatempat",namaterpilih);
                    map.put("username",username1);
                    map.put("idtempatcontributors",key);
                    FirebaseDatabase.getInstance().getReference().child("tempatcontributors").child(key)
                            .setValue(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Toast.makeText(getApplicationContext(),"Data telah ditambahkan",Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(),"Data tidak dapat ditambahkan",Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }

            return true;
    }

    private boolean isEmailChanged() {
        if (!email1.equals(email.getText().toString())){
            reference.child(iduser).child("emailuser").setValue(email.getText().toString());
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isNoTelpChanged() {
        if (!notelp1.equals(notelp.getText().toString())){
            reference.child(iduser).child("nomortelpuser").setValue(notelp.getText().toString());
            return true;
        }
        else{
            return false;
        }

    }

    private boolean isUsernameChanged() {
        if (!username1.equals(username.getText().toString())){
            reference.child(iduser).child("username").setValue(username.getText().toString());
            return true;
        }
        else{
            return false;
        }
    }


    public void onBackPressed(){

    }
}