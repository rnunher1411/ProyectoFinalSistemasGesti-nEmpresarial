package com.example.proyecto_sistema_gestion_empresarial;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivityModificarProyecto extends AppCompatActivity {

    String admin = "";
    int idAdminProyectoModificado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_modificar_proyecto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final int idProyecto = getIntent().getIntExtra("id", 0);
        final String nombreProyecto = getIntent().getStringExtra("nombre");
        final int idUsuarioAdmin = getIntent().getIntExtra("idAdmin", 0);

        TextView titulo = findViewById(R.id.titulo);
        SpannableString content = new SpannableString("Modificar " + nombreProyecto);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        titulo.setText(content);

        EditText nombre = findViewById(R.id.nombre);
        SpannableString content2 = new SpannableString("" + nombreProyecto);
        nombre.setText(content2);

        idAdminProyectoModificado = idUsuarioAdmin;

        final UsarProyecto usarAdmin = new Retrofit.Builder().baseUrl("http://rnunher1411.eu.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UsarProyecto.class);

        Spinner caja = findViewById(R.id.idAdmin);

        usarAdmin.UsarUsuario().enqueue(new Callback<Respuesta3>() {
            @Override
            public void onResponse(Call<Respuesta3> call, Response<Respuesta3> response) {

                ArrayList<Usuario> respuestaCaja  = response.body().data;

                PagadorAdapter puAdapter = new PagadorAdapter(MainActivityModificarProyecto.this, respuestaCaja);
                caja.setAdapter(puAdapter);

                int posicionPagador = 0;

                for (int x=0; x < respuestaCaja.size(); x++) {

                    Usuario usuario = respuestaCaja.get(x);

                    if (usuario.getId() == idUsuarioAdmin) {

                        posicionPagador = x;

                    }

                }
                caja.setSelection(posicionPagador);

            }

            @Override
            public void onFailure(Call<Respuesta3> call, Throwable t) {

                System.out.println("hola");

            }

        });

        caja.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                PagadorAdapter puAdapter = (PagadorAdapter) caja.getAdapter();
                Usuario usuario = (Usuario) parent.getItemAtPosition(position);
                idAdminProyectoModificado = usuario.getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final UsarProyecto modificarProyecto = new Retrofit.Builder().baseUrl("http://rnunher1411.eu.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UsarProyecto.class);

        Button botonAceptar = findViewById(R.id.modificar);

        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nombre = findViewById(R.id.nombre);
                String textoNombre = nombre.getText().toString();

                Proyecto proyecto = new Proyecto(textoNombre, idAdminProyectoModificado);

                modificarProyecto.ModificarProyecto(idProyecto, proyecto).enqueue(new Callback<RespuestaModificarProyecto>() {
                    @Override
                    public void onResponse(Call<RespuestaModificarProyecto> call, Response<RespuestaModificarProyecto> response) {

                        Proyecto respuestaModificarProyecto = response.body().data;

                    }

                    @Override
                    public void onFailure(Call<RespuestaModificarProyecto> call, Throwable t) {

                    }
                });

                final Intent intent = new Intent(MainActivityModificarProyecto.this, MainActivityExaminarProyecto.class);
                intent.putExtra("id", idProyecto);
                startActivity(intent);

            }
        });

        Button botonCancelar = findViewById(R.id.cancelar);

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(MainActivityModificarProyecto.this, MainActivityExaminarProyecto.class);
                intent.putExtra("id", idProyecto);
                startActivity(intent);
            }
        });



    }
}