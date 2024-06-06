package com.example.proyecto_sistema_gestion_empresarial;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proyecto_sistema_gestion_empresarial.Interfaces.UsarProyecto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity3 extends AppCompatActivity {

    int id_Usuarios = 0;

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



        final UsarProyecto leer = new Retrofit.Builder().baseUrl("http://rnunher1411.eu.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UsarProyecto.class);

        TextView nombreProyecto = findViewById(R.id.nombre_proyecto);

        leer.UsarProyectoId(idProyectoGasto).enqueue(new Callback<RespuestaUsarProyectoId>() {
            @Override
            public void onResponse(Call<RespuestaUsarProyectoId> call, Response<RespuestaUsarProyectoId> response) {

                Proyecto respuesta5  = response.body().data;
                nombreProyecto.setText(String.valueOf(respuesta5.getNombre()));

            }

            @Override
            public void onFailure(Call<RespuestaUsarProyectoId> call, Throwable t) {

                System.out.println("hola");

            }
        });



        TextView nombrePagador = findViewById(R.id.nombre_pagador);

        leer.UsarUsuarioId(idPagadorGasto).enqueue(new Callback<RespuestaUsarPagadorId>() {
            @Override
            public void onResponse(Call<RespuestaUsarPagadorId> call, Response<RespuestaUsarPagadorId> response) {

                Usuario respuesta6 = response.body().data;
                nombrePagador.setText(String.valueOf(respuesta6.getNombre()));

            }

            @Override
            public void onFailure(Call<RespuestaUsarPagadorId> call, Throwable t) {

                System.out.println("hola");

            }
        });

        ListView mListView = (ListView) findViewById(R.id.listaUsariosId);

        leer.LeerParticipaGasto(idGasto).enqueue(new Callback<Respuesta4>() {
            @Override
            public void onResponse(Call<Respuesta4> call, Response<Respuesta4> response) {

                ArrayList<ParticipaGasto> respuesta4  = response.body().data;

                PartcipaGastoUsuarioNombreAdapter uiAdapter = new PartcipaGastoUsuarioNombreAdapter(MainActivity3.this, respuesta4);

                mListView.setAdapter(uiAdapter);

            }

            @Override
            public void onFailure(Call<Respuesta4> call, Throwable t) {

                System.out.println("Hola");

            }
        });

        Button modificarGasto = findViewById(R.id.modificarGasto);

        modificarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(MainActivity3.this, MainActivityModificarGasto.class);
                intent.putExtra("idGasto", idGasto);
                intent.putExtra("conceptoGasto", conceptoGasto);
                intent.putExtra("importeGasto", importeGasto);
                intent.putExtra("idProyectoGasto", idProyectoGasto);
                intent.putExtra("idPagadorGasto", idPagadorGasto);
                startActivity(intent);

            }
        });

        Button eliminarGasto = findViewById(R.id.deleteGasto);

        eliminarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                leer.BorrarGasto(idGasto).enqueue(new Callback<Respuesta2>() {
                    @Override
                    public void onResponse(Call<Respuesta2> call, Response<Respuesta2> response) {

                        Toast.makeText(MainActivity3.this, "Gasto borrado",
                                Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<Respuesta2> call, Throwable t) {

                    }
                });

                final Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                intent.putExtra("id", idProyectoGasto);
                startActivity(intent);

            }
        });


    }
}