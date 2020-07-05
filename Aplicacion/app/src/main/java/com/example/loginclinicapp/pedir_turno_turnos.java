package com.example.loginclinicapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

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
    private GroupAdpPedirTurnoTurnosEspYMed gptteyM;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    AlertDialog.Builder builderCerrar;

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
        builderCerrar = new AlertDialog.Builder(this);


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

        ImageView imagenUsuario = (ImageView) headerView.findViewById(R.id.imageUser);
        imagenUsuario.setImageResource(R.drawable.userimage);

        Menu menu = nv.getMenu();

        MenuItem itemMenu = (MenuItem) menu.findItem(R.id.agenda);
        itemMenu.setTitle("Mis turnos");

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch(id)
                {
                    case R.id.inicio:
                        Intent intentInicio = new Intent(pedir_turno_turnos.this, inicio_paciente.class);
                        intentInicio.putExtra("idUsr", idUsr);
                        intentInicio.putExtra("idPaciente",idPaciente);
                        intentInicio.putExtra("matricula",  matricula);
                        intentInicio.putExtra("nombre",nombre);
                        startActivity(intentInicio);
                        break;
                    case R.id.agenda:
                        Intent i = new Intent(pedir_turno_turnos.this, ver_mis_turnos.class);
                        i.putExtra("idUsr", idUsr);
                        i.putExtra("idPaciente",idPaciente);
                        i.putExtra("matricula",  matricula);
                        i.putExtra("nombre",nombre);
                        startActivity(i);
                        break;
                    case R.id.cerrarSesion:
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        Intent intent = new Intent(pedir_turno_turnos.this, Login.class);
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
            cardsTurnos.setLayoutManager(new LinearLayoutManager(this));
            gptteyM = new GroupAdpPedirTurnoTurnosEspYMed(this, turnos, idUsr, idPaciente, matricula, nombre, idEsp, matriculaSeleccionado, nombreEsp, nombreMedico);
            cardsTurnos.setAdapter(gptteyM);
        }
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
