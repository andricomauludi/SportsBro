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

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.AdminUI.HomeadminActivity;
import com.example.cobaskripsi.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class editdatatempat extends AppCompatActivity {

    Spinner spinner;
    Button submit,back;
    String jenisolahraga,namatempat1,jenisolahraga1,alamattempat1,notelp1,marker1,idtempat, text;
    EditText namatempat,alamat,notelp,marker;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdatatempat);

        spinner=findViewById(R.id.spinnereditdatatempat);
        namatempat = findViewById(R.id.editnamatempat);
        alamat = findViewById(R.id.editalamattempat);
        notelp = findViewById(R.id.editnotelptempat);
        marker = findViewById(R.id.editmarkerlokasi);
        submit = findViewById(R.id.submiteditdatatempat);
        back=findViewById(R.id.backeditdatatempatadmin);

        reference = FirebaseDatabase.getInstance().getReference("tempat");

        Intent intent = getIntent();
        idtempat = intent.getStringExtra("IDTEMPAT");
        namatempat1 = intent.getStringExtra("NAMATEMPAT");
        jenisolahraga1 = intent.getStringExtra("JENISOLAHRAGA");
        alamattempat1 = intent.getStringExtra("ALAMAT");
        notelp1 = intent.getStringExtra("NOTELP");
        marker1 = intent.getStringExtra("MARKER");

        text = notelp1.replace("+62", "");

        namatempat.setText(namatempat1);
        alamat.setText(alamattempat1);
        notelp.setText(text);
        marker.setText(marker1);


        ArrayList<String> spinnerArray = new ArrayList<String>();
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTempat();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(editdatatempat.this,DataTempat.class));
                finish();
            }
        });
    }

    public void updateTempat(){
        if (isNamaTempatchanged() || isAlamatChanged() || isNoTelpChanged() || isMarkerChanged()){
            Toast.makeText(this, "Data sudah diperbaharui",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, HomeadminActivity.class));
            finish();
        }
        else {
            Toast.makeText(this, "Data tidak ada yang berubah",Toast.LENGTH_LONG).show();
        }
    }

    private boolean isMarkerChanged() {
        if (!marker1.equals(marker.getText().toString())){
            reference.child(idtempat).child("marker").setValue(marker.getText().toString());
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isNoTelpChanged() {
        if (!notelp1.equals(notelp.getText().toString())){
            reference.child(idtempat).child("notelptempat").setValue("+62"+notelp.getText().toString());
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isAlamatChanged() {
        if (!alamattempat1.equals(alamat.getText().toString())){
            reference.child(idtempat).child("alamattempat").setValue(alamat.getText().toString());
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isNamaTempatchanged() {
        if (!namatempat1.equals(namatempat.getText().toString())){
            reference.child(idtempat).child("namatempat").setValue(namatempat.getText().toString());
            return true;
        }
        else{
            return false;
        }
    }

    public void onBackPressed(){

    }
}