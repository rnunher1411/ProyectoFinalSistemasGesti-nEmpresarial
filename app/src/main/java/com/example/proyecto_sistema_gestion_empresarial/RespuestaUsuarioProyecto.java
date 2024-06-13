package com.example.proyecto_sistema_gestion_empresarial;

import com.example.proyecto_sistema_gestion_empresarial.ParticipaGasto;

import java.util.ArrayList;
import java.util.List;

public class RespuestaUsuarioProyecto {

    List<ParticipaUsarioProyecto> data;

    public RespuestaUsuarioProyecto(List<ParticipaUsarioProyecto> data) {
        this.data = data;
    }

    public List<ParticipaUsarioProyecto> getData() {
        return data;
    }

    public void setData(List<ParticipaUsarioProyecto> data) {
        this.data = data;
    }

}
