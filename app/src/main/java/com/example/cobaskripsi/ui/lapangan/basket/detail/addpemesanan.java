package com.example.cobaskripsi.ui.lapangan.basket.detail;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.Home;
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
    TextView tanggalpemesanan,namatempat,jenislapangan,jamtersedia;
    Button pesan,cancel,lihattanggal;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    String newString2,idtempat,newString3,idlapangan,newString4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpemesanan);



        namatempat = (TextView)findViewById(R.id.namatempat);
        jenislapangan = (TextView)findViewById(R.id.jenislapangan);
        jamtersedia=(TextView)findViewById(R.id.jamtersedia);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                idtempat=null;
                newString2= null;
                newString3= null;
                newString4= null;
                idlapangan=null;

            } else {
                idtempat= extras.getString("IDTEMPAT");
                newString2= extras.getString("NAMATEMPAT");
                newString3= extras.getString("JENISLAPANGAN");
                newString4= extras.getString("JAMTERSEDIA");
                idlapangan= extras.getString("IDLAPANGAN");
            }
        } else {
            idtempat= (String) savedInstanceState.getSerializable("IDTEMPAT");
            newString2= (String) savedInstanceState.getSerializable("NAMATEMPAT");
            newString3= (String) savedInstanceState.getSerializable("JENISLAPANGAN");
            newString4= (String) savedInstanceState.getSerializable("JAMTERSEDIA");
            idlapangan= (String) savedInstanceState.getSerializable("IDLAPANGAN");
        }
        namatempat.setText(newString2);
        jenislapangan.setText(newString3);
        jamtersedia.setText(newString4);

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

        LinearLayout linearLayout = findViewById(R.id.rootlayout);
        if(linearLayout!=null){
            for(int i = 0; i < 20; i++) {
                CheckBox cb = new CheckBox(getApplicationContext());
                cb.setText("I'm dynamic!");
                cb.setTextSize(12 sp);
               // cb.setBackgroundColor(getResources().getColor(R.color.white));
                cb.setTextColor(getResources().getColor(R.color.white));
                linearLayout.addView(cb);
            }
        }

        pesan=(Button)findViewById(R.id.pesan);
        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processInsert();
                startActivity(new Intent(getApplicationContext(), Home.class));
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
        map.put("idlapangan",idlapangan);
        map.put("idtempat",idtempat);
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