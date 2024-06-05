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

public class PartcipaGastoUsuarioNombreAdapter extends ArrayAdapter<ParticipaGasto> {

    private List<ParticipaGasto> participaGasto;

    public PartcipaGastoUsuarioNombreAdapter(Context context, List<ParticipaGasto> list) {
        super(context, 0, list);
        this.participaGasto = list;
    }


    public int getUsuarioId(int position) {

        return participaGasto.get(position).getId_usuario();

    }

    public int getSize() {

        return participaGasto.size();

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

        TextView textViewNombreUsuario = vistaPersonal.findViewById(R.id.nombreUsuario);
        ParticipaGasto currentItem = participaGasto.get(position);

        if (currentItem != null) {

            textViewNombreUsuario.setText(String.valueOf(currentItem.getNombre_usuario()));

        }

        return vistaPersonal;

    }
}
