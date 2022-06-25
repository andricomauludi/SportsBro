package com.example.cobaskripsi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText username,password,email,notelp;
    Button registers,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        email=findViewById(R.id.emailuser);
        notelp=findViewById(R.id.nomortelpuser);
        registers=findViewById(R.id.register1);
        cancel=findViewById(R.id.cancel);

        registers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processInsert();
            }
        });


    }

    public  boolean checkValidation() {
        String username1 = username.getText().toString().trim();
        String password1 = password.getText().toString().trim();
        String email1 = email.getText().toString().trim();
        String notelp1 = notelp.getText().toString().trim();


        if (username1.length() <= 0) {
            username.requestFocus();
            username.setError("Isi Nama");
            return false;
        } else if (password1.length() <= 0) {
            password.requestFocus();
            password.setError("Isi password");
            return false;

        } else if (email1.length() <= 0) {
            email.requestFocus();
            email.setError("Isi email");
            return false;
        }else if (notelp1.length() <= 0) {
            notelp.requestFocus();
            notelp.setError("Isi nomor telepon");
            return false;
        }else{
            return true;
        }
    }

    private void processInsert() {
        String username1 = username.getText().toString().trim();
        String password1 = password.getText().toString().trim();
        String email1 = email.getText().toString().trim();
        String notelp1 = notelp.getText().toString().trim();

        preferences.setUsername(this,username1);
        preferences.setEmail(this,email1);
        preferences.setNotelp(this,notelp1);

        String key = FirebaseDatabase.getInstance().getReference().child("user").push().getKey();
        Map<String,Object> map=new HashMap<>();
        if(checkValidation()) {
            map.put("username",username1);
            map.put("password",password1);
            map.put("nomortelpuser",notelp1);
            map.put("role","pelanggan");
            map.put("iduser",key);
            FirebaseDatabase.getInstance().getReference().child("user").child(key)
                    .setValue(map)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            username.setText("");
                            password.setText("");
                            Toast.makeText(getApplicationContext(), "inserted successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            username.setText("");
                            password.setText("");
                            Toast.makeText(getApplicationContext(), "could not insert", Toast.LENGTH_SHORT).show();
                        }
                    });
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));}

    }
}