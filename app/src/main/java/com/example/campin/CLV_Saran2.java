package com.example.campin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CLV_Saran2 extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<String> vnama;
    private ArrayList<String> vnim;
    private ArrayList<String> vsaran;

    public CLV_Saran2(Activity context, ArrayList<String> nama, ArrayList<String> nim, ArrayList<String> saran)
    {
        super(context, R.layout.list_saran,nama);
        this.context = context;
        this.vnama = nama;
        this.vnim = nim;
        this.vsaran = saran;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView=inflater.inflate(R.layout.list_saran, null, true);

        TextView nama = rowView.findViewById(R.id.nama);
        TextView nim = rowView.findViewById(R.id.nim);
        TextView saran = rowView.findViewById(R.id.saran1);

        nama.setText(vnama.get(position));
        nim.setText(vnim.get(position));
        saran.setText(vsaran.get(position));

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.down_from_top);
        animation.setDuration(500);
        rowView.startAnimation(animation);

        return  rowView;
    }
}
