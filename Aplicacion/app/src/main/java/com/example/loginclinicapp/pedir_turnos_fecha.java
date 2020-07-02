package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pedir_turnos_fecha extends AppCompatActivity {

    Button btnvolver1, btnlistaespera;
    RecyclerView recyclerCards;
    TextView turnosDeEspecialidad;
    RelativeLayout layoutSinTurnos, mensajeNotieneTurnos;
    GroupAdpPedirTurnosFecha gptf;

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
        final String nombreMedico = i.getStringExtra("nombreMedico");
        final String nombreEsp = i.getStringExtra("nombreEsp");

        btnvolver1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnlistaespera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent a "agregar a lista de espera" (todavia no esta en este proyecto).
                 Pasar todos los valores en el intent, y verificar en la otra activity si
                 selecciono medico o no*/
            }
        });

        if(matricula == null) {
            turnosDeEspecialidad.setText("Turnos disponibles de " + nombreEsp + ":");
            Call<TreeMap<Date, Integer>> turnos = RetrofitClient.getInstance().getGetCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadService().getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidad(idEsp);
            turnos.enqueue(new Callback<TreeMap<Date, Integer>>() {
                @Override
                public void onResponse(Call<TreeMap<Date, Integer>> call, Response<TreeMap<Date, Integer>> response) {
                    if(response.code() == 200){
                        if(response.body() != null){
                            recyclerCards.setVisibility(View.VISIBLE);
                            completarCards(response.body());
                        }
                        else{
                            layoutSinTurnos.setVisibility(View.VISIBLE);
                            btnvolver1.setVisibility(View.VISIBLE);
                            btnlistaespera.setVisibility(View.VISIBLE);
                        }
                    }

                }

                @Override
                public void onFailure(Call<TreeMap<Date, Integer>> call, Throwable t) {

                }
            });
        }
        else{
            turnosDeEspecialidad.setText("Turnos disponibles de " + nombreEsp + " de " + nombreMedico + ":");
            Call<TreeMap<Date, Integer>> turnos = RetrofitClient.getInstance().getGetCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedicoService().getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedico(idEsp,matricula);
            turnos.enqueue(new Callback<TreeMap<Date, Integer>>() {
                @Override
                public void onResponse(Call<TreeMap<Date, Integer>> call, Response<TreeMap<Date, Integer>> response) {

                }

                @Override
                public void onFailure(Call<TreeMap<Date, Integer>> call, Throwable t) {

                }
            });
        }

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

    private void completarCards(TreeMap<Date, Integer> turnos){

        recyclerCards.setLayoutManager(new LinearLayoutManager(this));
        gptf = new GroupAdpPedirTurnosFecha(this, turnos);
        recyclerCards.setAdapter(gptf);
    }

    private void vincular(){
        turnosDeEspecialidad = (TextView) findViewById(R.id.turnosDeEspecialidad);
        btnvolver1 = (Button) findViewById(R.id.btnVolverNoTieneTurnos);
        btnlistaespera = (Button) findViewById(R.id.btnAListaDeEsperaSinTurnos);
        layoutSinTurnos = (RelativeLayout) findViewById(R.id.layoutSinTurnos);
        recyclerCards = (RecyclerView) findViewById(R.id.recyclerCards);
        mensajeNotieneTurnos = (RelativeLayout) findViewById(R.id.mensajeNotieneTurnos);
    }
}
