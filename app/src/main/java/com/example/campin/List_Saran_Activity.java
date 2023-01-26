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

public class List_Saran_Activity extends AppCompatActivity {

    SwipeRefreshLayout srl_main;
    ProgressDialog progressDialog;
    ListView listView;
    ImageView ic_kembali;
    private Context mContext;

    ArrayList<String> array_nama,array_nim,array_saran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_saran);

        //set variable sesuai dengan widget yang digunakan
        listView = findViewById(R.id.LV);
        srl_main = findViewById(R.id.swipe_container);
        progressDialog = new ProgressDialog(this);
        ic_kembali = findViewById(R.id.ic_kembali_saran);


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



        ic_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_Saran_Activity.this, BerandaAdminActivity.class);
                startActivity(intent);
            }
        });

    }

    public void scrollRefresh(){
        progressDialog.setMessage("Mengambil Data.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        },2000);
    }


    void initializeArray(){
        array_nama       = new ArrayList<String>();
        array_nim     = new ArrayList<String>();
        array_saran    = new ArrayList<String>();

        array_nama.clear();
        array_nim.clear();
        array_saran   = new ArrayList<String>();

    }

    public void getData(){
        initializeArray();
        AndroidNetworking.get("https://tkjbpnup.com/kelompok_8/getsaran.php")
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

                                    array_nama.add(jo.getString("nama"));
                                    array_nim.add(jo.getString("nim"));
                                    array_saran.add(jo.getString("saran"));


                                }

                                //Menampilkan data berbentuk adapter menggunakan class CLVDataUser
                                final CLV_Saran2 adapter = new CLV_Saran2(List_Saran_Activity.this,array_nama,array_nim,array_saran);
                                //Set adapter to list
                                listView.setAdapter(adapter);


//edit and delete
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("TestKlik",""+array_nama.get(position));
                                        Toast.makeText(List_Saran_Activity.this, array_nama.get(position), Toast.LENGTH_SHORT).show();

                                        //Setelah proses koneksi keserver selesai, maka aplikasi akan berpindah class
                                        //DataActivity.class dan membawa/mengirim data-data hasil query dari server.
                                        Intent i = new Intent(List_Saran_Activity.this, List_Saran_Activity.class);
                                        i.putExtra("nama",array_nama.get(position));
                                        i.putExtra("nim",array_nim.get(position));
                                        i.putExtra("saran",array_saran.get(position));
                                        startActivity(i);
                                    }
                                });


                            }else{
                                Toast.makeText(List_Saran_Activity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

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