package com.example.loginclinicapp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AniadirTurnosService {

    String API_ROUTE = "/apirest/agregarTurnos";
    @PUT(API_ROUTE)
    Call<Void> aniadirTurnos(
            @Query("idEspecialidad") int idEspecialidad,
            @Query("matricula") String matricula,
            @Query("duracion") int duracion,
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