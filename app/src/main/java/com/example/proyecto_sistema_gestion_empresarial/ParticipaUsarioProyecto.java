package com.example.proyecto_sistema_gestion_empresarial;

public class ParticipaUsarioProyecto {

    Integer id_proyecto;
    Integer id_usuario;

    String nombre_usuario;

    public ParticipaUsarioProyecto(Integer id_proyecto, Integer id_usuario, String nombre_usuario) {
        this.id_proyecto = id_proyecto;
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
    }

    public ParticipaUsarioProyecto() {
    }

    public Integer getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(Integer id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
}
