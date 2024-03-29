package com.example.cobaskripsi.PengelolaUI.profilemitra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;

public class PelayananPelangganMitra extends AppCompatActivity {

    TextView telp, email,wa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelayanan_pelanggan_mitra);

        telp=findViewById(R.id.nomoradminmitra);
        email = findViewById(R.id.emailadminmitra);
        wa=findViewById(R.id.whatsappadminmitra);

        telp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(telp.getContext());
                builder.setTitle("Telepon");
                builder.setMessage("Anda akan diarahkan ke menu panggilan");


                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("Telepon", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+"+628121261983"));
                        startActivity(intent);
                    }
                });

                builder.show();
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(email.getContext());
                builder.setTitle("Email");
                builder.setMessage("Anda akan diarahkan ke menu Email");


                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("Email", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        Uri data = Uri.parse("mailto:mauludi21@gmail.com?subject=" + "" + "&body=" + "");
                        intent.setData(data);
                        startActivity(intent);
                    }
                });

                builder.show();
            }
        });

        wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(wa.getContext());
                builder.setTitle("Whatsapp");
                builder.setMessage("Anda akan diarahkan ke Whatsapp");


                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String url = "https://api.whatsapp.com/send?phone="+"+628121261983";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                });


                builder.show();
            }
        });




    }
}