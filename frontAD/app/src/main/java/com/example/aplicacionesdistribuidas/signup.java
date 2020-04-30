package com.example.aplicacionesdistribuidas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class signup extends AppCompatActivity {
    //TextView txttituloCambiarContra;
    EditText txtusuario, txtcontraseña1, txtcontraseña2;
    Button btnguardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        vincular();

    }

    private void vincular(){

        txtusuario = (EditText) findViewById(R.id.txtusuario);
        txtcontraseña1 = (EditText) findViewById(R.id.txtcontraseña1);
        txtcontraseña2 = (EditText) findViewById(R.id.txtcontraseña2);
        btnguardar = (Button) findViewById(R.id.btnguardar);
    }
}
