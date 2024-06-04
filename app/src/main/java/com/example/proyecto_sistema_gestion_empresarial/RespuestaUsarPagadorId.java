package com.example.proyecto_sistema_gestion_empresarial;

import java.util.ArrayList;

public class RespuestaUsarPagadorId {

    ArrayList<Usuario> data;

    public RespuestaUsarPagadorId(ArrayList<Usuario> data) {
        this.data = data;
    }

    public ArrayList<Usuario> getData() {
        return data;
    }

    public void setData(ArrayList<Usuario> data) {
        this.data = data;
    }

}
