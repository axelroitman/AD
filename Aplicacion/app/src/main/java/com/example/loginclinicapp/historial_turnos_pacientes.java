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

public class historial_turnos_pacientes extends AppCompatActivity {

    RelativeLayout txtMensajeError;
    RecyclerView recyclerView;
    GroupAdp adaptador_items;
    LinearLayoutManager linearLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_turnos_pacientes);
        vincular();
        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr",0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");

        Log.d("historial", "ANTES DEL METODO");
        traerDatosTurnos(idPaciente);
    }

    private void traerDatosTurnos(int idPaciente){
        Log.d("historial", "ENTRO AL METODO");

        Log.d("historial",""+idPaciente);
        Call<List<Turno>> call = RetrofitClient.getInstance().getTurnoPaciente().getTurnosPaciente(idPaciente, false);

        Log.d("historial", ""+call.request());
        call.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                Log.d("historial", response.toString());
                if (response.code() == 200) {
                    Log.d("historial", "ES 200");
                    if (!response.body().isEmpty()) {
                        Log.d("historial", response.toString());
                        Log.d("historial", "NO ESTOY VACIO");
                        recyclerView.setVisibility(View.VISIBLE);
                        //txtMensajeError.setVisibility(View.VISIBLE);

                        completarCards(response.body());
                        //Toast.makeText(historial_turnos_pacientes.this, response.body().getId(), Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("historial", "ESTOY VACIO");
                        txtMensajeError.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Log.d("historial","falla :(");
                Toast.makeText(historial_turnos_pacientes.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void completarCards(List<Turno> items){

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptador_items = new GroupAdp(this, items);
        recyclerView.setAdapter(adaptador_items);

    }


    private void vincular(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        txtMensajeError = (RelativeLayout) findViewById(R.id.txtMensajeError);
    }
}
