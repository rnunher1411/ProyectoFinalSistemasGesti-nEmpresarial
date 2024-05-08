package com.example.proyecto_sistema_gestion_empresarial.Interfaces;

import com.example.proyecto_sistema_gestion_empresarial.Proyecto;
import com.example.proyecto_sistema_gestion_empresarial.ProyectoAdapter;
import com.example.proyecto_sistema_gestion_empresarial.Respuesta;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsarProyecto {

    @GET("/proyectos")
    Call<Respuesta> UsarProyecto();

}
