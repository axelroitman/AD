package com.example.loginclinicapp;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetTurnosPaciente {

    String API_ROUTE = "/apirest/getTurnosPaciente";
    @GET(API_ROUTE)
    Call<List<Turno>> getTurnosPaciente(
        @Query("idPaciente") int idPaciente,
        @Query("proximos") boolean proximos
    );

}
