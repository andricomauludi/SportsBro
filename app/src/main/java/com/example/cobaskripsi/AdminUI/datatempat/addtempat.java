package com.example.cobaskripsi.AdminUI.datatempat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class addtempat extends AppCompatActivity {
    Spinner spinner;
    EditText namatempat,alamat,notelp,marker;
    String jenisolahraga;
    Button back,tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtempat);
        spinner=findViewById(R.id.spinnerjenisolahraga);
        back=findViewById(R.id.backadddatatempat);
        namatempat=findViewById(R.id.addnamatempat);
        alamat=findViewById(R.id.addalamattempat);
        notelp=findViewById(R.id.notelptempat);
        marker=findViewById(R.id.markerlokasi);
        tambah=findViewById(R.id.tambahadddatatempat);

        ArrayList<String> spinnerArray = new ArrayList<String>(24);
        spinnerArray.add("basket");
        spinnerArray.add("tenis");
        spinnerArray.add("badminton");
        spinnerArray.add("futsal");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                jenisolahraga=spinnerArray.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(addtempat.this,DataTempat.class));
            }
        });

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidation()){
                    processinsert();
                }
            }
        });

    }
    private void processinsert(){
        String key = FirebaseDatabase.getInstance().getReference().child("tempat").push().getKey();
        Map<String,Object> map = new HashMap<>();
        map.put("alamattempat", alamat.getText().toString());
        map.put("gambar", "");
        map.put("idtempat",key);
        map.put("jenisolahraga", jenisolahraga);
        map.put("marker", marker.getText().toString());
        map.put("namatempat",namatempat.getText().toString());
        map.put("notelptempat", "+62"+notelp.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("tempat").child(key)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        alamat.setText((""));
                        marker.setText((""));
                        namatempat.setText((""));
                        notelp.setText((""));
                        Toast.makeText(getApplicationContext(),"Data telah ditambahkan",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), DataTempat.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Data tidak dapat ditambahkan",Toast.LENGTH_LONG).show();
                    }
                });
    }
    public  boolean checkValidation() {
        String alamat1 = alamat.getText().toString().trim();
        String namatempat1 = namatempat.getText().toString().trim();
        String notelp1 = notelp.getText().toString().trim();


        if (alamat1.length() <= 0) {
            alamat.requestFocus();
            alamat.setError("Isi Alamat Tempat");
            return false;
        }else   if (namatempat1.length() <= 0) {
            namatempat.requestFocus();
            namatempat.setError("Isi Nama Tempat");
            return false;
        }else  if (notelp1.length() <0) {
            notelp.requestFocus();
            notelp.setError("Isi Nomor Telepon Tempat");
            return false;
        }
        else{
            return true;
        }
    }
}