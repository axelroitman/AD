package com.example.loginclinicapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    Button btnEliminarTurnosDeDia;
    public final Calendar c = Calendar.getInstance();
    AlertDialog.Builder builder, builder2, builderCerrar;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    List<Turno> seleccionados = new ArrayList<Turno>();
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
        final int idUsr = i.getIntExtra("idUsr", 0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");

        /* Inicio de panel desplegable */

        dl = (DrawerLayout)findViewById(R.id.inicioMedDL);
        t = new ActionBarDrawerToggle(this, dl,R.string.app_name, R.string.app_name);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //ESTA ES LA LÍNEA DE LA DISCORDIA, LA QUE REVIENTA TODA LA APP, Y A LA VEZ LA QUE MOSTRARÍA EL BOTÓN DE HAMBURGUESA.

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
                        Intent intentInicio = new Intent(EliminarTurnosDia.this, inicio_medico.class);
                        intentInicio.putExtra("idUsr", idUsr);
                        intentInicio.putExtra("idPaciente",idPaciente);
                        intentInicio.putExtra("matricula",  matricula);
                        intentInicio.putExtra("nombre",nombre);
                        startActivity(intentInicio);

                        break;
                    case R.id.agenda:
                        Intent intentAgenda = new Intent(EliminarTurnosDia.this, ver_agenda.class);
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
                                        Intent intent = new Intent(EliminarTurnosDia.this, Login.class);
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



        traerTurnosDia(idUsr, idPaciente, matricula, nombre, new Date());
        builder = new AlertDialog.Builder(this);
        builder2 = new AlertDialog.Builder(this);
        builderCerrar = new AlertDialog.Builder(this);

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
                        if (dayOfMonth < 10) {
                            diaTxt = "0" + dayOfMonth;
                        }
                        if ((month + 1) < 10) {
                            mesTxt = "0" + (month + 1);
                        }
                        txtfechaEliminar.setText(diaTxt + "/" + mesTxt + "/" + añoTxt);
                    }
                }, año, mes, dia);
                Calendar limSup = Calendar.getInstance();
                limSup.add(Calendar.MONTH, +2);
                DatePicker datePicker = dp.getDatePicker();
                datePicker.setMaxDate(limSup.getTimeInMillis());
                String fechaTxt = txtfechaEliminar.getText().toString();

                int mes = Integer.parseInt(fechaTxt.substring(3, 5)) - 1;
                datePicker.updateDate(Integer.parseInt(fechaTxt.substring(6)), mes, Integer.parseInt(fechaTxt.substring(0, 2)));
                Calendar limInf = Calendar.getInstance();
                datePicker.setMinDate(limInf.getTimeInMillis());
                dp.show();

                dp.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar fechaCal = Calendar.getInstance();
                        fechaCal.set(Calendar.YEAR, year);
                        fechaCal.set(Calendar.MONTH, month);
                        fechaCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        fecha = fechaCal.getTime();
                        traerTurnosDia(idUsr, idPaciente, matricula, nombre, fecha);
                    }
                });
            }

        });

    }

    private void traerTurnosDia(final int idUsr, final int idPaciente, final String matricula, final String nombre, Date fechaHoy) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String year = df.format(fechaHoy);

        String fechaTexto = (fechaHoy.getDate() < 10 ? "0" : "") + fechaHoy.getDate() + "/" + (fechaHoy.getMonth() + 1 < 10 ? "0" : "") + (fechaHoy.getMonth() + 1) + "/" + year;
        txtfechaEliminar.setText(fechaTexto);

        Log.d("agenda", "ENTRO AL METODO");

        Log.d("agenda", "" + matricula);

        SimpleDateFormat formatoCall = new SimpleDateFormat("MM/dd/yyyy");
        String fechaCallString = formatoCall.format(fechaHoy);

        Call<List<Turno>> call = RetrofitClient.getInstance().getTurnosDisponiblesMedicoPorDiaService().getTurnosDeMedico(matricula, fechaCallString);
        Log.d("agenda", "" + call.request());
        call.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        if (!response.body().isEmpty()) {
                            Log.d("ETDia", "" + response.body());
                            sinTurnos.setVisibility(View.GONE);
                            cardsTurnos.setVisibility(View.VISIBLE);
                            btnEliminarTurnosDeDia.setVisibility(View.VISIBLE);

                            completarCards(response.body(), idUsr, idPaciente, matricula, nombre);
                        } else {
                            Log.d("Turnos", "No hay turnos");
                            cardsTurnos.setVisibility(View.GONE);
                            btnEliminarTurnosDeDia.setVisibility(View.GONE);
                            sinTurnos.setVisibility(View.VISIBLE);
                        }
                    }


                }


            }

            @Override
            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Log.d("agenda", "falla :(");
                Toast.makeText(EliminarTurnosDia.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        btnEliminarTurnosDeDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                for (Turno t : seleccionados) {
                                    Call<Void> eliminar = RetrofitClient.getInstance().getEliminarTurnoService().eliminarTurno(t.getId());
                                    eliminar.enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                            if (response.code() == 200) {
                                                builder.setTitle("Eliminar Turno");
                                                if(seleccionados.size() == 1) {
                                                    builder.setMessage("El turno se eliminó correctamente.");
                                                }
                                                else {
                                                    builder.setMessage("Los turnos se eliminaron correctamente.");
                                                }
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
                                                        Intent i = new Intent(EliminarTurnosDia.this, inicio_medico.class);
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
                                }
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                dialog.cancel();
                                break;
                        }
                    }
                };
                builder2.setMessage("¿Está seguro de que desea eliminar " + seleccionados.size() + (seleccionados.size() > 1 ? " turnos?" : " turno?")).setPositiveButton("Sí",dialogClickListener).setNegativeButton("No",dialogClickListener).show();

            }
        });
    }

        private void completarCards (List < Turno > turnos,int idUsr, int idPaciente, String
        matricula, String nombre){
            cardsTurnos.setLayoutManager(new LinearLayoutManager(this));
            groupET = new GroupAdpEliminarTurno(this, turnos, idUsr, idPaciente, matricula, nombre);
            cardsTurnos.setAdapter(groupET);
            seleccionados = groupET.getSeleccionados();
        }

        private void vincular () {
            cardsTurnos = (RecyclerView) findViewById(R.id.cardsTurnos);
            sinTurnos = (TextView) findViewById(R.id.sinTurnos);
            txtfechaEliminar = (TextView) findViewById(R.id.txtfechaEliminar);
            btnEliminarTurnosDeDia = (Button) findViewById(R.id.btnEliminarTurnosDeDia);

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
