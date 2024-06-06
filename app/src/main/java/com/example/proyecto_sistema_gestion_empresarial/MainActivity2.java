package com.example.proyecto_sistema_gestion_empresarial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.proyecto_sistema_gestion_empresarial.Interfaces.UsarProyecto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

        public DrawerLayout drawerLayout;
        public ActionBarDrawerToggle actionBarDrawerToggle;

        int gastosTotales;

        int listaSize = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);


            final int idProyecto = getIntent().getIntExtra("id", 0);

            drawerLayout = findViewById(R.id.my_drawer_layout);
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();

            Button botonVolver = findViewById(R.id.volver);

            botonVolver.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);

                }
            });


            final UsarProyecto usarGasto = new Retrofit.Builder().baseUrl("http://rnunher1411.eu.pythonanywhere.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(UsarProyecto.class);

            ListView mListView = (ListView) findViewById(R.id.listaGasto);
            TextView conteoGasto = findViewById(R.id.conteoGastos);

            usarGasto.UsarGasto(idProyecto).enqueue(new Callback<Respuesta2>() {
                @Override
                public void onResponse(Call<Respuesta2> call, Response<Respuesta2> response) {

                    ArrayList<Gasto> respuesta2  = response.body().data;

                    GastoAdapter gAdapter = new GastoAdapter(MainActivity2.this, respuesta2);
                    mListView.setAdapter(gAdapter);

                    for (int x = 0; x < gAdapter.getSize(); x++) {

                        gastosTotales += gAdapter.getItemImporte(x);
                        conteoGasto.setText("Gastos totales: " + gastosTotales);
                        listaSize++;

                    }
                }

                @Override
                public void onFailure(Call<Respuesta2> call, Throwable t) {

                }
            });



            Button botonGasto = findViewById(R.id.botonAñadirGasto);

            botonGasto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final Intent intent = new Intent(MainActivity2.this, MainActivityCrearGasto.class);
                    intent.putExtra("id", idProyecto);
                    startActivity(intent);

                }
            });

            Button botonEliminarProyecto = findViewById(R.id.botonEliminarProyecto);

            botonEliminarProyecto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for (int y = 0; y < listaSize; y++) {

                        Gasto gasto = (Gasto) mListView.getItemAtPosition(y);
                        int idGasto = gasto.getId();

                        usarGasto.BorrarGasto(idGasto).enqueue(new Callback<Respuesta2>() {
                            @Override
                            public void onResponse(Call<Respuesta2> call, Response<Respuesta2> response) {

                            }

                            @Override
                            public void onFailure(Call<Respuesta2> call, Throwable t) {

                            }
                        });

                    }

                    usarGasto.BorrarProyecto(idProyecto).enqueue(new Callback<RespuestaUsarProyectoId>() {
                        @Override
                        public void onResponse(Call<RespuestaUsarProyectoId> call, Response<RespuestaUsarProyectoId> response) {

                            Toast.makeText(MainActivity2.this, "Proyecto borrado",
                                    Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onFailure(Call<RespuestaUsarProyectoId> call, Throwable t) {

                        }
                    });

                    final Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);

                }
            });

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Gasto gasto = (Gasto) parent.getItemAtPosition(position);


                    int idGasto = gasto.getId();
                    String conceptoGasto = gasto.getConcepto();
                    Float importeGasto = gasto.getImporte();
                    int id_proyectoGasto = gasto.getId_proyecto();
                    int id_pagadorGasto = gasto.getId_pagador();

                    final Intent intent = new Intent(MainActivity2.this, MainActivity3.class);

                    intent.putExtra("idGasto", idGasto);
                    intent.putExtra("conceptoGasto", conceptoGasto);
                    intent.putExtra("importeGasto", importeGasto);
                    intent.putExtra("idProyectoGasto", id_proyectoGasto);
                    intent.putExtra("idPagadorGasto", id_pagadorGasto);

                    startActivity(intent);

                }
            });


        }

}
