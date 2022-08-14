package com.example.cobaskripsi.UserUI.profilepelanggan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;

public class PelayananPelanggan extends AppCompatActivity {

    TextView wa,telepon,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelayananpelanggan);

        telepon=findViewById(R.id.teleponpelayananpelanggan);
        wa=findViewById(R.id.whatsapppelayananpelanggan);
        email=findViewById(R.id.emailpelayananpelanggan);

        telepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(telepon.getContext());
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

    }
}