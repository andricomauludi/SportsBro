package com.example.cobaskripsi.AdminUI.datatempat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;

import java.util.ArrayList;

public class editdatatempat extends AppCompatActivity {

    Spinner spinner;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdatatempat);

        spinner=findViewById(R.id.spinnereditdatatempat);
        back=findViewById(R.id.backdatatempat);

        ArrayList<String> spinnerArray = new ArrayList<String>();
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(editdatatempat.this,DataTempat.class));
                finish();
            }
        });
    }

    public void onBackPressed(){

    }
}