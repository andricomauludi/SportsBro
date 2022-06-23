package com.example.cobaskripsi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class preferences {
    private static final String DATA_LOGIN = "status_login",
            DATA_ROLE = "role", username="username", userID="userid", email="email", notelp="notelp",jenisolahraga="jenis_olahraga", idtempatmitra="idtempatmira";

    private static SharedPreferences getSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setDataRole(Context context,String data){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(DATA_ROLE,data);
        editor.apply();
    }

    public static String getDataRole(Context context){
        return getSharedPreferences(context).getString(DATA_ROLE,"");
    }

    public static void setIdtempatmitra(Context context,String data){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(idtempatmitra,data);
        editor.apply();
    }

    public static String getIdtempatmitra(Context context){
        return getSharedPreferences(context).getString(idtempatmitra,"");
    }

    public static void setJenisolahraga(Context context,String data){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(jenisolahraga,data);
        editor.apply();
    }

    public static String getJenisolahraga(Context context){
        return getSharedPreferences(context).getString(jenisolahraga,"");
    }

    public static void setEmail(Context context,String data){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(email,data);
        editor.apply();
    }

    public static String getEmail(Context context){
        return getSharedPreferences(context).getString(email,"");
    }

    public static void setNotelp(Context context,String data){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(notelp,data);
        editor.apply();
    }

    public static String getNotelp(Context context){
        return getSharedPreferences(context).getString(notelp,"");
    }

    public static void setUserID(Context context,String data){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(userID,data);
        editor.apply();
    }

    public static String getUserID(Context context){
        return getSharedPreferences(context).getString(userID,"");
    }

    public static void setUsername(Context context,String data){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(username,data);
        editor.apply();
    }

    public static String getUsername(Context context){
        return getSharedPreferences(context).getString(username,"");
    }

    public static void setDataLogin(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(DATA_LOGIN,status);
        editor.apply();
    }

    public static boolean getDataLogin(Context context){
        return getSharedPreferences(context).getBoolean(DATA_LOGIN,false);
    }

    public static void clearData(Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(DATA_ROLE);
        editor.remove(DATA_LOGIN);
        editor.remove(username);
        editor.remove(userID);
        editor.remove(email);
        editor.remove(notelp);
        editor.remove(jenisolahraga);
        editor.remove(idtempatmitra);
        editor.apply();
    }

    public static void clearJenisolahraga(Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(jenisolahraga);
        editor.apply();
    }

}
