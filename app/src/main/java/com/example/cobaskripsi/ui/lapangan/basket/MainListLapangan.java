package com.example.cobaskripsi.ui.lapangan.basket;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;

public class MainListLapangan extends AppCompatActivity {
    public String jenisolahraga;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {

            } else {
                jenisolahraga= extras.getString("JENISOLAHRAGA");
            }
        } else {
            jenisolahraga= (String) savedInstanceState.getSerializable("JENISOLAHRAGA");

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_basket);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new RecListLapangan()).commit();
    }
}