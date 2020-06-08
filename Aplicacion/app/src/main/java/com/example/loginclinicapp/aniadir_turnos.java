package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class aniadir_turnos extends AppCompatActivity {

    Spinner spinnerespecialidades,spinnerDuracion;
    EditText edTextHoraInicio, edTextHoraFin, edTextFechaInicial, edTextFechaFinal;
    CheckBox checkbox_lunes,checkbox_martes,checkbox_miercoles,checkbox_jueves,checkbox_viernes,checkbox_sabado,checkbox_domingo;
    Button btnaniadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_turnos);
        vincular();

        btnaniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(aniadir_turnos.this, inicio_medico.class);
                startActivity(i);
            }
        });
    }

    private void vincular(){
        spinnerDuracion = (Spinner) findViewById(R.id.spinnerDuracion);
        spinnerespecialidades = (Spinner) findViewById(R.id.spinnerespecialidades);
        edTextFechaFinal = (EditText) findViewById(R.id.edTextFechaFinal);
        edTextFechaInicial = (EditText) findViewById(R.id.edTextFechaInicial);
        edTextHoraFin = (EditText) findViewById(R.id.edTextHoraFin);
        edTextHoraInicio = (EditText) findViewById(R.id.edTextHoraInicio);
        checkbox_lunes = (CheckBox) findViewById(R.id.checkbox_lunes);
        checkbox_martes = (CheckBox) findViewById(R.id.checkbox_martes);
        checkbox_miercoles = (CheckBox) findViewById(R.id.checkbox_miercoles);
        checkbox_jueves = (CheckBox) findViewById(R.id.checkbox_jueves);
        checkbox_viernes = (CheckBox) findViewById(R.id.checkbox_viernes);
        checkbox_sabado = (CheckBox) findViewById(R.id.checkbox_sabado);
        checkbox_domingo = (CheckBox) findViewById(R.id.checkbox_domingo);
        btnaniadir = (Button) findViewById(R.id.btnaniadir);
    }
}
