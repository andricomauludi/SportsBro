package com.example.cobaskripsi.UserUI.jenisolahraga.caritempat;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.cobaskripsi.R;

public class cobahitungjarak extends AppCompatActivity {

    TextView lokasiSaya, value;
    EditText latitude, longitude;
    Button hitungJarak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobahitungjarak);

        ActivityCompat.requestPermissions(cobahitungjarak.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},0);

        lokasiSaya = (TextView) findViewById(R.id.txt_lokasiSaya);
        value = (TextView) findViewById(R.id.txt_value);
        latitude = (EditText) findViewById(R.id.edt_latitude);
        longitude = (EditText) findViewById(R.id.edt_longitude);
        hitungJarak = (Button) findViewById(R.id.btn_hitungJarak);

        hitungJarak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetLocation getLocation = new GetLocation(getApplicationContext());
                Location location = getLocation.getLocation();
                if (location != null){
                    double latitudeSaya = location.getLatitude();
                    double longitudeSaya = location.getLongitude();
                    double latitudeTujuan = Double.valueOf(latitude.getText().toString());
                    double longitudeTujuan = Double.valueOf(longitude.getText().toString());

                    lokasiSaya.setText(latitudeSaya + "," + longitudeSaya);
                    double jarak = getDistance(latitudeTujuan, longitudeTujuan, latitudeSaya, longitudeSaya);
                    jarak = Math.ceil(jarak / 1000);
                    value.setText(jarak+" km");

                }
            }
        });
    }

    private double getDistance(Double latitudeTujuan, Double longitudeTujuan, Double latitudeUser, Double longitudeUser){
        /*VARIABEL */
        Double pi =3.14159265358979;
        Double lat1 = latitudeTujuan;
        Double lon1 = longitudeTujuan;
        Double lat2 = latitudeUser;
        Double lon2 = longitudeUser;
        Double R = 6371e3;

        Double latRad1 = lat1 * (pi / 180);
        Double latRad2 = lat2 * (pi / 180);
        Double deltaLatRad = (lat2 - lat1) * (pi / 180);
        Double deltaLonRad = (lon2 - lon1) * (pi / 180);

        /* RUMUS HAVERSINE*/
        Double a = Math.sin(deltaLatRad / 2) * Math.sin(deltaLatRad / 2) + Math.cos(latRad1) * Math.cos(latRad2) * Math.sin(deltaLonRad / 2) * Math.sin(deltaLonRad / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double s = R * c; // hasil jarak dalam meter
        return s;
    }

}