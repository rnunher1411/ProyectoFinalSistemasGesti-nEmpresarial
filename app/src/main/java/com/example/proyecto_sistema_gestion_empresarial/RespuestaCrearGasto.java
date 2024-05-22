package com.example.proyecto_sistema_gestion_empresarial;

import java.util.ArrayList;
import java.util.List;

public class RespuestaCrearGasto {

    ArrayList<GastoUsuarios> data;

    public RespuestaCrearGasto(ArrayList<GastoUsuarios> data) {
        this.data = data;
    }

    public ArrayList<GastoUsuarios> getData() {
        return data;
    }

    public void setData(ArrayList<GastoUsuarios> data) {
        this.data = data;
    }


}
