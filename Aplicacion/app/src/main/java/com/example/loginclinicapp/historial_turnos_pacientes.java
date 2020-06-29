package com.example.loginclinicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.loginclinicapp.modelos.Turno;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class historial_turnos_pacientes extends AppCompatActivity {

    AlertDialog.Builder builder;
    RelativeLayout txtMensajeError;
    RecyclerView recyclerView;
    GroupAdp adaptador_items;
    ArrayList<String> items;
    LinearLayoutManager linearLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_turnos_pacientes);
        vincular();
        Log.d("historial", "ANTES DEL METODO");
        traerDatosTurnos();
    }

    private void traerDatosTurnos(){
        Log.d("historial", "ENTRO AL METODO");

       // boolean proximos = true;
        Call<List<Turno>> call = RetrofitClient.getInstance().getTurnoPaciente().getTurnos(2, true);
        call.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                Log.d("historial", response.toString());
                if (response.code() == 200) {
                    Log.d("historial", "ES 200");
                    if ( ! response.body().isEmpty()) {
                        Log.d("historial", response.toString());
                        Log.d("historial", "NO ESTOY VACIO");
                        recyclerView.setVisibility(View.GONE);
                        txtMensajeError.setVisibility(View.VISIBLE);

                        //completarCards();
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
                Toast.makeText(historial_turnos_pacientes.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void completarCards(){

        items = new ArrayList<>();
        items.add("primera cardview item");
        items.add("segunda cardview item");
        items.add("tercera cardview item");
        items.add("cuarta cardview item");
        items.add("quinta cardview item");
        items.add("sexta cardview item");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptador_items = new GroupAdp(this, items);
        recyclerView.setAdapter(adaptador_items);

    }


    private void vincular(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        txtMensajeError = (RelativeLayout) findViewById(R.id.txtMensajeError);
    }
}
