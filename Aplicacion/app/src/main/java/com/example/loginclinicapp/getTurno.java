package com.example.loginclinicapp;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface getTurno {

    String API_ROUTE = "/apirest/getTurno";
    @GET(API_ROUTE)
    Call<Turno> getTurno(
            @Query("id") int idTurno
    );

}
