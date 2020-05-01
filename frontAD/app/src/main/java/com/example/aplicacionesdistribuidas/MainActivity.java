package com.example.aplicacionesdistribuidas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText txtusuario, txtcontrasena;
    Button btningresar;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder = new AlertDialog.Builder(this);

        builder.setTitle("Error");
        builder.setMessage("Usuario y/o contraseña incorrectos.");
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.show();

        vincular();



        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void vincular(){
        txtusuario = (EditText)findViewById(R.id.txtusuario);
        txtcontrasena = (EditText) findViewById(R.id.txtcontrasena);
        btningresar = (Button) findViewById(R.id.btningresar);
    }
}
