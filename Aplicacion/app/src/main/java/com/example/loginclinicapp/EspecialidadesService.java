package com.example.loginclinicapp;

import java.util.Collection;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EspecialidadesService {

    String API_ROUTE = "/apirest/getEspecialidades";
    @GET(API_ROUTE)
    Call<Collection<Especialidad>> getEspecialidades();

}
