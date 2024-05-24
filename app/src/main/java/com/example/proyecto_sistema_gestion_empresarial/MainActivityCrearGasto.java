package com.example.proyecto_sistema_gestion_empresarial;

import static java.lang.Float.parseFloat;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityCrearGasto extends AppCompatActivity {

    String pagador = "";
    int idPagadorNuevoGasto = 0;

    List<Integer> usuariosSeleccionados;

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

        final int idProyecto = getIntent().getIntExtra("id", 0);
        int idPagador;


        TextView titulo = findViewById(R.id.titulo);
        SpannableString content = new SpannableString("Proyecto " + idProyecto);
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
                PagadorAdapter puAdapter = new PagadorAdapter(MainActivityCrearGasto.this, respuesta3);
                caja.setAdapter(puAdapter);
                mListView.setAdapter(uAdapter);

            }

            @Override
            public void onFailure(Call<Respuesta3> call, Throwable t) {

                System.out.println("hola");

            }

        });

        final UsarProyecto crearGasto = new Retrofit.Builder().baseUrl("http://rnunher1411.eu.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UsarProyecto.class);

        /*caja.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            setPagador((String) caja.getItemAtPosition(position));

        }
        });*/

        caja.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                PagadorAdapter puAdapter = (PagadorAdapter) caja.getAdapter();
                Usuario usuario = (Usuario) parent.getItemAtPosition(position);
                idPagadorNuevoGasto = usuario.getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //CheckBox seleccionarNombre = findViewById(R.id.caja);

        usuariosSeleccionados = new ArrayList<>();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Usuario usuario = (Usuario) parent.getItemAtPosition(position);
                usuariosSeleccionados.add(usuario.getId()/*(Integer) parent.getItemAtPosition(position)*/);

            }
        });

        Button botonAceptar = findViewById(R.id.guardar);

        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText importe = findViewById(R.id.importe);
                String valorStringImporte = importe.getText().toString();
                Float valorImporte = parseFloat(valorStringImporte);

                EditText concepto = findViewById(R.id.descripcion);
                String textoConcepto = concepto.getText().toString();


                int idProyectoGastoNuevo = idProyecto;

                GastoUsuarios nuevoGasto = new GastoUsuarios(textoConcepto, valorImporte, idProyectoGastoNuevo, idPagadorNuevoGasto, usuariosSeleccionados);

                crearGasto.CrearGasto(idProyecto, nuevoGasto).enqueue(new Callback<RespuestaCrearGasto>() {
                    @Override
                    public void onResponse(Call<RespuestaCrearGasto> call, Response<RespuestaCrearGasto> response) {

                        ArrayList<GastoUsuarios> respuestaCrearGasto = response.body().data;

                    }

                    @Override
                    public void onFailure(Call<RespuestaCrearGasto> call, Throwable t) {

                    }

                });

                final Intent intent = new Intent(MainActivityCrearGasto.this, MainActivity2.class);
                intent.putExtra("id", idProyecto);
                startActivity(intent);

            }
        });

        Button botonCancelar = findViewById(R.id.cancelar);

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(MainActivityCrearGasto.this, MainActivity2.class);
                intent.putExtra("id", idProyecto);
                startActivity(intent);
            }
        });

    }

    public void setPagador(String pagador) {
        this.pagador = pagador;
    }

    public void seleccionarUsuario(Usuario usuario) {

        usuariosSeleccionados.add(usuario.getId());

    }
}