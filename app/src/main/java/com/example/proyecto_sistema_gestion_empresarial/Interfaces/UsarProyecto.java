package com.example.proyecto_sistema_gestion_empresarial.Interfaces;

import com.example.proyecto_sistema_gestion_empresarial.Gasto;
import com.example.proyecto_sistema_gestion_empresarial.GastoUsuarios;
import com.example.proyecto_sistema_gestion_empresarial.Respuesta;
import com.example.proyecto_sistema_gestion_empresarial.Respuesta2;
import com.example.proyecto_sistema_gestion_empresarial.Respuesta3;
import com.example.proyecto_sistema_gestion_empresarial.RespuestaCrearGasto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsarProyecto {

    @GET("/proyectos")
    Call<Respuesta> UsarProyecto();

    @GET("/gastosList/{idproyecto}")
    Call<Respuesta2> UsarGasto(@Path("idproyecto") Integer idproyecto);

    @GET("/usuarios")
    Call<Respuesta3> UsarUsuario();

    @POST("/gastosList/{idproyecto}")
    Call<RespuestaCrearGasto> CrearGasto(@Path("idproyecto") Integer idproyecto, @Body GastoUsuarios nuevoGasto);


}
