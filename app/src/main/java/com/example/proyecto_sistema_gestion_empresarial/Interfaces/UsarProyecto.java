package com.example.proyecto_sistema_gestion_empresarial.Interfaces;

import com.example.proyecto_sistema_gestion_empresarial.GastoUsuarios;
import com.example.proyecto_sistema_gestion_empresarial.Respuesta;
import com.example.proyecto_sistema_gestion_empresarial.Respuesta2;
import com.example.proyecto_sistema_gestion_empresarial.Respuesta3;
import com.example.proyecto_sistema_gestion_empresarial.Respuesta4;
import com.example.proyecto_sistema_gestion_empresarial.RespuestaUsarPagadorId;
import com.example.proyecto_sistema_gestion_empresarial.RespuestaUsarProyectoId;
import com.example.proyecto_sistema_gestion_empresarial.RespuestaCrearGasto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsarProyecto {

    @GET("/proyectos")
    Call<Respuesta> UsarProyecto();

    @GET("/proyectos/{proyecto_id}")
    Call<RespuestaUsarProyectoId> UsarProyectoId(@Path("proyecto_id") Integer idproyecto);

    @DELETE("/proyectos/{proyecto_id}")
    Call<RespuestaUsarProyectoId> BorrarProyecto(@Path("proyecto_id") Integer idproyecto);

    @GET("/gastosList/{idproyecto}")
    Call<Respuesta2> UsarGasto(@Path("idproyecto") Integer idproyecto);

    @GET("/usuarios")
    Call<Respuesta3> UsarUsuario();

    @GET("/usuarios/{usuario_id}")
    Call<RespuestaUsarPagadorId> UsarUsuarioId(@Path("usuario_id") Integer idusuarios);

    @POST("/gastosList/{idproyecto}")
    Call<RespuestaCrearGasto> CrearGasto(@Path("idproyecto") Integer idproyecto, @Body GastoUsuarios nuevoGasto);

    @GET("/gastos/{idgasto}")
    Call<Respuesta2> LeerGasto(@Path("idgasto") Integer idgasto);

    @DELETE("/gastos/{idgasto}")
    Call<Respuesta2> BorrarGasto(@Path("idgasto") Integer idgasto);

    @GET("/participaGasto/{idgasto}")
    Call<Respuesta4> LeerParticipaGasto(@Path("idgasto") Integer idgasto);

}
