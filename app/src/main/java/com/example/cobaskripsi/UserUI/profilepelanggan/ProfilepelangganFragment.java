package com.example.cobaskripsi.UserUI.profilepelanggan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cobaskripsi.LoginActivity;
import com.example.cobaskripsi.R;
import com.example.cobaskripsi.preferences;

public class ProfilepelangganFragment extends Fragment {

    Button logouts, panduan,tentangaplikasi, pelayananpelanggan;
    TextView namapengguna,emailpengguna,notelppengguna;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profilepelanggan, container, false);

        logouts = root.findViewById(R.id.logoutt);
        namapengguna = root.findViewById(R.id.namapelangganprofile);
        emailpengguna = root.findViewById(R.id.emailpelangganprofile);
        notelppengguna = root.findViewById(R.id.nomortelppelangganprofile);
        panduan=root.findViewById(R.id.panduanketentuanpelanggan);
        tentangaplikasi = root.findViewById(R.id.tentangaplikasi);
        pelayananpelanggan = root.findViewById(R.id.pelayananpelanggan);

        namapengguna.setText(preferences.getUsername(getActivity()));
        emailpengguna.setText(preferences.getEmail(getActivity()));
        notelppengguna.setText(preferences.getNotelp(getActivity()));

        logouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(logouts.getContext());
                builder.setTitle("Logout");
                builder.setMessage("Anda yakin ingin logout?");


                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        logout();
                    }
                });


                builder.show();

            }
        });

        panduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),PanduanAplikasiPelanggan.class));
            }
        });

        tentangaplikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),TentangAplikasiPelanggan.class));
            }
        });

        pelayananpelanggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PelayananPelanggan.class));
            }
        });

        return root;
    }

    public void logout(){
        startActivity(new Intent(getActivity(), LoginActivity.class));
        preferences.clearData(getActivity());
        getActivity().finish();
    }
}

