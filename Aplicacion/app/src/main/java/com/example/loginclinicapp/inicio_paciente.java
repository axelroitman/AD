package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class inicio_paciente extends AppCompatActivity {

    Button btnVerMisTurnos, btnPedirTurno, btnHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_paciente);

        vincular();

        btnVerMisTurnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this, ver_mis_turnos.class);
                startActivity(i);
            }
        });

        btnPedirTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this, pedir_turno.class);
                startActivity(i);
            }
        });

        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this, historial_turnos_pacientes.class);
                startActivity(i);
            }
        });
    }

    private void vincular(){
        btnVerMisTurnos = (Button) findViewById(R.id.btnVerMisTurnos);
        btnPedirTurno = (Button) findViewById(R.id.btnPedirTurno);
        btnHistorial = (Button) findViewById(R.id.btnHistorial);
    }
}


