package com.example.proyecto_sistema_gestion_empresarial;

import java.util.ArrayList;

public class RespuestaModificarGasto {

    GastoUsuarios data;

    public RespuestaModificarGasto(GastoUsuarios data) {

        this.data = data;

    }

    public GastoUsuarios getData() {

        return data;

    }

    public void setData(GastoUsuarios data) {

        this.data = data;

    }

}
