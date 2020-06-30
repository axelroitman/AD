package com.example.loginclinicapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ver_mis_turnos extends AppCompatActivity {

    RelativeLayout mensajeSinTurnos;
    RecyclerView recyclerViewItems;
    GroupAdp adaptador_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_mis_turnos);

        vincular();

        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr",0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");

        traerDatosTurnos(idUsr, idPaciente, matricula, nombre);
    }


    private void vincular(){
        recyclerViewItems = (RecyclerView) findViewById(R.id.recyclerViewItems);
        mensajeSinTurnos = (RelativeLayout) findViewById(R.id.mensajeSinTurnos);
    }

    private void traerDatosTurnos(final int idUsr, final int idPaciente, final String matricula, final String nombre){

        Log.d("misTurnos",""+idPaciente);
        Call<List<Turno>> call = RetrofitClient.getInstance().getTurnoPaciente().getTurnosPaciente(idPaciente, true);

        Log.d("misTurnos", ""+call.request());
        call.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {

                Log.d("misTurnos", response.toString());
                if (response.code() == 200) {

                    Log.d("misTurnos", "ES 200");
                    if (!response.body().isEmpty()) {

                        Log.d("misTurnos", response.toString());
                        Log.d("misTurnos", "NO ESTOY VACIO");
                        recyclerViewItems.setVisibility(View.VISIBLE);
                        mensajeSinTurnos.setVisibility(View.GONE);

                        completarCards(response.body(), idUsr, idPaciente, matricula, nombre);

                    } else {

                        Log.d("misTurnos", "ESTOY VACIO");
                        mensajeSinTurnos.setVisibility(View.VISIBLE);
                        recyclerViewItems.setVisibility(View.GONE);
                    }
                }
            }

            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Log.d("misTurnos","falla :(");
                Toast.makeText(ver_mis_turnos.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void completarCards(List<Turno> items, int idUsr, int idPaciente, String matricula, String nombre){
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
        adaptador_items = new GroupAdp(this, items, idUsr, idPaciente, matricula, nombre);
        recyclerViewItems.setAdapter(adaptador_items);
    }

}



