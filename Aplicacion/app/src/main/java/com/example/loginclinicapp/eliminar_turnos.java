package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class eliminar_turnos extends AppCompatActivity {

    EditText edTextHoraInicio, edTextHoraFin, edTextFechaInicial, edTextFechaFinal;
    CheckBox checkbox_lunes,checkbox_martes,checkbox_miercoles,checkbox_jueves,checkbox_viernes,checkbox_sabado,checkbox_domingo;
    Button btneliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_turnos);
        vincular();

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(eliminar_turnos.this, inicio_medico.class);
                startActivity(i);
            }
        });
    }
    private void vincular(){
        edTextFechaFinal = (EditText) findViewById(R.id.TvFechaFinal);
        edTextFechaInicial = (EditText) findViewById(R.id.TvFechaInicial);
        edTextHoraFin = (EditText) findViewById(R.id.TvHoraFin);
        edTextHoraInicio = (EditText) findViewById(R.id.TvHoraInicio);
        checkbox_lunes = (CheckBox) findViewById(R.id.checkbox_lunes);
        checkbox_martes = (CheckBox) findViewById(R.id.checkbox_martes);
        checkbox_miercoles = (CheckBox) findViewById(R.id.checkbox_miercoles);
        checkbox_jueves = (CheckBox) findViewById(R.id.checkbox_jueves);
        checkbox_viernes = (CheckBox) findViewById(R.id.checkbox_viernes);
        checkbox_sabado = (CheckBox) findViewById(R.id.checkbox_sabado);
        checkbox_domingo = (CheckBox) findViewById(R.id.checkbox_domingo);
        btneliminar = (Button) findViewById(R.id.btneliminar);
    }
}
