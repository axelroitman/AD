package com.example.loginclinicapp;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MedicoPorIdUsuarioService {

    String API_ROUTE = "/apirest/getMedicoPorIdUsuario";
    @GET(API_ROUTE)
    Call<Medico> getMedicoPorIdUsuario(
            @Query("idUsuario") int idUsuario
    );
}
