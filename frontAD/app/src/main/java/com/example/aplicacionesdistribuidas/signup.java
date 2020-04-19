package com.example.aplicacionesdistribuidas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

public class signup extends AppCompatActivity {
    RadioButton rbmedico, rbpaciente;
    Button btnregistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        vincular();

    }

    private void vincular(){

        rbmedico = (RadioButton) findViewById(R.id.rbmedico);
        rbpaciente = (RadioButton) findViewById(R.id.rbpaciente);
        btnregistrarse = (Button) findViewById(R.id.btnregistrarse);
    }
}
