package com.example.cobaskripsi.PengelolaUI.pendinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.PengelolaUI.HomemitraActivity;
import com.example.cobaskripsi.PengelolaUI.datalapangan.RecListDatalapangan;
import com.example.cobaskripsi.PengelolaUI.datalapangan.adddatalapangan;
import com.example.cobaskripsi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PendingList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportFragmentManager().beginTransaction().replace(R.id.wrapperpendinglist,new RecListDatalapangan()).commit();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), adddatalapangan.class));
            }
        });
    }
    public void onBackPressed(){
        startActivity(new Intent(getApplicationContext(), HomemitraActivity.class));
        finish();
    }
}