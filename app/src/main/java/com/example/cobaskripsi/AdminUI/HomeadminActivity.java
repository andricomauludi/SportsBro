package com.example.cobaskripsi.AdminUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.AdminUI.datatempat.DataTempat;
import com.example.cobaskripsi.AdminUI.userlist.UserList;
import com.example.cobaskripsi.R;

public class HomeadminActivity extends AppCompatActivity {

    Button datatempat,userlist,profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeadmin);

        getSupportActionBar().hide();

        datatempat=findViewById(R.id.datatempatadmin);
        userlist=findViewById(R.id.userlistadmin);
        profile =findViewById(R.id.profiladmin);

        datatempat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeadminActivity.this, DataTempat.class));
            }
        });

        userlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeadminActivity.this, UserList.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}