package com.example.proyecto_sistema_gestion_empresarial;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.proyecto_sistema_gestion_empresarial.Interfaces.UsarProyecto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        final UsarProyecto usarProyecto = new Retrofit.Builder().baseUrl("http://rnunher1411.eu.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UsarProyecto.class);

        ListView mListView = (ListView) findViewById(R.id.listaProyecto);
        usarProyecto.UsarProyecto().enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {

                    ArrayList<Proyecto> respuesta  = response.body().data;

                    ProyectoAdapter pAdapter = new ProyectoAdapter(MainActivity.this, respuesta);
                    mListView.setAdapter(pAdapter);

            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {

                System.out.println("No funciona");

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int idProyecto = (int) id;

                boolean seleccionado = true;

                /*if (mListView.) {
                    seleccionado = true;
                }*/

                if (seleccionado) {

                    final Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("id", idProyecto);
                    startActivity(intent);

                }

            }
        });


    }
}