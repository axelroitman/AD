package com.example.loginclinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pedir_turno extends AppCompatActivity {

    Button btnbuscar, btncancelar;
    Spinner spmedicos, spespecialidades;
    TextView txtseleccionarEspecialidad, txtseleccionarMedico;
    Map<String, Integer> espNombreyId = new TreeMap<String, Integer>();
    Map<String, String> medNombreyMatricula = new TreeMap<String, String>();
    ArrayList<String> espNombres = new ArrayList<String>();
    ArrayList<String> medNombres = new ArrayList<String>();
    int idEsp;
    String matriculaSeleccionado, nombreEsp, nombreMedico;
    AlertDialog.Builder builder, builderCerrar;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_turno);

        vincular();
        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr",0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");

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
                        Intent intentInicio = new Intent(pedir_turno.this, inicio_paciente.class);
                        intentInicio.putExtra("idUsr", idUsr);
                        intentInicio.putExtra("idPaciente",idPaciente);
                        intentInicio.putExtra("matricula",  matricula);
                        intentInicio.putExtra("nombre",nombre);
                        startActivity(intentInicio);
                        break;
                    case R.id.agenda:
                        Intent i = new Intent(pedir_turno.this, ver_mis_turnos.class);
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
                                        Intent intent = new Intent(pedir_turno.this, Login.class);
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


        Call<Collection<Especialidad>> especialidades = RetrofitClient.getInstance().getEspecialidadesService().getEspecialidades();
        especialidades.enqueue(new Callback<Collection<Especialidad>>() {
            @Override
            public void onResponse(Call<Collection<Especialidad>> call, Response<Collection<Especialidad>> response) {
                if(response.code() == 200){
                    if(response.body() != null){
                        for (Especialidad e : response.body()){
                            espNombreyId.put(e.getNombre(),e.getIdEspecialidad());
                        }
                        espNombres.add("Seleccione una especialidad");
                        espNombres.addAll(espNombreyId.keySet());
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(pedir_turno.this,android.R.layout.simple_spinner_item, espNombres){
                            @Override
                            public boolean isEnabled(int position){
                                if(position == 0)
                                {
                                    // Disable the second item from Spinner
                                    return false;
                                }
                                else
                                {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                if(position == 0) {
                                    // Set the disable item text color
                                    tv.setTextColor(Color.GRAY);
                                }
                                else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spespecialidades.setAdapter(adapter);

                        spespecialidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                medNombreyMatricula.clear();
                                medNombres.clear();
                                if(spespecialidades.getSelectedItemPosition() != 0) {
                                    txtseleccionarMedico.setVisibility(View.VISIBLE);
                                    spmedicos.setVisibility(View.VISIBLE);
                                    idEsp = espNombreyId.get(spespecialidades.getSelectedItem().toString());
                                    nombreEsp = spespecialidades.getSelectedItem().toString();

                                    Call<Collection<Medico>> medicos = RetrofitClient.getInstance().getMedicoPorEspecialidadService().getMedicosPorEspecialidad(idEsp);
                                    medicos.enqueue(new Callback<Collection<Medico>>() {
                                        @Override
                                        public void onResponse(Call<Collection<Medico>> call, Response<Collection<Medico>> response) {
                                            if(response.code() == 200){
                                                if(response.body() != null){
                                                    if(matricula != null){
                                                        for (Medico m : response.body()){
                                                                if(! matricula.equals(m.getMatricula())){
                                                                    medNombreyMatricula.put(m.getNombre(),m.getMatricula());
                                                                }
                                                        }
                                                    }
                                                    else{
                                                        for (Medico m : response.body()){
                                                                medNombreyMatricula.put(m.getNombre(),m.getMatricula());
                                                        }
                                                    }
                                                    medNombres.add("Seleccione un médico");
                                                    medNombres.addAll(medNombreyMatricula.keySet());
                                                    ArrayAdapter<String> adapterMedicos = new ArrayAdapter<String>(pedir_turno.this,android.R.layout.simple_spinner_item,medNombres);
                                                    adapterMedicos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                    spmedicos.setAdapter(adapterMedicos);
                                                    spmedicos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                            if(position != 0) {
                                                                matriculaSeleccionado = medNombreyMatricula.get(spmedicos.getSelectedItem().toString());
                                                                nombreMedico = spmedicos.getSelectedItem().toString();
                                                            }
                                                            else{
                                                                matriculaSeleccionado = null;
                                                                nombreMedico = null;
                                                            }
                                                        }

                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> parent) {

                                                        }
                                                    });

                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Collection<Medico>> call, Throwable t) {

                                        }
                                    });

                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Collection<Especialidad>> call, Throwable t) {

            }
        });

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spespecialidades.getSelectedItem().toString().equals("Seleccione una especialidad"))
                {
                    builder.setTitle("Error");
                    builder.setMessage("Debe seleccionar una especialidad antes de iniciar su búsqueda.");
                    builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                }
                else {

                    Call<Paciente> paciente = RetrofitClient.getInstance().getPacientePorIdUsuarioService().getPacientePorIdUsuario(idUsr);
                    paciente.enqueue(new Callback<Paciente>() {
                        @Override
                        public void onResponse(Call<Paciente> call, Response<Paciente> responsePac) {

                            if (responsePac.code() == 200) {
                                if (responsePac.body() != null) {
                                    if (responsePac.body().getFechaVtoCuota().before(new Date())) {
                                        //Cuota vencida
                                        builder.setTitle("Error");
                                        builder.setMessage("Tiene una o más cuotas impagas. No podrá solicitar turnos hasta que se acredite su pago en el centro médico.");
                                        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });

                                        AlertDialog alert = builder.create();
                                        alert.show();

                                    } else {

                                        Intent i = new Intent(pedir_turno.this, pedir_turnos_fecha.class);
                                        i.putExtra("idUsr", idUsr);
                                        i.putExtra("idPaciente", idPaciente);
                                        i.putExtra("matricula", matricula);
                                        i.putExtra("nombre", nombre);
                                        i.putExtra("idEspecialidad", idEsp); //ID ESPECIALIDAD SELECCIONADA
                                        i.putExtra("nombreEsp", nombreEsp);
                                        i.putExtra("matriculaSeleccionado", matriculaSeleccionado); //MEDICO SELECCIONADO, O NULL SI NO SELECCIONÓ (IF)
                                        i.putExtra("nombreMedico", nombreMedico);
                                        startActivity(i);

                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Paciente> call, Throwable t) {
                        }
                    });
                }
            }
        });
    }

    private void vincular(){
        btnbuscar = (Button) findViewById(R.id.btnbuscar);
        btncancelar = (Button) findViewById(R.id.btncancelar);
        spmedicos = (Spinner) findViewById(R.id.spmedicos);
        spespecialidades = (Spinner) findViewById(R.id.spespecialidades);
        txtseleccionarMedico = (TextView) findViewById(R.id.txtseleccionarMedico);
        txtseleccionarEspecialidad =  (TextView) findViewById(R.id.txtseleccionarEspecialidad);
        builder = new AlertDialog.Builder(this);
        builderCerrar = new AlertDialog.Builder(this);

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
