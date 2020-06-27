package com.example.loginclinicapp;

import java.util.Collection;
import java.util.Date;

public class Usuario {

    private int id;
    private String usr;
    private String pass;
    private String nombre;
    private String telefono;
    private String dni;
    private Date fechaNac;

    //Datos Paciente
    private int idPaciente;
    private Date fechaVtoCuota;

    //Datos Medico
    private String matricula;
    private Collection<Especialidad> especialidades;

    public int getIdUsr() {
        return id;
    }
    public String getUsr() {
        return usr;
    }
    public void setUsr(String usr) {
        this.usr = usr;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getIdPaciente() {return idPaciente;}
    public void setIdPaciente(int idPaciente) {this.idPaciente = idPaciente;}
    public Date getFechaVtoCuota() {return fechaVtoCuota;}
    public void setFechaVtoCuota(Date fechaVtoCuota) {this.fechaVtoCuota = fechaVtoCuota;}
    public String getMatricula() {return matricula;}
    public void setMatricula(String matricula) {this.matricula = matricula;}
    public Collection<Especialidad> getEspecialidades() {return especialidades;}
    public void setEspecialidades(Collection<Especialidad> especialidades) {this.especialidades = especialidades;}

    public Usuario(String usr, String pass) {
        this.usr = usr;
        this.pass = pass;
    }

}
