package com.example.loginclinicapp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;

public interface AniadirTurnosService {

    String API_ROUTE = "/apirest/agregarTurnos";
    @FormUrlEncoded
    @PUT(API_ROUTE)
    Call<Void> aniadirTurnos(
            @Field("idEspecialidad") int idEspecialidad,
            @Field("matricula") String matricula,
            @Field("duracion") int duracion,
            @Field("horaInicial") String horaInicial,
            @Field("horaFinal") String horaFinal,
            @Field("fechaInicial") String fechaInicial,
            @Field("fechaFinal") String fechaFinal,
            @Field("lunes") boolean lunes,
            @Field("martes") boolean martes,
            @Field("miercoles") boolean miercoles,
            @Field("jueves") boolean jueves,
            @Field("viernes") boolean viernes,
            @Field("sabado") boolean sabado,
            @Field("domingo") boolean domingo
    );
}