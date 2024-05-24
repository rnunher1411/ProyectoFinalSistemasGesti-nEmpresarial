package com.example.proyecto_sistema_gestion_empresarial;

import java.util.List;

public class GastoUsuarios {

    String concepto;
    Float importe;
    int id_proyecto;
    int id_pagador;
    List<Integer> usuarios;

    public GastoUsuarios(String concepto, Float importe, int id_proyecto, int id_pagador, List<Integer> usuarios) {
        this.concepto = concepto;
        this.importe = importe;
        this.id_proyecto = id_proyecto;
        this.id_pagador = id_pagador;
        this.usuarios = usuarios;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    public int getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(int id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public int getId_pagador() {
        return id_pagador;
    }

    public void setId_pagador(int id_pagador) {
        this.id_pagador = id_pagador;
    }

    public List<Integer> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Integer> usuarios) {
        this.usuarios = usuarios;
    }

}
