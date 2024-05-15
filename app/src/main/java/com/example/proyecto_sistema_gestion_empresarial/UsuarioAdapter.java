package com.example.proyecto_sistema_gestion_empresarial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    private List<Usuario> usuario;
    public UsuarioAdapter(Context context, List<Usuario> list) {
        super(context, 0, list);
        this.usuario = list;
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

        final View vistaPersonal = LayoutInflater.from(getContext()).inflate(R.layout.cliente, parent, false);

        TextView textViewName = vistaPersonal.findViewById(R.id.nombre);
        Usuario currentItem = usuario.get(position);

        if (currentItem != null) {

            textViewName.setText(currentItem.getNombre());

        }

        return vistaPersonal;

    }

}
