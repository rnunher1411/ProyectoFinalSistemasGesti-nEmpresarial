package com.example.proyecto_sistema_gestion_empresarial;

import java.util.ArrayList;

public class Respuesta {

    ArrayList<Proyecto> data;

    public Respuesta(ArrayList<Proyecto> data) {
        this.data = data;
    }

    public ArrayList<Proyecto> getData() {
        return data;
    }

    public void setData(ArrayList<Proyecto> data) {
        this.data = data;
    }
}
