package com.example.loginclinicapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class inicio_paciente extends AppCompatActivity {

    Button btnVerMisTurnos, btnPedirTurno, btnHistorial;
    TextView textViewDiaTurno, textViewMesTurno, textViewDiaSemanaTurno, textViewHorario, textViewMedico, textViewEspecialidad, textViewProxTurno;
    RelativeLayout cuadroFecha, cuadroTurno, cuadroSinProximosTurnos, pacMed, layoutMisTurnos, layoutPedirTurno, layoutHistorial;
    ImageView seleccionado, noSeleccionado;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    AlertDialog.Builder builderCerrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_paciente);

        vincular();
        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr",0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");
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
                        break; //En este caso, ya está en el inicio, no tiene que hacer nada.
                    case R.id.agenda:
                        Intent i = new Intent(inicio_paciente.this, ver_mis_turnos.class);
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
                                        Intent intent = new Intent(inicio_paciente.this, Login.class);
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




        if(matricula != null){
            pacMed.setVisibility(View.VISIBLE);
        }

        noSeleccionado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this , inicio_medico.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });


        layoutMisTurnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this, ver_mis_turnos.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });

        layoutPedirTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this, pedir_turno.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });

        layoutHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this, historial_turnos_pacientes.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });


        btnVerMisTurnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this, ver_mis_turnos.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });

        btnPedirTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this, pedir_turno.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });

        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inicio_paciente.this, historial_turnos_pacientes.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });


        Call<ProximoTurno> proximoTurno = RetrofitClient.getInstance().getProximoTurnoPaciente().getProximoTurno(idPaciente);
        proximoTurno.enqueue(new Callback<ProximoTurno>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<ProximoTurno> call, Response<ProximoTurno> response) {
                if(response.code()==200) {
                    if (response.body() != null) {
                        ProximoTurno proximoTurno = new ProximoTurno(response.body().getFecha(), response.body().getEspecialidad(), response.body().getMedico(), response.body().getId());
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime fecha = LocalDateTime.parse(proximoTurno.getFecha(), formatter);


                        String diaEnPalabras = fecha.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es","ES"));
                        String mesEnPalabras = fecha.getMonth().getDisplayName(TextStyle.FULL, new Locale("es","ES"));

                        textViewDiaTurno = (TextView) findViewById(R.id.textViewDiaTurno);
                        textViewDiaTurno.setText(""+fecha.getDayOfMonth());

                        textViewMesTurno = (TextView) findViewById(R.id.textViewMesTurno);
                        textViewMesTurno.setText(mesEnPalabras.toUpperCase().substring(0,3) + ".");

                        textViewDiaSemanaTurno = (TextView) findViewById(R.id.textViewDiaSemanaTurno);
                        textViewDiaSemanaTurno.setText(diaEnPalabras.toUpperCase().substring(0,1) + diaEnPalabras.substring(1));

                        textViewHorario = (TextView) findViewById(R.id.textViewHorario);
                        textViewHorario.setText(fecha.getHour() + ":" + (fecha.getMinute() < 10 ? "0" : "") + fecha.getMinute() + "hs.");

                        textViewMedico = (TextView) findViewById(R.id.textViewMedico);
                        textViewMedico.setText(response.body().getMedico().getNombre());

                        textViewEspecialidad  = (TextView) findViewById(R.id.textViewEspecialidad);
                        textViewEspecialidad.setText(response.body().getEspecialidad().getNombre());

                        cuadroFecha = (RelativeLayout) findViewById(R.id.cuadroFecha);
                        cuadroTurno = (RelativeLayout) findViewById(R.id.cuadroTurno);

                        final int idTurno = proximoTurno.getId();
                        cuadroFecha.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(inicio_paciente.this , DetalleTurno.class);
                                i.putExtra("idUsr", idUsr);
                                i.putExtra("idPaciente",idPaciente);
                                i.putExtra("matricula",  matricula);
                                i.putExtra("nombre",nombre);
                                i.putExtra("idTurno", idTurno);
                                startActivity(i);
                            }
                        });
                        cuadroTurno.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(inicio_paciente.this , DetalleTurno.class);
                                i.putExtra("idUsr", idUsr);
                                i.putExtra("idPaciente",idPaciente);
                                i.putExtra("matricula",  matricula);
                                i.putExtra("nombre",nombre);
                                i.putExtra("idTurno", idTurno);
                                startActivity(i);
                            }
                        });

                    }
                    else {

                        textViewProxTurno = (TextView) findViewById(R.id.textViewProxTurno);
                        textViewProxTurno.setVisibility(View.GONE);

                        cuadroFecha = (RelativeLayout) findViewById(R.id.cuadroFecha);
                        cuadroFecha.setVisibility(View.GONE);

                        cuadroTurno = (RelativeLayout) findViewById(R.id.cuadroTurno);
                        cuadroTurno.setVisibility(View.GONE);

                        cuadroSinProximosTurnos = (RelativeLayout) findViewById(R.id.cuadroSinProximosTurnos);
                        cuadroSinProximosTurnos.setVisibility(View.VISIBLE);

                    }
                }
            }

            @Override
            public void onFailure(Call<ProximoTurno> call, Throwable t) {
                Log.d("proximoTurno", ":(");
            }
        });

    }

    private void vincular() {
        btnVerMisTurnos = (Button) findViewById(R.id.btnVerMisTurnos);
        btnPedirTurno = (Button) findViewById(R.id.btnPedirTurno);
        btnHistorial = (Button) findViewById(R.id.btnHistorial);
        pacMed = (RelativeLayout) findViewById(R.id.pacMed);
        seleccionado = (ImageView) findViewById(R.id.seleccionado);
        noSeleccionado = (ImageView) findViewById(R.id.noSeleccionado);
        layoutMisTurnos = (RelativeLayout) findViewById(R.id.layoutMisTurnos);
        layoutPedirTurno= (RelativeLayout) findViewById(R.id.layoutPedirTurno);
        layoutHistorial= (RelativeLayout) findViewById(R.id.layoutHistorial);
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


