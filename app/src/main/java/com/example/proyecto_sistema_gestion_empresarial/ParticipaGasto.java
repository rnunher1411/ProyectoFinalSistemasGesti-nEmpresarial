package com.example.proyecto_sistema_gestion_empresarial;

public class ParticipaGasto {

    int id_gasto;
    int id_usuario;
    float importe;

    public ParticipaGasto(int id_gasto, int id_usuario, float importe) {
        this.id_gasto = id_gasto;
        this.id_usuario = id_usuario;
        this.importe = importe;
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

}
