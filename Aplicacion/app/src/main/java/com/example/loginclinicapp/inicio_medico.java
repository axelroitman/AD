package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class inicio_medico extends AppCompatActivity {

    Button btnVerMiAgenda,btnAgregarTurnos, btnBorrarTurnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_medico);
        vincular();

        btnAgregarTurnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_medico.this, aniadir_turnos.class);
                startActivity(i);
            }
        });
        btnVerMiAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_medico.this, ver_agenda.class);
                startActivity(i);
            }
        });
        btnBorrarTurnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_medico.this, eliminar_turnos.class);
                startActivity(i);
            }
        });
    }

    private void vincular(){
        btnVerMiAgenda = (Button) findViewById(R.id.btnVerMiAgenda);
        btnBorrarTurnos = (Button) findViewById(R.id.btnEliminarTurnos);
        btnAgregarTurnos = (Button) findViewById(R.id.btnAddTurnos);
    }
}
