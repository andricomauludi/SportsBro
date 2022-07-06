package com.example.cobaskripsi.PengelolaUI.bookinglist;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.detail.PemesananModel;
import com.example.cobaskripsi.preferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class RecBookingList extends Fragment {

    RecyclerView recview;
    BookingListAdapter adapter;
    BookingListAdapterBaru myadapter;
    Button berlangsungbookinglist,semuariwayat,expired, filtertanggal;
    ArrayList<PemesananModel> arrayList;
    DatabaseReference databaseReference;



    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;


    public RecBookingList() {

    }


    public static RecBookingList newInstance(int columnCount) {
        RecBookingList fragment = new RecBookingList();
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
        View view = inflater.inflate(R.layout.fragment_rec_booking_list_list, container, false);
        recview=(RecyclerView)view.findViewById(R.id.recviewbookinglist);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));
        semuariwayat=view.findViewById(R.id.semuariwayatbookinglist);
        berlangsungbookinglist=view.findViewById(R.id.berlangsungbookinglist);
        expired=view.findViewById(R.id.expiredbookinglist);
        filtertanggal=view.findViewById(R.id.filtertanggal);
        LinearLayout linearLayout = view.findViewById(R.id.rootlayoutfiltertanggal);

        arrayList= new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("pemesanan");

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String todayString = formatter.format(todayDate);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        recview.setItemAnimator(null);
        Query query=databaseReference.orderByChild("idtempat").equalTo(preferences.getIdtempatmitra(getActivity()));
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    arrayList.clear();
                    for (DataSnapshot snap : snapshot.getChildren()){
                        final PemesananModel pemesananModel = snap.getValue(PemesananModel.class);
                        String tanggalpesan = pemesananModel.getTanggalpemesanan();

                        Date tanggal = null;
                        try {
                            tanggal = sdf.parse(tanggalpesan);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date today = null;
                        try {
                            today = sdf.parse(todayString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (tanggal.compareTo(today)>=0){
                            if (pemesananModel.getStatuspemesanan().contains("Booked")){
                                arrayList.add(pemesananModel);
                            }

                        }

                    }

                    myadapter =  new BookingListAdapterBaru(getActivity().getApplicationContext(),arrayList);
                    recview.setAdapter(myadapter);
                    myadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        semuariwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeAllViews();
                recview.setItemAnimator(null);
                Query query=databaseReference.orderByChild("idtempat").equalTo(preferences.getIdtempatmitra(getActivity()));
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()){
                            arrayList.clear();
                            for (DataSnapshot snap : snapshot.getChildren()){
                                final PemesananModel pemesananModel = snap.getValue(PemesananModel.class);
                                arrayList.add(pemesananModel);

                            }

                            myadapter =  new BookingListAdapterBaru(getActivity().getApplicationContext(),arrayList);
                            recview.setAdapter(myadapter);
                            myadapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        berlangsungbookinglist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeAllViews();
                recview.setItemAnimator(null);
                Query query=databaseReference.orderByChild("idtempat").equalTo(preferences.getIdtempatmitra(getActivity()));
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()){
                            arrayList.clear();
                            for (DataSnapshot snap : snapshot.getChildren()){
                                final PemesananModel pemesananModel = snap.getValue(PemesananModel.class);
                                String tanggalpesan = pemesananModel.getTanggalpemesanan();

                                Date tanggal = null;
                                try {
                                    tanggal = sdf.parse(tanggalpesan);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Date today = null;
                                try {
                                    today = sdf.parse(todayString);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                if (tanggal.compareTo(today)>=0){
                                    if (pemesananModel.getStatuspemesanan().contains("Booked")){
                                        arrayList.add(pemesananModel);
                                    }

                                }

                            }

                            myadapter =  new BookingListAdapterBaru(getActivity().getApplicationContext(),arrayList);
                            recview.setAdapter(myadapter);
                            myadapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        expired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeAllViews();
                recview.setItemAnimator(null);
                Query query=databaseReference.orderByChild("idtempat").equalTo(preferences.getIdtempatmitra(getActivity()));
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()){
                            arrayList.clear();
                            for (DataSnapshot snap : snapshot.getChildren()){
                                final PemesananModel pemesananModel = snap.getValue(PemesananModel.class);
                                String tanggalpesan = pemesananModel.getTanggalpemesanan();

                                Date tanggal = null;
                                try {
                                    tanggal = sdf.parse(tanggalpesan);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Date today = null;
                                try {
                                    today = sdf.parse(todayString);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                if (tanggal.compareTo(today)<0){
                                    if (pemesananModel.getStatuspemesanan().contains("Booked")){
                                        arrayList.add(pemesananModel);
                                    }

                                }

                            }

                            myadapter =  new BookingListAdapterBaru(getActivity().getApplicationContext(),arrayList);
                            recview.setAdapter(myadapter);
                            myadapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


        filtertanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeAllViews();
                recview.setItemAnimator(null);
                Button addbtn = new Button (getActivity());
                if (linearLayout!=null) {
                    linearLayout.addView(addbtn);
                    addbtn.setText("Pilih Tanggal");
                    addbtn.setId(0);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(20, 0, 10, 0);
                    addbtn.setLayoutParams(params);


                    //addlapangan[i]=view.findViewById(R.id.lapangan1);

                    String abc = "mantab";

                    int resID = getResources().getIdentifier(abc, "id", getActivity().getPackageName());
                    addbtn = (Button) view.findViewById(resID);
                    addbtn.setText("Pilih Tanggal");
                    addbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DatePickerDialog datePickerDialog = new DatePickerDialog(
                                    getActivity(), new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int day) {
                                    recview.setItemAnimator(null);
                                    month = month+1;
                                    String date = day +"/"+ month +"/"+year;


                                    Query query=databaseReference.orderByChild("idtempat").equalTo(preferences.getIdtempatmitra(getActivity()));
                                    query.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.hasChildren()){
                                                arrayList.clear();
                                                for (DataSnapshot snap : snapshot.getChildren()){
                                                    final PemesananModel pemesananModel = snap.getValue(PemesananModel.class);
                                                    String tanggalpesan = pemesananModel.getTanggalpemesanan();

                                                    Date tanggal = null;
                                                    try {
                                                        tanggal = sdf.parse(tanggalpesan);
                                                    } catch (ParseException e) {
                                                        e.printStackTrace();
                                                    }
                                                    Date tanggalpilih = null;
                                                    try {
                                                        tanggalpilih = sdf.parse(date);
                                                    } catch (ParseException e) {
                                                        e.printStackTrace();
                                                    }

                                                    if (tanggal.compareTo(tanggalpilih)==0){
                                                        if (pemesananModel.getStatuspemesanan().contains("Booked")){
                                                            arrayList.add(pemesananModel);
                                                        }

                                                    }

                                                }

                                                myadapter =  new BookingListAdapterBaru(getActivity().getApplicationContext(),arrayList);
                                                recview.setAdapter(myadapter);
                                                myadapter.notifyDataSetChanged();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });





                                }
                            },year,month,day);
                            datePickerDialog.show();
                        }
                    });
                }
            }
        });


        return view;
    }


}