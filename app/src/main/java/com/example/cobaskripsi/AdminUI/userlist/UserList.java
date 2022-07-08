package com.example.cobaskripsi.AdminUI.userlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.AdminUI.HomeadminActivity;
import com.example.cobaskripsi.R;

public class UserList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("User List");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportFragmentManager().beginTransaction().replace(R.id.wrapperuserlist, new RecUserList()).commit();

    }

    public void onBackPressed() {
        startActivity(new Intent(UserList.this, HomeadminActivity.class));
        finish();
    }
}