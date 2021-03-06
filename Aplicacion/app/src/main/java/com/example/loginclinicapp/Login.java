package com.example.loginclinicapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText txtusuario, txtcontrasena;
    Button btningresar;
    CheckBox rbrecordarme;
    AlertDialog.Builder builder;
    Usuario usuario = null;
    Intent i = null;
    int idUsr;
    int idPaciente;
    String matricula;
    String nombre;
    boolean cierraSesion = false;

    int pacMed = 0; //1 = Paciente ; 2 = Medico ; 3 = Paciente y medico

    boolean estaActivado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        builder = new AlertDialog.Builder(this);
        getSupportActionBar().hide();
        vincular();
        Log.d("loginresp", "Existo.");
        Intent intent = getIntent();
        boolean cierraSesion = intent.getBooleanExtra("cierraSesion", false);

        if(cierraSesion)
        {
            SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
            preferences.edit().putBoolean("estado", false).apply();
        }

        if(obtenerInfoLogin()){ // esto es para cuando el RB es true, que se saltee la pantalla de log-in y que comience en el inicio.
            Log.d("datosRecordarmeNom", ""+nombre);
            Log.d("datosRecordarmeMat", ""+matricula);
            Log.d("datosRecordarmePac", ""+idPaciente);
            Log.d("datosRecordarmeUsr", ""+idUsr);
            if(matricula != null)
            {
                Intent i = new Intent(Login.this, inicio_medico.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula", matricula);
                i.putExtra("nombre", nombre);
                startActivity(i);
            }
            else{
                Intent i = new Intent(Login.this, inicio_paciente.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula", matricula);
                i.putExtra("nombre", nombre);
                startActivity(i);

            }

        }

        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtusuario.getText().toString().isEmpty()){
                    Toast.makeText(Login.this,"Introduzca su nombre de usuario.",Toast.LENGTH_LONG).show();
                }
                else if(txtcontrasena.getText().toString().isEmpty()){
                    Toast.makeText(Login.this,"Introduzca su contraseña.",Toast.LENGTH_LONG).show();
                }
                else {
                    usuario = new Usuario(txtusuario.getText().toString(), txtcontrasena.getText().toString());

                    Call<Usuario> call = RetrofitClient.getInstance().getLoginService().getUsuario(txtusuario.getText().toString(), txtcontrasena.getText().toString());
                    call.enqueue(new Callback<Usuario>() {
                                     @Override
                                     public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                         Log.d("loginresp", response.toString());

                                         if(response.code() == 200)
                                         {

                                             if(response.body() != null) {
                                                 usuario.setDni(response.body().getDni());
                                                 usuario.setFechaNac(response.body().getFechaNac());
                                                 usuario.setNombre(response.body().getNombre());
                                                 usuario.setTelefono(response.body().getTelefono());
                                                 usuario.setIdUsr(response.body().getIdUsr());

                                                 Log.d("logiresp", "" + response.body().getIdUsr());
                                                 //Toast.makeText(Login.this, "Logueado con éxito. Id de usuario: " + response.body().getIdUsr(), Toast.LENGTH_LONG).show();

                                                 Call<Paciente> paciente = RetrofitClient.getInstance().getPacientePorIdUsuarioService().getPacientePorIdUsuario(response.body().getIdUsr());
                                                 paciente.enqueue(new Callback<Paciente>() {
                                                     @Override
                                                     public void onResponse(Call<Paciente> call, Response<Paciente> responsePac) {

                                                         if(responsePac.code() == 200) {
                                                             if(responsePac.body() != null){
                                                                 pacMed = 1;
                                                                 usuario.setIdPaciente(responsePac.body().getIdPaciente());
                                                                 usuario.setFechaVtoCuota(responsePac.body().getFechaVtoCuota());
                                                             }
                                                         }
                                                     }
                                                     @Override
                                                     public void onFailure(Call<Paciente> call, Throwable t) {
                                                     }
                                                 });

                                                 Call<Medico> medico = RetrofitClient.getInstance().getMedicoPorIdUsuarioService().getMedicoPorIdUsuario(response.body().getIdUsr());
                                                 medico.enqueue(new Callback<Medico>() {
                                                     @Override
                                                     public void onResponse(Call<Medico> call, Response<Medico> responseMed) {
                                                         if(responseMed.code()==200) {
                                                             if (responseMed.body() != null) {
                                                                 usuario.setMatricula(responseMed.body().getMatricula());
                                                                 usuario.setEspecialidades(responseMed.body().getEspecialidades());
                                                                 guardarInfoLogin();

                                                                 //Es medico y puede o no ser paciente.

                                                                 i = new Intent(Login.this, inicio_medico.class);
                                                                 i.putExtra("idUsr",usuario.getIdUsr());
                                                                 i.putExtra("idPaciente",usuario.getIdPaciente());
                                                                 i.putExtra("nombre",usuario.getNombre());
                                                                 i.putExtra("matricula", usuario.getMatricula());


                                                                 startActivity(i);
                                                             }
                                                             else{
                                                                 guardarInfoLogin();
                                                                 i = new Intent(Login.this, inicio_paciente.class);
                                                                 i.putExtra("idUsr",usuario.getIdUsr());
                                                                 i.putExtra("idPaciente",usuario.getIdPaciente());
                                                                 i.putExtra("matricula", usuario.getMatricula());
                                                                 i.putExtra("nombre",usuario.getNombre());
                                                                 startActivity(i);
                                                             }
                                                         }
                                                     }

                                                     @Override
                                                     public void onFailure(Call<Medico> call, Throwable t) {

                                                     }
                                                 });

                                               /*  if(pacMed == 1){
                                                     i = new Intent(Login.this, inicio_paciente.class);
                                                 }
                                                 else if (pacMed == 2){
                                                     i = new Intent(Login.this, inicio_medico.class);
                                                 }
                                                 else{
                                                     //Intent hacia el inicio de medico que es tambien paciente.
                                                 }*/


                                                // startActivity(i);
                                                 finishAffinity(); //hace que cuando estas loggeado y decidis ir para atras, no aparezca la activity de log-in ni la de carga. Va a la pantalla ppl del celu.
                                             }
                                             else{
                                                 builder.setTitle("Error");
                                                 builder.setMessage("Usuario y/o contraseña incorrectos.");
                                                 builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                                     @Override
                                                     public void onClick(DialogInterface dialog, int which) {
                                                         dialog.cancel();
                                                     }
                                                 });

                                                 AlertDialog alert = builder.create();
                                                 //Setting the title manually
                                                 alert.show();
                                                 //Toast.makeText(MainActivity.this, "Usuario y/o contraseña incorrectos.", Toast.LENGTH_LONG).show();
                                             }
                                         }
                                         else{
                                             Toast.makeText(Login.this, "Error del servidor, por favor vuelva a intentarlo.", Toast.LENGTH_LONG).show();
                                         }
                                     }

                                     @Override
                                     public void onFailure(Call<Usuario> call, Throwable t) {

                                         Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();

                                     }
                                 }
                    );

                }
            }
        });

        estaActivado = rbrecordarme.isChecked(); //el radio Button NO esta seleccionado.
        rbrecordarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estaActivado){
                    rbrecordarme.setChecked(false);
                }
                estaActivado = rbrecordarme.isChecked();
            }
        });
    }

    private void vincular(){
        txtusuario = (EditText) findViewById(R.id.txtusuario);
        txtcontrasena = (EditText) findViewById(R.id.txtcontrasena);
        btningresar = (Button) findViewById(R.id.btningresar);
        rbrecordarme = (CheckBox) findViewById(R.id.recordarme);
    }
    private void guardarInfoLogin(){
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        preferences.edit().putBoolean("estado", rbrecordarme.isChecked()).apply();
        preferences.edit().putInt("idUsuario", usuario.getIdUsr()).apply();
        preferences.edit().putString("matricula", usuario.getMatricula()).apply();
        preferences.edit().putInt("idPaciente", usuario.getIdPaciente()).apply();
        preferences.edit().putString("nombre", usuario.getNombre()).apply();


        Log.d("VerPref", preferences.getAll().toString());

    }
    private boolean obtenerInfoLogin(){
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        idUsr = preferences.getInt("idUsr", 0);
        idPaciente = preferences.getInt("idPaciente", 0);
        matricula = preferences.getString("matricula", null);
        nombre = preferences.getString("nombre", null);
        return preferences.getBoolean("estado", false);

    }

    @Override
    public void onBackPressed()
    {
        //finish();
        moveTaskToBack(true);

    }

}