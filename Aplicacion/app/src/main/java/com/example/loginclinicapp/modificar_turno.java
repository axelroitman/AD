package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class modificar_turno extends AppCompatActivity {

    ImageView imgEspecialidad, imgEstado;
    TextView estado, especialidad, precio;
    TextView hora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_turno);
    }
}
