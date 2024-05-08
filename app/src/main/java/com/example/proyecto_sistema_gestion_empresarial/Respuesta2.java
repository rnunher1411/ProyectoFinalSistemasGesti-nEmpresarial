package com.example.proyecto_sistema_gestion_empresarial;

import java.util.ArrayList;

public class Respuesta2 {

    ArrayList<Gasto> data;

    public Respuesta2(ArrayList<Gasto> data) {
        this.data = data;
    }

    public ArrayList<Gasto> getData() {
        return data;
    }

    public void setData(ArrayList<Gasto> data) {
        this.data = data;
    }

}
