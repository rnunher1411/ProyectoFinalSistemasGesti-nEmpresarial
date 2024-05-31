package com.example.proyecto_sistema_gestion_empresarial;

public class Proyecto {

    int id;
    String nombre;
    int id_admin;


    public Proyecto(int id, String nombre, int id_admin) {
        this.id = id;
        this.nombre = nombre;
        this.id_admin = id_admin;
    }

    public Proyecto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }
}
