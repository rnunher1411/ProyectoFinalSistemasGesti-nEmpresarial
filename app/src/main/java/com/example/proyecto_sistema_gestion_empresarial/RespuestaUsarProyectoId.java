package com.example.proyecto_sistema_gestion_empresarial;

import java.util.ArrayList;

public class RespuestaUsarProyectoId {

    ArrayList<Proyecto> data;

    public RespuestaUsarProyectoId(ArrayList<Proyecto> data) {
        this.data = data;
    }

    public ArrayList<Proyecto> getData() {
        return data;
    }

    public void setData(ArrayList<Proyecto> data) {
        this.data = data;
    }

}
