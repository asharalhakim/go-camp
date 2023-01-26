package com.example.campin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class BerandaAdminActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    RelativeLayout fitur_user, fitur_kontak, fitur_saran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda_admin);

        drawerLayout = findViewById(R.id.drawer_layout1);
        fitur_kontak = findViewById(R.id.fitur2_kontak);
        fitur_user   = findViewById(R.id.fitur3_user);
        fitur_saran  = findViewById(R.id.fitur4_saran);

        fitur_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BerandaAdminActivity.this,List_User_Activity.class);
                startActivity(intent);
            }
        });

        fitur_saran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BerandaAdminActivity.this,List_Saran_Activity.class);
                startActivity(intent);
            }
        });

        fitur_kontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BerandaAdminActivity.this, AlatAdminActivity.class);
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

        redirectActivity(this,ProfilAdminActivity.class);

    }

    public void Clickinfo(View view) {

        redirectActivity(this,InfoAdminActivity.class);

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