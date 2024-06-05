package com.example.proyecto_sistema_gestion_empresarial;

public class ParticipaGasto {

    int id_gasto;
    int id_usuario;
    float importe;
    String nombre_usuario;

    public ParticipaGasto(int id_gasto, int id_usuario, float importe, String nombre_usuario) {
        this.id_gasto = id_gasto;
        this.id_usuario = id_usuario;
        this.importe = importe;
        this.nombre_usuario = nombre_usuario;
    }

    public ParticipaGasto() {
    }

    public int getId_gasto() {
        return id_gasto;
    }

    public void setId_gasto(int id_gasto) {
        this.id_gasto = id_gasto;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
}
