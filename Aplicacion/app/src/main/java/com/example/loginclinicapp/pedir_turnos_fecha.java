package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pedir_turnos_fecha extends AppCompatActivity {

    Button btnvolver1, btnlistaespera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_turnos_fecha);
        vincular();

        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr",0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");
        final int idEsp = i.getIntExtra("idEspecialidad", 0);
        final String matriculaSeleccionado = i.getStringExtra("matriculaSeleccionado");

        btnvolver1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(pedir_turnos_fecha.this, pedir_turno.class);
                startActivity(i);
            }
        });

        btnlistaespera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(pedir_turnos_fecha.this, pedir_turno.class);
                //startActivity(i);
            }
        });

    }
    private void vincular(){
        btnvolver1 = (Button) findViewById(R.id.btnvolver1);
        btnlistaespera = (Button) findViewById(R.id.btnlistaespera);
    }
}
