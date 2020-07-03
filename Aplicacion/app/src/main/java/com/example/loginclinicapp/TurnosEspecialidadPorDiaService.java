package com.example.loginclinicapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TurnosEspecialidadPorDiaService {

    String API_ROUTE = "/apirest/getTurnosEspecialidadPorDia";
    @GET(API_ROUTE)
    Call<List<Turno>> getTurnosEspecialidadPorDiaService(
            @Query("idEspecialidad") int idEspecialidad,
            @Query("fecha") String fecha
    );
}
