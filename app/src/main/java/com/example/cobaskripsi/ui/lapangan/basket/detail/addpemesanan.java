package com.example.cobaskripsi.ui.lapangan.basket.detail;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.ui.lapangan.basket.MainListLapangan;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class addpemesanan extends AppCompatActivity {

    EditText namapemesan,nomortelppemesan;
    TextView tanggalpemesanan;
    Button pesan,cancel,lihattanggal;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpemesanan);

        tanggalpemesanan = (TextView) findViewById(R.id.tanggalpemesanan);
        dateFormatter = new SimpleDateFormat("dd-mm-yyyy");
        Button lihattanggal = (Button)findViewById(R.id.lihattanggal);

        lihattanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });


        namapemesan=(EditText)findViewById(R.id.namapemesan);
        nomortelppemesan=(EditText)findViewById(R.id.nomortelppemesanan);

        pesan=(Button)findViewById(R.id.pesan);
        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processInsert();
            }
        });


        cancel=(Button)findViewById(R.id.batal);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainListLapangan.class));
                finish();
            }
        });
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year,month,dayOfMonth);
                tanggalpemesanan.setText(dateFormatter.format(newDate.getTime()));
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void processInsert() {

        Map<String,Object> map=new HashMap<>();
        map.put("namapemesan",namapemesan.getText().toString());
        map.put("nomortelppemesan",nomortelppemesan.getText().toString());
        map.put("tanggalpemesanan",tanggalpemesanan.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("pemesanan").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        namapemesan.setText("");
                        nomortelppemesan.setText("");
                        Toast.makeText(getApplicationContext(), "inserted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        namapemesan.setText("");
                        nomortelppemesan.setText("");
                        Toast.makeText(getApplicationContext(), "could not insert", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}