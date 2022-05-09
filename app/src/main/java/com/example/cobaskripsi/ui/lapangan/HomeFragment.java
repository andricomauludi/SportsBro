package com.example.cobaskripsi.ui.lapangan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.ui.lapangan.basket.ListBasket;

public class HomeFragment extends Fragment {

    private Button btnBasket;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_home, container, false);

        btnBasket = root.findViewById(R.id.btnBasket);

        btnBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListBasket.class);
                startActivity(intent);
            }
        });
        return root;
    }
}