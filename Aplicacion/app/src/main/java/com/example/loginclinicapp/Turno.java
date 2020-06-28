package com.example.loginclinicapp.modelos;

import com.example.loginclinicapp.Especialidad;
import com.example.loginclinicapp.Medico;
import com.example.loginclinicapp.Paciente;

public class Turno {

    private int id ;
    //private Date fecha;
    //private float precio;
    //private Asistencia asistencia;
    //private String justifInasistencia;
    //private Disponibilidad disponibilidad;
    //private Especialidad idEspecialidad;
    //private Paciente idPaciente;
    //private Medico idMedico;

    public Turno(int id) {
        this.id = id;
        //this.fecha = fecha;
        //this.precio = precio;
        //this.justifInasistencia = justifInasistencia;
    }

    public int getId() { return id; }

//    public Date getFecha() { return fecha; }

    //public float getPrecio() { return precio; }

    //public String getJustifInasistencia() { return justifInasistencia; }

    // public void setFecha(Date fecha) { this.fecha = fecha; }

    //   public void setPrecio(float precio) { this.precio = precio; }

    //public void setJustifInasistencia(String justifInasistencia) { this.justifInasistencia = justifInasistencia; }
}
