package com.example.loginclinicapp;

import android.util.Log;

import java.time.LocalDateTime;
import java.util.Date;

public class ProximoTurno {

    private String fecha;
    private Especialidad especialidad;
    private Medico medico;

    public ProximoTurno(String fecha, Especialidad especialidad, Medico medico) {
        this.fecha = fecha;
        this.especialidad = especialidad;
        this.medico = medico;
    }

    public String getFecha() {return fecha;}
    public void setFecha(String fecha) {this.fecha = fecha;}
    public Especialidad getEspecialidad() {return especialidad;}
    public void setEspecialidad(Especialidad especialidad) {this.especialidad = especialidad;}
    public Medico getMedico() {return medico;}
    public void setMedico(Medico medico) {this.medico = medico;}

}
