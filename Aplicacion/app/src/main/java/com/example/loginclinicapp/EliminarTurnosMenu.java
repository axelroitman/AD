package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EliminarTurnosMenu extends AppCompatActivity {
    Button btnEliminarDia,btnEliminarMasivo;
    RelativeLayout eliminarDeUnDia, eliminarMasivo;

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

}
