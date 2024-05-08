package com.example.proyecto_sistema_gestion_empresarial;

public class Gasto {

    int id;
    String concepto;
    float importe;
    int id_proyecto;
    int id_pagador;

    public Gasto(int id, String concepto, float importe, int id_proyecto, int id_pagador) {
        this.id = id;
        this.concepto = concepto;
        this.importe = importe;
        this.id_proyecto = id_proyecto;
        this.id_pagador = id_pagador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
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
}
