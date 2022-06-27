package com.example.cobaskripsi.AdminUI.datatempat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.TempatModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class RecDataTempat extends Fragment {

    RecyclerView recview;
    DataTempatAdapter adapter;


    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;


    public RecDataTempat() {
    }


    public static RecDataTempat newInstance(int columnCount) {
        RecDataTempat fragment = new RecDataTempat();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rec_data_tempat_list, container, false);
        recview=(RecyclerView)view.findViewById(R.id.recviewdatatempat);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<TempatModel> options =
                new FirebaseRecyclerOptions.Builder<TempatModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("tempat"), TempatModel.class)
                        .build();
        adapter = new DataTempatAdapter(options);
        recview.setAdapter(adapter);

        // Set the adapter

        // Set the adapter
        return view;
    }

    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}