package com.example.cobaskripsi.PengelolaUI.datalapangan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.detail.LapanganModel;
import com.example.cobaskripsi.preferences;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RecListDatalapangan extends Fragment {

    RecyclerView recview;
    AdapterDataLapangan adapter;
    DatabaseReference mDatabase;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public RecListDatalapangan() {

    }


    public static RecListDatalapangan newInstance(String param1, String param2) {
        RecListDatalapangan fragment = new RecListDatalapangan();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_rec_list_datalapangan, container, false);
        recview=(RecyclerView)view.findViewById(R.id.recviewdatalapangan);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<LapanganModel> options =
                new FirebaseRecyclerOptions.Builder<LapanganModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("lapangan").orderByChild("idtempat").equalTo(preferences.getIdtempatmitra(getContext())), LapanganModel.class)
                        .build();
        adapter = new AdapterDataLapangan(options);
        recview.setAdapter(adapter);

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