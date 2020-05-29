package com.example.loginclinicapp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {

    String API_ROUTE = "/apirest/login";
    @FormUrlEncoded
    @POST(API_ROUTE)
    Call<Usuario> getUsuario(
            @Field("usuario") String usr,
            @Field("password") String pass
    );
}
