package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class pantalla_cargando extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_cargando);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(pantalla_cargando.this, Login.class);
                startActivity(intent);
            }
        },2000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_pantalla_cargando);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(pantalla_cargando.this, Login.class);
                startActivity(intent);
            }
        },2000);

    }
}
