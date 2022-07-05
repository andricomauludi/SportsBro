package com.example.cobaskripsi.PengelolaUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.PengelolaUI.bookinglist.BookingList;
import com.example.cobaskripsi.PengelolaUI.datalapangan.datalapanganmitra;
import com.example.cobaskripsi.PengelolaUI.pendinglist.PendingList;
import com.example.cobaskripsi.PengelolaUI.profilemitra.ProfileMitra;
import com.example.cobaskripsi.R;

public class HomemitraActivity extends AppCompatActivity {

    Button datalapangan,logout, pendinglist,bookinglist, profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homemitra);

        getSupportActionBar().hide();

        datalapangan=findViewById(R.id.datalapanganmitra);
        pendinglist=findViewById(R.id.pendinglistmitra);
        bookinglist=findViewById(R.id.bookinglistmitra);
        profil =findViewById(R.id.profilmitra);


        datalapangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomemitraActivity.this, datalapanganmitra.class));
            }
        });

        pendinglist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomemitraActivity.this, PendingList.class));
            }
        });

        bookinglist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomemitraActivity.this, BookingList.class));
            }
        });

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomemitraActivity.this, ProfileMitra.class));
            }
        });

    }

}