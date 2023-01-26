package com.example.campin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class SewaActivity extends AppCompatActivity {
    int harga_sewa_mobil,jml_lmsw,ttl_hargasewa,jml_uang;
    String s_nama;

    private CheckBox tenda, hammock, slip, matras;
    TextView harga_mobil;
    EditText lama_sewa,nama_penyewa;

    String list_mobil[] = {"Tenda","Hammock","Slipping Bag","Matras"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyewaan);
        nama_penyewa = findViewById(R.id.nama_penyewa);
        harga_mobil = findViewById(R.id.harga_mobil);
        lama_sewa = findViewById(R.id.lama_sewa);
        tenda = findViewById(R.id.tenda);
        hammock = findViewById(R.id.hammock);
        slip = findViewById(R.id.slip);
        matras = findViewById(R.id.matras);
    }

    public void tmbl_OK (View view) {
        jml_lmsw= Integer.parseInt(lama_sewa.getText().toString());
        s_nama = nama_penyewa.getText().toString();


        if(tenda.isChecked() &&
        hammock.isChecked() &&
        slip.isChecked() &&
        matras.isChecked()){
            harga_sewa_mobil = 80000;
            ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
            harga_mobil.setText(Integer.toString(ttl_hargasewa));
        }else if (tenda.isChecked() &&
                    hammock.isChecked() &&
                    slip.isChecked()){
                harga_sewa_mobil = 70000;
                ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
                harga_mobil.setText(Integer.toString(ttl_hargasewa));
        } else if (hammock.isChecked() &&
        slip.isChecked() &&
        matras.isChecked()){
            harga_sewa_mobil = 65000;
            ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
            harga_mobil.setText(Integer.toString(ttl_hargasewa));
        } else if(tenda.isChecked() &&
        hammock.isChecked()){
            harga_sewa_mobil = 55000;
            ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
            harga_mobil.setText(Integer.toString(ttl_hargasewa));
        } else if (tenda.isChecked() &&
                slip.isChecked()){
            harga_sewa_mobil = 45000;
            ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
            harga_mobil.setText(Integer.toString(ttl_hargasewa));
        } else if (tenda.isChecked() &&
                matras.isChecked()){
            harga_sewa_mobil = 40000;
            ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
            harga_mobil.setText(Integer.toString(ttl_hargasewa));
        } else if (hammock.isChecked() &&
                slip.isChecked()){
            harga_sewa_mobil = 40000;
            ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
            harga_mobil.setText(Integer.toString(ttl_hargasewa));
        } else if (hammock.isChecked() &&
                matras.isChecked()){
            harga_sewa_mobil = 35000;
            ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
            harga_mobil.setText(Integer.toString(ttl_hargasewa));
        } else if(slip.isChecked() &&
                matras.isChecked()){
            harga_sewa_mobil = 25000;
            ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
            harga_mobil.setText(Integer.toString(ttl_hargasewa));
        } else if (tenda.isChecked()){
            harga_sewa_mobil = 30000;
            ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
            harga_mobil.setText(Integer.toString(ttl_hargasewa));
        } else if (hammock.isChecked()){
            harga_sewa_mobil = 25000;
            ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
            harga_mobil.setText(Integer.toString(ttl_hargasewa));
        } else if (slip.isChecked()){
            harga_sewa_mobil = 15000;
            ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
            harga_mobil.setText(Integer.toString(ttl_hargasewa));
        } else if (matras.isChecked()) {
            harga_sewa_mobil = 10000;
            ttl_hargasewa = jml_lmsw * harga_sewa_mobil;
            harga_mobil.setText(Integer.toString(ttl_hargasewa));
        }

    }

    public void tombol_sewa2(View view) {
        Intent intent = new Intent(SewaActivity.this,RiwayatActivity.class);

        intent.putExtra("nama",s_nama);
        intent.putExtra("alat",list_mobil);
        intent.putExtra("lama",jml_lmsw);
        intent.putExtra("total",ttl_hargasewa);
        intent.putExtra("uang",jml_uang);
        intent.putExtra("kembalian",jml_uang-ttl_hargasewa);

        startActivity(intent);
    }

    public void hal_utama(View view) {
        Intent intent = new Intent(SewaActivity.this,BerandaActivity.class);
        startActivity(intent);
    }
}