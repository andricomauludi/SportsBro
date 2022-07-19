package com.example.cobaskripsi.AdminUI.profileadmin;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;

public class TentangAplikasiAdmin extends AppCompatActivity {

    TextView isitentang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_aplikasi_admin);

        isitentang=findViewById(R.id.isitentangaplikasiadmin);
        isitentang.setText("Aplikasi SPORTSBRO. merupakan aplikasi yang dikembangkan oleh Andrico Mauludi Junianto" +
                " yang merupakan mahasiswa S1 Sistem Informasi, Universitas Airlangga. Aplikasi ini dibangun untuk" +
                " sebagai simulasi pencarian dan pemesanan sarana olahraga terdekat dari pengguna. dan aplikasi ini bertujuan" +
                " untuk sebagai syarat kelulusan dari Andrico Mauludi Junianto");
    }
}