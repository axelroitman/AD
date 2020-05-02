package com.example.aplicacionesdistribuidas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class historial_turnos_medico extends AppCompatActivity {

    Button btnfecha;
    EditText txtfecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_turnos_medico);

        vincular();

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
            }
        });
    }

    private void vincular(){
        btnfecha = (Button) findViewById(R.id.btnfecha);
        txtfecha = (EditText) findViewById(R.id.txtfecha);
    }
}
