package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome extends AppCompatActivity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button btngetstart = findViewById(R.id.btngetstarted);
    btngetstart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent gologin = new Intent(welcome.this, login.class);
            startActivity(gologin);
        }
    });



    }
}