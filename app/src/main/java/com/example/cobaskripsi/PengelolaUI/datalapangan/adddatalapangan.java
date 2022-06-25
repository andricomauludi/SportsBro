package com.example.cobaskripsi.PengelolaUI.datalapangan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;
import com.example.cobaskripsi.preferences;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class adddatalapangan extends AppCompatActivity {

    EditText namalapangan;
    Button submit, back;
    CheckBox[] addcheckbox;
    String jamtersedia, jamtersediabarustring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddatalapangan);

        namalapangan=findViewById(R.id.addnamalapangan);
        submit=findViewById(R.id.tambahdatalapangan);

        LinearLayout layout = new LinearLayout(adddatalapangan.this);
        layout=findViewById(R.id.rootlayoutadddatalapangan);
        ArrayList<String> spinnerArray = new ArrayList<String>(24);
        ArrayList<String> jamasli = new ArrayList<String>(24);
        for (int i=0; i<24;i++){
            jamasli.add(Integer.toString(i));
            spinnerArray.add(jamdictionary(Integer.toString(i)));
        }


        Spinner spinner = new Spinner(this);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinner.setAdapter(spinnerArrayAdapter);

        layout.addView(spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                LinearLayout layout2 = new LinearLayout(adddatalapangan.this);
                layout2=findViewById(R.id.rootlayoutadddatalapangan2);
                layout2.removeAllViews();
                ArrayList<String> spinnerArray2 = new ArrayList<String>(24);
                ArrayList<String> jamasli2 = new ArrayList<String>(24);
                int giliran=position+1;
                int giliran2=0;
                int semua=0;
                for (int i=0; i<23;i++){
                    if (giliran<24){
                        spinnerArray2.add(jamdictionary(Integer.toString(giliran)));
                        giliran=giliran+1;
                        semua++;
                    }
                    else {
                        spinnerArray2.add(jamdictionary(Integer.toString(giliran2)));
                        giliran2++;
                        semua++;
                    }

                }


                Spinner spinner2 = new Spinner(adddatalapangan.this);
                ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(adddatalapangan.this, android.R.layout.simple_spinner_dropdown_item, spinnerArray2);
                spinner2.setAdapter(spinnerArrayAdapter2);

                layout2.addView(spinner2);
                int finalSemua = semua;
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position2, long id2) {


                        String jamawal = dictionarytojam(spinnerArray.get(position));
                        String jamakhir = dictionarytojam(spinnerArray2.get(position2));
                        ArrayList<String> jamtersediabaru = new ArrayList<>();
                        jamtersediabaru.clear();


                        if (Integer.parseInt(jamakhir)<Integer.parseInt(jamawal)){

                            for (int i=Integer.parseInt(dictionarytojam(spinnerArray.get(position)));i<24;i++){
                                jamtersediabaru.add(Integer.toString(i));
                            }
                            for (int i=0;i<=Integer.parseInt(dictionarytojam(spinnerArray2.get(position2)));i++){
                                jamtersediabaru.add(Integer.toString(i));
                            }


                        }else{
                            for (int i=Integer.parseInt(jamawal);i<=Integer.parseInt(jamakhir);i++){
                                jamtersediabaru.add(Integer.toString(i));
                            }
                        }
                        String str = jamtersediabaru.toString();
                        String noSpaceStr = str.replaceAll("\\s", "");
                        jamtersediabarustring = noSpaceStr;

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });



            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        //checkboxInsert();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidation()){
                    processinsert();
                    startActivity(new Intent(getApplicationContext(),datalapanganmitra.class));
                }
            }
        });

        back=findViewById(R.id.backdatalapangan);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),datalapanganmitra.class));
            }
        });
    }

    private void spinnerInsert() {


    }

    private void processinsert(){
        /**final ArrayList<String> arrcheck = new ArrayList<>();
        for(int i = 0; i < addcheckbox.length; i++) {
            if (addcheckbox[i].isChecked()) {
                arrcheck.add(Integer.toString(i));
            }}
        String str = arrcheck.toString();
        String noSpaceStr = str.replaceAll("\\s", "");
        jamtersedia = noSpaceStr;
**/
        String key = FirebaseDatabase.getInstance().getReference().child("pemesanan").push().getKey();
        Map<String,Object> map = new HashMap<>();
        map.put("namalapangan",namalapangan.getText().toString());
        map.put("idtempat", preferences.getIdtempatmitra(getApplicationContext()));
        map.put("jamtersedia",jamtersediabarustring);
        map.put("idlapangan",key);
        FirebaseDatabase.getInstance().getReference().child("lapangan").child(key)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        namalapangan.setText((""));
                        Toast.makeText(getApplicationContext(),"Data telah ditambahkan",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Data tidak dapat ditambahkan",Toast.LENGTH_LONG).show();
                    }
                });
    }

   /** private void checkboxInsert(){
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.rootlayoutdatalapangan);
        LinearLayout row = new LinearLayout(this);

        addcheckbox = new CheckBox[24];
        linearLayout.removeAllViews();
        if(linearLayout!=null){
            for(int i = 0; i < addcheckbox.length; i++) {
                CheckBox cb = new CheckBox(getApplicationContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.setMargins(10,15,10,10);
                cb.setLayoutParams(params);
                String str = jamdictionary(Integer.toString(i));
                cb.setText(str);
                // cb.setBackgroundColor(getResources().getColor(R.color.white));
                cb.setTextColor(getResources().getColor(R.color.darkteal));
                cb.setScaleX((float) 1.3);
                cb.setScaleY((float) 1.3);
                cb.setId(i);
                linearLayout.addView(cb);


                String abc =String.valueOf(i);

                int resID = getResources().getIdentifier(abc, "id", this.getPackageName());
                addcheckbox[i] = (CheckBox) findViewById(resID );
            }
        }
    }**/

   private String jamdictionary(String str){
       Map<String, String> m = new HashMap<>();
       m.put("0","07:00");
       m.put("1","08:00");
       m.put("2","09:00");
       m.put("3","10:00");
       m.put("4","11:00");
       m.put("5","12:00");
       m.put("6","13:00");
       m.put("7","14:00");
       m.put("8","15:00");
       m.put("9","16:00");
       m.put("10","17:00");
       m.put("11","18:00");
       m.put("12","19:00");
       m.put("13","20:00");
       m.put("14","21:00");
       m.put("15","22:00");
       m.put("16","23:00");
       m.put("17","24:00");
       m.put("18","01:00");
       m.put("19","02:00");
       m.put("20","03:00");
       m.put("21","04:00");
       m.put("22","05:00");
       m.put("23","06:00");
       String dict = m.get(str);
       return dict;
   }

    private String dictionarytojam(String str){
        Map<String, String> m = new HashMap<>();
        m.put("07:00","0");
        m.put("08:00","1");
        m.put("09:00","2");
        m.put("10:00","3");
        m.put("11:00","4");
        m.put("12:00","5");
        m.put("13:00","6");
        m.put("14:00","7");
        m.put("15:00","8");
        m.put("16:00","9");
        m.put("17:00","10");
        m.put("18:00","11");
        m.put("19:00","12");
        m.put("20:00","13");
        m.put("21:00","14");
        m.put("22:00","15");
        m.put("23:00","16");
        m.put("24:00","17");
        m.put("01:00","18");
        m.put("02:00","19");
        m.put("03:00","20");
        m.put("04:00","21");
        m.put("05:00","22");
        m.put("06:00","23");
        String dict = m.get(str);
        return dict;
    }

    public  boolean checkValidation() {
        String namalapangan1 = namalapangan.getText().toString().trim();
       /** boolean atLeastOneChecked = false;
        for (int i = 0; i < addcheckbox.length; i++){
            if (addcheckbox[i].isChecked()) {
                atLeastOneChecked = true;
                break;
            }
        }**/

        if (namalapangan1.length() <= 0) {
            namalapangan.requestFocus();
            namalapangan.setError("Isi Nama Lapangan");
            return false;
        } /**else if (!atLeastOneChecked){
            Toast.makeText(adddatalapangan.this,
                    "Tidak ada tanggal yang terpilih", Toast.LENGTH_LONG).show();
            return false;
        }**/else{
            return true;
        }
    }

    public void onBackPressed(){
        startActivity(new Intent(getApplicationContext(),datalapanganmitra.class));
        finish();
    }
}