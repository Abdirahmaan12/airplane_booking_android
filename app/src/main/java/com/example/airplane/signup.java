package com.example.hotel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText password;
    Button btncustumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        TextView loginlink = findViewById(R.id.loginlink);
        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gologin = new Intent(signup.this, login.class);
                startActivity(gologin);
            }
        });

        username = findViewById(R.id.idusername);
        email = findViewById(R.id.iduseremail);
        password = findViewById(R.id.iduserpass);
        btncustumer = findViewById(R.id.btncustumer);
        btncustumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txtusername = username.getText().toString();
                String txtuseremail = email.getText().toString();
                String txtpassword = password.getText().toString();


                savecustomer(txtusername, txtuseremail, txtpassword);

            }
        });

    }


    void savecustomer(String username, String email, String password){
        String url ="http://10.0.2.2/airplanebooking/customer_register.php";
        StringRequest sr = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("res",response);
                        Toast.makeText(signup.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(signup.this, "error"+error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }

        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("username", username);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(signup.this);
        queue.add(sr);



    }



}