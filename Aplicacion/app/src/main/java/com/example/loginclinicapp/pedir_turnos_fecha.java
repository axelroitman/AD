package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pedir_turnos_fecha extends AppCompatActivity {

    Button btnvolver1, btnlistaespera;
    RecyclerView recyclerCards;
    TextView turnosDeEspecialidad;
    RelativeLayout layoutSinTurnos;

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

        if(matricula == null) {
            Call<Map<Date, Integer>> turnos = RetrofitClient.getInstance().getGetCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadService().getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidad(idEsp);
            turnos.enqueue(new Callback<Map<Date, Integer>>() {
                @Override
                public void onResponse(Call<Map<Date, Integer>> call, Response<Map<Date, Integer>> response) {

                }

                @Override
                public void onFailure(Call<Map<Date, Integer>> call, Throwable t) {

                }
            });
        }
        else{

            Call<Map<Date, Integer>> turnos = RetrofitClient.getInstance().getGetCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedicoService().getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedico(idEsp,matricula);
            turnos.enqueue(new Callback<Map<Date, Integer>>() {
                @Override
                public void onResponse(Call<Map<Date, Integer>> call, Response<Map<Date, Integer>> response) {

                }

                @Override
                public void onFailure(Call<Map<Date, Integer>> call, Throwable t) {

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
    private void vincular(){
        turnosDeEspecialidad = (TextView) findViewById(R.id.turnosDeEspecialidad);
        btnvolver1 = (Button) findViewById(R.id.btnVolverNoTieneTurnos);
        btnlistaespera = (Button) findViewById(R.id.btnAListaDeEsperaSinTurnos);
        layoutSinTurnos = (RelativeLayout) findViewById(R.id.layoutSinTurnos);
        recyclerCards = (RecyclerView) findViewById(R.id.recyclerCards);
    }
}
