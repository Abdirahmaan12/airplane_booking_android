package com.example.hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class pageone extends AppCompatActivity {
    RecyclerView room_rac;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pageone);

        room_rac=findViewById(R.id.room_rec);
        room_rac.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(pageone.this);
        room_rac.setLayoutManager(layoutManager);


        loadallroom();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.roommenu,menu);


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.titile)
        {
        startActivity(new Intent(pageone.this, orders.class));

        }
        if(item.getItemId()==R.id.logout)
        {
            startActivity(new Intent(pageone.this, login.class));

        }
        return super.onOptionsItemSelected(item);
    }

    private void loadallroom() {
        String url ="http://10.0.2.2/airplanebooking/airplanes.php";
        JsonArrayRequest ja = new JsonArrayRequest(Request.Method.POST,
                url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject ob= response.getJSONObject(0);
                    if(ob.getString("code").equals("yes"))
                    {
                        adapter = new airplaneadapter(response, pageone.this);
                        room_rac.setAdapter(adapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(pageone.this, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        Volley.newRequestQueue(pageone.this).add(ja);
    }


}