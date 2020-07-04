package com.example.loginclinicapp;

import com.example.loginclinicapp.Especialidad;
import com.example.loginclinicapp.Medico;
import com.example.loginclinicapp.Paciente;

import java.util.Date;

public class Turno {

    private int id ;
    private String fecha;
    private float precio;
    private String asistencia;
    private String justifInasistencia;
    private String disponibilidad;
    private Especialidad especialidad;
    private Paciente paciente;
    private Medico medico;


    //Para eliminarTurno:
    private boolean seleccionado;

    public Turno(int id, Especialidad especialidad, Paciente paciente, Medico medico) {
        this.id = id;
        this.paciente = paciente;
        this.especialidad = especialidad;
        this.medico = medico;
        seleccionado = false;
    }

     public int getId() { return id; }
     public String getFecha() { return fecha; }
     public float getPrecio() { return precio; }
     public String getJustifInasistencia() { return justifInasistencia; }
     public void setFecha(String fecha) { this.fecha = fecha; }
     public void setPrecio(float precio) { this.precio = precio; }
     public void setJustifInasistencia(String justifInasistencia) { this.justifInasistencia = justifInasistencia; }
    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente idPaciente) { this.paciente = paciente; }
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public String getAsistencia() { return asistencia; }
    public void setAsistencia(String asistencia) { this.asistencia = asistencia; }
    public String getDisponibilidad() { return disponibilidad; }
    public void setDisponibilidad(String disponibilidad) { this.disponibilidad = disponibilidad; }


    public void setSeleccionado(boolean seleccionado){
        this.seleccionado = seleccionado;
    }
    public boolean isSeleccionado(){
        return this.seleccionado;
    }

}
