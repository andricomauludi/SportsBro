package com.example.cobaskripsi.PengelolaUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.LoginActivity;
import com.example.cobaskripsi.PengelolaUI.datalapangan.datalapanganmitra;
import com.example.cobaskripsi.R;
import com.example.cobaskripsi.preferences;

public class HomemitraActivity extends AppCompatActivity {

    Button datalapangan,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homemitra);

        getSupportActionBar().hide();

        datalapangan=findViewById(R.id.datalapanganmitra);

        datalapangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomemitraActivity.this, datalapanganmitra.class));
            }
        });
    }

    public void logout(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        preferences.clearData(this);
        finish();
    }
}