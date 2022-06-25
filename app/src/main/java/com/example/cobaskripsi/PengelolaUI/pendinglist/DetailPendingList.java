package com.example.cobaskripsi.PengelolaUI.pendinglist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailPendingList extends AppCompatActivity {

    TextView namapemesan,timestamp,namalapangan,tanggalpemesanan,waktupemesanan,statuspemesanan;
    String namapemesan1,timestamp1,namalapangan1,tanggalpemesanan1,waktupemesanan1,statuspemesanan1,idpemesanan1;
    Button tolak, terima, back;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pending_list);

        namapemesan = findViewById(R.id.namapemesandetailpendinglist);
        timestamp = findViewById(R.id.timestampdetailpendinglist);
        namalapangan = findViewById(R.id.namalapangandetailpendinglist);
        tanggalpemesanan = findViewById(R.id.tanggalpemesanandetailpendinglist);
        waktupemesanan = findViewById(R.id.waktupemesanandetailpendinglist);
        statuspemesanan= findViewById(R.id.statuspemesanandetailpendinglist);


        tolak = findViewById(R.id.tolakpendinglist);
        terima = findViewById(R.id.terimapendinglist);
        back = findViewById(R.id.backpendinglist);

        Intent intent = getIntent();
        namapemesan1 = intent.getStringExtra("NAMAPEMESAN");
        timestamp1 = intent.getStringExtra("TIMESTAMP");
        namalapangan1 = intent.getStringExtra("NAMALAPANGAN");
        tanggalpemesanan1 = intent.getStringExtra("TANGGALPEMESANAN");
        waktupemesanan1 = intent.getStringExtra("WAKTUPEMESANAN");
        statuspemesanan1 = intent.getStringExtra("STATUSPEMESANAN");
        idpemesanan1 = intent.getStringExtra("IDPEMESANAN");

        namapemesan.setText(namapemesan1);
        timestamp .setText(timestamp1);
        namalapangan .setText(namalapangan1);
        tanggalpemesanan .setText(tanggalpemesanan1);
        waktupemesanan.setText(waktupemesanan1);

        if(statuspemesanan1.equals("Menunggu Konfirmasi")){
            statuspemesanan.setTextColor(Color.RED);
            statuspemesanan.setText(statuspemesanan1.substring(0, 1).toUpperCase() + statuspemesanan1.substring(1).toLowerCase());
        }else{
            statuspemesanan.setTextColor(Color.GREEN);
            statuspemesanan.setText(statuspemesanan1.substring(0, 1).toUpperCase() + statuspemesanan1.substring(1).toLowerCase());
        }

        tolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(DetailPendingList.this);
                builder.setMessage("Tolak Pemesanan ?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("pemesanan").child(idpemesanan1).child("statuspemesanan").setValue("Ditolak");
                        startActivity(new Intent(DetailPendingList.this, PendingList.class));
                        finish();
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
        terima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(DetailPendingList.this);
                builder.setMessage("Terima Pemesanan ?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("pemesanan").child(idpemesanan1).child("statuspemesanan").setValue("Booked");
                        startActivity(new Intent(DetailPendingList.this, PendingList.class));
                        finish();
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailPendingList.this, PendingList.class));
                finish();
            }
        });


    }

    public void onBackPressed(){

    }
}