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
import java.util.List;

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
        TextView nombreAdmin = findViewById(R.id.nombreAdmin);

        TextView id = findViewById(R.id.id);

        TextView titulo = findViewById(R.id.titulo);

        leer.UsarProyectoId(idProyecto).enqueue(new Callback<RespuestaUsarProyectoId>() {
            @Override
            public void onResponse(Call<RespuestaUsarProyectoId> call, Response<RespuestaUsarProyectoId> response) {

                Proyecto respuesta8  = response.body().data;
                nombreProyecto.setText(String.valueOf(respuesta8.getNombre()));
                nombreProyectoTexto = nombreProyecto.getText().toString();
                id_admin = respuesta8.getId_admin();

                id.setText(String.valueOf(idProyecto));
                SpannableString content = new SpannableString(nombreProyectoTexto);
                titulo.setText(content);
                leer.UsarUsuarioId(id_admin).enqueue(new Callback<RespuestaUsarPagadorId>() {
                    @Override
                    public void onResponse(Call<RespuestaUsarPagadorId> call, Response<RespuestaUsarPagadorId> response) {

                        Usuario respuesta6 = response.body().data;
                        nombreAdmin.setText(String.valueOf(respuesta6.getNombre()));

                    }

                    @Override
                    public void onFailure(Call<RespuestaUsarPagadorId> call, Throwable t) {

                        System.out.println("hola");

                    }
                });

            }

            @Override
            public void onFailure(Call<RespuestaUsarProyectoId> call, Throwable t) {

                System.out.println("hola");

            }
        });

        ListView mListView = (ListView) findViewById(R.id.listaUsariosId);

        leer.LeerParticipaUsuario(idProyecto).enqueue(new Callback<RespuestaUsuarioProyecto>() {
            @Override
            public void onResponse(Call<RespuestaUsuarioProyecto> call, Response<RespuestaUsuarioProyecto> response) {

                List<ParticipaUsarioProyecto> respuestaParticipa = response.body().data;

                ParticipaAdapter participaUsarioProyecto = new ParticipaAdapter(MainActivityExaminarProyecto.this, respuestaParticipa);

                mListView.setAdapter(participaUsarioProyecto);

            }

            @Override
            public void onFailure(Call<RespuestaUsuarioProyecto> call, Throwable t) {

            }
        });


        Button botonModificarProyecto = findViewById(R.id.modificarProyecto);

        botonModificarProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombreProyectoString = nombreProyecto.getText().toString();

                Intent intent = new Intent(MainActivityExaminarProyecto.this, MainActivityModificarProyecto.class);
                intent.putExtra("id", idProyecto);
                intent.putExtra("nombre", nombreProyectoString);
                intent.putExtra("idAdmin", id_admin);
                startActivity(intent);

            }
        });


    }
}