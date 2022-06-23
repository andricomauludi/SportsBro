package com.example.cobaskripsi.PengelolaUI.datalapangan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.PengelolaUI.HomemitraActivity;
import com.example.cobaskripsi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;

public class datalapanganmitra extends AppCompatActivity {
    DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_datalapanganmitra);


        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportFragmentManager().beginTransaction().replace(R.id.wrapperdatalapangan,new RecListDatalapangan()).commit();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),adddatalapangan.class));
            }
        });
    }
    public void onBackPressed(){
        startActivity(new Intent(getApplicationContext(), HomemitraActivity.class));
        finish();
    }
}