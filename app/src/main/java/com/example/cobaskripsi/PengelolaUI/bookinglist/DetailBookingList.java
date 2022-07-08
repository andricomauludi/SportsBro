package com.example.cobaskripsi.PengelolaUI.bookinglist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DetailBookingList extends AppCompatActivity {

    TextView namapemesan,timestamp,namalapangan,tanggalpemesanan,waktupemesanan,statuspemesanan,notelp;
    String namapemesan1,timestamp1,namalapangan1,tanggalpemesanan1,waktupemesanan1,statuspemesanan1,idpemesanan1,notelp1;
    Button tolak, terima, back;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Detail Booking List");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_booking_list);

        namapemesan = findViewById(R.id.namapemesandetailbookinglist);
        timestamp = findViewById(R.id.timestampdetailbookinglist);
        namalapangan = findViewById(R.id.namalapangandetailbookinglist);
        tanggalpemesanan = findViewById(R.id.tanggalpemesanandetailbookinglist);
        waktupemesanan = findViewById(R.id.waktupemesanandetailbookinglist);
        statuspemesanan= findViewById(R.id.statuspemesanandetailbookinglist);
        notelp= findViewById(R.id.nomortelpdetailbookinglist);

        back = findViewById(R.id.backbookinglist);

        Intent intent = getIntent();
        namapemesan1 = intent.getStringExtra("NAMAPEMESAN");
        timestamp1 = intent.getStringExtra("TIMESTAMP");
        namalapangan1 = intent.getStringExtra("NAMALAPANGAN");
        tanggalpemesanan1 = intent.getStringExtra("TANGGALPEMESANAN");
        waktupemesanan1 = intent.getStringExtra("WAKTUPEMESANAN");
        statuspemesanan1 = intent.getStringExtra("STATUSPEMESANAN");
        idpemesanan1 = intent.getStringExtra("IDPEMESANAN");
        notelp1 = intent.getStringExtra("NOMORTELP");

        String sudahdipesan;
        String simpen;
        ArrayList<String> simpenlist = new ArrayList();
        sudahdipesan = (waktupemesanan1);
        String sudahdipesanbaru = String.valueOf(sudahdipesan.replaceAll("[\\[\\]\\(\\)]", ""));
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(sudahdipesanbaru.split(",")));
        for (int i=0; i<myList.size();i++){
            simpen=myList.get(i);
            simpenlist.add(jamdictionary(simpen));
        }

        namapemesan.setText(namapemesan1);
        timestamp .setText(timestamp1);
        namalapangan .setText(namalapangan1);
        tanggalpemesanan .setText(tanggalpemesanan1);
        waktupemesanan.setText(simpenlist.toString());
        notelp.setText(notelp1);

        if(statuspemesanan1.equals("Menunggu Konfirmasi")){
            statuspemesanan.setTextColor(Color.RED);
            statuspemesanan.setText(statuspemesanan1.substring(0, 1).toUpperCase() + statuspemesanan1.substring(1).toLowerCase());
        }else{
            statuspemesanan.setTextColor(Color.GREEN);
            statuspemesanan.setText(statuspemesanan1.substring(0, 1).toUpperCase() + statuspemesanan1.substring(1).toLowerCase());
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailBookingList.this, BookingList.class));
                finish();
            }
        });


    }

    private String jamdictionary(String str){
        Map<String, String> m = new HashMap<>();
        m.put("0","07:00 - 08:00");
        m.put("1","08:00 - 09:00");
        m.put("2","09:00 - 10:00");
        m.put("3","10:00 - 11:00");
        m.put("4","11:00 - 12:00");
        m.put("5","12:00 - 13:00");
        m.put("6","13:00 - 14:00");
        m.put("7","14:00 - 15:00");
        m.put("8","15:00 - 16:00");
        m.put("9","16:00 - 17:00");
        m.put("10","17:00 - 18:00");
        m.put("11","18:00 - 19:00");
        m.put("12","19:00 - 20:00");
        m.put("13","20:00 - 21:00");
        m.put("14","21:00 - 22:00");
        m.put("15","22:00 - 23:00");
        m.put("16","23:00 - 24:00");
        m.put("17","24:00 - 01:00");
        m.put("18","01:00 - 02:00");
        m.put("19","02:00 - 03:00");
        m.put("20","03:00 - 04:00");
        m.put("21","04:00 - 05:00");
        m.put("22","05:00 - 06:00");
        m.put("23","06:00 - 07:00");
        String dict = m.get(str);
        return dict;
    }

    public void onBackPressed(){

    }
}