package com.example.loginclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetalleTurno extends AppCompatActivity {

    TextView txtFechaTurno, txtAsistencia, txtCancela, txtMotivoCancela, txtPrecio, txtHorario, txtEspecialidad, txtProfesionalPaciente, txtEstado;
    ImageView imgAsistencia, imgEstado, imgProfesionalPaciente;
    RelativeLayout layoutConfirmarAsistencia, layoutMedicoTurnoSinConfirmar, layoutTurnoLibre, layoutPacienteCancelarTurno;
    Button btnCancelarTurnoPaciente, btnPedirTurno, btnModificarTurno, btnEliminarTurno, btnNoAsistire, btnAsistire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_turno);
        vincular();
    }

    private void vincular(){
        txtFechaTurno = (TextView) findViewById(R.id.txtFechaTurno);
        txtAsistencia = (TextView) findViewById(R.id.txtAsistencia);
        txtCancela = (TextView) findViewById(R.id.txtCancela);
        txtMotivoCancela = (TextView) findViewById(R.id.txtCancela);
        txtPrecio = (TextView) findViewById(R.id.txtPrecio);
        txtHorario = (TextView) findViewById(R.id.txtHorario);
        txtEspecialidad = (TextView) findViewById(R.id.txtEspecialidad);
        txtProfesionalPaciente = (TextView) findViewById(R.id.txtProfesionalPaciente);
        txtEstado = (TextView) findViewById(R.id.txtEstado);

        imgAsistencia = (ImageView) findViewById(R.id.imgAsistencia);
        imgEstado = (ImageView) findViewById(R.id.imgEstado);
        imgProfesionalPaciente = (ImageView) findViewById(R.id.imgProfesionalPaciente);

        layoutConfirmarAsistencia = (RelativeLayout) findViewById(R.id.layoutConfirmarAsistencia);
        layoutMedicoTurnoSinConfirmar = (RelativeLayout) findViewById(R.id.layoutMedicoTurnoSinConfirmar);
        layoutTurnoLibre = (RelativeLayout) findViewById(R.id.layoutTurnoLibre);
        layoutPacienteCancelarTurno = (RelativeLayout) findViewById(R.id.layoutPacienteCancelarTurno);

        btnCancelarTurnoPaciente = (Button) findViewById(R.id.btnCancelarTurnoPaciente);
        btnPedirTurno = (Button) findViewById(R.id.btnPedirTurno);
        btnModificarTurno = (Button) findViewById(R.id.btnModificarTurno);
        btnEliminarTurno = (Button) findViewById(R.id.btnEliminarTurno);
        btnNoAsistire = (Button) findViewById(R.id.btnNoAsistire);
        btnAsistire = (Button) findViewById(R.id.btnAsistire);
    }

}
