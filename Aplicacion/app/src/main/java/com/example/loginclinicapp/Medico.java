package com.example.loginclinicapp;

import java.util.Collection;

public class Medico {
    private String matricula;
    private Collection<Especialidad> especialidades;
    private int idUsr;
    private String nombre;

    public Medico(String matricula, Collection<Especialidad> especialidades) {
        this.matricula = matricula;
        this.especialidades = especialidades;

    }

    public String getMatricula() {
        return matricula;
    }

    public String getNombre(){ return nombre; }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Collection<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Collection<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }
}
