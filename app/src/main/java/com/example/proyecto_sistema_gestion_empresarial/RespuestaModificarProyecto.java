package com.example.proyecto_sistema_gestion_empresarial;

import com.example.proyecto_sistema_gestion_empresarial.Proyecto;

public class RespuestaModificarProyecto {

    Proyecto data;

    public RespuestaModificarProyecto(Proyecto data) {
        this.data = data;
    }

    public Proyecto getData() {
        return data;
    }

    public void setData(Proyecto data) {
        this.data = data;
    }

}
