package com.example.cobaskripsi.UserUI.jenisolahraga;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.MainListLapangan;
import com.example.cobaskripsi.preferences;

public class CaritempatFragment extends Fragment {

    private ImageView btnBasket,btnTenis,btnBadminton,btnFutsal;
    TextView jenisbasket, jenistenis, jenisfutsal, jenisbadminton,nama;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_caritempat, container, false);

        btnBasket = root.findViewById(R.id.btnBasket);
        btnBadminton = root.findViewById(R.id.btnBadminton);
        btnTenis = root.findViewById(R.id.btnTenis);
        btnFutsal = root.findViewById(R.id.btnFutsal);

        jenisbasket = (TextView)root.findViewById(R.id.jenisbasket);
        jenisbadminton = (TextView)root.findViewById(R.id.jenisbadminton);
        jenistenis = (TextView)root.findViewById(R.id.jenistenis);
        jenisfutsal = (TextView)root.findViewById(R.id.jenisfutsal);
        nama=root.findViewById(R.id.namapelangganhome);
        nama.setText("Halo, "+preferences.getUsername(getActivity())+" !");

        String jenisbasket1 = jenisbasket.getText().toString();
        String jenisbadminton1 = jenisbadminton.getText().toString();
        String jenistenis1 = jenistenis.getText().toString();
        String jenisfutsal1 = jenisfutsal.getText().toString();

        preferences.clearJenisolahraga(getActivity());
        btnBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainListLapangan.class);
                Bundle extras = new Bundle();
                extras.putString("JENISOLAHRAGA", jenisbasket1);
                preferences.setJenisolahraga(getActivity(),jenisbasket1);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        btnBadminton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainListLapangan.class);
                Bundle extras = new Bundle();
                extras.putString("JENISOLAHRAGA", jenisbadminton1);
                preferences.setJenisolahraga(getActivity(),jenisbadminton1);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        btnTenis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainListLapangan.class);
                Bundle extras = new Bundle();
                extras.putString("JENISOLAHRAGA", jenistenis1);
                preferences.setJenisolahraga(getActivity(),jenistenis1);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        btnFutsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainListLapangan.class);
                Bundle extras = new Bundle();
                extras.putString("JENISOLAHRAGA", jenisfutsal1);
                preferences.setJenisolahraga(getActivity(),jenisfutsal1);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        return root;
    }
}