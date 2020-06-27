package com.example.loginclinicapp;

import java.util.Date;

public class Paciente {

    private int id;
    private Date fechaVtoCuota;
    private int idUsr;

    public Paciente(int id, Date fechaVtoCuota, int idUsr) {
        this.id = id;
        this.fechaVtoCuota = fechaVtoCuota;
        this.idUsr = idUsr;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public Date getFechaVtoCuota() {return fechaVtoCuota;}
    public void setFechaVtoCuota(Date fechaVtoCuota) {this.fechaVtoCuota = fechaVtoCuota;}
    public int getidUsuario() {return idUsr;}
    public void setidUsuario(int idUsuario) {this.idUsr = idUsuario;}
}
