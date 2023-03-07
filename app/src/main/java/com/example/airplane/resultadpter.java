package com.example.hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class resultadpter extends RecyclerView.Adapter<resultadpter.Myviewholder> {
    JSONArray res;
    Context ctx;
    public resultadpter(JSONArray response, orders orders) {
        res=response;
        ctx=orders;

    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.resultsinglerow, parent, false);
        return new Myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        try {
            JSONObject ob=res.getJSONObject(position);
            holder.seatname.setText(ob.getString("seat"));
            holder.username.setText(ob.getString("username"));
            holder.airplane_name.setText(ob.getString("airplane_name"));
            holder.date.setText(ob.getString("date"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public int getItemCount() {
        return res.length();
    }

    class Myviewholder extends RecyclerView.ViewHolder {
        TextView username, airplane_name, seatname , date;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.idusername);
            seatname = itemView.findViewById(R.id.seatname);
            airplane_name = itemView.findViewById(R.id.idname);
            date = itemView.findViewById(R.id.iddate);

        }
    }
}
