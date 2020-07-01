package com.example.loginclinicapp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedicoService {

    String API_ROUTE = "/apirest/getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidad";
    @GET(API_ROUTE)
    Call<Map<Date,Integer>> getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedico(
            @Query("idEspecialidad") int idEspecialidad,
            @Query("matricula") String matricula
    );

}
