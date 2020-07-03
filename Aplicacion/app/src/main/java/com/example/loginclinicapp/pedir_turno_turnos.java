package com.example.loginclinicapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pedir_turno_turnos extends AppCompatActivity {

    TextView txtDia, turnosDeEspecialidad;
    RecyclerView cardsTurnos;
    private String mesEnPalabras;
    private int nroDia;
    private LocalDate fecha;
    private String fechaString;
    private GroupAdpPedirTurnoTurnosEsp gptte;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_turno_turnos);
        vincular();

        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr", 0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");
        final int idEsp = i.getIntExtra("idEspecialidad", 0);
        final String matriculaSeleccionado = i.getStringExtra("matriculaSeleccionado");
        final String nombreMedico = i.getStringExtra("nombreMedico");
        final String nombreEsp = i.getStringExtra("nombreEsp");
        fecha = (LocalDate) i.getSerializableExtra("fecha");

        nroDia = fecha.getDayOfMonth();
        mesEnPalabras = fecha.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        mesEnPalabras = mesEnPalabras.substring(0, 3);
        txtDia.setText(nroDia + " " + mesEnPalabras.toUpperCase() + ".");
        fechaString = fecha.toString().replace("-","/");

        if (matriculaSeleccionado == null) {
            turnosDeEspecialidad.setText("Turnos disponibles de " + nombreEsp + ":");
            Log.d("turnoss", "Fecha: " + fecha.toString());
            Log.d("turnoss","IdEsp: " + idEsp);
            Log.d("turnoss", "FechaComoString: " + fechaString);
            Call<List<Turno>> turnos = RetrofitClient.getInstance().getGetTurnosEspecialidadPorDiaService().getTurnosEspecialidadPorDiaService(idEsp,fechaString);
            turnos.enqueue(new Callback<List<Turno>>() {
                @Override
                public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                    if (response.code() == 200) {
                        if (response != null) {
                            cardsTurnos.setVisibility(View.VISIBLE);
                            completarCards(response.body(), idUsr, idPaciente, matricula, nombre, idEsp, matriculaSeleccionado, nombreEsp, nombreMedico);

                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Turno>> call, Throwable t) {

                }
            });

        }
        else{
            turnosDeEspecialidad.setText("Turnos disponibles de " + nombreEsp + " de " + nombreMedico + ":");
            Call<List<Turno>> turnos = RetrofitClient.getInstance().getGetTurnosEspecialidadYMedicoPorDiaService().getTurnosEspecialidadYMedicoPorDiaService(idEsp, fechaString, matriculaSeleccionado);
            turnos.enqueue(new Callback<List<Turno>>() {
                @Override
                public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                    if (response.code() == 200) {
                        if (response != null) {
                            cardsTurnos.setVisibility(View.VISIBLE);
                            completarCards(response.body(), idUsr, idPaciente, matricula, nombre, idEsp, matriculaSeleccionado, nombreEsp, nombreMedico);

                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Turno>> call, Throwable t) {

                }
            });
        }
    }



    private void vincular () {
        txtDia = (TextView) findViewById(R.id.txtdia);
        turnosDeEspecialidad = (TextView) findViewById(R.id.turnosDeEspecialidad);
        cardsTurnos = (RecyclerView) findViewById(R.id.cardsTurnos);
    }
    private void completarCards(List<Turno>turnos, int idUsr, int idPaciente, String matricula, String nombre, int idEsp, String matriculaSeleccionado, String nombreEsp, String nombreMedico){
        if(matriculaSeleccionado == null) {
            cardsTurnos.setLayoutManager(new LinearLayoutManager(this));
            gptte = new GroupAdpPedirTurnoTurnosEsp(this, turnos, idUsr, idPaciente, matricula, nombre, idEsp, matriculaSeleccionado, nombreEsp, nombreMedico);
            cardsTurnos.setAdapter(gptte);
        }
        else{
            //Hacer otro itemturnoturnos y groupadp en el caso de que se seleccione medico
        }
    }


}
