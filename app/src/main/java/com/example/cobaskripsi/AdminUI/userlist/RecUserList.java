package com.example.cobaskripsi.AdminUI.userlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class RecUserList extends Fragment {
    RecyclerView recview;
    UserListAdapter adapter;


    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;

    public RecUserList() {
    }


    public static RecUserList newInstance(int columnCount) {
        RecUserList fragment = new RecUserList();
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
        View view = inflater.inflate(R.layout.fragment_rec_user_list_list, container, false);
        recview=(RecyclerView)view.findViewById(R.id.recviewuserlist);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<UserModel> options =
                new FirebaseRecyclerOptions.Builder<UserModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("user"), UserModel.class)
                        .build();
        adapter = new UserListAdapter(options);
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