package com.example.loginclinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class EliminarTurnosMenu extends AppCompatActivity {
    Button btnEliminarDia,btnEliminarMasivo;
    RelativeLayout eliminarDeUnDia, eliminarMasivo;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    AlertDialog.Builder builderCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_turnos_menu);
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

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch(id)
                {
                    case R.id.inicio:
                        Intent intentInicio = new Intent(EliminarTurnosMenu.this, inicio_medico.class);
                        intentInicio.putExtra("idUsr", idUsr);
                        intentInicio.putExtra("idPaciente",idPaciente);
                        intentInicio.putExtra("matricula",  matricula);
                        intentInicio.putExtra("nombre",nombre);
                        startActivity(intentInicio);

                        break;
                    case R.id.agenda:
                        Intent intentAgenda = new Intent(EliminarTurnosMenu.this, ver_agenda.class);
                        intentAgenda.putExtra("idUsr", idUsr);
                        intentAgenda.putExtra("idPaciente",idPaciente);
                        intentAgenda.putExtra("matricula",  matricula);
                        intentAgenda.putExtra("nombre",nombre);
                        startActivity(intentAgenda);
                        break;
                    case R.id.cerrarSesion:
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        Intent intent = new Intent(EliminarTurnosMenu.this, Login.class);
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

        eliminarMasivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EliminarTurnosMenu.this, eliminar_turnos.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });
        btnEliminarMasivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EliminarTurnosMenu.this, eliminar_turnos.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });

        eliminarDeUnDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EliminarTurnosMenu.this, EliminarTurnosDia.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });
        btnEliminarDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EliminarTurnosMenu.this, EliminarTurnosDia.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });

    }

    private void vincular(){
        btnEliminarDia = (Button) findViewById(R.id.btnEliminarDia);
        btnEliminarMasivo = (Button) findViewById(R.id.btnEliminarMasivo);
        eliminarDeUnDia = (RelativeLayout) findViewById(R.id.eliminarDeUnDia);
        eliminarMasivo =(RelativeLayout) findViewById(R.id.eliminarMasivo);

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
