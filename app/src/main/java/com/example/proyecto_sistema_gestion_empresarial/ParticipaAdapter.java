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

public class ParticipaAdapter extends ArrayAdapter<ParticipaUsarioProyecto> {

    private List<ParticipaUsarioProyecto> participaUsarioProyecto;
    public ParticipaAdapter(Context context, List<ParticipaUsarioProyecto> list) {
        super(context, 0, list);
        this.participaUsarioProyecto = list;
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

        final View vistaPersonal = LayoutInflater.from(getContext()).inflate(R.layout.participa_usuario_proyecto, parent, false);

        TextView textViewNombreUsuario = vistaPersonal.findViewById(R.id.nombreUsuario);
        ParticipaUsarioProyecto currentItem = participaUsarioProyecto.get(position);

        if (currentItem != null) {

            textViewNombreUsuario.setText(currentItem.getNombre_usuario());

        }

        return vistaPersonal;

    }

}
