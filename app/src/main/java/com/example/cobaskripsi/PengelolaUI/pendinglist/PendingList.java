package com.example.cobaskripsi.PengelolaUI.pendinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.PengelolaUI.HomemitraActivity;
import com.example.cobaskripsi.R;

public class PendingList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Pending List");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportFragmentManager().beginTransaction().replace(R.id.wrapperpendinglist,new RecPendingList()).commit();

    }

    public void onBackPressed(){
        startActivity(new Intent(PendingList.this, HomemitraActivity.class));
        finish();
    }

}