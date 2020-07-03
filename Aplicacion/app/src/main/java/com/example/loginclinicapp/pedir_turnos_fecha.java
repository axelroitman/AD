package com.example.loginclinicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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
    RelativeLayout layoutSinTurnos, mensajeNotieneTurnos,sinTurnos;
    GroupAdpPedirTurnosFecha gptf;
    AlertDialog.Builder builder, builder2;

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
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Call<Void> agregarAListaDeEspera = RetrofitClient.getInstance().agregarAListaDeEspera().agregarAListaDeEspera(idPaciente, idEsp, matriculaSeleccionado);
                                agregarAListaDeEspera.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {

                                        if (response.code() == 201) {
                                            //Agrega a la lista
                                            builder.setTitle("Lista de espera");
                                            builder.setMessage("Usted fue agregado a la lista de espera solicitada. Cuando se liberen nuevos turnos, nos contactaremos con usted.");
                                            builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent i = new Intent(pedir_turnos_fecha.this, inicio_paciente.class);
                                                    i.putExtra("idUsr", idUsr);
                                                    i.putExtra("idPaciente", idPaciente);
                                                    i.putExtra("matricula", matricula);
                                                    i.putExtra("nombre", nombre);
                                                    startActivity(i);
                                                }
                                            });

                                            AlertDialog alert = builder.create();
                                            alert.show();
                                        }
                                        else{
                                            //Estaba en la lista
                                            builder.setTitle("Lista de espera");
                                            builder.setMessage("Ya se había unido a la lista de espera anteriormente. Cuando se liberen nuevos turnos, nos contactaremos con usted.");
                                            builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent i = new Intent(pedir_turnos_fecha.this, inicio_paciente.class);
                                                    i.putExtra("idUsr", idUsr);
                                                    i.putExtra("idPaciente", idPaciente);
                                                    i.putExtra("matricula", matricula);
                                                    i.putExtra("nombre", nombre);
                                                    startActivity(i);
                                                }
                                            });

                                            AlertDialog alert = builder.create();
                                            alert.show();

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                    }
                                });
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                dialog.cancel();
                                break;
                        }
                    }
                };

                builder2.setMessage("¿Está seguro de que quiere unirse a la lista de espera?").setPositiveButton("Sí", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

            }
        });

        if(matriculaSeleccionado == null) {
            turnosDeEspecialidad.setText("Turnos disponibles de " + nombreEsp + ":");
            Call<TreeMap<Date, Integer>> turnos = RetrofitClient.getInstance().getGetCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadService().getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidad(idEsp);
            turnos.enqueue(new Callback<TreeMap<Date, Integer>>() {
                @Override
                public void onResponse(Call<TreeMap<Date, Integer>> call, Response<TreeMap<Date, Integer>> response) {
                    if(response.code() == 200){
                        if(response.body() != null) {
                            if (!response.body().isEmpty()) {
                                recyclerCards.setVisibility(View.VISIBLE);
                                completarCards(response.body(), idUsr, idPaciente, matricula, nombre, idEsp, matriculaSeleccionado, nombreEsp, nombreMedico);
                            }
                            else{
                                layoutSinTurnos.setVisibility(View.VISIBLE);
                                btnvolver1.setVisibility(View.VISIBLE);
                                btnlistaespera.setVisibility(View.VISIBLE);
                                mensajeNotieneTurnos.setVisibility(View.VISIBLE);
                            }
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
            Call<TreeMap<Date, Integer>> turnos = RetrofitClient.getInstance().getGetCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedicoService().getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedico(idEsp,matriculaSeleccionado);
            turnos.enqueue(new Callback<TreeMap<Date, Integer>>() {
                @Override
                public void onResponse(Call<TreeMap<Date, Integer>> call, Response<TreeMap<Date, Integer>> response) {
                    if(response.code() == 200){
                        if(response.body() != null) {
                            if (!response.body().isEmpty()) {
                                recyclerCards.setVisibility(View.VISIBLE);
                                completarCards(response.body(), idUsr, idPaciente, matricula, nombre, idEsp, matriculaSeleccionado, nombreEsp, nombreMedico);
                            }
                            else{
                                layoutSinTurnos.setVisibility(View.VISIBLE);
                                btnvolver1.setVisibility(View.VISIBLE);
                                btnlistaespera.setVisibility(View.VISIBLE);
                                mensajeNotieneTurnos.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<TreeMap<Date, Integer>> call, Throwable t) {

                }
            });
        }


    }

    private void completarCards(TreeMap<Date, Integer> turnos,int idUsr,int idPaciente,String matricula, String nombre, int idEsp, String matriculaSeleccionado, String nombreEsp, String nombreMedico){

        recyclerCards.setLayoutManager(new LinearLayoutManager(this));
        gptf = new GroupAdpPedirTurnosFecha(this, turnos,idUsr,idPaciente,matricula,nombre,idEsp,matriculaSeleccionado,nombreEsp, nombreMedico);
        recyclerCards.setAdapter(gptf);
    }

    private void vincular(){
        turnosDeEspecialidad = (TextView) findViewById(R.id.turnosDeEspecialidad);
        btnvolver1 = (Button) findViewById(R.id.btnVolverNoTieneTurnos);
        btnlistaespera = (Button) findViewById(R.id.btnAListaDeEsperaSinTurnos);
        layoutSinTurnos = (RelativeLayout) findViewById(R.id.layoutSinTurnos);
        recyclerCards = (RecyclerView) findViewById(R.id.recyclerCards);
        mensajeNotieneTurnos = (RelativeLayout) findViewById(R.id.sinTurnos);
        builder = new AlertDialog.Builder(this);
        builder2 = new AlertDialog.Builder(this);

    }
}
