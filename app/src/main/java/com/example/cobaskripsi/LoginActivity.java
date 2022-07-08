package com.example.cobaskripsi;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

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
    Boolean passwordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},0);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=password.getRight()-password.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=password.getSelectionEnd();
                        if (passwordVisible){
                            //set drawable
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_visibility_off_24,0);
                            //for hide passworrd
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else {
                            //set drawable
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_visibility_24,0);
                            //for show passworrd
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });


        login = findViewById(R.id.login);
        //coba = findViewById(R.id.cobalogin);
        toregister = findViewById(R.id.toregister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()){
                 input1 =  username.getText().toString();
                 input2 = password.getText().toString();


                     databaseReference.child("user")
                             .addValueEventListener(new ValueEventListener() {
                                 @Override
                                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                                     for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                         UserModel userModel = childSnapshot.getValue(UserModel.class);
                                         if (userModel.getUsername().contains(input1) &&
                                                 userModel.getPassword().contains(input2) && userModel.getRole().contains("mitra")){
                                             String iduser = userModel.getIduser();
                                             preferences.setDataLogin(LoginActivity.this,true);
                                             preferences.setUsername(LoginActivity.this,input1);
                                             String nomortelpuser = (userModel.getNomortelpuser());
                                             String emailuser = (userModel.getEmailuser());
                                             preferences.setNotelp(LoginActivity.this,nomortelpuser);
                                             preferences.setEmail(LoginActivity.this,emailuser);
                                             preferences.setUserID(LoginActivity.this,iduser);
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

                                                                 }
                                                             }
                                                         }

                                                         @Override
                                                         public void onCancelled(@NonNull DatabaseError error) {

                                                         }
                                                     });
                                             startActivity(new Intent(LoginActivity.this, HomemitraActivity.class));
                                             finish();
                                         }
                                         else if(userModel.getUsername().contains(input1) &&
                                                 userModel.getPassword().contains(input2) && userModel.getRole().contains("admin")) {
                                             String iduser = userModel.getIduser();
                                             preferences.setDataLogin(LoginActivity.this, true);
                                             preferences.setUsername(LoginActivity.this, input1);
                                             String nomortelpuser = (userModel.getNomortelpuser());
                                             String emailuser = (userModel.getEmailuser());
                                             preferences.setNotelp(LoginActivity.this, nomortelpuser);
                                             preferences.setEmail(LoginActivity.this, emailuser);
                                             preferences.setUserID(LoginActivity.this, iduser);
                                             preferences.setDataRole(LoginActivity.this, "admin");
                                             startActivity(new Intent(LoginActivity.this, HomeadminActivity.class));
                                             finish();
                                         }else if(userModel.getUsername().contains(input1) &&
                                                 userModel.getPassword().contains(input2) && userModel.getRole().contains("pelanggan")) {
                                             String iduser = userModel.getIduser();
                                             preferences.setDataLogin(LoginActivity.this, true);
                                             preferences.setUsername(LoginActivity.this, input1);
                                             String nomortelpuser = (userModel.getNomortelpuser());
                                             String emailuser = (userModel.getEmailuser());
                                             preferences.setNotelp(LoginActivity.this, nomortelpuser);
                                             preferences.setEmail(LoginActivity.this, emailuser);
                                             preferences.setUserID(LoginActivity.this, iduser);
                                             preferences.setDataRole(LoginActivity.this, "pelanggan");
                                             startActivity(new Intent(LoginActivity.this, Homepelanggan.class));
                                             finish();
                                         }
                                     }

                                 }

                                 @Override
                                 public void onCancelled(@NonNull DatabaseError error) {

                                 }
                             });                 }






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

    public  boolean checkValidation() {
        String username1 = username.getText().toString().trim();
        String password1 = password.getText().toString().trim();

        if (username1.length() <= 0) {
            username.requestFocus();
            username.setError("Isi Nama");
            return false;
        } else if (password1.length() <= 0) {
            password.requestFocus();
            password.setError("Isi password");
            return false;

        } else{
            return true;
        }
    }


}