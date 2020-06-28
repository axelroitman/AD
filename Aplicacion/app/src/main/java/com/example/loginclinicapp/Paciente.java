package com.example.loginclinicapp;

import java.util.Date;

public class Paciente {

    private int idPaciente;
    private Date fechaVtoCuota;
    private int idUsr;

    public Paciente(int idPaciente, Date fechaVtoCuota, int idUsr) {
        this.idPaciente = idPaciente;
        this.fechaVtoCuota = fechaVtoCuota;
        this.idUsr = idUsr;
    }

    public int getIdPaciente() {return idPaciente;}
    public void setIdPaciente(int idPaciente) {this.idPaciente = idPaciente;}
    public Date getFechaVtoCuota() {return fechaVtoCuota;}
    public void setFechaVtoCuota(Date fechaVtoCuota) {this.fechaVtoCuota = fechaVtoCuota;}
    public int getidUsuario() {return idUsr;}
    public void setidUsuario(int idUsuario) {this.idUsr = idUsuario;}
}
