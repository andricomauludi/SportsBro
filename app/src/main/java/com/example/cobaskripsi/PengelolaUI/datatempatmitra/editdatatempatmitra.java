package com.example.cobaskripsi.PengelolaUI.datatempatmitra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class editdatatempatmitra extends AppCompatActivity {

    String idtempat,notelp;
    EditText notelpedit;
    DatabaseReference reference;
    String text;
    Button submit, back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdatatempatmitra);

        Intent intent = getIntent();
        idtempat = intent.getStringExtra("idtempat");
        notelp = intent.getStringExtra("notelp");
        reference = FirebaseDatabase.getInstance().getReference("tempat");

        notelpedit=findViewById(R.id.nomortelpeditdatatempatmitra);
        submit=findViewById(R.id.savedatatempatmitra);
        back=findViewById(R.id.backdatatempatmitra);

        text = notelp.replace("+62", "");
        notelpedit.setText(text);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataTempat();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(editdatatempatmitra.this,datatempatmitra.class));
                finish();
            }
        });








    }

    public void updateDataTempat(){
        if (isNotelpChanged()){
            Toast.makeText(this, "Nomor telepon sudah diperbaharui",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, datatempatmitra.class));
            finish();
        }
        else {
            Toast.makeText(this, "Nomor telepon tidak berubah",Toast.LENGTH_LONG).show();
        }

    }

    private boolean isNotelpChanged() {
        if (!notelp.equals(notelpedit.getText().toString())){
            reference.child(idtempat).child("notelptempat").setValue("+62"+notelpedit.getText().toString());
            return true;
        }
        else{
            return false;
        }
    }
}