package com.example.cobaskripsi.AdminUI.userlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.PengelolaUI.datalapangan.datalapanganmitra;
import com.example.cobaskripsi.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EditUserList extends AppCompatActivity {

    EditText username,email,notelp;
    String username1,email1,notelp1,role1,rolebaru,iduser;
    Button back;
    Spinner spinnerrole;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_list);


        username=findViewById(R.id.namauseredituserlist);
        email=findViewById(R.id.emailedituserlist);
        notelp=findViewById(R.id.notelpedituserulist);
        back=findViewById(R.id.backedituserlist);
        reference = FirebaseDatabase.getInstance().getReference("user");

        Intent intent = getIntent();
        username1 = intent.getStringExtra("NAMAUSER");
        email1 = intent.getStringExtra("EMAIL");
        notelp1 = intent.getStringExtra("NOTELP");
        role1 = intent.getStringExtra("ROLE");
        iduser = intent.getStringExtra("IDUSER");

        username.setText(username1);
        email.setText(email1);
        notelp.setText(notelp1);


        ArrayList<String> spinnerArray = new ArrayList<String>(24);
        spinnerArray.add("pelanggan");
        spinnerArray.add("mitra");
        spinnerArray.add("admin");

        spinnerrole=findViewById(R.id.spinneredituser);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinnerrole.setAdapter(spinnerArrayAdapter);

        spinnerrole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String roleterpilih = spinnerArray.get(position);
                rolebaru = roleterpilih;
                Toast.makeText(EditUserList.this, rolebaru, Toast.LENGTH_SHORT).show();
    }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditUserList.this,UserList.class));
                finish();
            }
        });
    }

    public void updateuser(View view){
        if (isUsernameChanged() || isEmailChanged() || isNoTelpChanged()){
            Toast.makeText(this, "Data sudah diperbaharui",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, datalapanganmitra.class));
            finish();
        }
        else {
            Toast.makeText(this, "Data tidak ada yang berubah",Toast.LENGTH_LONG).show();
        }

    }

    private boolean isEmailChanged() {
        if (!email1.equals(email.getText().toString())){
            reference.child(iduser).child("emailuser").setValue(email.getText().toString());
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isNoTelpChanged() {
        if (!notelp1.equals(notelp.getText().toString())){
            reference.child(iduser).child("nomortelpuser").setValue(notelp.getText().toString());
            return true;
        }
        else{
            return false;
        }

    }

    private boolean isUsernameChanged() {
        if (!username1.equals(username.getText().toString())){
            reference.child(iduser).child("username").setValue(username.getText().toString());
            return true;
        }
        else{
            return false;
        }
    }


    public void onBackPressed(){

    }
}