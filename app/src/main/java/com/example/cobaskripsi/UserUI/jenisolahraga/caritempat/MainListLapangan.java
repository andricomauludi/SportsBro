package com.example.cobaskripsi.UserUI.jenisolahraga.caritempat;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.preferences;

public class MainListLapangan extends AppCompatActivity{

    public String jenisolahraga, jenisolahraga1;

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

        jenisolahraga1=preferences.getJenisolahraga(MainListLapangan.this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tempat);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new RecListLapangan()).commit();
    }
}