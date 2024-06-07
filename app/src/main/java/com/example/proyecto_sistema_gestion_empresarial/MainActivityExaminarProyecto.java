package com.example.proyecto_sistema_gestion_empresarial;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityExaminarProyecto extends AppCompatActivity {

    String nombreProyectoTexto = "";
    int id_admin = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_examinar_proyecto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final int idProyecto = getIntent().getIntExtra("id", 0);

        Button botonVolver = findViewById(R.id.volver);
        botonVolver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivityExaminarProyecto.this, MainActivity2.class);
                intent.putExtra("id", idProyecto);
                startActivity(intent);

            }
        });

        final UsarProyecto leer = new Retrofit.Builder().baseUrl("http://rnunher1411.eu.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UsarProyecto.class);

        TextView nombreProyecto = findViewById(R.id.nombreProyecto);

        leer.UsarProyectoId(idProyecto).enqueue(new Callback<RespuestaUsarProyectoId>() {
            @Override
            public void onResponse(Call<RespuestaUsarProyectoId> call, Response<RespuestaUsarProyectoId> response) {

                Proyecto respuesta8  = response.body().data;
                nombreProyecto.setText(String.valueOf(respuesta8.getNombre()));

            }

            @Override
            public void onFailure(Call<RespuestaUsarProyectoId> call, Throwable t) {

            }
        });

        Proyecto proyecto = new Proyecto(idProyecto, nombreProyectoTexto, id_admin);

        TextView id = findViewById(R.id.id);
        id.setText(String.valueOf(proyecto.getId()));

        TextView titulo = findViewById(R.id.titulo);
        SpannableString content = new SpannableString(nombreProyectoTexto);
        titulo.setText(content);

        TextView nombreAdmin = findViewById(R.id.nombreAdmin);

        leer.UsarUsuarioId(id_admin).enqueue(new Callback<RespuestaUsarPagadorId>() {
            @Override
            public void onResponse(Call<RespuestaUsarPagadorId> call, Response<RespuestaUsarPagadorId> response) {

                Usuario respuesta6 = response.body().data;
                nombreAdmin.setText(String.valueOf(respuesta6.getNombre()));

            }

            @Override
            public void onFailure(Call<RespuestaUsarPagadorId> call, Throwable t) {

            }
        });

        ListView mListView = (ListView) findViewById(R.id.listaUsariosId);


    }
}