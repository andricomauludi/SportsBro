package com.example.cobaskripsi;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.cobaskripsi.AdminUI.HomeadminActivity;
import com.example.cobaskripsi.PengelolaUI.HomemitraActivity;
import com.example.cobaskripsi.PengelolaUI.datalapangan.TempatcontributorsModel;
import com.example.cobaskripsi.UserUI.Homepelanggan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {


    private EditText username, password;
    private Button login,toregister;
    TextView coba;
    String input1,input2,input3;
    Switch active;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},0);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        //coba = findViewById(R.id.cobalogin);
        toregister = findViewById(R.id.toregister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 input1 =  username.getText().toString();
                 input2 = password.getText().toString();

                databaseReference.child("user")
                        .orderByChild("username")
                        .equalTo(input1)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                                        String clubkey = childSnapshot.getKey();
                                        input3 = clubkey;
                                    }

                                    databaseReference.child("user")
                                            .addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    if(snapshot.child(input3).exists()){
                                                        if (snapshot.child(input3).child("password").getValue(String.class).equals(input2)){
                                                            if(snapshot.child(input3).child("role").getValue(String.class).equals("mitra")){
                                                                preferences.setDataLogin(LoginActivity.this,true);
                                                                preferences.setUsername(LoginActivity.this,input1);
                                                                preferences.setUserID(LoginActivity.this,input3);
                                                                preferences.setDataRole(LoginActivity.this,"mitra");
                                                                databaseReference.child("tempatcontributors")
                                                                        .orderByChild("iduser")
                                                                        .equalTo(preferences.getUserID(LoginActivity.this))
                                                                        .addValueEventListener(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                                if(snapshot.exists()) {
                                                                                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                                                                        TempatcontributorsModel tempatcontributorsModel = childSnapshot.getValue(TempatcontributorsModel.class);
                                                                                        String clubkey = (tempatcontributorsModel.getIdtempat());
                                                                                        preferences.setIdtempatmitra(LoginActivity.this,clubkey);
                                                                                        Toast.makeText(LoginActivity.this,preferences.getIdtempatmitra(LoginActivity.this),Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                }
                                                                            }

                                                                            @Override
                                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                                            }
                                                                        });
                                                                startActivity(new Intent(LoginActivity.this, HomemitraActivity.class));
                                                            }else if(snapshot.child(input3).child("role").getValue(String.class).equals("admin")) {
                                                                preferences.setDataLogin(LoginActivity.this,true);
                                                                preferences.setDataRole(LoginActivity.this,"admin");
                                                                preferences.setUsername(LoginActivity.this,input1);
                                                                preferences.setUserID(LoginActivity.this,input3);
                                                                startActivity(new Intent(LoginActivity.this, HomeadminActivity.class));
                                                            }else if(snapshot.child(input3).child("role").getValue(String.class).equals("pelanggan")){
                                                                preferences.setDataLogin(LoginActivity.this,true);
                                                                preferences.setDataRole(LoginActivity.this,"pelanggan");
                                                                preferences.setUsername(LoginActivity.this,input1);
                                                                preferences.setUserID(LoginActivity.this,input3);
                                                                startActivity(new Intent(LoginActivity.this, Homepelanggan.class));
                                                            }
                                                        }else{
                                                            Toast.makeText(LoginActivity.this,"Kata sandi salah",Toast.LENGTH_SHORT).show();
                                                        }
                                                    }else{
                                                        Toast.makeText(LoginActivity.this,"Data belum terdaftar",Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });
                                }
                                else {
                                    Toast.makeText(LoginActivity.this,"Data belum terdaftar",Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


            }
        });

        toregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        if (preferences.getDataLogin(this)) {
            if (preferences.getDataRole(this).equals("mitra")) {
                startActivity(new Intent(this, HomemitraActivity.class));
                finish();
            } else if(preferences.getDataRole(this).equals("admin")) {
                startActivity(new Intent(this, HomeadminActivity.class));
                finish();
            }else{
                startActivity(new Intent(this, Homepelanggan.class));
                finish();
            }
        }
    }
}