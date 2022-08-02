package com.example.cobaskripsi;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    EditText forgot;
    Button resetpass;
    FirebaseAuth auth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgot = findViewById(R.id.emailforgot);
        resetpass = findViewById(R.id.resetpassword);
        progressBar = findViewById(R.id.progressbarforgot);

        auth= FirebaseAuth.getInstance();

        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

    }

    private void resetPassword(){
        String email = forgot.getText().toString().trim();
        if (email.isEmpty()){
            forgot.setError("Masukkan Email");
            forgot.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            forgot.setError("Masukkan Alamat Email yang Benar");
            forgot.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ForgotPassword.this, "Cek email untuk reset password anda!", Toast.LENGTH_LONG).show();
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ForgotPassword.this, "Coba lagi! terdapat eror", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}