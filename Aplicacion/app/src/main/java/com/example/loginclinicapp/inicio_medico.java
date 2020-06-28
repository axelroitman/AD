package com.example.loginclinicapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class inicio_medico extends AppCompatActivity {

    Button btnVerMiAgenda,btnAgregarTurnos, btnBorrarTurnos;
    TextView textViewInfo;
    RelativeLayout pacMed, verAgenda, añadirTurnos, cancelarTurno;
    ImageView seleccionado, noSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_medico);
        vincular();
        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr",0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");

        if(idPaciente > 0){
            pacMed.setVisibility(View.VISIBLE);
        }

        noSeleccionado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_medico.this , inicio_paciente.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });


        añadirTurnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_medico.this, aniadir_turnos.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });
        verAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_medico.this, ver_agenda.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });
        cancelarTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_medico.this, eliminar_turnos.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });


        Call<InfoMedico> infoMedico = RetrofitClient.getInstance().getInfoMedico().getInfoInicioMedico(matricula);
        infoMedico.enqueue(new Callback<InfoMedico>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<InfoMedico> call, Response<InfoMedico> response) {
                if(response.code()==200 && response.body() != null) {
                    InfoMedico infoMedico = new InfoMedico(response.body().getHoraPrimerTurnoMan(), response.body().getHoraUltimoTurnoHoy(), response.body().getCantTurnosMan(), response.body().getHoraPrimerTurnoHoy(), response.body().getHoraUltimoTurnoMan(), response.body().getCantTurnosHoy());
                    String textoInfo = "Buen día " + nombre + ".";

                    if(infoMedico.getCantTurnosHoy() == 0)
                    {
                        textoInfo += " Hoy no tiene turnos.";
                    }
                    else if(infoMedico.getCantTurnosHoy() == 1)
                    {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                        LocalDateTime fechaPrimerTurno = LocalDateTime.parse(infoMedico.getHoraPrimerTurnoHoy(), formatter);

                        textoInfo += " Hoy tiene " + infoMedico.getCantTurnosHoy() + " turno las " + fechaPrimerTurno.getHour() + ":" + (fechaPrimerTurno.getMinute() < 10 ? "0" : "") + fechaPrimerTurno.getMinute() + "hs.";
                    }
                    else {

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                        LocalDateTime fechaPrimerTurno = LocalDateTime.parse(infoMedico.getHoraPrimerTurnoHoy(), formatter);
                        LocalDateTime fechaUltimoTurno = LocalDateTime.parse(infoMedico.getHoraUltimoTurnoHoy(), formatter);

                        textoInfo += " Hoy tiene " + infoMedico.getCantTurnosHoy() + " turnos entre las " + fechaPrimerTurno.getHour() + ":" + (fechaPrimerTurno.getMinute() < 10 ? "0" : "") + fechaPrimerTurno.getMinute() + "hs. y las " + fechaUltimoTurno.getHour() + ":" + (fechaUltimoTurno.getMinute() < 10 ? "0" : "") + fechaUltimoTurno.getMinute() + "hs.";
                    }

                    if(infoMedico.getCantTurnosMan() == 0)
                    {
                        textoInfo += " Mañana no tiene turnos.";
                    }
                    else if(infoMedico.getCantTurnosMan() == 1)
                    {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                        LocalDateTime fechaPrimerTurno = LocalDateTime.parse(infoMedico.getHoraPrimerTurnoMan(), formatter);

                        textoInfo += " Hoy tiene " + infoMedico.getCantTurnosMan() + " turno las " + fechaPrimerTurno.getHour() + ":" + (fechaPrimerTurno.getMinute() < 10 ? "0" : "") + fechaPrimerTurno.getMinute() + "hs.";
                    }
                    else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                        LocalDateTime fechaPrimerTurno = LocalDateTime.parse(infoMedico.getHoraPrimerTurnoMan(), formatter);
                        LocalDateTime fechaUltimoTurno = LocalDateTime.parse(infoMedico.getHoraUltimoTurnoMan(), formatter);

                        textoInfo += " Mañana tiene " + infoMedico.getCantTurnosMan() + " turnos entre las " + fechaPrimerTurno.getHour() + ":" + (fechaPrimerTurno.getMinute() < 10 ? "0" : "") + fechaPrimerTurno.getMinute() + "hs. y las " + fechaUltimoTurno.getHour() + ":" + (fechaUltimoTurno.getMinute() < 10 ? "0" : "") + fechaUltimoTurno.getMinute() + "hs.";
                    }

                    textViewInfo = (TextView) findViewById(R.id.textoInfo);
                    textViewInfo.setText(textoInfo);
                }
            }

            @Override
            public void onFailure(Call<InfoMedico> call, Throwable t) {
                Log.d("inicioMedico", ":(");
            }
        });

    }

    private void vincular(){
        btnVerMiAgenda = (Button) findViewById(R.id.btnVerMiAgenda);
        btnBorrarTurnos = (Button) findViewById(R.id.btnEliminarTurnos);
        btnAgregarTurnos = (Button) findViewById(R.id.btnAddTurnos);
        pacMed = (RelativeLayout) findViewById(R.id.pacMed);
        seleccionado = (ImageView) findViewById(R.id.seleccionado);
        noSeleccionado = (ImageView) findViewById(R.id.noSeleccionado);
        verAgenda =(RelativeLayout) findViewById(R.id.verAgenda);
        añadirTurnos =(RelativeLayout) findViewById(R.id.añadirTurnos);
        cancelarTurno =(RelativeLayout) findViewById(R.id.cancelarTurno);

    }
}
