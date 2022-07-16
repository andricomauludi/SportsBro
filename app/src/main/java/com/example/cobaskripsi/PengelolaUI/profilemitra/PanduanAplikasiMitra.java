package com.example.cobaskripsi.PengelolaUI.profilemitra;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;

public class PanduanAplikasiMitra extends AppCompatActivity {

    TextView isipanduan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan_aplikasi_mitra);

        isipanduan=findViewById(R.id.isipanduanmitra);
        isipanduan.setText("Apa itu SPORTSBRO. ?\n\n" +
                "SPORTSBRO. Merupakan aplikasi pencarian dan pemesanan sarana olahraga yang dapat dilakukan melalui smartphone. Aplikasi ini tersedia dalam sistem operasi Android saja\n" +
                "\n\n" +
                "Bagaimana saya mengatur hari buka tempat?\n\n" +
                "Anda dapat melakukannya dengan menekan data tempat pada home. Lalu menekan tombol pengaturan hari buka. Setelah itu anda dapat memilih kapan tempat anda akan libur.\n" +
                "\n" +
                "Bagaimana saya mengelola lapangan pada tempat saya?\n\n" +
                "Anda dapat melakukannya dengan menekan tombol “Data Lapangan” pada home. Setelah itu anda akan melihat lapangan apa saja yang berada di tempat anda. Anda dapat menambahkan data lapangan dengan menekan tombol “+” pada pojok kanan bawah kaku mengisi data yang diperlukan. Anda pun dapat melakukan pengubahan data pada lapangan yang sudah ada dan melakukan penghapusan pada data lapangan yang ada di tempat anda.\n" +
                "\n\n" +
                "Bagaimana saya menerima pemesanan pelanggan?\n\n" +
                "Anda dapat melakukannya dengan menekan tombol pending list pada halaman home . Halaman ini akan menampilkan pemesanan yang dilakukan pelanggan yang belum anda eksekusi. Selanjutnya anda menekan salah satu pemesanan dan anda dapat memilih apakah anda menerima pemesanan ini atau menolaknya. Apabila anda menerima pemesanan maka pemesanan akan masuk kedalam “Booked list”. Fitur ini anda dapat buka pada halaman home lalu memilih tombol “Booked List”. Disini anda dapat melihat pemesanan yang sudah diterima oleh anda.\n"
        );
    }
}