package com.example.proyecto_sistema_gestion_empresarial;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
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

public class MainActivityCrearGasto extends AppCompatActivity {

    String pagador = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_crear_gasto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView titulo = findViewById(R.id.titulo);
        SpannableString content = new SpannableString("Proyecto 1");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        titulo.setText(content);

        TextView titulo2 = findViewById(R.id.titulo2);
        SpannableString content2 = new SpannableString("Nuevo Gasto");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        titulo2.setText(content2);

        final UsarProyecto usarCliente = new Retrofit.Builder().baseUrl("http://rnunher1411.eu.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UsarProyecto.class);

        ListView mListView = (ListView) findViewById(R.id.listaCliente);
        Spinner caja = findViewById(R.id.pagador);


        usarCliente.UsarUsuario().enqueue(new Callback<Respuesta3>() {
            @Override
            public void onResponse(Call<Respuesta3> call, Response<Respuesta3> response) {

                ArrayList<Usuario> respuesta3  = response.body().data;

                UsuarioAdapter uAdapter = new UsuarioAdapter(MainActivityCrearGasto.this, respuesta3);
                caja.setAdapter(uAdapter);
                mListView.setAdapter(uAdapter);

            }

            @Override
            public void onFailure(Call<Respuesta3> call, Throwable t) {

                System.out.println("hola");

            }
        });

        caja.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                setPagador((String) caja.getItemAtPosition(position));

            }
        });

    }

    public void setPagador(String pagador) {
        this.pagador = pagador;
    }
}