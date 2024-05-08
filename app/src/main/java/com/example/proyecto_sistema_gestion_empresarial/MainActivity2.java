package com.example.proyecto_sistema_gestion_empresarial;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

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

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);


            final int idProyecto = getIntent().getIntExtra("id", 0);

            drawerLayout = findViewById(R.id.my_drawer_layout);
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();

            final UsarProyecto usarGasto = new Retrofit.Builder().baseUrl("http://rnunher1411.eu.pythonanywhere.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(UsarProyecto.class);

            ListView mListView = (ListView) findViewById(R.id.listaGasto);

            usarGasto.UsarGasto(idProyecto).enqueue(new Callback<Respuesta2>() {
                @Override
                public void onResponse(Call<Respuesta2> call, Response<Respuesta2> response) {

                    ArrayList<Gasto> respuesta2  = response.body().data;


                }

                @Override
                public void onFailure(Call<Respuesta2> call, Throwable t) {

                }
            });


        }

}
