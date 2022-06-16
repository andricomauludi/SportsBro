package com.example.cobaskripsi.UserUI.riwayatpemesanan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.detail.PemesananModel;
import com.example.cobaskripsi.preferences;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class RiwayatpelangganFragment extends Fragment {
    private RecyclerView recyclerView;
    ConstraintLayout constraintLayout;
    String username,userid;
    TextView nonedata;

    RandomNumListAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_riwayatuser, container, false);

        username= preferences.getUsername(getActivity());
        userid=preferences.getUserID(getContext());

        if (userid!=null){
        recyclerView = root.findViewById(R.id.recyclerviewriwayat);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setHasFixedSize(true);
       // recyclerView.setAdapter(new RandomNumListAdapter(1234));

        FirebaseRecyclerOptions<PemesananModel> options =
                new FirebaseRecyclerOptions.Builder<PemesananModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("pemesanan").orderByChild("idpemesan").equalTo(userid), PemesananModel.class)
                        .build();
        adapter = new RandomNumListAdapter(options);
        recyclerView.setAdapter(adapter);}
        else {
            constraintLayout=root.findViewById(R.id.wrapper2);
            nonedata = new TextView(getActivity());
            nonedata.setText("Anda belum melakukan pemesanan");
            nonedata.setId(0);
            nonedata.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT));
            constraintLayout.addView(nonedata);
        }

        return root;
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