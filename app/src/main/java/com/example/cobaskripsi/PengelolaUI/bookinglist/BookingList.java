package com.example.cobaskripsi.PengelolaUI.bookinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.PengelolaUI.HomemitraActivity;
import com.example.cobaskripsi.R;

public class BookingList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Booking List");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookinglist);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportFragmentManager().beginTransaction().replace(R.id.wrapperbookinglist, new RecBookingList()).commit();

    }

    public void onBackPressed() {
        startActivity(new Intent(BookingList.this, HomemitraActivity.class));
        finish();
    }
}