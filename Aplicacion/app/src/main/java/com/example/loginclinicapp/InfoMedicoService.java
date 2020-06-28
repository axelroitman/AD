package com.example.loginclinicapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InfoMedicoService {

    String API_ROUTE = "/apirest/getInfoInicioMedico";
    @GET(API_ROUTE)
    Call<InfoMedico> getInfoInicioMedico(
            @Query("matricula") String matricula
    );
}
