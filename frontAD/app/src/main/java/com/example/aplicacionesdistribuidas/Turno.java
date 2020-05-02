package com.example.aplicacionesdistribuidas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

public class Turno extends AppCompatActivity {

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turno);


        builder = new AlertDialog.Builder(this);

        builder.setTitle("Atención");
        builder.setMessage("El turno ha sido eliminado.");
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.show();
    }
}
