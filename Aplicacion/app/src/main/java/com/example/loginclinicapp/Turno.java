package com.example.loginclinicapp;

import com.example.loginclinicapp.Especialidad;
import com.example.loginclinicapp.Medico;
import com.example.loginclinicapp.Paciente;

import java.util.Date;

public class Turno {

    private int id ;
    private String fecha;
    private float precio;
    //private Asistencia asistencia;
    private String justifInasistencia;
    //private Disponibilidad disponibilidad;
    private Especialidad especialidad;
    private Paciente paciente;
    private Medico medico;

    public Turno(int id, Especialidad especialidad, Paciente paciente, Medico medico) {
        this.id = id;
        this.paciente = paciente;
        this.especialidad = especialidad;
        this.medico = medico;
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
}
