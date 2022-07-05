package com.example.cobaskripsi.PengelolaUI.profilemitra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.LoginActivity;
import com.example.cobaskripsi.R;
import com.example.cobaskripsi.preferences;

public class ProfileMitra extends AppCompatActivity {

    TextView namamitra, notelpmitra, emailmitra;
    Button logoutt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_mitra);

        namamitra = findViewById(R.id.namamitraprofile);
        notelpmitra= findViewById(R.id.nomortelpmitraprofile);
        emailmitra= findViewById(R.id.emailmitraprofile);
        logoutt=findViewById(R.id.logoutmitra);

        namamitra.setText(preferences.getUsername(ProfileMitra.this));
        notelpmitra.setText("mantab");
        emailmitra.setText("mantab");

        logoutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.clearData(ProfileMitra.this);
                Intent intent=new Intent(ProfileMitra.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                ProfileMitra.this.finish();
            }
        });
    }

}