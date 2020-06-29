package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class aniadir_turnos extends AppCompatActivity {

    Spinner spinnerespecialidades, spinnerDuracion;
    EditText edTextHoraInicio, edTextHoraFin, edTextFechaInicial, edTextFechaFinal;
    CheckBox checkbox_lunes,checkbox_martes,checkbox_miercoles,checkbox_jueves,checkbox_viernes,checkbox_sabado,checkbox_domingo;
    Button btnaniadir;

    Collection<Especialidad> especialidades = new ArrayList<Especialidad>();
    ArrayList<String> espsNombres = new ArrayList<String>();
    Map<String, Integer> duraciones = new HashMap<String,Integer>();
    ArrayList<String> durSpinner = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_turnos);
        vincular();
        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr",0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");

        duraciones.put("10 minutos", 10);
        duraciones.put("15 minutos", 15);
        duraciones.put("20 minutos", 20);
        duraciones.put("30 minutos", 30);
        duraciones.put("40 minutos", 40);
        duraciones.put("45 minutos", 45);
        duraciones.put("1 hora", 60);
        durSpinner.add("Seleccione duración");
        durSpinner.addAll(duraciones.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, durSpinner){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the second item from Spinner
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0) {
                    // Set the disable item text color
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDuracion.setAdapter(adapter);

        Call<Medico> medico = RetrofitClient.getInstance().getMedicoPorIdUsuarioService().getMedicoPorIdUsuario(idUsr);
        medico.enqueue(new Callback<Medico>() {
            @Override
            public void onResponse(Call<Medico> call, Response<Medico> response) {
                if(response.code() == 200){
                    if(response != null){
                        especialidades = response.body().getEspecialidades();
                        espsNombres.add("Seleccione una especialidad");
                        for(Especialidad e : especialidades){
                            espsNombres.add(e.getNombre());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(aniadir_turnos.this,android.R.layout.simple_spinner_item, espsNombres){
                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                if(position == 0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerespecialidades.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<Medico> call, Throwable t) {

            }
        });


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
