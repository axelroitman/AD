package com.example.loginclinicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.SearchManager;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class aniadir_turnos extends AppCompatActivity {

    Spinner spinnerespecialidades, spinnerDuracion;
    TextView tvHoraInicio, tvHoraFin, tvFechaInicial, tvFechaFinal;
    CheckBox checkbox_lunes,checkbox_martes,checkbox_miercoles,checkbox_jueves,checkbox_viernes,checkbox_sabado,checkbox_domingo;
    Button btnaniadir;
    AlertDialog.Builder builder;


    Collection<Especialidad> especialidades = new ArrayList<Especialidad>();
    ArrayList<String> espsNombres = new ArrayList<String>();
    Map<String, Integer> duraciones = new HashMap<String,Integer>();
    Map<String, Integer> especialidadesConId = new HashMap<String,Integer>();

    ArrayList<String> durSpinner = new ArrayList<String>();

    boolean lunes = false, martes = false, miercoles = false, jueves = false, viernes = false, sabado = false, domingo = false;


    public final Calendar c = Calendar.getInstance();
    int horaI = c.get(Calendar.HOUR_OF_DAY);
    int minutoI = c.get(Calendar.MINUTE);
    String horaIni,minutoIni;

    int añoI = c.get(Calendar.YEAR);
    int mesI = c.get(Calendar.MONTH);
    int diaI = c.get(Calendar.DAY_OF_MONTH);
    String añoIni, mesIni,diaIni;

    int horaF = c.get(Calendar.HOUR_OF_DAY);
    int minutoF = c.get(Calendar.MINUTE);
    String horaFin, minutoFin;

    int añoF = c.get(Calendar.YEAR);
    int mesF = c.get(Calendar.MONTH);
    int diaF = c.get(Calendar.DAY_OF_MONTH);
    String añoFin,mesFin,diaFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_turnos);
        vincular();
        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr",0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");

        builder = new AlertDialog.Builder(this);

        tvHoraInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tp = new TimePickerDialog(aniadir_turnos.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        minutoIni = String.valueOf(minute);
                        horaIni = String.valueOf(hourOfDay);

                        horaI = hourOfDay;
                        minutoI = minute;

                        if(minute<10){
                            minutoIni = "0" + minute;
                        }
                        if(Math.floor(hourOfDay/10) == 0){
                            horaIni = "0" + hourOfDay;
                        }
                        tvHoraInicio.setText(horaIni + ":" + minutoIni);
                    }
                },horaI,minutoI,false);
                //TimePicker t = tp.


                tp.show();
            }
        });

        tvHoraFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tp = new TimePickerDialog(aniadir_turnos.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        horaF = hourOfDay;
                        minutoF = minute;
                        horaFin = String.valueOf(hourOfDay);
                        minutoFin = String.valueOf(minute);

                        if(minute<10){
                            minutoFin = "0" + minute;
                        }
                        if(Math.floor(hourOfDay/10) == 0){
                            horaFin = "0" + String.valueOf(hourOfDay);
                        }
                        tvHoraFin.setText(horaFin + ":" + minutoFin);

                    }
                },horaF,minutoF,false);
                tp.show();
            }
        });

        tvFechaInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dp = new DatePickerDialog(aniadir_turnos.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        añoI = year;
                        mesI = month;
                        diaI = dayOfMonth;
                        diaIni = String.valueOf(dayOfMonth);
                        mesIni = String.valueOf(month + 1);
                        añoIni = String.valueOf(year);
                        if(dayOfMonth < 10){
                            diaIni = "0" + dayOfMonth;
                        }
                        if((month + 1) < 10){
                            mesIni = "0" + (month+1);
                        }
                        tvFechaInicial.setText(diaIni + "/" + mesIni + "/" + añoIni);
                    }
                },añoI,mesI,diaI);
                Calendar limSup = Calendar.getInstance();
                limSup.add(Calendar.MONTH,+2);
                DatePicker datePicker = dp.getDatePicker();
                datePicker.setMaxDate(limSup.getTimeInMillis());

                Calendar limInf = Calendar.getInstance();
                datePicker.setMinDate(limInf.getTimeInMillis());
                dp.show();
            }
        });

        tvFechaFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dp = new DatePickerDialog(aniadir_turnos.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        añoF = year;
                        mesF = month;
                        diaF = dayOfMonth;
                        diaFin = String.valueOf(dayOfMonth);
                        mesFin = String.valueOf(month + 1);
                        añoFin = String.valueOf(year);
                        if(dayOfMonth < 10){
                            diaFin = "0" + dayOfMonth;
                        }
                        if((month + 1) < 10){
                            mesFin = "0" + (month+1);
                        }
                        tvFechaFinal.setText(diaFin + "/" + mesFin + "/" + añoFin);
                    }
                },añoF,mesF,diaF);
                Calendar limSup = Calendar.getInstance();
                limSup.add(Calendar.MONTH,+2);
                DatePicker datePicker = dp.getDatePicker();
                datePicker.setMaxDate(limSup.getTimeInMillis());

                Calendar limInf = Calendar.getInstance();
                datePicker.setMinDate(limInf.getTimeInMillis());
                dp.show();

                dp.show();
            }
        });



        duraciones.put("10 minutos", 10);
        duraciones.put("15 minutos", 15);
        duraciones.put("20 minutos", 20);
        duraciones.put("30 minutos", 30);
        duraciones.put("40 minutos", 40);
        duraciones.put("45 minutos", 45);
        duraciones.put("1 hora", 60);
        durSpinner.add("Seleccione duración");
        durSpinner.addAll(duraciones.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, durSpinner){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the second item from Spinner
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0) {
                    // Set the disable item text color
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDuracion.setAdapter(adapter);

        Call<Medico> medico = RetrofitClient.getInstance().getMedicoPorIdUsuarioService().getMedicoPorIdUsuario(idUsr);
        medico.enqueue(new Callback<Medico>() {
            @Override
            public void onResponse(Call<Medico> call, Response<Medico> response) {
                if(response.code() == 200){
                    if(response != null){
                        especialidades = response.body().getEspecialidades();
                        espsNombres.add("Seleccione una especialidad");
                        for(Especialidad e : especialidades){
                            especialidadesConId.put(e.getNombre(),e.getIdEspecialidad());
                            espsNombres.add(e.getNombre());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(aniadir_turnos.this,android.R.layout.simple_spinner_item, espsNombres){
                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                if(position == 0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerespecialidades.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<Medico> call, Throwable t) {

            }
        });


        btnaniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lunes = checkbox_lunes.isChecked();
                martes = checkbox_martes.isChecked();
                miercoles = checkbox_miercoles.isChecked();
                jueves = checkbox_jueves.isChecked();
                viernes = checkbox_viernes.isChecked();
                sabado = checkbox_sabado.isChecked();
                domingo = checkbox_domingo.isChecked();
                int d = duraciones.get(spinnerDuracion.getSelectedItem().toString()); //Duracion
                int idEspecialidad = especialidadesConId.get(spinnerespecialidades.getSelectedItem().toString());
                String fechaInicial = diaIni + "/" + mesIni + "/" + añoIni;
                String fechaFinal = diaFin + "/" + mesFin + "/" + añoFin;
                String horaInicial = horaIni + ":" + minutoIni + ":00";
                String horaFinal = horaFin + ":" + minutoFin + ":00";

                Log.d("AniadirVARIABLES", String.valueOf(d));
                Log.d("AniadirVARIABLES", String.valueOf(idEspecialidad));
                Log.d("AniadirVARIABLES", fechaInicial);
                Log.d("AniadirVARIABLES", fechaFinal);
                Log.d("AniadirVARIABLES", horaInicial);
                Log.d("AniadirVARIABLES", horaFinal);
                Log.d("AniadirVARIABLES", matricula);

                if ((mesI == c.get(Calendar.MONTH) && diaI == c.get(Calendar.DAY_OF_MONTH) && (horaI < c.get(Calendar.HOUR) || minutoI < c.get(Calendar.MINUTE))) || (mesI > mesF) || (mesI == mesF && diaI > diaF) || (añoI > añoF)) {
                    builder.setTitle("Error");
                    builder.setMessage("Introduzca un horario válido.");
                    builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.show();
                }

                else {

                    Call<Void> an = RetrofitClient.getInstance().getAniadirTurnosService().aniadirTurnos(idEspecialidad, matricula, d, horaInicial, horaFinal, fechaInicial, fechaFinal, lunes, martes, miercoles, jueves, viernes, sabado, domingo);
                    an.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 201) {
                                builder.setTitle("Agregar Turnos");
                                builder.setMessage("Los turnos se crearon correctamente.");
                                builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                                AlertDialog alert = builder.create();
                                //Setting the title manually
                                alert.show();
                                alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialog) {
                                        Intent i = new Intent(aniadir_turnos.this, inicio_medico.class);
                                        i.putExtra("idUsr", idUsr);
                                        i.putExtra("idPaciente",idPaciente);
                                        i.putExtra("matricula",  matricula);
                                        i.putExtra("nombre",nombre);
                                        startActivity(i);
                                    }
                                });
                            } else if (response.code() == 418) {
                                builder.setTitle("Agregar Turnos");
                                builder.setMessage("No fue posible crear todos los turnos, por favor verifique su agenda. Los turnos sin incompatibilidades se crearon correctamente.");
                                builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                                AlertDialog alert = builder.create();
                                //Setting the title manually
                                alert.show();
                                alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialog) {
                                        Intent i = new Intent(aniadir_turnos.this, inicio_medico.class);
                                        i.putExtra("idUsr", idUsr);
                                        i.putExtra("idPaciente",idPaciente);
                                        i.putExtra("matricula",  matricula);
                                        i.putExtra("nombre",nombre);
                                        startActivity(i);
                                    }
                                });
                            }
                            /*Intent i = new Intent(aniadir_turnos.this, inicio_medico.class);
                            startActivity(i);*/
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d("Aniadir", "Todo falla.");

                        }
                    });
                }
            }
        });
    }

    private void vincular(){
        spinnerDuracion = (Spinner) findViewById(R.id.spinnerDuracion);
        spinnerespecialidades = (Spinner) findViewById(R.id.spinnerespecialidades);
        tvFechaFinal = (TextView) findViewById(R.id.TvFechaFinal);
        tvFechaInicial = (TextView) findViewById(R.id.TvFechaInicial);
        tvHoraFin = (TextView) findViewById(R.id.TvHoraFin);
        tvHoraInicio = (TextView) findViewById(R.id.TvHoraInicio);
        checkbox_lunes = (CheckBox) findViewById(R.id.checkbox_lunes);
        checkbox_martes = (CheckBox) findViewById(R.id.checkbox_martes);
        checkbox_miercoles = (CheckBox) findViewById(R.id.checkbox_miercoles);
        checkbox_jueves = (CheckBox) findViewById(R.id.checkbox_jueves);
        checkbox_viernes = (CheckBox) findViewById(R.id.checkbox_viernes);
        checkbox_sabado = (CheckBox) findViewById(R.id.checkbox_sabado);
        checkbox_domingo = (CheckBox) findViewById(R.id.checkbox_domingo);
        btnaniadir = (Button) findViewById(R.id.btnaniadir);
    }
}
