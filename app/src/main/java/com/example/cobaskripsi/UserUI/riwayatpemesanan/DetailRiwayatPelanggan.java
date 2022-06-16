package com.example.cobaskripsi.UserUI.riwayatpemesanan;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.Homepelanggan;

public class DetailRiwayatPelanggan extends AppCompatActivity {
    String namapemesan,
            nomortelppemesan,
            tanggalpemesanan,
            timestamp,
            jenisolahraga,
            namatempat,
            namalapangan,
            statuspemesanan,
            waktupemesanan;

    TextView namapemesan1,nomortelppemesan1,tanggalpemesan1,timestamp1,jenisolahraga1,namatempat1,namalapangan1,statuspemesanan1, waktupemesanan1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailriwayatpengguna);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                namapemesan=null;
                nomortelppemesan=null;
                tanggalpemesanan=null;
                timestamp=null;
                jenisolahraga=null;
                namatempat=null;
                namalapangan=null;
                statuspemesanan=null;
                waktupemesanan=null;

            } else {
                namapemesan=extras.getString("NAMAPEMESAN");
                nomortelppemesan=extras.getString("NOTELPPEMESAN");
                tanggalpemesanan=extras.getString("TANGGALPEMESANAN");
                timestamp=extras.getString("TIMESTAMP");
                jenisolahraga=extras.getString("JENISOLAHRAGA");
                namatempat=extras.getString("NAMATEMPAT");
                namalapangan=extras.getString("NAMALAPANGAN");
                statuspemesanan=extras.getString("STATUSPEMESANAN");
                waktupemesanan=extras.getString("WAKTUPEMESANAN");
            }
        } else {
            namapemesan= (String) savedInstanceState.getSerializable("NAMAPEMESAN");
            nomortelppemesan= (String) savedInstanceState.getSerializable("NOTELPPEMESAN");
            tanggalpemesanan= (String) savedInstanceState.getSerializable("TANGGALPEMESANAN");
            timestamp= (String) savedInstanceState.getSerializable("TIMESTAMP");
            jenisolahraga= (String) savedInstanceState.getSerializable("JENISOLAHRAGA");
            namatempat= (String) savedInstanceState.getSerializable("NAMATEMPAT");
            namalapangan= (String) savedInstanceState.getSerializable("NAMALAPANGAN");
            //statuspemesanan= (String) savedInstanceState.getSerializable("STATUSPEMESANAN");
            waktupemesanan= (String) savedInstanceState.getSerializable("WAKTUPEMESANAN");
        }



        namapemesan1=(TextView)findViewById(R.id.namapemesandetailriwayat);
        nomortelppemesan1=(TextView)findViewById(R.id.nomortelpdetailriwayat);
        tanggalpemesan1=(TextView)findViewById(R.id.tanggalmaindetailriwayat);
        timestamp1=(TextView)findViewById(R.id.timestampdetailriwayat);
        jenisolahraga1=(TextView)findViewById(R.id.jenisolahragadetailriwayat);
        namatempat1=(TextView)findViewById(R.id.namatempatdetailriwayat);
        namalapangan1= (TextView)findViewById(R.id.pilihanlapangandetailriwayat);
        statuspemesanan1=(TextView)findViewById(R.id.status);
        waktupemesanan1= (TextView)findViewById(R.id.jammaindetailriwayat);

        namapemesan1.setText("Atas Nama Pemesan :    "+namapemesan.substring(0, 1).toUpperCase() + namapemesan.substring(1).toLowerCase());
        nomortelppemesan1.setText("Nomor HP Pemesan :   "+nomortelppemesan);
        tanggalpemesan1.setText("Tanggal Main :     "+tanggalpemesanan);
        timestamp1.setText("Tanggal Pesan :     "+timestamp);
        jenisolahraga1.setText("Jenis Olahraga :    "+jenisolahraga.substring(0, 1).toUpperCase() + jenisolahraga.substring(1).toLowerCase());
        namatempat1.setText(namatempat.substring(0, 1).toUpperCase() + namatempat.substring(1).toLowerCase());
        namalapangan1.setText("Jenis Lapangan :     "+namalapangan.substring(0, 1).toUpperCase() + namalapangan.substring(1).toLowerCase());

        if(statuspemesanan.equals("Menunggu Konfirmasi")){
           statuspemesanan1.setTextColor(Color.RED);
           statuspemesanan1.setText(statuspemesanan.substring(0, 1).toUpperCase() + statuspemesanan.substring(1).toLowerCase());
        }else{
            statuspemesanan1.setTextColor(Color.GREEN);
            statuspemesanan1.setText(statuspemesanan.substring(0, 1).toUpperCase() + statuspemesanan.substring(1).toLowerCase());
        }

        waktupemesanan1.setText("Jam Main :     "+waktupemesanan);
    }



    public void onBackPressed(){
        Intent intent = new Intent(DetailRiwayatPelanggan.this, Homepelanggan.class);
        startActivity(intent);
    }
}