package com.example.loginclinicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        // traerDatosTurnos();

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

    private void traerDatosTurnos(){
        builder = new AlertDialog.Builder(this);

        Call<Turno> call = (Call<Turno>) RetrofitClient.getInstance().getTurnoPaciente();
        call.enqueue(new Callback<Turno>() {
                         @Override
                         public void onResponse(Call<Turno> call, Response<Turno> response) {
                             Log.d("historialturnos", response.toString());
                             if (response.code() == 200) {
                                 if (response.body() != null) {
                                     //Log.d("historialturnos", "" + response.body().getId());
                                     //Toast.makeText(historial_turnos_pacientes.this, response.body().getId(), Toast.LENGTH_LONG).show();
                                 }
                                 else{
                                     txtMensajeError.setVisibility(View.VISIBLE);
                                     /*builder.setTitle("No tiene turnos");
                                     builder.setMessage("No tiene ningÃºn turno.");
                                     builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                         @Override
                                         public void onClick(DialogInterface dialog, int which) {
                                             dialog.cancel();
                                         }
                                     });

                                     AlertDialog alert = builder.create();
                                     alert.show();*/
                                 }
                             }
                         }

                         @Override
                         public void onFailure(Call<Turno> call, Throwable t) {
                             Toast.makeText(historial_turnos_pacientes.this, t.getMessage(), Toast.LENGTH_LONG).show();
                         }
                     }
        );
    }



    private void vincular(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        txtMensajeError = (RelativeLayout) findViewById(R.id.txtMensajeError);
    }
}
