package com.example.cobaskripsi;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText username,password,email,notelp;
    Button registers,cancel;
    String useridauth;
    Boolean passwordVisible = false;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.username);
        password=findViewById(R.id.passwordregister);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbarregister);


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
                            //for show password
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
        email=findViewById(R.id.emailuser);
        notelp=findViewById(R.id.nomortelpuser);
        registers=findViewById(R.id.register1);
        cancel=findViewById(R.id.cancel);

        registers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register(username.getText().toString(), email.getText().toString(), password.getText().toString(), notelp.getText().toString());
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });


    }

    private void register(String nameregister, String emailregister, String passwordregister, String notelpregister){
        if (checkValidation()){
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(emailregister, passwordregister)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                processInsert();
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "Gagal register", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                    /**.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user =  mAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                            useridauth=user.getUid();
                            FirebaseAuth.getInstance().signOut();
                            processInsert(useridauth);

                        }
                    });**/
        }
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
        } else if (password1.length() < 6) {
            password.requestFocus();
            password.setError("Isi password lebih dari 6 digit");
            return false;

        } else if (email1.length() <= 0) {
            email.requestFocus();
            email.setError("Isi email");
            return false;
        }else if (notelp1.length() < 10 && notelp1.length() > 12) {
            notelp.requestFocus();
            notelp.setError("Isi nomor telepon yang valid");
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
            email.setError("isi email yang valid");
            email.requestFocus();
            return false;
        }else{
            return true;
        }
    }

    private void processInsert() {
        String username1 = username.getText().toString().trim();
        String email1 = email.getText().toString().trim();
        String notelp1 = notelp.getText().toString().trim();

        //String key = FirebaseDatabase.getInstance().getReference().child("user").push().getKey();
        Map<String,Object> map=new HashMap<>();
        if(checkValidation()) {
            map.put("username",username1);
            map.put("nomortelpuser","+62"+notelp1);
            map.put("emailuser",email1);
            map.put("role","pelanggan");
            map.put("iduser",FirebaseAuth.getInstance().getCurrentUser().getUid());
            FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                username.setText("");
                                password.setText("");
                                notelp.setText("");
                                email.setText("");
                                Toast.makeText(getApplicationContext(), "Berhasil disimpan", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                finish();
                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "Gagal Register, Coba lagi", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }

    }
}