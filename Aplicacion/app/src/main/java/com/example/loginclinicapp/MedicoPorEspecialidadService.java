package com.example.loginclinicapp;

import java.util.Collection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MedicoPorEspecialidadService {

    String API_ROUTE = "/apirest/getMedicosPorEspecialidad";
    @GET(API_ROUTE)
    Call<Collection<Medico>> getMedicosPorEspecialidad(
            @Query("idEspecialidad") int idEspecialidad
    );

}
