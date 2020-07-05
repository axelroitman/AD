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

public class ver_mis_turnos extends AppCompatActivity {

    RelativeLayout mensajeSinTurnos;
    RecyclerView recyclerViewItems;
    GroupAdp adaptador_items;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    AlertDialog.Builder builderCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_mis_turnos);

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
                        Intent i = new Intent(ver_mis_turnos.this, inicio_paciente.class);
                        i.putExtra("idUsr", idUsr);
                        i.putExtra("idPaciente",idPaciente);
                        i.putExtra("matricula",  matricula);
                        i.putExtra("nombre",nombre);
                        startActivity(i);

                        break;
                    case R.id.agenda:
                        break; //En este caso, ya está en mis turnos, no tiene que hacer nada.
                    case R.id.cerrarSesion:
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        Intent intent = new Intent(ver_mis_turnos.this, Login.class);
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



        traerDatosTurnos(idUsr, idPaciente, matricula, nombre);
    }


    private void vincular(){
        recyclerViewItems = (RecyclerView) findViewById(R.id.recyclerViewItems);
        mensajeSinTurnos = (RelativeLayout) findViewById(R.id.mensajeSinTurnos);
    }

    private void traerDatosTurnos(final int idUsr, final int idPaciente, final String matricula, final String nombre){

        Log.d("misTurnos",""+idPaciente);
        Call<List<Turno>> call = RetrofitClient.getInstance().getTurnoPaciente().getTurnosPaciente(idPaciente, true);

        Log.d("misTurnos", ""+call.request());
        call.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {

                Log.d("misTurnos", response.toString());
                if (response.code() == 200) {

                    Log.d("misTurnos", "ES 200");
                    if (!response.body().isEmpty()) {

                        Log.d("misTurnos", response.toString());
                        Log.d("misTurnos", "NO ESTOY VACIO");
                        recyclerViewItems.setVisibility(View.VISIBLE);
                        mensajeSinTurnos.setVisibility(View.GONE);

                        completarCards(response.body(), idUsr, idPaciente, matricula, nombre);

                    } else {

                        Log.d("misTurnos", "ESTOY VACIO");
                        mensajeSinTurnos.setVisibility(View.VISIBLE);
                        recyclerViewItems.setVisibility(View.GONE);
                    }
                }
            }

            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Log.d("misTurnos","falla :(");
                Toast.makeText(ver_mis_turnos.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void completarCards(List<Turno> items, int idUsr, int idPaciente, String matricula, String nombre){
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
        adaptador_items = new GroupAdp(this, items, idUsr, idPaciente, matricula, nombre);
        recyclerViewItems.setAdapter(adaptador_items);
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



