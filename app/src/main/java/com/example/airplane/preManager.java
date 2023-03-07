package com.example.hotel;

import android.content.Context;
import android.content.SharedPreferences;

public class preManager {
    SharedPreferences pr;
    SharedPreferences.Editor editor;


    public  preManager (Context ctx)
    {
        pr =ctx.getSharedPreferences("hotel", Context.MODE_PRIVATE);
        editor= pr.edit();

    }

    public void setcustomers_id(String customers_id){
        editor.putString("customers_id", customers_id);
        editor.commit();

    }

    public  String getcustomers_id(){
        return pr.getString("customers_id", "");
    }



}

