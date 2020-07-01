package com.example.loginclinicapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    RelativeLayout layoutConfirmarAsistencia, layoutMedicoTurnoSinConfirmar, layoutTurnoLibre, layoutPacienteCancelarTurno;
    Button btnCancelarTurnoPaciente, btnPedirTurno, btnEliminarTurno, btnNoAsistire, btnAsistire;
    AlertDialog.Builder builder, builder2;

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


        Log.d("turnoDet","Estoy por hacer la call");
        Call<Turno> turno = RetrofitClient.getInstance().getTurno().getTurno(idTurno);
        Log.d("turnoDet", ""+ turno.request());
        turno.enqueue(new Callback<Turno>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<Turno> call, Response<Turno> response) {
                if(response.code()==200) {
                    if (response.body() != null) {
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
                                                            Call<Void> eliminar = RetrofitClient.getInstance().getCambiarEstadoDeTurnoService().cambiarEstadoDeTurno(idTurno, null, 3, 1);
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
                                        }
                                    });
                                    btnNoAsistire.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //Abre el intent de la justificacion
                                             /*Intent i = new Intent(inicio_paciente.this , DetalleTurno.class);
                                            i.putExtra("idUsr", idUsr);
                                            i.putExtra("idPaciente",idPaciente);
                                            i.putExtra("matricula",  matricula);
                                            i.putExtra("nombre",nombre);
                                            i.putExtra("idTurno", idTurno);
                                            startActivity(i);*/
                                        }
                                    });
                                }
                            }


                        }


                        txtFechaTurno.setText("Turno " + fecha.getDayOfMonth() + " de " + mesEnPalabras.toUpperCase().substring(0,1) + mesEnPalabras.substring(1));

                        if(response.body().getDisponibilidad().equals("Disponible")) {
                            txtEstado.setText("Estado: Disponible");
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
                        else if(response.body().getDisponibilidad().equals("Confirmado")) {
                            txtEstado.setText("Estado: Confirmado");
                            imgEstado.setImageResource(R.drawable.ok);
                        }
                        else if(response.body().getDisponibilidad().equals("Terminado")) {
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
                            txtPrecio.setText("Precio: " + response.body().getPrecio());
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

                        if(response.body().getDisponibilidad() == "Cancelado") {
                            txtCancela.setVisibility(View.VISIBLE);
                            txtMotivoCancela.setVisibility(View.VISIBLE);
                            imgMotivoCancela.setVisibility(View.VISIBLE);
                            imgCanceladoPor.setVisibility(View.VISIBLE);

                            if (response.body().getAsistencia() == "NoAsiste") {
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
        txtMotivoCancela = (TextView) findViewById(R.id.txtCancela);
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
    }

}
