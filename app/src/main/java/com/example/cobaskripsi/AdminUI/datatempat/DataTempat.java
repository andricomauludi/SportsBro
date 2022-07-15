package com.example.cobaskripsi.AdminUI.datatempat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.AdminUI.HomeadminActivity;
import com.example.cobaskripsi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DataTempat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_tempat);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportFragmentManager().beginTransaction().replace(R.id.wrapperdatatempat, new RecDataTempat()).commit();

        FloatingActionButton fab = findViewById(R.id.fabaddtempat);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), addtempat.class));
            }
        });

    }

    public void onBackPressed() {
        startActivity(new Intent(DataTempat.this, HomeadminActivity.class));
        finish();
    }
}