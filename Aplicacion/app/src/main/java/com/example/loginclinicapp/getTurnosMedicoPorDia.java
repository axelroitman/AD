package com.example.loginclinicapp;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface getTurnosMedicoPorDia {

    String API_ROUTE = "/apirest/getTurnosMedicoPorDia";
    @GET(API_ROUTE)
    Call<List<Turno>> getTurnosMedicoPorDia(
            @Query("idMedico") String idMedico,
            @Query("fecha") String fecha
    );

}
