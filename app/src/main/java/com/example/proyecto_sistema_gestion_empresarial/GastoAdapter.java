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

public class GastoAdapter extends ArrayAdapter<Gasto> {

    private List<Gasto> gasto;
    public GastoAdapter(Context context, List<Gasto> list) {
        super(context, 0, list);
        this.gasto = list;
    }


    public float getItemImporte(int position) {

        return gasto.get(position).getImporte();

    }

    public int getSize() {

        return gasto.size();

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

        final View vistaPersonal = LayoutInflater.from(getContext()).inflate(R.layout.gasto, parent, false);

        TextView textViewName = vistaPersonal.findViewById(R.id.concepto);
        TextView textViewImporte = vistaPersonal.findViewById(R.id.importe);
        Gasto currentItem = gasto.get(position);

        if (currentItem != null) {

            textViewName.setText(currentItem.getConcepto());
            textViewImporte.setText(String.valueOf(currentItem.getImporte()));

        }

        return vistaPersonal;

    }
}
