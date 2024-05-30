package com.example.proyecto_sistema_gestion_empresarial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UsuarioIdAdapter extends ArrayAdapter<ParticipaGasto> {

    private List<ParticipaGasto> participaGasto;

    public UsuarioIdAdapter(Context context, List<ParticipaGasto> list) {
        super(context, 0, list);
        this.participaGasto = list;
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

        final View vistaPersonal = LayoutInflater.from(getContext()).inflate(R.layout.usuarios_id, parent, false);

        TextView textViewIdUsuario = vistaPersonal.findViewById(R.id.idUsuario);
        ParticipaGasto currentItem = participaGasto.get(position);

        if (currentItem != null) {

            textViewIdUsuario.setText(String.valueOf(currentItem.getId_usuario()));

        }

        return vistaPersonal;

    }
}
