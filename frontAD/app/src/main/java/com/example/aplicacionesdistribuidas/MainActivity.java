package com.example.aplicacionesdistribuidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText txtusuario, txtcontrasena;
    TextView tvregistrarse;
    Button btningresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vincular();


        tvregistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, signup.class);
                startActivity(intent);
            }
        });

        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void vincular(){
        txtusuario = (EditText)findViewById(R.id.txtusuario);
        txtcontrasena = (EditText) findViewById(R.id.txtcontrasena);
        tvregistrarse = (TextView) findViewById(R.id.tvregistrarse);
        btningresar = (Button) findViewById(R.id.btningresar);
    }
}
