package com.example.aplicacionesdistribuidas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SolicitarTurno extends AppCompatActivity {

    Spinner especialidades, medicos;
    TextView selMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_turno);
        especialidades = (Spinner) findViewById(R.id.especialidades);
        selMed = (TextView) findViewById(R.id.selMed);
        medicos = (Spinner) findViewById(R.id.medicos);
        selMed.setVisibility(View.GONE);
        medicos.setVisibility(View.GONE);
        ArrayList<String> med = new ArrayList<String>();

        ArrayList<String> esp = new ArrayList<String>();
        esp.add("Traumatología");
        esp.add("Cardiología");
        esp.add("Nefrología");
        esp.add("Oftalmología");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,esp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        especialidades.setAdapter(adapter);

        especialidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(especialidades.getSelectedItemPosition()!=0) {
                    selMed.setVisibility(View.VISIBLE);
                    medicos.setVisibility(View.VISIBLE);
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
        medicos.setAdapter(aa);



    }
}
