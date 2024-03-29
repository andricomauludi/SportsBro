package com.example.cobaskripsi.AdminUI.userlist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;

public class DetailUserList extends AppCompatActivity {

    TextView namauser, email,notelp,role;
    String namauser1,email1,notelp1,role1;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user_list);

        namauser=findViewById(R.id.namauserdetailuserlist);
        email=findViewById(R.id.emaildetailuserlist);
        notelp=findViewById(R.id.notelpdetailuserlist);
        role=findViewById(R.id.roleuserdetailuserlist);
        back=findViewById(R.id.backuserlist);

        Intent intent = getIntent();
        namauser1 = intent.getStringExtra("NAMAUSER");
        email1 = intent.getStringExtra("EMAIL");
        notelp1 = intent.getStringExtra("NOTELP");
        role1 = intent.getStringExtra("ROLE");

        namauser.setText(namauser1);
        email.setText(email1);
        notelp.setText(notelp1);

        if(role1.equals("pelanggan")){
            role.setTextColor(Color.GREEN);
            role.setText(role1.substring(0, 1).toUpperCase() + role1.substring(1).toLowerCase());
        }else if (role1.equals("admin")){
            role.setTextColor(Color.RED);
            role.setText(role1.substring(0, 1).toUpperCase() + role1.substring(1).toLowerCase());
        }else if (role1.equals("mitra")){
            role.setTextColor(Color.rgb(255,165,0));
            role.setText(role1.substring(0, 1).toUpperCase() + role1.substring(1).toLowerCase());
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailUserList.this,UserList.class));
                finish();
            }
        });



    }

    public void onBackPressed(){

    }
}