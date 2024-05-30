package com.example.proyecto_sistema_gestion_empresarial;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proyecto_sistema_gestion_empresarial.Interfaces.UsarProyecto;

import java.sql.SQLOutput;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final int idGasto = getIntent().getIntExtra("idGasto", 0);
        final String conceptoGasto = getIntent().getStringExtra("conceptoGasto");
        final float importeGasto = getIntent().getFloatExtra("importeGasto", 0);
        final int idProyectoGasto = getIntent().getIntExtra("idProyectoGasto", 0);
        final int idPagadorGasto = getIntent().getIntExtra("idPagadorGasto", 0);

        TextView titulo = findViewById(R.id.titulo);
        SpannableString content = new SpannableString("Gasto " + idGasto);
        titulo.setText(content);

        Button botonVolver = findViewById(R.id.volver);

        botonVolver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                intent.putExtra("id", idProyectoGasto);
                startActivity(intent);

            }
        });

        Gasto gasto = new Gasto(idGasto, conceptoGasto, importeGasto, idProyectoGasto, idPagadorGasto);

        TextView id = findViewById(R.id.id);
        id.setText(String.valueOf(gasto.getId()));

        TextView concepto = findViewById(R.id.concepto);
        concepto.setText(gasto.getConcepto());

        TextView importe = findViewById(R.id.importe);
        importe.setText(String.valueOf(gasto.getImporte()));

        TextView idProyecto = findViewById(R.id.id_proyecto);
        idProyecto.setText(String.valueOf(gasto.getId_proyecto()));

        TextView idPagador = findViewById(R.id.id_pagador);
        idPagador.setText(String.valueOf(gasto.getId_pagador()));

        final UsarProyecto leerParticipaGasto = new Retrofit.Builder().baseUrl("http://rnunher1411.eu.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UsarProyecto.class);


        ListView mListView = (ListView) findViewById(R.id.listaUsariosId);

        leerParticipaGasto.LeerParticipaGasto(idGasto).enqueue(new Callback<Respuesta4>() {
            @Override
            public void onResponse(Call<Respuesta4> call, Response<Respuesta4> response) {

                ArrayList<ParticipaGasto> respuesta4  = response.body().data;

                UsuarioIdAdapter uiAdapter = new UsuarioIdAdapter(MainActivity3.this, respuesta4);
                mListView.setAdapter(uiAdapter);

            }

            @Override
            public void onFailure(Call<Respuesta4> call, Throwable t) {

                System.out.println("Hola");

            }
        });

    }
}