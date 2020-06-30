package com.example.loginclinicapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ver_agenda extends AppCompatActivity {

    Button btnfecha;
    EditText txtfecha;
    RelativeLayout mensajeNotieneTurnos;
    RecyclerView recyclerViewItem;
    GroupAdp adaptador_items;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_agenda);

        vincular();
        Intent i = getIntent();
        final int idUsr = i.getIntExtra("idUsr",0);
        final int idPaciente = i.getIntExtra("idPaciente", 0);
        final String matricula = i.getStringExtra("matricula");
        final String nombre = i.getStringExtra("nombre");

        java.util.Date fechaHoy = Calendar.getInstance().getTime();
        try {
            metodosSobreSeleccionarFecha(matricula);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        traerProximosTurnosMedico(matricula, fechaHoy);


    }

    private void vincular(){
        btnfecha = (Button) findViewById(R.id.btnfecha);
        txtfecha = (EditText) findViewById(R.id.txtfecha);
        mensajeNotieneTurnos = (RelativeLayout) findViewById(R.id.mensajeNotieneTurnos);
        recyclerViewItem = (RecyclerView) findViewById(R.id.recyclerViewItem);
    }

    private void metodosSobreSeleccionarFecha(String matricula) throws ParseException {
        Log.d("agenda", "ENTRO AL METODO DE LA FECHA");
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("seleccionar una fecha");

        final MaterialDatePicker materialDatePicker = builder.build();

        btnfecha.setOnClickListener(new View.OnClickListener() {
            private Object tag;

            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), (String) (tag="DATE_PICKER"));
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                txtfecha.setText(materialDatePicker.getHeaderText());
                Log.d("agenda", " "+txtfecha);

            }
        });

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaElegida = new Date(formato.parse(String.valueOf(txtfecha)).getTime());

        traerProximosTurnosMedico( matricula, fechaElegida);
    }

    private void traerProximosTurnosMedico(String matricula, Date fechaHoy){
        Log.d("agenda", "ENTRO AL METODO");

        Log.d("agenda",""+matricula);
        Call<List<Turno>> call = RetrofitClient.getInstance().getTurnosMedicoPorDia().getTurnosMedicoPorDia(matricula, fechaHoy);

        Log.d("agenda", ""+call.request());
        call.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                Log.d("agenda", response.toString());
                if (response.code() == 200) {
                    Log.d("agenda", "ES 200");
                    if (!response.body().isEmpty()) {
                        Log.d("agenda", response.toString());
                        Log.d("agenda", "NO ESTOY VACIO");
                        recyclerViewItem.setVisibility(View.VISIBLE);
                        mensajeNotieneTurnos.setVisibility(View.GONE);

                        completarCards(response.body());
                    } else {
                        Log.d("agenda", "ESTOY VACIO");
                        mensajeNotieneTurnos.setVisibility(View.VISIBLE);
                        recyclerViewItem.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Log.d("agenda","falla :(");
                Toast.makeText(ver_agenda.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void completarCards(List<Turno> items){

        recyclerViewItem.setLayoutManager(new LinearLayoutManager(this));
        adaptador_items = new GroupAdp(this, items);
        recyclerViewItem.setAdapter(adaptador_items);

    }
}
