package com.example.loginclinicapp;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface EliminarTurnosService {
    String API_ROUTE = "/apirest/eliminarTurnos";
    @DELETE(API_ROUTE)
    Call<Void> eliminarTurnos(
            @Query("matricula") String matricula,
            @Query("horaInicial") String horaInicial,
            @Query("horaFinal") String horaFinal,
            @Query("fechaInicial") String fechaInicial,
            @Query("fechaFinal") String fechaFinal,
            @Query("lunes") boolean lunes,
            @Query("martes") boolean martes,
            @Query("miercoles") boolean miercoles,
            @Query("jueves") boolean jueves,
            @Query("viernes") boolean viernes,
            @Query("sabado") boolean sabado,
            @Query("domingo") boolean domingo
    );
}
