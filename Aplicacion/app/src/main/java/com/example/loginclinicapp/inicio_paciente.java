package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class inicio_paciente extends AppCompatActivity {

    Button btnVerMisTurnos, btnPedirTurno, btnHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_paciente);

        vincular();

    }

    private void vincular(){
        btnVerMisTurnos = (Button) findViewById(R.id.btnVerMisTurnos);
        btnPedirTurno = (Button) findViewById(R.id.btnPedirTurno);
        btnHistorial = (Button) findViewById(R.id.btnHistorial);
    }
}


