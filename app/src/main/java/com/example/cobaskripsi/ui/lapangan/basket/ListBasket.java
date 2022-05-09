package com.example.cobaskripsi.ui.lapangan.basket;

import android.location.Location;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaskripsi.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListBasket extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    BasketAdapter basketAdapter;
    ArrayList<DataBasket> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_basket);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<BasketModel> options =
                new FirebaseRecyclerOptions.Builder<BasketModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("lapanganbasket"), BasketModel.class)
                        .build();

        basketAdapter = new BasketAdapter(options);
        recyclerView.setAdapter(basketAdapter);

        GetLocation getLocation = new GetLocation(getApplicationContext());
        Location location = getLocation.getLocation();
        if (location != null){
            double latitudeSaya = location.getLatitude();
            double longitudeSaya = location.getLongitude();

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        basketAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        basketAdapter.stopListening();
    }
}