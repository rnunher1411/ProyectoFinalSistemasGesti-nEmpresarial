package com.example.proyecto_sistema_gestion_empresarial;

import android.os.Bundle;
import android.widget.ListView;

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

        final int idProyecto = getIntent().getIntExtra("idProyecto", 0);
        final int idGasto = getIntent().getIntExtra("idGasto", 0);

        final UsarProyecto leerCliente = new Retrofit.Builder().baseUrl("http://rnunher1411.eu.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UsarProyecto.class);

        ListView mListView = (ListView) findViewById(R.id.listaGasto);
        leerCliente.LeerGasto(idGasto).enqueue(new Callback<Respuesta2>() {
            @Override
            public void onResponse(Call<Respuesta2> call, Response<Respuesta2> response) {


                ArrayList<Gasto> respuesta2  = response.body().data;

                LeerGastoAdapter lgAdapter = new LeerGastoAdapter(MainActivity3.this, respuesta2);
                mListView.setAdapter(lgAdapter);

            }

            @Override
            public void onFailure(Call<Respuesta2> call, Throwable t) {

                System.out.println("hola");

            }
        });

    }
}