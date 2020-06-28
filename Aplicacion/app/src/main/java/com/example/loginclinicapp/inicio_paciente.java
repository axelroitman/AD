package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class inicio_paciente extends AppCompatActivity {

    Button btnVerMisTurnos, btnPedirTurno, btnHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_paciente);

        vincular();

        btnVerMisTurnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this, ver_mis_turnos.class);
                startActivity(i);
            }
        });

        btnPedirTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this, pedir_turno.class);
                startActivity(i);
            }
        });

        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this, historial_turnos_pacientes.class);
                startActivity(i);
            }
        });

        Log.d("proximoTurno", "Hola");

        Call<ProximoTurno> proximoTurno = RetrofitClient.getInstance().getProximoTurnoPaciente().getProximoTurno(obtenerIdPaciente());
        proximoTurno.enqueue(new Callback<ProximoTurno>() {
            @Override
            public void onResponse(Call<ProximoTurno> call, Response<ProximoTurno> response) {
                if(response.code()==200) {
                    if (response.body() != null) {
                        ProximoTurno proximoTurno = new ProximoTurno(response.body().getFecha(), response.body().getEspecialidad(), response.body().getMedico());
                        Log.d("proximoTurno", ""+ proximoTurno.getFecha());
                    }
                }
            }

            @Override
            public void onFailure(Call<ProximoTurno> call, Throwable t) {
                Log.d("proximoTurno", ":(");

            }
        });
        Log.d("proximoTurno", "Chau");

    }

    private void vincular(){
        btnVerMisTurnos = (Button) findViewById(R.id.btnVerMisTurnos);
        btnPedirTurno = (Button) findViewById(R.id.btnPedirTurno);
        btnHistorial = (Button) findViewById(R.id.btnHistorial);
    }

    private int obtenerIdPaciente(){
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        return preferences.getInt("idPaciente", 0);
    }

}


