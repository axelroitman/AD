package com.example.loginclinicapp;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadService {

    String API_ROUTE = "/apirest/getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidad";
    @GET(API_ROUTE)
    Call<TreeMap<Date,Integer>> getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidad(
            @Query("idEspecialidad") int idEspecialidad,
            @Query("matriculaMedPac") String matriculaMedPac
    );

}
