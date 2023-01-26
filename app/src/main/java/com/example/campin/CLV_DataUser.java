package com.example.campin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CLV_DataUser extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<String> vNamaLengkap;
    private ArrayList<String> vNamaPenguna;
    private ArrayList<String> vNIK;
    private ArrayList<String> vNoHP;
    private ArrayList<String> vEmail;
    private ArrayList<String> vKataSandi;


    public CLV_DataUser(Activity context, ArrayList<String> NamaLengkap, ArrayList<String> NamaPenguna,
                        ArrayList<String> NIK, ArrayList<String> NoHP, ArrayList<String> Email, ArrayList<String> KataSandi) {
        super(context, R.layout.list_item_user,NamaPenguna);
        this.context = context;
        this.vNamaLengkap = NamaLengkap;
        this.vNamaPenguna = NamaPenguna;
        this.vNIK = NIK;
        this.vNoHP = NoHP;
        this.vEmail = Email;
        this.vKataSandi = KataSandi;

    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        //Load Custom Layout untuk list
        View rowView= inflater.inflate(R.layout.list_item_user, null, true);

        //Declarasi komponen
        TextView name             = rowView.findViewById(R.id.nama_user);
        TextView nik              = rowView.findViewById(R.id.nik_user);
        CircleImageView foto      = rowView.findViewById(R.id.pic_profil_user);

        //Set Parameter Value sesuai widget textview
        name.setText(vNamaPenguna.get(position));
        nik.setText(vNIK.get(position));


        //Load the animation from the xml file and set it to the row
        //load animasi untuk listview
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.down_from_top);
        animation.setDuration(500);
        rowView.startAnimation(animation);

        return rowView;
    }



}
