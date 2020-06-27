package com.example.loginclinicapp;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PacientePorIdUsuarioService {

    String API_ROUTE = "/apirest/getPacientePorIdUsuario";
    @GET(API_ROUTE)
    Call<Paciente> getPacientePorIdUsuario(
            @Query("idUsuario") int idUsuario
    );
}
