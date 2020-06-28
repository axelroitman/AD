package com.example.loginclinicapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProximoTurnoService {

    String API_ROUTE = "/apirest/getProximoTurnoPaciente";
    @GET(API_ROUTE)
    Call<ProximoTurno> getProximoTurno(
            @Query("idPaciente") int idPaciente
    );
}
