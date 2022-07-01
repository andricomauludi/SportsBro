package com.example.cobaskripsi.AdminUI.profileadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.LoginActivity;
import com.example.cobaskripsi.R;
import com.example.cobaskripsi.preferences;

public class ProfileAdmin extends AppCompatActivity {


    TextView namaadmin, notelpadmin, emailadmin;
    Button logoutt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_admin);

        namaadmin = findViewById(R.id.namaadminprofile);
        notelpadmin= findViewById(R.id.nomortelpadminprofile);
        emailadmin= findViewById(R.id.emailadminprofile);
        logoutt=findViewById(R.id.logoutadmin);

        namaadmin.setText(preferences.getUsername(ProfileAdmin.this));
        notelpadmin.setText("mantab");
        emailadmin.setText("mantab");

        logoutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileAdmin.this, LoginActivity.class));
                preferences.clearData(ProfileAdmin.this);
                finish();
            }
        });
    }
}