package com.example.cobaskripsi.PengelolaUI.datatempatmitra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;

public class editdatatempatmitra extends AppCompatActivity {

    String idtempat,namatempat,jenisolahraga,alamat,marker,notelp;
    EditText namatempatedit,jenisolahragaedit,alamatedit,markeredit,notelpedit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdatatempatmitra);

        Intent intent = getIntent();
        idtempat = intent.getStringExtra("idtempat");
        namatempat = intent.getStringExtra("namatempat");
        jenisolahraga = intent.getStringExtra("jenisolahraga");
        alamat = intent.getStringExtra("alamat");
        marker = intent.getStringExtra("marker");
        notelp = intent.getStringExtra("notelp");

        namatempatedit=findViewById(R.id.namatempateditdatatempatmitra);
        alamatedit=findViewById(R.id.alamattempateditdatatempatmitra);
        notelpedit=findViewById(R.id.nomortelpeditdatatempatmitra);

        namatempatedit.setText(namatempat);
        alamatedit.setText(alamat);
        notelpedit.setText(notelp);




    }
}