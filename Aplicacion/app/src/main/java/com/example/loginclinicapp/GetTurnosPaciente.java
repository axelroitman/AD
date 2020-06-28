package com.example.loginclinicapp;

import com.example.loginclinicapp.modelos.Turno;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface GetTurnosPaciente {

    String API_ROUTE = "/apirest/getTurnosPaciente";
    @GET(API_ROUTE)
    Call<Turno> getTurnoPaciente(
            @Field("idPaciente") int idPaciente
    );

}
