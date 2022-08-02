package com.example.cobaskripsi;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {


    private EditText username, password, email;
    private Button login,toregister;
    ProgressBar progressBar;
    TextView coba,lupa;
    String input1,input2,input3;
    Switch active;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    Boolean passwordVisible = false;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},0);

        email = findViewById(R.id.emaillogin);
        password = findViewById(R.id.password);
        lupa = findViewById(R.id.lupapassword);
        progressBar = findViewById(R.id.progressbarlogin);
        mAuth = FirebaseAuth.getInstance();

        FirebaseAuth.getInstance().signOut();

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
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                                        processInsert();
                                    }
                                    else{
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(LoginActivity.this, "Data anda ada yang salah", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                    );
                }
            }
        });
        toregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        lupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ForgotPassword.class));
            }
        });

    }

    private void processInsert() {
        if (checkValidation()){
            input1 =  email.getText().toString();
            databaseReference.child("user")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                UserModel userModel = childSnapshot.getValue(UserModel.class);

                                    if (userModel.getEmailuser().equals(input1) && userModel.getRole().contains("mitra")) {
                                        String iduser = userModel.getIduser();
                                        String nameuser = (userModel.getUsername());
                                        String nomortelpuser = (userModel.getNomortelpuser());
                                        String emailuser = (userModel.getEmailuser());

                                        preferences.setDataLogin(LoginActivity.this, true);
                                        preferences.setUsername(LoginActivity.this, nameuser);
                                        preferences.setNotelp(LoginActivity.this, nomortelpuser);
                                        preferences.setEmail(LoginActivity.this, input1);
                                        preferences.setUserID(LoginActivity.this, iduser);
                                        preferences.setDataRole(LoginActivity.this, "mitra");

                                        databaseReference.child("tempatcontributors")
                                                .orderByChild("iduser")
                                                .equalTo(preferences.getUserID(LoginActivity.this))
                                                .addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        if (snapshot.exists()) {
                                                            for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                                                TempatcontributorsModel tempatcontributorsModel = childSnapshot.getValue(TempatcontributorsModel.class);
                                                                String clubkey = (tempatcontributorsModel.getIdtempat());
                                                                preferences.setIdtempatmitra(LoginActivity.this, clubkey);

                                                            }
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });
                                        startActivity(new Intent(LoginActivity.this, HomemitraActivity.class));
                                        finish();
                                    } else if (userModel.getEmailuser().equals(input1) && userModel.getRole().contains("admin")) {
                                        String iduser = userModel.getIduser();
                                        String nameuser = (userModel.getUsername());
                                        preferences.setDataLogin(LoginActivity.this, true);
                                        preferences.setUsername(LoginActivity.this, nameuser);
                                        String nomortelpuser = (userModel.getNomortelpuser());

                                        preferences.setNotelp(LoginActivity.this, nomortelpuser);
                                        preferences.setEmail(LoginActivity.this, input1);
                                        preferences.setUserID(LoginActivity.this, iduser);
                                        preferences.setDataRole(LoginActivity.this, "admin");
                                        startActivity(new Intent(LoginActivity.this, HomeadminActivity.class));
                                        finish();
                                    } else if (userModel.getEmailuser().equals(input1) && userModel.getRole().contains("pelanggan")
                                    ) {
                                        String iduser = userModel.getIduser();
                                        preferences.setDataLogin(LoginActivity.this, true);
                                        String nameuser = (userModel.getUsername());
                                        preferences.setUsername(LoginActivity.this, nameuser);
                                        String nomortelpuser = (userModel.getNomortelpuser());

                                        preferences.setNotelp(LoginActivity.this, nomortelpuser);
                                        preferences.setEmail(LoginActivity.this, input1);
                                        preferences.setUserID(LoginActivity.this, iduser);
                                        preferences.setDataRole(LoginActivity.this, "pelanggan");
                                        startActivity(new Intent(LoginActivity.this, Homepelanggan.class));
                                        finish();
                                    }




                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(LoginActivity.this, "cek data anda kembali", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                @Override
                public void onSuccess(DataSnapshot dataSnapshot) {
                    if (preferences.getDataRole(getApplicationContext()).equals("pelanggan")){
                        startActivity(new Intent(getApplicationContext(),Homepelanggan.class));
                        finish();
                    }else if (preferences.getDataRole(getApplicationContext()).equals("mitra")){
                        Toast.makeText(LoginActivity.this, "masuk", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),HomemitraActivity.class));
                        finish();
                    }else if (preferences.getDataRole(getApplicationContext()).equals("admin")){
                        Toast.makeText(LoginActivity.this, "masuk", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),HomeadminActivity.class));
                        finish();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    FirebaseAuth.getInstance().signOut();
                    preferences.clearData(getApplicationContext());
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                }
            });
        }
    }

    public  boolean checkValidation() {
        String email1 = email.getText().toString().trim();
        String password1 = password.getText().toString().trim();

        if (email1.length() <= 0) {
            email.requestFocus();
            email.setError("Isi Email");
            return false;
        } else if (password1.length() <= 0) {
            password.requestFocus();
            password.setError("Isi password");
            return false;

        }else if (password1.length() < 6) {
            password.requestFocus();
            password.setError("Password harus lebih dari 6 karakter");
            return false;

        }else if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            email.setError("isi email yang valid");
            email.requestFocus();
            return false;
        } else{
            return true;
        }
    }


}