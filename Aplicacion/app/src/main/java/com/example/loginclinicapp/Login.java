package com.example.loginclinicapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        builder = new AlertDialog.Builder(this);
        vincular();
        Log.d("loginresp", "Existo.");

        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usr = new Usuario(txtusuario.getText().toString(),txtcontrasena.getText().toString());
                if(txtusuario.getText().toString().isEmpty()){
                    Toast.makeText(Login.this,"Introduzca su nombre de usuario.",Toast.LENGTH_LONG).show();
                }
                else if(txtcontrasena.getText().toString().isEmpty()){
                    Toast.makeText(Login.this,"Introduzca su contraseña.",Toast.LENGTH_LONG).show();
                }
                else {

                    Call<Usuario> call = RetrofitClient.getInstance().getLoginService().getUsuario(txtusuario.getText().toString(), txtcontrasena.getText().toString());
                    call.enqueue(new Callback<Usuario>() {
                                     @Override
                                     public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                         Log.d("loginresp", response.toString());

                                         if(response.code() == 200)
                                         {

                                             if(response.body() != null) {
                                                 Log.d("logiresp", "" + response.body().getIdUsr());
                                                 Toast.makeText(Login.this, "Logueado con éxito. Id de usuario: " + response.body().getIdUsr(), Toast.LENGTH_LONG).show();
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
    }

    private void vincular(){
        txtusuario = (EditText)findViewById(R.id.txtusuario);
        txtcontrasena = (EditText) findViewById(R.id.txtcontrasena);
        btningresar = (Button) findViewById(R.id.btningresar);
    }
}
