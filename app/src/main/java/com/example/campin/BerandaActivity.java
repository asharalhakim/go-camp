package com.example.campin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class BerandaActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    RelativeLayout fitur_kirimSaran, fitur_kontakstaff, fitur_pemesanan;
    private RecyclerView recyclerView;
    private StaticInfoAdapter staticInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        drawerLayout        = findViewById(R.id.drawer_layout);
        fitur_kontakstaff   = findViewById(R.id.fitur3_kontakstaff);
        fitur_kirimSaran    = findViewById(R.id.fitur4_kirimsaran);
        fitur_pemesanan     = findViewById(R.id.fitur2_peminjaman);

        ArrayList<StaticInfoModel> item = new ArrayList<>();
        item.add(new StaticInfoModel(R.drawable.logo12, "Selamat Datang di GoCamp",
                "Sebuah aplikasi Sewa Alat Outdoor berbasis internet"));
        item.add(new StaticInfoModel(R.drawable.fitur1, "Bingung mencari lokasi penyewaan alat outdoor?",
                "Dengan aplikasi ini akan mempermudah anda menemukan tempat penyewaan alat"));
        item.add(new StaticInfoModel(R.drawable.fitur4, "Proses penyewaan alat yang ribet?",
                "Dengan aplikasi ini, proses penyewaan alat akan mudah dilakukan karena berbasis internet"));

        recyclerView = findViewById(R.id.rv_info);
        staticInfoAdapter = new StaticInfoAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticInfoAdapter);

        fitur_kirimSaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BerandaActivity.this, SaranActivity.class);
                startActivity(intent);
            }
        });

        fitur_pemesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BerandaActivity.this,SewaActivity.class);
                startActivity(intent);
            }
        });

        fitur_kontakstaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BerandaActivity.this, List_Alat_Activity.class);
                startActivity(intent);
            }
        });

    }


    public void ClickNavMenu (View view) {

        openDrawer(drawerLayout);

    }

    public static void openDrawer(DrawerLayout drawerLayout) {

        drawerLayout.openDrawer((GravityCompat.START));

    }

    private static void closeDrawer(DrawerLayout drawerLayout) {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);

        }

    }

    public void Clickberanda(View view) {

        recreate();

    }

    public void Clickprofil(View view) {

        redirectActivity(this,ProfilActivity.class);

    }

    public void Clickriwayat(View view) {

        redirectActivity(this,RiwayatActivity.class);

    }

    public void Clickinfo(View view) {

        redirectActivity(this,InfoActivity.class);

    }

    public void Clickkeluar(View view) {

        redirectActivity(this,LoginActivity.class);

    }

    public static void keluar(Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Keluar");
        builder.setMessage("Apakah anda mau keluar dari aplikasi?");

        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();

                System.exit(0);

            }
        });

        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        builder.show();

    }

    protected void onPause() {
        super.onPause();

        closeDrawer(drawerLayout);

    }

    public static void redirectActivity(Activity activity, Class aClass) {

        Intent intent = new Intent(activity,aClass);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        activity.startActivity(intent);

    }

}