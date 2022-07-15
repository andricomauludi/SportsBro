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

        namaadmin.setText(preferences.getUsername(ProfileAdmin.this));
        notelpadmin.setText(preferences.getNotelp(ProfileAdmin.this));
        emailadmin.setText(preferences.getEmail(ProfileAdmin.this));

    }

    public void logoutadmin(View view){
        preferences.clearData(ProfileAdmin.this);
        Intent intent=new Intent(ProfileAdmin.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }
}