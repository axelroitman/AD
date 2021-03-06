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
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ver_agenda extends AppCompatActivity {

    TextView txtfecha;
    RelativeLayout mensajeNotieneTurnos;
    RecyclerView recyclerViewItem;
    GroupAdp adaptador_items;
    AlertDialog.Builder builderCerrar;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    public final Calendar c = Calendar.getInstance();

    int año = c.get(java.util.Calendar.YEAR);
    int mes = c.get(java.util.Calendar.MONTH);
    int dia = c.get(Calendar.DAY_OF_MONTH);

    String diaTxt;
    String mesTxt;
    String añoTxt;

    Date fecha;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_agenda);

        vincular();
        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr",0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");
        builderCerrar = new AlertDialog.Builder(this);

        /* Inicio de panel desplegable */

       /* mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mTopToolbar.setTitle("ClinicApp");
        mTopToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mTopToolbar);*/
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
                        Intent i = new Intent(ver_agenda.this, inicio_medico.class);
                        i.putExtra("idUsr", idUsr);
                        i.putExtra("idPaciente",idPaciente);
                        i.putExtra("matricula",  matricula);
                        i.putExtra("nombre",nombre);
                        startActivity(i);

                        break;
                    case R.id.agenda:
                        //En este caso, ya está en agenda, no tiene que hacer nada.
                        break;
                    case R.id.cerrarSesion:
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        Intent intent = new Intent(ver_agenda.this, Login.class);
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


        traerProximosTurnosMedico(idUsr,idPaciente,matricula,nombre,new Date());


        txtfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dp = new DatePickerDialog(ver_agenda.this, new DatePickerDialog.OnDateSetListener() {
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
                        txtfecha.setText(diaTxt + "/" + mesTxt + "/" + añoTxt);
                    }
                },año,mes,dia);
                Calendar limSup = Calendar.getInstance();
                limSup.add(Calendar.MONTH,+2);
                DatePicker datePicker = dp.getDatePicker();
                datePicker.setMaxDate(limSup.getTimeInMillis());
                String fechaTxt = txtfecha.getText().toString();

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
                        traerProximosTurnosMedico(idUsr,idPaciente,matricula,nombre,fecha);


                    }
                });
            }

        });
        /*java.util.Date fechaHoy = Calendar.getInstance().getTime();
        try {
            metodosSobreSeleccionarFecha(idUsr, idPaciente, matricula, nombre);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        //traerProximosTurnosMedico(idUsr, idPaciente, matricula, nombre, fechaHoy);


    }


    /*private void metodosSobreSeleccionarFecha(final int idUsr, final int idPaciente, final String matricula, final String nombre) throws ParseException {
        Log.d("agenda", "ENTRO AL METODO DE LA FECHA");
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("seleccionar una fecha");

        final MaterialDatePicker materialDatePicker = builder.build();

        txtfecha.setOnClickListener(new View.OnClickListener() {
            private Object tag;

            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), (String) (tag="DATE_PICKER"));
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                txtfecha.setText(materialDatePicker.getHeaderText());
                Log.d("agenda", " "+txtfecha);

            }
        });

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaElegida = new Date(formato.parse(String.valueOf(txtfecha)).getTime());

        traerProximosTurnosMedico(idUsr, idPaciente, matricula, nombre, fechaElegida);
    }*/

    private void traerProximosTurnosMedico(final int idUsr, final int idPaciente, final String matricula, final String nombre, Date fechaHoy){
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String year = df.format(fechaHoy);

        String fechaTexto = (fechaHoy.getDate() < 10 ? "0" : "") + fechaHoy.getDate() + "/" + (fechaHoy.getMonth() + 1 < 10 ? "0" : "") + (fechaHoy.getMonth()+1) + "/" + year;
        txtfecha.setText(fechaTexto);

        Log.d("agenda", "ENTRO AL METODO");

        Log.d("agenda",""+matricula);

        SimpleDateFormat formatoCall = new SimpleDateFormat("MM/dd/yyyy");
        String fechaCallString = formatoCall.format(fechaHoy);

        Call<List<Turno>> call = RetrofitClient.getInstance().getTurnosMedicoPorDia().getTurnosMedicoPorDia(matricula, fechaCallString);
        Log.d("agenda", ""+call.request());
        call.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                Log.d("agenda", response.toString());
                if (response.code() == 200) {
                    Log.d("agenda", "ES 200");
                    if (!response.body().isEmpty()) {
                        Log.d("agenda", response.toString());
                        Log.d("agenda", "NO ESTOY VACIO");
                        recyclerViewItem.setVisibility(View.VISIBLE);
                        mensajeNotieneTurnos.setVisibility(View.GONE);

                        completarCards(response.body(), idUsr, idPaciente, matricula, nombre);
                    } else {
                        Log.d("agenda", "ESTOY VACIO");
                        mensajeNotieneTurnos.setVisibility(View.VISIBLE);
                        recyclerViewItem.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Log.d("agenda","falla :(");
                Toast.makeText(ver_agenda.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void completarCards(List<Turno> items, int idUsr, int idPaciente, String matricula, String nombre){

        recyclerViewItem.setLayoutManager(new LinearLayoutManager(this));
        adaptador_items = new GroupAdp(this, items, idUsr, idPaciente, matricula, nombre);
        recyclerViewItem.setAdapter(adaptador_items);
    }

    private void vincular(){
        txtfecha = (TextView) findViewById(R.id.txtfecha);
        mensajeNotieneTurnos = (RelativeLayout) findViewById(R.id.mensajeNotieneTurnos);
        recyclerViewItem = (RecyclerView) findViewById(R.id.recyclerViewItem);
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
