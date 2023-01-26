package com.example.campin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int mode = 0;

    private static final String pref_name = "session";
    private static final String is_login = "islogin";
    public static final String KEY_NAMA_LENGKAP = "nama_lengkap";
    public static final String KEY_NAMA_PENGGUNA = "nama_pengguna";
    public static final String KEY_NIK= "nik";
    public static final String KEY_NOHP = "nohp";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_KATA_SANDI = "kata_sandi";


    public SessionManager (Context context){
        this.context = context;
        //pref = context.getActivity().getSharedPreferences(pref_name, mode);
        pref = context.getSharedPreferences(pref_name, mode);
        editor = pref.edit();
    }

    public void createSession_id(String nama_lengkap){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_NAMA_LENGKAP, nama_lengkap);
        editor.apply();

    }

    public void createSession_nik(String nama_pengguna){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_NAMA_PENGGUNA, nama_pengguna);
        editor.apply();

    }

    public void createSession_username(String nik){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_NIK, nik);
        editor.apply();
    }

    public void createSession_nohp(String nohp){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_NOHP, nohp);
        editor.apply();
    }

    public void createSession_email(String email){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public void createSession_kata_sandi(String kata_sandi){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_KATA_SANDI, kata_sandi);
        editor.apply();
    }

    public void logOut(){
        editor.clear();
        editor.apply();
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(pref_name, pref.getString(pref_name, null));
        user.put(KEY_NAMA_LENGKAP, pref.getString(KEY_NAMA_LENGKAP, null));
        user.put(KEY_NAMA_PENGGUNA, pref.getString(KEY_NAMA_PENGGUNA, null));
        user.put(KEY_NIK, pref.getString(KEY_NIK, null));
        user.put(KEY_NOHP, pref.getString(KEY_NOHP, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_KATA_SANDI, pref.getString(KEY_KATA_SANDI, null));
        return user;
    }

    public boolean getSPSudahLogin(){
        return pref.getBoolean(is_login, false);
    }

}