package com.example.cobaskripsi.PengelolaUI.profilemitra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.LoginActivity;
import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.profilepelanggan.TentangAplikasiPelanggan;
import com.example.cobaskripsi.preferences;

public class ProfileMitra extends AppCompatActivity {

    TextView namamitra, notelpmitra, emailmitra;
    Button logoutt,panduan,tentangaplikasi, pelayananpelanggan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_mitra);

        namamitra = findViewById(R.id.namamitraprofile);
        notelpmitra= findViewById(R.id.nomortelpmitraprofile);
        emailmitra= findViewById(R.id.emailmitraprofile);
        logoutt=findViewById(R.id.logoutmitra);
        panduan=findViewById(R.id.panduanketentuanmitra);
        tentangaplikasi=findViewById(R.id.tentangaplikasimitra);
        pelayananpelanggan=findViewById(R.id.pelayananpelangganmitra);

        namamitra.setText(preferences.getUsername(ProfileMitra.this));
        notelpmitra.setText(preferences.getNotelp(ProfileMitra.this));
        emailmitra.setText(preferences.getEmail(ProfileMitra.this));

        logoutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(logoutt.getContext());
                builder.setTitle("Logout");
                builder.setMessage("Anda yakin ingin logout?");


                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        preferences.clearData(ProfileMitra.this);
                        Intent intent=new Intent(ProfileMitra.this,LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        ProfileMitra.this.finish();
                    }
                });


                builder.show();

            }
        });

        panduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileMitra.this, PanduanAplikasiMitra.class));
            }
        });

        tentangaplikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileMitra.this, TentangAplikasiMitra.class));
            }
        });

        pelayananpelanggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileMitra.this, PelayananPelangganMitra.class));
            }
        });
    }

}