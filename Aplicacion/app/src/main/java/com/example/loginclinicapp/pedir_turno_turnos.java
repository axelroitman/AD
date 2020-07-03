package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class pedir_turno_turnos extends AppCompatActivity {

    TextView txtDia, turnosDeEspecialidad;
    RecyclerView cardsTurnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_turno_turnos);
        vincular();

        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr",0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");
        final int idEsp = i.getIntExtra("idEspecialidad", 0);
        final String matriculaSeleccionado = i.getStringExtra("matriculaSeleccionado");
        final String nombreMedico = i.getStringExtra("nombreMedico");
        final String nombreEsp = i.getStringExtra("nombreEsp");


    }

    private void vincular(){
        txtDia = (TextView) findViewById(R.id.txtdia);
        turnosDeEspecialidad = (TextView) findViewById(R.id.turnosDeEspecialidad);
        cardsTurnos = (RecyclerView) findViewById(R.id.cardsTurnos);
    }
}
