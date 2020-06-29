package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pedir_turno extends AppCompatActivity {

    Button btnbuscar, btncancelar;
    Spinner spmedicos, spespecialidades;
    TextView txtseleccionarEspecialidad, txtseleccionarMedico;
    Map<String, Integer> espNombreyId = new HashMap<String, Integer>();
    Map<String, String> medNombreyMatricula = new HashMap<String, String>();
    ArrayList<String> espNombres = new ArrayList<String>();
    ArrayList<String> medNombres = new ArrayList<String>();
    int idEsp;
    String matriculaSeleccionado;


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

        Call<Collection<Especialidad>> especialidades = RetrofitClient.getInstance().getEspecialidadesService().getEspecialidades();
        especialidades.enqueue(new Callback<Collection<Especialidad>>() {
            @Override
            public void onResponse(Call<Collection<Especialidad>> call, Response<Collection<Especialidad>> response) {
                if(response.code() == 200){
                    if(response.body() != null){
                        for (Especialidad e : response.body()){
                            espNombreyId.put(e.getNombre(),e.getIdEspecialidad());
                        }
                        espNombres.addAll(espNombreyId.keySet());
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(pedir_turno.this,android.R.layout.simple_spinner_item, espNombres);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spespecialidades.setAdapter(adapter);

                        spespecialidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(spespecialidades.getSelectedItemPosition() != 0) {
                                    txtseleccionarMedico.setVisibility(View.VISIBLE);
                                    spmedicos.setVisibility(View.VISIBLE);
                                    idEsp = espNombreyId.get(spespecialidades.getSelectedItem().toString());
                                    medNombreyMatricula.clear();
                                    spmedicos.clear


                                    Call<Collection<Medico>> medicos = RetrofitClient.getInstance().getMedicoPorEspecialidadService().getMedicosPorEspecialidad(idEsp);
                                    medicos.enqueue(new Callback<Collection<Medico>>() {
                                        @Override
                                        public void onResponse(Call<Collection<Medico>> call, Response<Collection<Medico>> response) {
                                            if(response.code() == 200){
                                                if(response.body() != null){
                                                    for (Medico m : response.body()){
                                                        if(matricula != null){
                                                            if(! matricula.equals(m.getMatricula())){
                                                                medNombreyMatricula.put(m.getNombre(),m.getMatricula());
                                                            }
                                                        }
                                                    }
                                                    medNombres.addAll(medNombreyMatricula.keySet());
                                                    ArrayAdapter<String> adapterMedicos = new ArrayAdapter<String>(pedir_turno.this,android.R.layout.simple_spinner_item,medNombres);
                                                    adapterMedicos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                    spmedicos.setAdapter(adapterMedicos);
                                                    spmedicos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                            matriculaSeleccionado = medNombreyMatricula.get(spmedicos.getSelectedItem().toString());
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







        //Harcodeo solo el caso en que selecciono "Nefrología"

        /*ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,med);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spmedicos.setAdapter(aa);*/

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(pedir_turno.this, pedir_turnos_fecha.class);
                i.putExtra("idUsr", idUsr);
                i.putExtra("idPaciente",idPaciente);
                i.putExtra("matricula",  matricula);
                i.putExtra("nombre",nombre);
                i.putExtra("idEspecialidad", idEsp); //ID ESPECIALIDAD SELECCIONADA
                i.putExtra("matriculaSeleccionado", matriculaSeleccionado); //MEDICO SELECCIONADO, O NULL SI NO SELECCIONÓ (IF)

                startActivity(i);

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
    }
}
