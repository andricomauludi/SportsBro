package com.example.cobaskripsi.AdminUI.datatempat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;

public class DetailDataTempat extends AppCompatActivity {

    TextView namatempat, jenisolahraga, alamattempat, mitra, notelp;
    String namatempat1, jenisolahraga1, alamattempat1, mitra1, notelp1;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_tempat);

        namatempat=findViewById(R.id.namatempatdetaildatatempat);
        jenisolahraga=findViewById(R.id.jenisolahragadetaildatatempat);
        alamattempat=findViewById(R.id.alamattempatdetaildatatempat);
        mitra=findViewById(R.id.mitradetaildatatempat);
        notelp = findViewById(R.id.nomortelptempatdetaildatatempat);
        back=findViewById(R.id.backdatatempat);

        Intent intent = getIntent();
        namatempat1 = intent.getStringExtra("NAMATEMPAT");
        jenisolahraga1 = intent.getStringExtra("JENISOLAHRAGA");
        alamattempat1 = intent.getStringExtra("ALAMAT");
        mitra1 = intent.getStringExtra("MITRA");
        notelp1 = intent.getStringExtra("NOTELP");

        namatempat.setText(namatempat1);
        jenisolahraga.setText(jenisolahraga1);
        alamattempat.setText(alamattempat1);
        mitra.setText(mitra1);
        notelp.setText(notelp1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailDataTempat.this, DataTempat.class));
                finish();
            }
        });



    }
    public void onBackPressed(){
    }
}