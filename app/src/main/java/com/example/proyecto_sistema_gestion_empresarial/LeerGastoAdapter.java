package com.example.proyecto_sistema_gestion_empresarial;

import static java.lang.Float.parseFloat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LeerGastoAdapter extends ArrayAdapter<Gasto> {

    private List<Gasto> gasto;
    public LeerGastoAdapter(Context context, List<Gasto> list) {
        super(context, 0, list);
        this.gasto = list;
    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        return initView(position, convertView, parent);

    };

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return initView(position, convertView, parent);

    }

    private View initView(int position, View convertView, ViewGroup parent) {

        final View vistaPersonal = LayoutInflater.from(getContext()).inflate(R.layout.gasto_completo, parent, false);

        TextView textViewId = vistaPersonal.findViewById(R.id.id);
        TextView textViewName = vistaPersonal.findViewById(R.id.concepto);
        TextView textViewImporte = vistaPersonal.findViewById(R.id.importe);
        TextView textViewIdProyecto = vistaPersonal.findViewById(R.id.idProyecto);
        TextView textViewIdPagador = vistaPersonal.findViewById(R.id.idPagador);
        Gasto currentItem = gasto.get(position);

        if (currentItem != null) {

            int id = currentItem.getId_proyecto();
            String idTexto = String.valueOf(id);
            float importe = currentItem.getImporte();
            String importeTexto = String.valueOf(importe);
            int idProyecto = currentItem.getId_proyecto();
            String idProyectoTexto = String.valueOf(idProyecto);
            int idPagador = currentItem.getId_pagador();
            String idPagadorTexto = String.valueOf(idPagador);

            textViewId.setText(idTexto);
            textViewName.setText(currentItem.getConcepto());
            textViewImporte.setText(importeTexto);
            textViewIdProyecto.setText(idProyectoTexto);
            textViewIdPagador.setText(idPagadorTexto);

        }

        return vistaPersonal;

    }
}