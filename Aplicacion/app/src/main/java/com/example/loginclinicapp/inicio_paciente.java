package com.example.loginclinicapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class inicio_paciente extends AppCompatActivity {

    Button btnVerMisTurnos, btnPedirTurno, btnHistorial;
    TextView textViewDiaTurno, textViewMesTurno, textViewDiaSemanaTurno, textViewHorario, textViewMedico, textViewEspecialidad, textViewProxTurno;
    RelativeLayout cuadroFecha, cuadroTurno, cuadroSinProximosTurnos, pacMed;
    ImageView seleccionado, noSeleccionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_paciente);

        vincular();
        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr",0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");

        if(matricula != null){
            pacMed.setVisibility(View.VISIBLE);
        }

        noSeleccionado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this , inicio_medico.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });

        btnVerMisTurnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this, ver_mis_turnos.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });

        btnPedirTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this, pedir_turno.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });

        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this, historial_turnos_pacientes.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });

        final Call<ProximoTurno> proximoTurno = RetrofitClient.getInstance().getProximoTurnoPaciente().getProximoTurno(idPaciente);
        proximoTurno.enqueue(new Callback<ProximoTurno>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<ProximoTurno> call, Response<ProximoTurno> response) {
                if(response.code()==200) {
                    if (response.body() != null) {
                        ProximoTurno proximoTurno = new ProximoTurno(response.body().getFecha(), response.body().getEspecialidad(), response.body().getMedico());
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime fecha = LocalDateTime.parse(proximoTurno.getFecha(), formatter);


                        String diaEnPalabras = fecha.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es","ES"));
                        String mesEnPalabras = fecha.getMonth().getDisplayName(TextStyle.FULL, new Locale("es","ES"));

                        textViewDiaTurno = (TextView) findViewById(R.id.textViewDiaTurno);
                        textViewDiaTurno.setText(""+fecha.getDayOfMonth());

                        textViewMesTurno = (TextView) findViewById(R.id.textViewMesTurno);
                        textViewMesTurno.setText(mesEnPalabras.toUpperCase().substring(0,3) + ".");

                        textViewDiaSemanaTurno = (TextView) findViewById(R.id.textViewDiaSemanaTurno);
                        textViewDiaSemanaTurno.setText(diaEnPalabras.toUpperCase().substring(0,1) + diaEnPalabras.substring(1));

                        textViewHorario = (TextView) findViewById(R.id.textViewHorario);
                        textViewHorario.setText(fecha.getHour() + ":" + fecha.getMinute() + "hs.");

                        textViewMedico = (TextView) findViewById(R.id.textViewMedico);
                        textViewMedico.setText(response.body().getMedico().getNombre());

                        textViewEspecialidad  = (TextView) findViewById(R.id.textViewEspecialidad);
                        textViewEspecialidad.setText(response.body().getEspecialidad().getNombre());

                    }
                    else {

                        textViewProxTurno = (TextView) findViewById(R.id.textViewProxTurno);
                        textViewProxTurno.setVisibility(View.GONE);

                        cuadroFecha = (RelativeLayout) findViewById(R.id.cuadroFecha);
                        cuadroFecha.setVisibility(View.GONE);

                        cuadroTurno = (RelativeLayout) findViewById(R.id.cuadroTurno);
                        cuadroTurno.setVisibility(View.GONE);

                        cuadroSinProximosTurnos = (RelativeLayout) findViewById(R.id.cuadroSinProximosTurnos);
                        cuadroSinProximosTurnos.setVisibility(View.VISIBLE);

                    }
                }
            }

            @Override
            public void onFailure(Call<ProximoTurno> call, Throwable t) {
                Log.d("proximoTurno", ":(");
            }
        });

    }

    private void vincular() {
        btnVerMisTurnos = (Button) findViewById(R.id.btnVerMisTurnos);
        btnPedirTurno = (Button) findViewById(R.id.btnPedirTurno);
        btnHistorial = (Button) findViewById(R.id.btnHistorial);
        pacMed = (RelativeLayout) findViewById(R.id.pacMed);
        seleccionado = (ImageView) findViewById(R.id.seleccionado);
        noSeleccionado = (ImageView) findViewById(R.id.noSeleccionado);
    }
}


