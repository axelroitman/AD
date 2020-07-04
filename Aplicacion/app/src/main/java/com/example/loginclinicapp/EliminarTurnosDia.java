package com.example.loginclinicapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EliminarTurnosDia extends AppCompatActivity {

    RecyclerView cardsTurnos;
    TextView sinTurnos, txtfechaEliminar;
    GroupAdpEliminarTurno groupET;
    public final Calendar c = Calendar.getInstance();

    int año = c.get(java.util.Calendar.YEAR);
    int mes = c.get(java.util.Calendar.MONTH);
    int dia = c.get(Calendar.DAY_OF_MONTH);

    String diaTxt;
    String mesTxt;
    String añoTxt;

    Date fecha;

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
        traerTurnosDia(idUsr,idPaciente,matricula,nombre,new Date());


        txtfechaEliminar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog dp = new DatePickerDialog(EliminarTurnosDia.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        año = year;
                        mes = month;
                        dia = dayOfMonth;
                        diaTxt = String.valueOf(dayOfMonth);
                        mesTxt = String.valueOf(month + 1);
                        añoTxt = String.valueOf(year);
                        if(dayOfMonth < 10){
                            diaTxt = "0" + dayOfMonth;
                        }
                        if((month + 1) < 10){
                            mesTxt = "0" + (month+1);
                        }
                        txtfechaEliminar.setText(diaTxt + "/" + mesTxt + "/" + añoTxt);
                    }
                },año,mes,dia);
                Calendar limSup = Calendar.getInstance();
                limSup.add(Calendar.MONTH,+2);
                DatePicker datePicker = dp.getDatePicker();
                datePicker.setMaxDate(limSup.getTimeInMillis());
                String fechaTxt = txtfechaEliminar.getText().toString();

                int mes = Integer.parseInt(fechaTxt.substring(3, 5)) - 1;
                datePicker.updateDate(Integer.parseInt(fechaTxt.substring(6)), mes, Integer.parseInt(fechaTxt.substring(0, 2)));

                dp.show();

                dp.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar fechaCal = Calendar.getInstance();
                        fechaCal.set(Calendar.YEAR,year);
                        fechaCal.set(Calendar.MONTH,month);
                        fechaCal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        fecha = fechaCal.getTime();
                        traerTurnosDia(idUsr,idPaciente,matricula,nombre,fecha);


                    }
                });
            }

        });



    }

    /*
    * Call<List<Turno>> turnos = RetrofitClient.getInstance().getTurnosDeMedicoService().getTurnosDeMedico(matricula,1);
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
    * */
    private void traerTurnosDia(final int idUsr, final int idPaciente, final String matricula, final String nombre, Date fechaHoy){
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String year = df.format(fechaHoy);

        String fechaTexto = (fechaHoy.getDate() < 10 ? "0" : "") + fechaHoy.getDate() + "/" + (fechaHoy.getMonth() + 1 < 10 ? "0" : "") + (fechaHoy.getMonth()+1) + "/" + year;
        txtfechaEliminar.setText(fechaTexto);

        Log.d("agenda", "ENTRO AL METODO");

        Log.d("agenda",""+matricula);

        SimpleDateFormat formatoCall = new SimpleDateFormat("MM/dd/yyyy");
        String fechaCallString = formatoCall.format(fechaHoy);

        Call<List<Turno>> call = RetrofitClient.getInstance().getTurnosMedicoPorDia().getTurnosMedicoPorDia(matricula, fechaCallString);
        Log.d("agenda", ""+call.request());
        call.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                if(response.code() == 200){
                    if(response.body() != null) {
                        if (!response.body().isEmpty()) {
                            Log.d("ETDia", ""+response.body());
                            sinTurnos.setVisibility(View.GONE);
                            cardsTurnos.setVisibility(View.VISIBLE);

                            completarCards(response.body(), idUsr, idPaciente, matricula, nombre);
                        }
                        else {
                            Log.d("Turnos", "No hay turnos");
                            cardsTurnos.setVisibility(View.GONE);
                            sinTurnos.setVisibility(View.VISIBLE);
                        }
                    }



                }


            }

            @Override
            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Log.d("agenda","falla :(");
                Toast.makeText(EliminarTurnosDia.this, t.getMessage(), Toast.LENGTH_LONG).show();
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
        txtfechaEliminar = (TextView) findViewById(R.id.txtfechaEliminar);

    }
}
