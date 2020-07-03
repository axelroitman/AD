package com.example.loginclinicapp;

import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface CambiarEstadoDeTurnoService {

    String API_ROUTE = "/apirest/cambiarEstadoDeTurno";
    @PUT(API_ROUTE)
    Call<Void> cambiarEstadoDeTurno(
            @Query("idTurno") int idTurno,
            @Query("idPaciente") Integer idPaciente,
            @Query("asistencia") Integer asistencia,
            @Query("disponibilidad") Integer disponibilidad,
            @Query("justificacionInasistencia") String justificacionInasistencia

    );
}