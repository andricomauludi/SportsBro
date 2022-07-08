package com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.detail;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.Homepelanggan;
import com.example.cobaskripsi.preferences;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class  addpemesanan extends AppCompatActivity {

    EditText namapemesan,nomortelppemesan;
    TextView namatempat,jenislapangan,jamtersedia, coba,tanggalpemesanan;
    Button pesan,cancel,lihattanggal;
    String sudahdipesan, nomortelppemesanfinal;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    String newString2,idtempat,newString3,idlapangan,newString4, waktupemesanan, idpemesan, username, jenisolahraga;
    String[] checkboxjam;
    private DatabaseReference mDatabase;
    String tanggalpemesananpilih;
    DatePickerDialog.OnDateSetListener setListener;
    public CheckBox[] addcheckbox;
    private ArrayList checkboxjam2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_addpemesanan);



        namatempat = (TextView)findViewById(R.id.namatempat);
        jenislapangan = (TextView)findViewById(R.id.jenislapangan);
        jamtersedia=(TextView)findViewById(R.id.jamtersedia);
        coba=(TextView)findViewById(R.id.coba);
        pesan=(Button)findViewById(R.id.pesan);
        pesan.setEnabled(false);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                idtempat=null;
                newString2= null;
                newString3= null;
                newString4= null;
                idlapangan=null;
                jenisolahraga=null;

            } else {
                idtempat= extras.getString("IDTEMPAT");
                newString2= extras.getString("NAMATEMPAT");
                newString3= extras.getString("JENISLAPANGAN");
                newString4= extras.getString("JAMTERSEDIA");
                idlapangan= extras.getString("IDLAPANGAN");
                jenisolahraga= extras.getString("JENISOLAHRAGA");
            }
        } else {
            idtempat= (String) savedInstanceState.getSerializable("IDTEMPAT");
            newString2= (String) savedInstanceState.getSerializable("NAMATEMPAT");
            newString3= (String) savedInstanceState.getSerializable("JENISLAPANGAN");
            newString4= (String) savedInstanceState.getSerializable("JAMTERSEDIA");
            idlapangan= (String) savedInstanceState.getSerializable("IDLAPANGAN");
            jenisolahraga= (String) savedInstanceState.getSerializable("JENISOLAHRAGA");
        }
        namatempat.setText(newString2);
        jenislapangan.setText(newString3);


        tanggalpemesanan = findViewById(R.id.tanggalpemesanan);




        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Button lihattanggal = (Button)findViewById(R.id.lihattanggal);

        lihattanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        addpemesanan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day +"/"+ month +"/"+year;
                        tanggalpemesanan.setText(date);
                        tanggalpemesananpilih=date;
                        String str = String.valueOf(newString4.replaceAll("[\\[\\]\\(\\)]", ""));
                        checkboxjam2 = new ArrayList<String>(Arrays.asList(str.split(",")));
                        compareData(date);
                        pesan.setEnabled(true);





                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();



                }


        });



        //dateFormatter = new SimpleDateFormat("dd-mm-yyyy");
       // Button lihattanggal = (Button)findViewById(R.id.lihattanggal);

       /** lihattanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });**/


        namapemesan=(EditText)findViewById(R.id.namapemesan);
        nomortelppemesan=(EditText)findViewById(R.id.nomortelppemesanan);

        checkboxjam = splitarray(newString4);







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
                onBackPressed();
                finish();
            }
        });
    }

    private void processInsert() {
        idpemesan= preferences.getUserID(getApplicationContext());
        username = preferences.getUsername(getApplicationContext());
        String key = FirebaseDatabase.getInstance().getReference().child("pemesanan").push().getKey();
        Map<String,Object> map=new HashMap<>();

       /**
        }**/
        //String waktupemesanan = arrcheck.toString();
        final ArrayList<String> arrcheck = new ArrayList<>();
        for(int i = 0; i < checkboxjam2.size(); i++) {
            if (addcheckbox[i].isChecked()) {
                arrcheck.add((String) addpemesanan.this.checkboxjam2.get(i));
            }}
        String str = arrcheck.toString();
        String noSpaceStr = str.replaceAll("\\s", "");
        waktupemesanan = noSpaceStr;

        String ts = Calendar.getInstance().getTime().toString();
        if(checkValidation()) {
            AlertDialog.Builder builder=new AlertDialog.Builder(pesan.getContext());
            builder.setTitle("Konfirmasi Pemesanan");
            builder.setMessage("Apakah anda sudah mengecek semua pesanan?");
            builder.setNegativeButton("Belum", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setPositiveButton("Sudah", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    map.put("namapemesan",namapemesan.getText().toString());
                    map.put("nomortelppemesan",nomortelppemesan.getText().toString());
                    map.put("tanggalpemesanan",tanggalpemesanan.getText().toString());
                    map.put("namalapangan",jenislapangan.getText().toString());
                    map.put("namatempat",namatempat.getText().toString());
                    map.put("idlapangan",idlapangan);
                    map.put("idtempat",idtempat);
                    map.put("waktupemesanan",waktupemesanan);
                    map.put("username",username);
                    map.put("iduser",idpemesan);
                    map.put("jenisolahraga",jenisolahraga);
                    map.put("timestamp",ts);
                    map.put("statuspemesanan","Menunggu Konfirmasi");
                    map.put("idpemesanan",key);

                    FirebaseDatabase.getInstance().getReference().child("pemesanan").child(key)
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
                    startActivity(new Intent(getApplicationContext(), Homepelanggan.class));
                    finish();
                }
            });
            builder.show();
            }

        }


    public  boolean checkValidation() {
        String namapemesan1 = namapemesan.getText().toString().trim();
        String nomortelppemesan1 = nomortelppemesan.getText().toString().trim();
        String tanggalpemesan1 = tanggalpemesanan.getText().toString().trim();

        boolean atLeastOneChecked = false;
        for (int i = 0; i < checkboxjam2.size(); i++){
            if (addcheckbox[i].isChecked()) {
                atLeastOneChecked = true;
                break;
            }
        }

        String regexp = "^.{11,13}$"; //your regexp here



        if (namapemesan1.length() <= 0) {
            namapemesan.requestFocus();
            namapemesan.setError("Isi Nama");
            return false;
        } else if (nomortelppemesan1.length() <= 10 || nomortelppemesan1.length() > 13 ) {
            nomortelppemesan.requestFocus();
            nomortelppemesan.setError("Isi Nomor Telp yang Valid");
            return false;

        } else if (tanggalpemesan1.length() <= 0) {
            tanggalpemesanan.requestFocus();
            tanggalpemesanan.setError("Tanggal belum dipilih");
            return false;
        }else if (!atLeastOneChecked){
            Toast.makeText(addpemesanan.this,
                    "Tidak ada tanggal yang terpilih", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }

    private String[] splitarray(String str){
        String[] strArray= null;
        strArray = str.split(",");
        return strArray;
    }


    private void compareData(String date){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("pemesanan")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()){
                            PemesananModel pemesananModel = childSnapshot.getValue(PemesananModel.class);


                            if (pemesananModel.getIdtempat().contains(idtempat) &&
                                    pemesananModel.getIdlapangan().contains(idlapangan) &&
                                    pemesananModel.getTanggalpemesanan().contains(date) && (pemesananModel.getStatuspemesanan().contains("Booked")
                            || pemesananModel.getStatuspemesanan().contains("Menunggu Konfirmasi"))
                            ){
                                sudahdipesan = (pemesananModel.getWaktupemesanan());
                                String sudahdipesanbaru = String.valueOf(sudahdipesan.replaceAll("[\\[\\]\\(\\)]", ""));
                                ArrayList<String> myList = new ArrayList<String>(Arrays.asList(sudahdipesanbaru.split(",")));

                                java.util.List<String> needToRemove = new ArrayList<>();
                                for (int i = 0; i < checkboxjam2.size(); i++) {
                                    if (myList.contains(checkboxjam2.get(i))) {
                                        needToRemove.add((String) addpemesanan.this.checkboxjam2.get(i));
                                    }
                                }
                                checkboxjam2.removeAll(needToRemove);
                                checkboxInsert(checkboxjam2);
                                if (checkboxjam2.size()==0){
                                    jamtersedia.setText("Lapangan penuh untuk "+date+", mohon cari waktu lain");
                                }
                                else{
                                    jamtersedia.setText("Jam Tersedia");
                                }
                                //coba.setText(String.valueOf(checkboxjam2.size()));

                            }else {
                                checkboxInsert(checkboxjam2);
                                if (checkboxjam2.size()==0){
                                    jamtersedia.setText("Lapangan penuh untuk "+date+", mohon cari waktu lain");
                                }
                                else{
                                    jamtersedia.setText("Jam Tersedia");
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


    private void checkboxInsert(ArrayList checkboxjam2){
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.rootlayout);
        LinearLayout row = new LinearLayout(this);

        addcheckbox = new CheckBox[checkboxjam2.size()];
        linearLayout.removeAllViews();
        if(linearLayout!=null){
            for(int i = 0; i < checkboxjam2.size(); i++) {
                CheckBox cb = new CheckBox(getApplicationContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.setMargins(10,15,10,10);
                cb.setLayoutParams(params);
                String str = jamdictionary((String) checkboxjam2.get(i));
                cb.setText(str);
                // cb.setBackgroundColor(getResources().getColor(R.color.white));
                cb.setTextColor(getResources().getColor(R.color.darkteal));
                cb.setScaleX((float) 1.3);
                cb.setScaleY((float) 1.3);
                cb.setId(i);
                linearLayout.addView(cb);


                String abc =String.valueOf(i);

                int resID = getResources().getIdentifier(abc, "id", this.getPackageName());
                addcheckbox[i] = (CheckBox) findViewById(resID );
            }
        }
    }

    private String jamdictionary(String str){
        Map<String, String> m = new HashMap<>();
        m.put("0","07:00 - 08:00");
        m.put("1","08:00 - 09:00");
        m.put("2","09:00 - 10:00");
        m.put("3","10:00 - 11:00");
        m.put("4","11:00 - 12:00");
        m.put("5","12:00 - 13:00");
        m.put("6","13:00 - 14:00");
        m.put("7","14:00 - 15:00");
        m.put("8","15:00 - 16:00");
        m.put("9","16:00 - 17:00");
        m.put("10","17:00 - 18:00");
        m.put("11","18:00 - 19:00");
        m.put("12","19:00 - 20:00");
        m.put("13","20:00 - 21:00");
        m.put("14","21:00 - 22:00");
        m.put("15","22:00 - 23:00");
        m.put("16","23:00 - 24:00");
        m.put("17","24:00 - 01:00");
        m.put("18","01:00 - 02:00");
        m.put("19","02:00 - 03:00");
        m.put("20","03:00 - 04:00");
        m.put("21","04:00 - 05:00");
        m.put("22","05:00 - 06:00");
        m.put("23","06:00 - 07:00");
        String dict = m.get(str);
        return dict;
    }
}