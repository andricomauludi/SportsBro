package com.example.cobaskripsi.AdminUI.profileadmin;

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
import com.example.cobaskripsi.preferences;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileAdmin extends AppCompatActivity {


    TextView namaadmin, notelpadmin, emailadmin;
    Button logout, tentang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_admin);

        namaadmin = findViewById(R.id.namaadminprofile);
        notelpadmin= findViewById(R.id.nomortelpadminprofile);
        emailadmin= findViewById(R.id.emailadminprofile);
        tentang=findViewById(R.id.tentangaplikasiadmin);
        logout=findViewById(R.id.logoutadmin);

        namaadmin.setText(preferences.getUsername(ProfileAdmin.this));
        notelpadmin.setText(preferences.getNotelp(ProfileAdmin.this));
        emailadmin.setText(preferences.getEmail(ProfileAdmin.this));

        tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileAdmin.this, TentangAplikasiAdmin.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(logout.getContext());
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
                        preferences.clearData(ProfileAdmin.this);
                        FirebaseAuth.getInstance().signOut();
                        Intent intent=new Intent(ProfileAdmin.this,LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                });


                builder.show();

            }
        });

    }


}