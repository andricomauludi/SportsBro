package com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.detail;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.GetLocation;
import com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.RecListLapangan;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DetailLapangan extends Fragment {

    private StorageReference mStorageReference;

    private DatabaseReference mDatabase;
    Button whatsapp,telepon;
    ListView listView;
    TextView nomortelp;




    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    String namatempat,marker,gambar,jenisolahraga,alamattempat,idtempat,notelp;

    public DetailLapangan() {

    }

    public DetailLapangan(String namatempat, String marker, String gambar,String alamattempat, String idtempat, String jenisolahraga,String notelp) {
        this.namatempat = namatempat;
        this.marker = marker;
        this.gambar = gambar;
        this.alamattempat = alamattempat;
        this.idtempat=idtempat;
        this.jenisolahraga=jenisolahraga;
        this.notelp=notelp;
    }


    public static DetailLapangan newInstance(String param1, String param2) {
        DetailLapangan fragment = new DetailLapangan();
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
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_detail_lapangan, container, false);

        
        
        ImageView imageholder= view.findViewById(R.id.gambarlapangan);
        TextView nameholder= view.findViewById(R.id.namalapangan);
        TextView alamatlapangan = view.findViewById(R.id.lokasilapangan);
        Button direction = view.findViewById(R.id.directionbutton);
        whatsapp = view.findViewById(R.id.whatsapptempat);
        telepon=view.findViewById(R.id.telepontempat);
        nomortelp=view.findViewById(R.id.nomortelpdetaillapangan);

        mStorageReference = FirebaseStorage.getInstance().getReference().child("picture/"+gambar);

        try {
            final File localFile = File.createTempFile("girl","jpg");
            mStorageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            ((ImageView)view.findViewById(R.id.gambarlapangan)).setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    
                }
            })   ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TextView markerholder= view.findViewById(R.id.markerlapangan);






        nameholder.setText(namatempat);
        alamatlapangan.setText(alamattempat);
        nomortelp.setText(notelp);
        String str = marker;

        String[] latlong = str.split(",");
        String lat = latlong[0];
        String lng = latlong[1];
        double latitudeTujuan = Double.valueOf(lat.toString());
        double longitudeTujuan = Double.valueOf(lng.toString());
        final double[] latitudeSaya = new double[1];
        final double[] longitudeSaya = new double[1];



        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(direction.getContext());
                builder.setTitle("Direction");
                builder.setMessage("Anda akan diarahkan google maps untuk melihat rute menuju lokasi");


                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        GetLocation getLocation = new GetLocation(getActivity());
                        Location location = getLocation.getLocation();
                        if (location != null){
                            latitudeSaya[0] = location.getLatitude();
                            longitudeSaya[0] = location.getLongitude();
                        }
                        String uri = "http://maps.google.com/maps?f=d&hl=en&saddr="+latitudeSaya[0]+","+longitudeSaya[0]+"&daddr="+latitudeTujuan+","+longitudeTujuan;
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(Intent.createChooser(intent, "Select an application"));
                    }
                });


                builder.show();

            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(direction.getContext());
                builder.setTitle("Whatsapp");
                builder.setMessage("Anda akan diarahkan ke Whatsapp");


                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String url = "https://api.whatsapp.com/send?phone="+notelp;
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                });


                builder.show();

            }
        });
        telepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(telepon.getContext());
                builder.setTitle("Telepon");
                builder.setMessage("Anda akan diahrahkan ke menu panggilan");


                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+notelp));
                        startActivity(intent);
                    }
                });


                builder.show();

            }
        });

        //markerholder.setText(stringjarak);
      


       // listView = view.findViewById(R.id.listView);

       // final ArrayList<String> list = new ArrayList<>();
        final ArrayList<String> namalapangan = new ArrayList<>();
        final ArrayList<String> idlapangan = new ArrayList<>();
        final ArrayList<String> jamtersedia = new ArrayList<>();
        final ArrayList<String> deskripsilapangan = new ArrayList<>();
       // final ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, list);
       // listView.setAdapter(adapter);

        LinearLayout linearLayout = view.findViewById(R.id.rootlayoutdetail);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("lapangan")
                .orderByChild("idtempat")
                .equalTo(idtempat)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       // list.clear();
                        namalapangan.clear();
                        idlapangan.clear();
                        jamtersedia.clear();
                        deskripsilapangan.clear();
                        for (DataSnapshot childSnapshot : snapshot.getChildren()){
                            LapanganModel lapanganModel = childSnapshot.getValue(LapanganModel.class);
                            //list.add(lapanganModel.getNamalapangan());
                            namalapangan.add(lapanganModel.getNamalapangan());
                            jamtersedia.add(lapanganModel.getJamtersedia());
                            deskripsilapangan.add(lapanganModel.getDeskripsilapangan());
                            String clubkey = childSnapshot.getKey();
                            idlapangan.add(clubkey);

                        }

                        Button[] addlapangan = new Button[namalapangan.size()];
                        linearLayout.removeAllViews();
                        for (int i=0;i<namalapangan.size();i++) {
                            Button addbtn = new Button (getActivity());
                            if (linearLayout!=null){
                                linearLayout.addView(addbtn);
                                addbtn.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
                                addbtn.setText(namalapangan.get(i));

                                addbtn.setId(i);


                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.WRAP_CONTENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                params.setMargins(20,0,10,0);
                                addbtn.setLayoutParams(params);

                                addbtn.setTextColor(getResources().getColor(R.color.white));
                                final int sdk = android.os.Build.VERSION.SDK_INT;
                                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {

                                    addbtn.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.buttonround_gradient) );

                                } else {
                                    addbtn.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.buttonround_gradient));

                                }


                                //addbtn.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.darkrosered), PorterDuff.Mode.MULTIPLY);



                                //addlapangan[i]=view.findViewById(R.id.lapangan1);

                                String abc = String.valueOf(i);

                                int resID = getResources().getIdentifier(abc, "id", getActivity().getPackageName());
                                addlapangan[i] = (Button) view.findViewById(resID );
                                addlapangan[i].setText(namalapangan.get(i));
                                String jenislapangan = namalapangan.get(i);
                                String idlapanganpesan = idlapangan.get(i);
                                String jamtersediapesan = jamtersedia.get(i);
                                String deskripsilapanganpesan = deskripsilapangan.get(i);
                                addlapangan[i].setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getActivity(), addpemesanan.class);
                                        Bundle extras = new Bundle();
                                        extras.putString("IDTEMPAT", idtempat);
                                        extras.putString("NAMATEMPAT", namatempat);
                                        extras.putString("JENISLAPANGAN", jenislapangan);
                                        extras.putString("IDLAPANGAN", idlapanganpesan);
                                        extras.putString("JAMTERSEDIA", jamtersediapesan);
                                        extras.putString("JENISOLAHRAGA", jenisolahraga);
                                        extras.putString("NOMORTELEPON", notelp);
                                        extras.putString("DESKRIPSILAPANGAN",deskripsilapanganpesan);
                                        intent.putExtras(extras);
                                        startActivity(intent);



                                    }
                                });


                            }
                        }

                      //  adapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });




        /**listView = view.findViewById(R.id.listView);

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, list);
        listView.setAdapter(adapter);
**/
        return view;


    }
    public void onBackPressed(){
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new RecListLapangan()).addToBackStack(null).commit();

    }
    private double getDistance(Double latitudeTujuan, Double longitudeTujuan, Double latitudeUser, Double longitudeUser){
        /*VARIABEL */
        Double pi =3.14159265358979;
        Double lat1 = latitudeTujuan;
        Double lon1 = longitudeTujuan;
        Double lat2 = latitudeUser;
        Double lon2 = longitudeUser;
        Double R = 6371e3;

        Double latRad1 = lat1 * (pi / 180);
        Double latRad2 = lat2 * (pi / 180);
        Double deltaLatRad = (lat2 - lat1) * (pi / 180);
        Double deltaLonRad = (lon2 - lon1) * (pi / 180);

        /* RUMUS HAVERSINE*/
        Double a = Math.sin(deltaLatRad / 2) * Math.sin(deltaLatRad / 2) + Math.cos(latRad1) * Math.cos(latRad2) * Math.sin(deltaLonRad / 2) * Math.sin(deltaLonRad / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double s = R * c; // hasil jarak dalam meter
        return s;
    }

    /**public void addButton(LayoutInflater inflater, ViewGroup container){
        View view= inflater.inflate(R.layout.fragment_detail_lapangan, container, false);
        LinearLayout layout =(LinearLayout) view.findViewById(R.id.rootlayout);
        newbtn= new Button(getActivity());
        newbtn.setText("new button");
        layout.addView(newbtn);
    }**/

































}