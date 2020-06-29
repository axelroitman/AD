package com.example.loginclinicapp;

import com.example.loginclinicapp.modelos.Turno;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String URL_API = "http://10.0.2.2:8080/apirest/";
    private static RetrofitClient instance;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if(instance == null){
            instance = new RetrofitClient();
        }
        return instance;
    }

    public LoginService getLoginService(){
        return retrofit.create(LoginService.class);
    }

    public PacientePorIdUsuarioService getPacientePorIdUsuarioService() {
        return retrofit.create(PacientePorIdUsuarioService.class);
    }
    public MedicoPorIdUsuarioService getMedicoPorIdUsuarioService() {
        return retrofit.create(MedicoPorIdUsuarioService.class);
    }

    public ProximoTurnoService getProximoTurnoPaciente() {
        return retrofit.create(ProximoTurnoService.class);
    }
    public InfoMedicoService getInfoMedico() {
        return retrofit.create(InfoMedicoService.class);
    }

    public GetTurnosPaciente getTurnoPaciente() {
        return retrofit.create(GetTurnosPaciente.class);
    }

}
