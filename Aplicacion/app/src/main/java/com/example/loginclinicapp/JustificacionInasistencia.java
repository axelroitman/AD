package com.example.loginclinicapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JustificacionInasistencia extends AppCompatActivity {

    TextView txtTituloTurno, txtHoraTurno;
    Button btnCancelar, btnConfirmar;
    EditText inputJustificacion;
    AlertDialog.Builder builder, builder2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_justificacion_inasistencia);
        vincular();

        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr",0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");
        final int idTurno = i.getIntExtra("idTurno", 0);

        builder = new AlertDialog.Builder(this);
        builder2 = new AlertDialog.Builder(this);

        Call<Turno> turno = RetrofitClient.getInstance().getTurno().getTurno(idTurno);
        turno.enqueue(new Callback<Turno>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<Turno> call, Response<Turno> response) {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime fecha = LocalDateTime.parse(response.body().getFecha(), formato);
                String mesEnPalabras = fecha.getMonth().getDisplayName(TextStyle.FULL, new Locale("es","ES"));

                txtTituloTurno.setText("Turno " + fecha.getDayOfMonth() + " de " + mesEnPalabras.toUpperCase().substring(0,1) + mesEnPalabras.substring(1));
                txtHoraTurno.setText("Hora: " + fecha.getHour() + ":" + (fecha.getMinute() < 10 ? "0" : "") + fecha.getMinute());
                btnCancelar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(JustificacionInasistencia.this , DetalleTurno.class);
                                i.putExtra("idUsr", idUsr);
                                i.putExtra("idPaciente",idPaciente);
                                i.putExtra("matricula",  matricula);
                                i.putExtra("nombre",nombre);
                                i.putExtra("idTurno",idTurno);
                                startActivity(i);
                            }
                        }
                );
                btnConfirmar.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {

                               DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {
                                       switch (which){
                                           case DialogInterface.BUTTON_POSITIVE:
                                               String justificacion = inputJustificacion.getText().toString();
                                               Call<Void> confirmarInasistencia = RetrofitClient.getInstance().getCambiarEstadoDeTurnoService().cambiarEstadoDeTurno(idTurno, idPaciente, 2, 6, justificacion);
                                               confirmarInasistencia.enqueue(new Callback<Void>() {
                                                   @Override
                                                   public void onResponse(Call<Void> call, Response<Void> response) {
                                                       if(response.code() == 201){
                                                           builder2.setTitle("Turno");
                                                           builder2.setMessage("Se ha confirmado su inasistencia.");
                                                           builder2.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                                               @Override
                                                               public void onClick(DialogInterface dialog, int which) {
                                                                   Intent i = new Intent(JustificacionInasistencia.this, inicio_paciente.class);
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

                               builder.setMessage("¿Está seguro de que desea confirmar su inasistencia? Se le generarán gastos adicionales al no asistir.").setPositiveButton("Sí", dialogClickListener)
                                       .setNegativeButton("No", dialogClickListener).show();

                           }
                       }
                );


            }

            @Override
            public void onFailure(Call<Turno> call, Throwable t) {
                Log.d("Turno", ":(");
            }
        });


    }

    private void vincular(){
        txtTituloTurno = (TextView) findViewById(R.id.txtTituloTurno);
        txtHoraTurno = (TextView) findViewById(R.id.txtHoraTurno);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        inputJustificacion = (EditText) findViewById(R.id.inputJustificacion);

    }

}
