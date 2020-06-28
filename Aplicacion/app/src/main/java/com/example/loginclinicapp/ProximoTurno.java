package com.example.loginclinicapp;

import java.util.Date;

public class ProximoTurno {

    private Date fecha;
    private Especialidad especialidad;
    private Medico medico;

    public ProximoTurno(Date fecha, Especialidad especialidad, Medico medico) {
        this.fecha = fecha;
        this.especialidad = especialidad;
        this.medico = medico;
    }

    public Date getFecha() {return fecha;}
    public void setFecha(Date fecha) {this.fecha = fecha;}
    public Especialidad getEspecialidad() {return especialidad;}
    public void setEspecialidad(Especialidad especialidad) {this.especialidad = especialidad;}
    public Medico getMedico() {return medico;}
    public void setMedico(Medico medico) {this.medico = medico;}

}
