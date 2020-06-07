package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class pedir_turno extends AppCompatActivity {

    Button btnbuscar, btncancelar;
    Spinner spmedicos, spespecialidades;
    TextView txtseleccionarEspecialidad, txtseleccionarMedico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_turno);

        vincular();

        txtseleccionarMedico.setVisibility(View.GONE);
        spmedicos.setVisibility(View.GONE);

        ArrayList<String> med = new ArrayList<String>();

        ArrayList<String> esp = new ArrayList<String>();
        esp.add("Traumatología");
        esp.add("Cardiología");
        esp.add("Nefrología");
        esp.add("Oftalmología");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,esp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spespecialidades.setAdapter(adapter);

        spespecialidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spespecialidades.getSelectedItemPosition()!=0) {
                    txtseleccionarMedico.setVisibility(View.VISIBLE);
                    spmedicos.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Harcodeo solo el caso en que selecciono "Nefrología"
        med.add("Vanesa Teller");
        med.add("Mariana Panza");
        med.add("David Fernández");
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,med);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spmedicos.setAdapter(aa);

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // no se a que pantalla se dirije
                /* Intent i = new Intent(Login.this, inicio_paciente.class);
                    startActivity(i);
                */
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
