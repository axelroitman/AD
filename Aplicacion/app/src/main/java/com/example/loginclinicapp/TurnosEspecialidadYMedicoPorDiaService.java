package com.example.loginclinicapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TurnosEspecialidadYMedicoPorDiaService {

    String API_ROUTE = "/apirest/getTurnosEspecialidadYMedicoPorDia";
    @GET(API_ROUTE)
    Call<List<Turno>> getTurnosEspecialidadYMedicoPorDiaService(
            @Query("idEspecialidad") int idEspecialidad,
            @Query("fecha") String fecha,
            @Query("matricula") String matricula
    );
}
