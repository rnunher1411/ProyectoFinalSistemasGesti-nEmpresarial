package com.example.proyecto_sistema_gestion_empresarial.Interfaces;

import com.example.proyecto_sistema_gestion_empresarial.Respuesta;
import com.example.proyecto_sistema_gestion_empresarial.Respuesta2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsarProyecto {

    @GET("/proyectos")
    Call<Respuesta> UsarProyecto();

    @GET("/gastos/{idproyecto}")
    Call<Respuesta2> UsarGasto(@Path("idproyecto") Integer idproyecto);

}
