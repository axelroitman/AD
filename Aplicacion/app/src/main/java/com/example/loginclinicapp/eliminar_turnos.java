package com.example.loginclinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class eliminar_turnos extends AppCompatActivity {

    TextView tvHoraInicio, tvHoraFin, tvFechaInicial, tvFechaFinal;
    CheckBox checkbox_lunes,checkbox_martes,checkbox_miercoles,checkbox_jueves,checkbox_viernes,checkbox_sabado,checkbox_domingo;
    Button btneliminar;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    boolean lunes = false, martes = false, miercoles = false, jueves = false, viernes = false, sabado = false, domingo = false;
    AlertDialog.Builder builder, builder2, builderCerrar;


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
        setContentView(R.layout.activity_eliminar_turnos);
        vincular();

        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr",0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");

        /* Inicio de panel desplegable */

        dl = (DrawerLayout)findViewById(R.id.inicioMedDL);
        t = new ActionBarDrawerToggle(this, dl,R.string.app_name, R.string.app_name);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);

        View headerView = nv.getHeaderView(0);
        TextView nombreUsr = (TextView) headerView.findViewById(R.id.nombreUsuario);
        nombreUsr.setText(nombre);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch(id)
                {
                    case R.id.inicio:
                        Intent intentInicio = new Intent(eliminar_turnos.this, inicio_medico.class);
                        intentInicio.putExtra("idUsr", idUsr);
                        intentInicio.putExtra("idPaciente",idPaciente);
                        intentInicio.putExtra("matricula",  matricula);
                        intentInicio.putExtra("nombre",nombre);
                        startActivity(intentInicio);

                        break;
                    case R.id.agenda:
                        Intent intentAgenda = new Intent(eliminar_turnos.this, ver_agenda.class);
                        intentAgenda.putExtra("idUsr", idUsr);
                        intentAgenda.putExtra("idPaciente",idPaciente);
                        intentAgenda.putExtra("matricula",  matricula);
                        intentAgenda.putExtra("nombre",nombre);
                        startActivity(intentAgenda);
                        break;
                    case R.id.cerrarSesion:
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        Intent intent = new Intent(eliminar_turnos.this, Login.class);
                                        intent.putExtra("cierraSesion", true);
                                        startActivity(intent);
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        dialog.cancel();
                                        break;
                                }
                            }
                        };

                        builderCerrar.setMessage("¿Está seguro de que quiere cerrar sesión?").setPositiveButton("Sí", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });


        /* Fin de panel desplegable */


        builder = new AlertDialog.Builder(this);
        builder2 = new AlertDialog.Builder(this);
        builderCerrar = new AlertDialog.Builder(this);

        tvHoraInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tp = new TimePickerDialog(eliminar_turnos.this, new TimePickerDialog.OnTimeSetListener() {
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

                tp.show();
            }
        });

        tvHoraFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tp = new TimePickerDialog(eliminar_turnos.this, new TimePickerDialog.OnTimeSetListener() {
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
                DatePickerDialog dp = new DatePickerDialog(eliminar_turnos.this, new DatePickerDialog.OnDateSetListener() {
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
                limInf.add(Calendar.WEEK_OF_MONTH,1);
                datePicker.setMinDate(limInf.getTimeInMillis());
                dp.show();
            }
        });

        tvFechaFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dp = new DatePickerDialog(eliminar_turnos.this, new DatePickerDialog.OnDateSetListener() {
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
                limInf.add(Calendar.WEEK_OF_MONTH,1);
                datePicker.setMinDate(limInf.getTimeInMillis());
                dp.show();

                dp.show();
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lunes = checkbox_lunes.isChecked();
                martes = checkbox_martes.isChecked();
                miercoles = checkbox_miercoles.isChecked();
                jueves = checkbox_jueves.isChecked();
                viernes = checkbox_viernes.isChecked();
                sabado = checkbox_sabado.isChecked();
                domingo = checkbox_domingo.isChecked();

                if ((mesI == c.get(Calendar.MONTH) && diaI == c.get(Calendar.DAY_OF_MONTH) && (horaI <= c.get(Calendar.HOUR) && minutoI < c.get(Calendar.MINUTE))) || (mesI > mesF) || (mesI == mesF && diaI > diaF) || (añoI > añoF) || (horaI > horaF)) {
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

                else if(diaIni != null && mesIni != null && añoIni != null && diaFin != null && mesFin != null && añoFin != null && horaIni != null && minutoIni != null && horaFin != null && minutoFin != null) {
                    final String fechaInicial = diaIni + "/" + mesIni + "/" + añoIni;
                    final String fechaFinal = diaFin + "/" + mesFin + "/" + añoFin;
                    final String horaInicial = horaIni + ":" + minutoIni + ":00";
                    final String horaFinal = horaFin + ":" + minutoFin + ":00";
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    Call<Void> eliminar = RetrofitClient.getInstance().getEliminarTurnosService().eliminarTurnos(matricula, horaInicial, horaFinal, fechaInicial, fechaFinal, lunes, martes, miercoles, jueves, viernes, sabado, domingo);
                                    eliminar.enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                            if (response.code() == 200) {
                                                builder.setTitle("Eliminar Turnos");
                                                builder.setMessage("Los turnos se eliminaron correctamente.");
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
                                                        Intent i = new Intent(eliminar_turnos.this, inicio_medico.class);
                                                        i.putExtra("idUsr", idUsr);
                                                        i.putExtra("idPaciente", idPaciente);
                                                        i.putExtra("matricula", matricula);
                                                        i.putExtra("nombre", nombre);
                                                        startActivity(i);
                                                    }
                                                });
                                            } else if (response.code() == 418) {
                                                builder.setTitle("Eliminar Turnos");
                                                builder.setMessage("No fue posible eliminar todos los turnos, por favor verifique su agenda. Los turnos sin incompatibilidades se eliminaron correctamente.");
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
                                                        Intent i = new Intent(eliminar_turnos.this, inicio_medico.class);
                                                        i.putExtra("idUsr", idUsr);
                                                        i.putExtra("idPaciente", idPaciente);
                                                        i.putExtra("matricula", matricula);
                                                        i.putExtra("nombre", nombre);
                                                        startActivity(i);
                                                    }
                                                });

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Void> call, Throwable t) {

                                        }
                                    });
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    dialog.cancel();
                                    break;
                            }
                        }

                    };
                    builder2.setMessage("¿Está seguro de hacer una eliminación masiva de turnos?").setPositiveButton("Sí",dialogClickListener).setNegativeButton("No",dialogClickListener).show();

                }
                else{
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
                /*Intent i = new Intent(eliminar_turnos.this, inicio_medico.class);
                startActivity(i);*/
            }
        });
    }
    private void vincular(){
        tvFechaFinal = (TextView) findViewById(R.id.tvFechaFinal);
        tvFechaInicial = (TextView) findViewById(R.id.tvFechaInicial);
        tvHoraFin = (TextView) findViewById(R.id.tvHoraFin);
        tvHoraInicio = (TextView) findViewById(R.id.tvHoraInicio);
        checkbox_lunes = (CheckBox) findViewById(R.id.checkbox_lunes);
        checkbox_martes = (CheckBox) findViewById(R.id.checkbox_martes);
        checkbox_miercoles = (CheckBox) findViewById(R.id.checkbox_miercoles);
        checkbox_jueves = (CheckBox) findViewById(R.id.checkbox_jueves);
        checkbox_viernes = (CheckBox) findViewById(R.id.checkbox_viernes);
        checkbox_sabado = (CheckBox) findViewById(R.id.checkbox_sabado);
        checkbox_domingo = (CheckBox) findViewById(R.id.checkbox_domingo);
        btneliminar = (Button) findViewById(R.id.btneliminar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_desplegable, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
