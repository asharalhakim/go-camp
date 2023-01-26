package com.example.campin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class List_User_Activity extends AppCompatActivity {

    SwipeRefreshLayout srl_main;
    ArrayList<String> array_nama_lengkap,array_nama_pengguna,array_nik,array_nohp,array_email,array_kata_sandi;
    ProgressDialog progressDialog;
    ListView listProses;
    ImageView ic_kembali;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        //set variable sesuai dengan widget yang digunakan
        listProses = findViewById(R.id.LV);
        srl_main    = findViewById(R.id.swipe_container);
        progressDialog = new ProgressDialog(this);

        ic_kembali = findViewById(R.id.ic_kembali_ubahprofil);

        ic_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_User_Activity.this,BerandaAdminActivity.class);
                startActivity(intent);
            }
        });

        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scrollRefresh();
                srl_main.setRefreshing(false);
            }
        });
        // Scheme colors for animation
        srl_main.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)

        );

        scrollRefresh();
    }

    public void scrollRefresh(){
        progressDialog.setMessage("Mengambil Data User....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        },300);
    }

    void initializeArray(){
        array_nama_lengkap  = new ArrayList<String>();
        array_nama_pengguna = new ArrayList<String>();
        array_nik           = new ArrayList<String>();
        array_nohp          = new ArrayList<String>();
        array_email         = new ArrayList<String>();
        array_kata_sandi    = new ArrayList<String>();


        array_nama_lengkap.clear();
        array_nama_pengguna.clear();
        array_nik.clear();
        array_nohp.clear();
        array_email.clear();
        array_kata_sandi.clear();


    }

    public void getData(){
        initializeArray();
        AndroidNetworking.get("https://tkjbpnup.com/kelompok_8/getdatauser.php")
                .setTag("Get Data")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();

                        try{
                            Boolean status = response.getBoolean("status");
                            if(status){
                                JSONArray ja = response.getJSONArray("result");
                                Log.d("respon",""+ja);
                                for(int i = 0 ; i < ja.length() ; i++){
                                    JSONObject jo = ja.getJSONObject(i);


                                    array_nama_lengkap.add(jo.getString("nama_lengkap"));
                                    array_nama_pengguna.add(jo.getString("nama_pengguna"));
                                    array_nik.add(jo.getString("nik"));
                                    array_nohp.add(jo.getString("nohp"));
                                    array_email.add(jo.getString("email"));
                                    array_kata_sandi.add(jo.getString("kata_sandi"));


                                }

                                //Menampilkan data berbentuk adapter menggunakan class CLVDataUser
                                final CLV_DataUser adapter = new CLV_DataUser(List_User_Activity.this,array_nama_lengkap,array_nama_pengguna,
                                        array_nik,array_nohp,array_email,array_kata_sandi);
                                //Set adapter to list
                                listProses.setAdapter(adapter);

                                //edit and delete
                                listProses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("TestKlik",""+array_nama_lengkap.get(position));
                                        Toast.makeText(List_User_Activity.this, array_nama_lengkap.get(position), Toast.LENGTH_SHORT).show();

                                        //Setelah proses koneksi keserver selesai, maka aplikasi akan berpindah class
                                        //DataActivity.class dan membawa/mengirim data-data hasil query dari server.
                                        Intent i = new Intent(List_User_Activity.this, DetailDataUserAdminActivity.class);

                                        i.putExtra("nama_pengguna",array_nama_pengguna.get(position));
                                        i.putExtra("nama_lengkap",array_nama_lengkap.get(position));
                                        i.putExtra("nik",array_nik.get(position));
                                        i.putExtra("nohp",array_nohp.get(position));
                                        i.putExtra("email",array_email.get(position));
                                        i.putExtra("kata_sandi",array_kata_sandi.get(position));


                                        startActivity(i);
                                    }
                                });


                            }else{
                                Toast.makeText(List_User_Activity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

                            }

                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

}