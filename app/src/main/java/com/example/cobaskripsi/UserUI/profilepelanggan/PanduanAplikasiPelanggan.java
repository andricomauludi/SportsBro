package com.example.cobaskripsi.UserUI.profilepelanggan;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaskripsi.R;

public class PanduanAplikasiPelanggan extends AppCompatActivity {

    TextView isipanduan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan_aplikasi_pelanggan);

        isipanduan=findViewById(R.id.isipanduanpelanggan);
        isipanduan.setText(
                "Apa itu SPORTSBRO. ?\n\n " +
                        "SPORTSBRO. Merupakan aplikasi pencarian dan pemesanan sarana olahraga yang dapat dilakukan melalui smartphone. Aplikasi ini tersedia dalam sistem operasi Android saja\n" +
                        "\n\n" +
                        "Bagaimana saya dapat mencari sarana olahraga terdekat saya?\n\n" +
                        "Anda dapat melakukannya dengan memilih salah satu olahraga yang ingin dicari sarana nya melalui tab Home. Setelah menekan salah satu olahraga, anda mencari sesuai dengan filter jarak yang anda ingin cari. Setelah itu sistem akan menampilkan sarana olahraga terdekat dari anda sesuai jarak yang dicari.\n" +
                        "\n\n" +
                        "Bagaimana saya memesan sarana olahraga?\n\n" +
                        "Anda dapat melakukannya dengan melanjutkan langkah mencari sarana olahraga dengan cara menekan salah satu tempat yang diinginkan, lalu memilih salah satu lapangan yang dimiliki oleh tempat pada detail tempat. Lalu memilih tanggal dan mengisi data, sistem akan menampilkan jadwal yang kosong." +
                        " Apabila jadwal sudah penuh maka sistem akan menampilkan bahwa jadwal sudah penuh. " +
                        "Setelah anda memesan maka pesanan anda akan masuk ke tab pemesanan dan tunggu hingga detail pemesanan berubah tulisan menjadi “BOOKED”. Apabila terdapat kendala, anda dapat menghubungi pengelola lapangan melalui whatsapp atau telepon pada detail tempat pengelola lapangan tersebut.\n" +
                        "\n\n" +
                        "Bagaimana saya melihat riwayat pemesanan saya?\n\n" +
                        "Anda dapat melakukannya dengan memilih tab Pemesanan. Lalu mencari riwayat yang anda ingin lihat. Tab ini menampilkan riwayat yang menunggu konfirmasi, yang sudah terpesan, yang ditolak oleh pengelola, dan yang sudah kadaluarsa.\n"

        );
    }
}