package com.example.loginclinicapp;

import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface AgregarAListaDeEspera {

    String API_ROUTE = "/apirest/agregarAListaDeEspera";
    @PUT(API_ROUTE)
    Call<Void> agregarAListaDeEspera(
            @Query("idPaciente") int idPaciente,
            @Query("idEspecialidad") int idEspecialidad,
            @Query("matricula") String matricula
    );
}