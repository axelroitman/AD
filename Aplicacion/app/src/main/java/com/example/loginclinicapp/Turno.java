package com.example.loginclinicapp.modelos;

import com.example.loginclinicapp.Especialidad;
import com.example.loginclinicapp.Medico;
import com.example.loginclinicapp.Paciente;

import java.util.Date;

public class Turno {

    private int id ;
    private Date fecha;
    private float precio;
    //private Asistencia asistencia;
    private String justifInasistencia;
    //private Disponibilidad disponibilidad;
    private Especialidad idEspecialidad;
    private Paciente idPaciente;
    private Medico idMedico;

    public Turno(int id, Especialidad idEspecialidad, Paciente idPaciente, Medico idMedico) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idEspecialidad = idEspecialidad;
        this.idMedico = idMedico;
    }

     public int getId() { return id; }
     public Date getFecha() { return fecha; }
     public float getPrecio() { return precio; }
     public String getJustifInasistencia() { return justifInasistencia; }
     public void setFecha(Date fecha) { this.fecha = fecha; }
     public void setPrecio(float precio) { this.precio = precio; }
     public void setJustifInasistencia(String justifInasistencia) { this.justifInasistencia = justifInasistencia; }
    public Especialidad getIdEspecialidad() { return idEspecialidad; }
    public void setIdEspecialidad(Especialidad idEspecialidad) { this.idEspecialidad = idEspecialidad; }
    public Paciente getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Paciente idPaciente) { this.idPaciente = idPaciente; }
    public Medico getIdMedico() { return idMedico; }
    public void setIdMedico(Medico idMedico) { this.idMedico = idMedico; }
}
