package com.example.loginclinicapp;

class InfoMedico {
    private String horaPrimerTurnoHoy;
    private String horaPrimerTurnoMan;
    private String horaUltimoTurnoHoy;
    private String horaUltimoTurnoMan;
    private int cantTurnosHoy;
    private int cantTurnosMan;

    public InfoMedico(String horaPrimerTurnoMan, String horaUltimoTurnoHoy, int cantTurnosMan, String horaPrimerTurnoHoy, String horaUltimoTurnoMan, int cantTurnosHoy) {
        this.horaPrimerTurnoMan = horaPrimerTurnoMan;
        this.horaPrimerTurnoHoy = horaPrimerTurnoHoy;
        this.horaUltimoTurnoHoy = horaUltimoTurnoHoy;
        this.horaUltimoTurnoMan = horaUltimoTurnoMan;
        this.cantTurnosHoy = cantTurnosHoy;
        this.cantTurnosMan = cantTurnosMan;


    }
    public String getHoraPrimerTurnoMan() {return horaPrimerTurnoMan;}
    public String getHoraPrimerTurnoHoy() {return horaPrimerTurnoHoy;}
    public String getHoraUltimoTurnoHoy() {return horaUltimoTurnoHoy;}
    public String getHoraUltimoTurnoMan() {return horaUltimoTurnoMan;}
    public int getCantTurnosHoy() {return cantTurnosHoy;}
    public int getCantTurnosMan() {return cantTurnosMan;}



}
