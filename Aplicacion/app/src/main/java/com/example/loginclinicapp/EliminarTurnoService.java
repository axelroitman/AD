package com.example.loginclinicapp;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Query;

public interface EliminarTurnoService {

    String API_ROUTE = "/apirest/eliminarTurno";
    @DELETE(API_ROUTE)
    Call<Void> eliminarTurno(
            @Query("id") int id
    );
}