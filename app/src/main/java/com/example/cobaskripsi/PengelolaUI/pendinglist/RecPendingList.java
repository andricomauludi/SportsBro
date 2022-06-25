package com.example.cobaskripsi.PengelolaUI.pendinglist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.detail.PemesananModel;
import com.example.cobaskripsi.preferences;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class RecPendingList extends Fragment {

    RecyclerView recview;
    PendingListAdapter adapter;


    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;


    public RecPendingList() {
    }


    @SuppressWarnings("unused")
    public static RecPendingList newInstance(int columnCount) {
        RecPendingList fragment = new RecPendingList();
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
        View view = inflater.inflate(R.layout.fragment_rec_pending_list_list, container, false);
        recview=(RecyclerView)view.findViewById(R.id.recviewpendinglist);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<PemesananModel> options =
                new FirebaseRecyclerOptions.Builder<PemesananModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("pemesanan").orderByChild("idtempat").equalTo(preferences.getIdtempatmitra(getContext())), PemesananModel.class)
                        .build();
        adapter = new PendingListAdapter(options);
        recview.setAdapter(adapter);

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