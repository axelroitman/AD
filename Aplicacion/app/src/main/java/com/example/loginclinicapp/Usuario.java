package com.example.loginclinicapp;

public class Usuario {

    private int id;
    private String usr;
    private String pass;

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

    public Usuario(String usr, String pass) {
        this.usr = usr;
        this.pass = pass;
    }

}
