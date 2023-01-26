package com.example.campin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RiwayatActivity extends AppCompatActivity {
    TextView nama_penyewa,jenis_mobil,lama_sewa,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);
        nama_penyewa = findViewById(R.id.nama_penyewa);
        jenis_mobil = findViewById(R.id.jenis_mobil);
        lama_sewa = findViewById(R.id.lama_sewa);
        total = findViewById(R.id.total);

        nama_penyewa.setText(getIntent().getStringExtra("nama"));
        jenis_mobil.setText(getIntent().getStringExtra("mobil"));
        lama_sewa.setText(String.valueOf(getIntent().getIntExtra("lama",0)));
        total.setText(String.valueOf(getIntent().getIntExtra("total",0)));
    }

    public void hal_utama(View view) {
        Intent intent =new Intent(RiwayatActivity.this, BerandaActivity.class);
        startActivity(intent);
    }
}