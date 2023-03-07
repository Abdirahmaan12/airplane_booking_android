package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class orders extends AppCompatActivity {
    RecyclerView orders_rec;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        orders_rec=findViewById(R.id.orders_rec);
        orders_rec.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(orders.this);
        orders_rec.setLayoutManager(layoutManager);

        loadallresults();
    }

    private void loadallresults() {
        String customers_id= new preManager(orders.this).getcustomers_id();

        String url ="http://10.0.2.2/airplanebooking/result.php?customers_id="+customers_id;
        JsonArrayRequest ja = new JsonArrayRequest(Request.Method.GET, url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject ob = response.getJSONObject(0);
                            if(ob.getString("code").equals("yes"))
                            {
                                adapter = new resultadpter(response, orders.this);
                                orders_rec.setAdapter(adapter);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(orders.this, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(orders.this).add(ja);



    }


}