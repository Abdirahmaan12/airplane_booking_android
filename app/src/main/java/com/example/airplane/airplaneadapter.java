package com.example.hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class airplaneadapter extends RecyclerView.Adapter<airplaneadapter.MyViewHolder> {
    JSONArray res;
    Context ctx;

    public airplaneadapter(JSONArray response, pageone pageone) {
        res=response;
        ctx=pageone;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.roomsinglerow, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        try {
            JSONObject ob = res.getJSONObject(position);
            holder.txtname.setText(ob.getString("name"));
            holder.txtprice.setText(ob.getString("price"));
            holder.txtseat.setText(ob.getString("seat"));


            String imgurl ="http://10.0.2.2/airplanebooking/"+ob.getString("img");
            Glide.with(ctx).load(imgurl).into(holder.airplanesimg);


            holder.btnbooknow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //register airplane
                    try {
                        preManager pr = new preManager(ctx);
                        String airplanes_id = ob.getString("airplanes_id");
                        String customers_id = pr.getcustomers_id();

                        booking(airplanes_id, customers_id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void booking(String airplanes_id, String customers_id) {
        String url ="http://10.0.2.2/airplanebooking/booking.php?airplanes_id="+airplanes_id+"&customers_id="+customers_id;

        StringRequest sr = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ctx, response, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        Volley.newRequestQueue(ctx).add(sr);
    }


    @Override
    public int getItemCount() {
        return res.length();
    }

    class MyViewHolder extends RecyclerView.ViewHolder

    {

        ImageView airplanesimg;
        TextView txtname, txtprice, txtseat;
        Button btnbooknow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            airplanesimg = itemView.findViewById(R.id.airplanesimg);
            txtname = itemView.findViewById(R.id.txtname);
            txtprice = itemView.findViewById(R.id.txtprice);
            txtseat = itemView.findViewById(R.id.txtseat);

            btnbooknow = itemView.findViewById(R.id.btnbooknow);



        }
    }
}
