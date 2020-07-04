package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EliminarTurnosDia extends AppCompatActivity {

    RecyclerView cardsTurnos;
    TextView sinTurnos;
    GroupAdpEliminarTurno groupET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_turnos_dia);
        vincular();

        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr",0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");

        Call<List<Turno>> turnos = RetrofitClient.getInstance().getTurnosDeMedicoService().getTurnosDeMedico(matricula,1);
        turnos.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                Log.d("ETDia",""+response.code());
                if(response.code() == 200){
                    if(response.body() != null) {
                        if (!response.body().isEmpty()) {
                            Log.d("ETDia", ""+response.body());
                            cardsTurnos.setVisibility(View.VISIBLE);
                            completarCards(response.body(), idUsr, idPaciente, matricula, nombre);
                        }
                        else {
                            sinTurnos.setVisibility(View.VISIBLE);
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Turno>> call, Throwable t) {

            }
        });
    }

    private void completarCards(List<Turno> turnos, int idUsr, int idPaciente, String matricula, String nombre) {
        cardsTurnos.setLayoutManager(new LinearLayoutManager(this));
        groupET = new GroupAdpEliminarTurno(this,turnos,idUsr,idPaciente,matricula,nombre);
        cardsTurnos.setAdapter(groupET);
    }

    private void vincular(){
        cardsTurnos = (RecyclerView) findViewById(R.id.cardsTurnos);
        sinTurnos = (TextView) findViewById(R.id.sinTurnos);

    }
}
