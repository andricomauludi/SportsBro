package com.example.cobaskripsi.AdminUI.datatempat;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;

import java.util.ArrayList;

public class addtempat extends AppCompatActivity {
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Tambah Tempat");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtempat);
        spinner=findViewById(R.id.spinnerjenisolahraga);

        ArrayList<String> spinnerArray = new ArrayList<String>(24);
        spinnerArray.add("basket");
        spinnerArray.add("tenis");
        spinnerArray.add("badminton");
        spinnerArray.add("futsal");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}