package com.example.loginclinicapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.time.temporal.ChronoUnit.HOURS;

public class DetalleTurno extends AppCompatActivity {

    TextView txtFechaTurno, txtAsistencia, txtCancela, txtMotivoCancela, txtPrecio, txtHorario, txtEspecialidad, txtProfesionalPaciente, txtEstado;
    ImageView imgAsistencia, imgEstado, imgProfesionalPaciente, imgMotivoCancela, imgCanceladoPor, imgPrecio;
    RelativeLayout layoutConfirmarAsistencia, layoutMedicoTurnoSinConfirmar, layoutTurnoLibre, layoutPacienteCancelarTurno, layoutInfo;
    Button btnCancelarTurnoPaciente, btnPedirTurno, btnEliminarTurno, btnNoAsistire, btnAsistire;
    AlertDialog.Builder builder, builder2, builderCerrar;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_turno);
        vincular();

        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr",0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");
        final int idTurno = i.getIntExtra("idTurno", 0);

        builder = new AlertDialog.Builder(this);
        builder2 = new AlertDialog.Builder(this);
        builderCerrar = new AlertDialog.Builder(this);


        dl = (DrawerLayout)findViewById(R.id.inicioMedDL);
        t = new ActionBarDrawerToggle(this, dl,R.string.app_name, R.string.app_name);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);

        final View headerView = nv.getHeaderView(0);
        TextView nombreUsr = (TextView) headerView.findViewById(R.id.nombreUsuario);
        nombreUsr.setText(nombre);
        final ImageView imagenUsuario = (ImageView) headerView.findViewById(R.id.imageUser);


        Log.d("turnoDet","Estoy por hacer la call");
        Call<Turno> turno = RetrofitClient.getInstance().getTurno().getTurno(idTurno);
        Log.d("turnoDet", ""+ turno.request());
        turno.enqueue(new Callback<Turno>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<Turno> call, final Response<Turno> response) {
                if(response.code()==200) {
                    if (response.body() != null) {


                        /* Inicio de panel desplegable */



                        if(!response.body().getMedico().getMatricula().equals(matricula)) {

                            imagenUsuario.setImageResource(R.drawable.userimage);

                            Menu menu = nv.getMenu();
                            MenuItem itemMenu = (MenuItem) menu.findItem(R.id.agenda);
                            itemMenu.setTitle("Mis turnos");
                        }
                        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                                int id = item.getItemId();
                                switch(id)
                                {
                                    case R.id.inicio:
                                        if(response.body().getMedico().getMatricula().equals(matricula)) {
                                            Intent intentInicio = new Intent(DetalleTurno.this, inicio_medico.class);
                                            intentInicio.putExtra("idUsr", idUsr);
                                            intentInicio.putExtra("idPaciente", idPaciente);
                                            intentInicio.putExtra("matricula", matricula);
                                            intentInicio.putExtra("nombre", nombre);
                                            startActivity(intentInicio);
                                        }
                                        else{
                                            Intent intentInicio = new Intent(DetalleTurno.this, inicio_paciente.class);
                                            intentInicio.putExtra("idUsr", idUsr);
                                            intentInicio.putExtra("idPaciente", idPaciente);
                                            intentInicio.putExtra("matricula", matricula);
                                            intentInicio.putExtra("nombre", nombre);
                                            startActivity(intentInicio);

                                        }
                                        break;
                                    case R.id.agenda:
                                        if(response.body().getMedico().getMatricula().equals(matricula)) {
                                            Intent i = new Intent(DetalleTurno.this, ver_agenda.class);
                                            i.putExtra("idUsr", idUsr);
                                            i.putExtra("idPaciente",idPaciente);
                                            i.putExtra("matricula",  matricula);
                                            i.putExtra("nombre",nombre);
                                            startActivity(i);
                                        }
                                        else{
                                            Intent i = new Intent(DetalleTurno.this, ver_mis_turnos.class);
                                            i.putExtra("idUsr", idUsr);
                                            i.putExtra("idPaciente",idPaciente);
                                            i.putExtra("matricula",  matricula);
                                            i.putExtra("nombre",nombre);
                                            startActivity(i);

                                        }
                                        break;
                                    case R.id.cerrarSesion:

                                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                switch (which){
                                                    case DialogInterface.BUTTON_POSITIVE:
                                                        Intent intent = new Intent(DetalleTurno.this, Login.class);
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

                        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime fecha = LocalDateTime.parse(response.body().getFecha(), formato);
                        String mesEnPalabras = fecha.getMonth().getDisplayName(TextStyle.FULL, new Locale("es","ES"));

                        if(response.body().getMedico().getMatricula().equals(matricula)) {
                            //OPCIONES DEL MEDICO
                            LocalDateTime fechaActual = LocalDateTime.now();
                            long diferenciaHoras = HOURS.between(fechaActual, fecha);
                            if(response.body().getPaciente() == null && diferenciaHoras >= 0){
                                layoutMedicoTurnoSinConfirmar.setVisibility(View.VISIBLE);
                                btnEliminarTurno.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {


                                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                switch (which){
                                                    case DialogInterface.BUTTON_POSITIVE:
                                                        Call<Void> eliminar = RetrofitClient.getInstance().getEliminarTurnoService().eliminarTurno(idTurno);
                                                        eliminar.enqueue(new Callback<Void>() {
                                                            @Override
                                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                                if(response.code() == 200){


                                                                    builder2.setTitle("Turno");
                                                                    builder2.setMessage("El turno se ha eliminado.");
                                                                    builder2.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                            Intent i = new Intent(DetalleTurno.this, inicio_medico.class);
                                                                            i.putExtra("idUsr", idUsr);
                                                                            i.putExtra("idPaciente",idPaciente);
                                                                            i.putExtra("matricula",  matricula);
                                                                            i.putExtra("nombre",nombre);
                                                                            startActivity(i);
                                                                        }
                                                                    });

                                                                    AlertDialog alert = builder2.create();
                                                                    //Setting the title manually
                                                                    alert.show();
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Call<Void> call, Throwable t) {

                                                            }
                                                        });
                                                        break;

                                                    case DialogInterface.BUTTON_NEGATIVE:
                                                        dialog.cancel();
                                                        break;
                                                }
                                            }
                                        };

                                        builder.setMessage("¿Está seguro de que quiere eliminar el turno?").setPositiveButton("Sí", dialogClickListener)
                                                .setNegativeButton("No", dialogClickListener).show();

                                    }
                                });

                            }
                        }
                        else {
                            //OPCIONES DEL PACIENTE
                            if(response.body().getPaciente() == null){
                                layoutTurnoLibre.setVisibility(View.VISIBLE);
                                btnPedirTurno.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //Hace la call pedir el turno
                                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                switch (which){
                                                    case DialogInterface.BUTTON_POSITIVE:
                                                        Call<Void> pedirTurno = RetrofitClient.getInstance().getCambiarEstadoDeTurnoService().cambiarEstadoDeTurno(idTurno, idPaciente, 3, 2, null);
                                                        pedirTurno.enqueue(new Callback<Void>() {
                                                            @Override
                                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                                if(response.code() == 201){
                                                                    builder2.setTitle("Turno");
                                                                    builder2.setMessage("El turno fue solicitado exitosamente. Recuerde confirmar asistencia a partir de las 12 horas previas al turno.");
                                                                    builder2.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                            Intent i = new Intent(DetalleTurno.this, inicio_paciente.class);
                                                                            i.putExtra("idUsr", idUsr);
                                                                            i.putExtra("idPaciente",idPaciente);
                                                                            i.putExtra("matricula",  matricula);
                                                                            i.putExtra("nombre",nombre);
                                                                            startActivity(i);
                                                                        }
                                                                    });

                                                                    AlertDialog alert = builder2.create();
                                                                    //Setting the title manually
                                                                    alert.show();
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Call<Void> call, Throwable t) {
                                                                Log.d("cambiarEstado",":(");

                                                            }
                                                        });
                                                        break;

                                                    case DialogInterface.BUTTON_NEGATIVE:
                                                        dialog.cancel();
                                                        break;
                                                }
                                            }
                                        };

                                        builder.setMessage("¿Está seguro de que desea solicitar el turno?").setPositiveButton("Sí", dialogClickListener)
                                                .setNegativeButton("No", dialogClickListener).show();

                                    }
                                });
                            }
                            else{
                                LocalDateTime fechaActual = LocalDateTime.now();
                                long diferenciaHoras = HOURS.between(fechaActual, fecha);
                                if(diferenciaHoras > 12 && !response.body().getDisponibilidad().equals("Cancelado")) {
                                    layoutPacienteCancelarTurno.setVisibility(View.VISIBLE);
                                    btnCancelarTurnoPaciente.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //Hace la call cancelar el turno
                                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    switch (which){
                                                        case DialogInterface.BUTTON_POSITIVE:
                                                            Call<Void> eliminar = RetrofitClient.getInstance().getCambiarEstadoDeTurnoService().cambiarEstadoDeTurno(idTurno, null, 3, 1, null);
                                                            Log.d("cambiarEstado","Paso1");
                                                            eliminar.enqueue(new Callback<Void>() {
                                                                @Override
                                                                public void onResponse(Call<Void> call, Response<Void> response) {
                                                                    if(response.code() == 201){
                                                                        Log.d("cambiarEstado","Paso2");
                                                                        builder2.setTitle("Turno");
                                                                        builder2.setMessage("El turno se ha cancelado.");
                                                                        builder2.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialog, int which) {
                                                                                Intent i = new Intent(DetalleTurno.this, inicio_paciente.class);
                                                                                i.putExtra("idUsr", idUsr);
                                                                                i.putExtra("idPaciente",idPaciente);
                                                                                i.putExtra("matricula",  matricula);
                                                                                i.putExtra("nombre",nombre);
                                                                                startActivity(i);
                                                                            }
                                                                        });

                                                                        AlertDialog alert = builder2.create();
                                                                        //Setting the title manually
                                                                        alert.show();
                                                                    }
                                                                }

                                                                @Override
                                                                public void onFailure(Call<Void> call, Throwable t) {
                                                                    Log.d("cambiarEstado",":(");

                                                                }
                                                            });
                                                            break;

                                                        case DialogInterface.BUTTON_NEGATIVE:
                                                            dialog.cancel();
                                                            break;
                                                    }
                                                }
                                            };

                                            builder.setMessage("¿Está seguro de que desea cancelar el turno?").setPositiveButton("Sí", dialogClickListener)
                                                    .setNegativeButton("No", dialogClickListener).show();

                                        }
                                    });
                                }
                                else if(diferenciaHoras <= 12 && diferenciaHoras >= 0 && !response.body().getDisponibilidad().equals("Cancelado") && response.body().getAsistencia().equals("NoConfirmo")){
                                    layoutConfirmarAsistencia.setVisibility(View.VISIBLE);
                                    btnAsistire.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //Hace la call confirmar turno

                                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    switch (which){
                                                        case DialogInterface.BUTTON_POSITIVE:
                                                            Call<Void> pedirTurno = RetrofitClient.getInstance().getCambiarEstadoDeTurnoService().cambiarEstadoDeTurno(idTurno, idPaciente, 1, 4, null);
                                                            pedirTurno.enqueue(new Callback<Void>() {
                                                                @Override
                                                                public void onResponse(Call<Void> call, Response<Void> response) {
                                                                    if(response.code() == 201){
                                                                        builder2.setTitle("Turno");
                                                                        builder2.setMessage("Su asistencia ha sido confirmada.");
                                                                        builder2.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialog, int which) {
                                                                                Intent i = new Intent(DetalleTurno.this, inicio_paciente.class);
                                                                                i.putExtra("idUsr", idUsr);
                                                                                i.putExtra("idPaciente",idPaciente);
                                                                                i.putExtra("matricula",  matricula);
                                                                                i.putExtra("nombre",nombre);
                                                                                startActivity(i);
                                                                            }
                                                                        });

                                                                        AlertDialog alert = builder2.create();
                                                                        //Setting the title manually
                                                                        alert.show();
                                                                    }
                                                                }

                                                                @Override
                                                                public void onFailure(Call<Void> call, Throwable t) {
                                                                    Log.d("cambiarEstado",":(");

                                                                }
                                                            });
                                                            break;

                                                        case DialogInterface.BUTTON_NEGATIVE:
                                                            dialog.cancel();
                                                            break;
                                                    }
                                                }
                                            };

                                            builder.setMessage("¿Está seguro de que desea confirmar su asistencia?").setPositiveButton("Sí", dialogClickListener)
                                                    .setNegativeButton("No", dialogClickListener).show();


                                        }
                                    });
                                    btnNoAsistire.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //Abre el intent de la justificacion
                                            Intent i = new Intent(DetalleTurno.this , JustificacionInasistencia.class);
                                            i.putExtra("idUsr", idUsr);
                                            i.putExtra("idPaciente",idPaciente);
                                            i.putExtra("matricula",  matricula);
                                            i.putExtra("nombre",nombre);
                                            i.putExtra("idTurno", idTurno);
                                            startActivity(i);
                                        }
                                    });
                                }
                            }


                        }


                        txtFechaTurno.setText("Turno " + fecha.getDayOfMonth() + " de " + mesEnPalabras.toUpperCase().substring(0,1) + mesEnPalabras.substring(1));
                        LocalDateTime fechaActual = LocalDateTime.now();
                        long diferenciaHoras = HOURS.between(fechaActual, fecha);

                        if(response.body().getDisponibilidad().equals("Disponible")  && diferenciaHoras >= 0) {
                            txtEstado.setText("Estado: Disponible");
                            imgEstado.setImageResource(R.drawable.ok);
                        }
                        else if(response.body().getDisponibilidad().equals("Disponible")  && diferenciaHoras < 0) {
                            txtEstado.setText("Estado: Sin ocupar");
                            imgEstado.setImageResource(R.drawable.ok);
                        }
                        else if(response.body().getDisponibilidad().equals("Programado")) {
                            txtEstado.setText("Estado: Programado");
                            imgEstado.setImageResource(R.drawable.ok);
                        }
                        else if(response.body().getDisponibilidad().equals("AConfirmar")) {
                            txtEstado.setText("Estado: A Confirmar");
                            imgEstado.setImageResource(R.drawable.ok);
                        }
                        else if(response.body().getDisponibilidad().equals("Confirmado") && diferenciaHoras >= 0) {
                            txtEstado.setText("Estado: Confirmado");
                            imgEstado.setImageResource(R.drawable.ok);
                        }
                        else if(response.body().getDisponibilidad().equals("Terminado") || (response.body().getDisponibilidad().equals("Confirmado") && diferenciaHoras < 0)) {
                            txtEstado.setText("Estado: Terminado");
                            imgEstado.setImageResource(R.drawable.ok);
                        }
                        else{
                            //Cancelado
                            txtEstado.setText("Estado: Cancelado");
                            imgEstado.setImageResource(R.drawable.notok);
                        }


                        txtEspecialidad.setText("Especialidad: " + response.body().getEspecialidad().getNombre());

                        if(response.body().getMedico().getMatricula().equals(matricula)) {
                            //Vista del medico

                            if(response.body().getPaciente() == null)
                            {
                                //No muestra paciente
                                txtProfesionalPaciente.setVisibility(View.GONE);
                                imgProfesionalPaciente.setVisibility(View.GONE);
                            }
                            else {
                                txtProfesionalPaciente.setText("Paciente: " + response.body().getPaciente().getNombre());
                                imgProfesionalPaciente.setImageResource(R.drawable.userimage);

                            }

                        }
                        else {
                            txtProfesionalPaciente.setText("Profesional: " + response.body().getMedico().getNombre());
                            imgProfesionalPaciente.setImageResource(R.drawable.doctorimage);
                        }
                        txtHorario.setText("Hora: " + fecha.getHour() + ":" + (fecha.getMinute() < 10 ? "0" : "") + fecha.getMinute());

                        if(response.body().getPrecio() != 0) {
                            txtPrecio.setText("Precio: $" + (int) response.body().getPrecio());
                        }
                        else{
                            imgPrecio.setVisibility(View.GONE);
                            txtPrecio.setVisibility(View.GONE);
                        }

                        if(response.body().getAsistencia().equals("NoAsiste")) {
                            txtAsistencia.setText("Asistencia: No asiste");
                            imgAsistencia.setImageResource(R.drawable.asistenciano);
                        }
                        else if(response.body().getAsistencia().equals("Asiste")) {
                            txtAsistencia.setText("Asistencia: Asiste");
                            imgAsistencia.setImageResource(R.drawable.asistenciaok);
                        }
                        else {
                            txtAsistencia.setText("Asistencia: Sin confirmar");
                            imgAsistencia.setImageResource(R.drawable.asistenciapendiente);
                        }

                        if(response.body().getDisponibilidad().equals("Cancelado")) {
                            //layoutInfo.setBottom(0);
                            txtCancela.setVisibility(View.VISIBLE);
                            txtMotivoCancela.setVisibility(View.VISIBLE);
                            imgMotivoCancela.setVisibility(View.VISIBLE);
                            imgCanceladoPor.setVisibility(View.VISIBLE);

                            if (response.body().getAsistencia().equals("NoAsiste")) {
                                txtCancela.setText("Cancelado por: Paciente");
                                txtMotivoCancela.setText("Motivo de cancelación: " + response.body().getJustifInasistencia());
                            }
                            else{
                                txtCancela.setText("Cancelado por: Centro médico");
                                txtMotivoCancela.setText("Motivo de cancelación: " + response.body().getJustifInasistencia());

                            }

                        }
                        Log.d("turnoDet", "Tengo el turno");
                    }
                }
            }

            @Override
            public void onFailure(Call<Turno> call, Throwable t) {
                Log.d("turnoDet", ":(");
            }
        });



    }

    private void vincular(){
        txtFechaTurno = (TextView) findViewById(R.id.txtFechaTurno);
        txtAsistencia = (TextView) findViewById(R.id.txtAsistencia);
        txtCancela = (TextView) findViewById(R.id.txtCancela);
        txtMotivoCancela = (TextView) findViewById(R.id.txtMotivoCancela);
        txtPrecio = (TextView) findViewById(R.id.txtPrecio);
        txtHorario = (TextView) findViewById(R.id.txtHorario);
        txtEspecialidad = (TextView) findViewById(R.id.txtEspecialidad);
        txtProfesionalPaciente = (TextView) findViewById(R.id.txtProfesionalPaciente);
        txtEstado = (TextView) findViewById(R.id.txtEstado);

        imgAsistencia = (ImageView) findViewById(R.id.imgAsistencia);
        imgEstado = (ImageView) findViewById(R.id.imgEstado);
        imgProfesionalPaciente = (ImageView) findViewById(R.id.imgProfesionalPaciente);
        imgMotivoCancela = (ImageView) findViewById(R.id.imgMotivoCancela);
        imgCanceladoPor = (ImageView) findViewById(R.id.imgCanceladoPor);
        imgPrecio = (ImageView) findViewById(R.id.imgPrecio);

        layoutConfirmarAsistencia = (RelativeLayout) findViewById(R.id.layoutConfirmarAsistencia);
        layoutMedicoTurnoSinConfirmar = (RelativeLayout) findViewById(R.id.layoutMedicoTurnoSinConfirmar);
        layoutTurnoLibre = (RelativeLayout) findViewById(R.id.layoutTurnoLibre);
        layoutPacienteCancelarTurno = (RelativeLayout) findViewById(R.id.layoutPacienteCancelarTurno);

        btnCancelarTurnoPaciente = (Button) findViewById(R.id.btnCancelarTurnoPaciente);
        btnPedirTurno = (Button) findViewById(R.id.btnPedirTurno);
        btnEliminarTurno = (Button) findViewById(R.id.btnEliminarTurno);
        btnNoAsistire = (Button) findViewById(R.id.btnNoAsistire);
        btnAsistire = (Button) findViewById(R.id.btnAsistire);

        layoutInfo = (RelativeLayout) findViewById(R.id.layoutInfo);
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
