package com.example.loginclinicapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class historial_turnos_pacientes extends AppCompatActivity {

    RelativeLayout txtMensajeError;
    RecyclerView recyclerView;
    GroupAdp adaptador_items;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    AlertDialog.Builder builderCerrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_turnos_pacientes);
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
                        Intent intentInicio = new Intent(historial_turnos_pacientes.this, inicio_paciente.class);
                        intentInicio.putExtra("idUsr", idUsr);
                        intentInicio.putExtra("idPaciente",idPaciente);
                        intentInicio.putExtra("matricula",  matricula);
                        intentInicio.putExtra("nombre",nombre);
                        startActivity(intentInicio);
                        break;
                    case R.id.agenda:
                        Intent i = new Intent(historial_turnos_pacientes.this, ver_mis_turnos.class);
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
                                        Intent intent = new Intent(historial_turnos_pacientes.this, Login.class);
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

        Log.d("historial", "ANTES DEL METODO");
        traerDatosTurnos(idUsr, idPaciente, matricula, nombre);
    }

    private void traerDatosTurnos(final int idUsr, final int idPaciente, final String matricula, final String nombre){
        Log.d("historial", "ENTRO AL METODO");

        Log.d("historial",""+idPaciente);
        Call<List<Turno>> call = RetrofitClient.getInstance().getTurnoPaciente().getTurnosPaciente(idPaciente, false);

        Log.d("historial", ""+call.request());
        call.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                Log.d("historial", response.toString());
                if (response.code() == 200) {
                    Log.d("historial", "ES 200");
                    if (!response.body().isEmpty()) {
                        Log.d("historial", response.toString());
                        Log.d("historial", "NO ESTOY VACIO");
                        recyclerView.setVisibility(View.VISIBLE);
                        txtMensajeError.setVisibility(View.GONE);

                        completarCards(response.body(), idUsr, idPaciente, matricula, nombre);
                        //Toast.makeText(historial_turnos_pacientes.this, response.body().getId(), Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("historial", "ESTOY VACIO");
                        txtMensajeError.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Log.d("historial","falla :(");
                Toast.makeText(historial_turnos_pacientes.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void completarCards(List<Turno> items, int idUsr, int idPaciente, String matricula, String nombre){

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptador_items = new GroupAdp(this, items, idUsr, idPaciente, matricula, nombre);
        recyclerView.setAdapter(adaptador_items);

    }


    private void vincular(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        txtMensajeError = (RelativeLayout) findViewById(R.id.txtMensajeError);
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
