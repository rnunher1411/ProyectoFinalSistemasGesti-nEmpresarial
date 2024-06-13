package com.example.proyecto_sistema_gestion_empresarial;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class MainActivityModificarGasto extends AppCompatActivity {

    String pagador = "";
    int idPagadorGastoModificado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_modificar_gasto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final int idGasto = getIntent().getIntExtra("idGasto", 0);
        final String conceptoGasto = getIntent().getStringExtra("conceptoGasto");
        final float importeGasto = getIntent().getFloatExtra("importeGasto", 0);
        final int idProyecto = getIntent().getIntExtra("idProyectoGasto", 0);
        final int idPagador = getIntent().getIntExtra("idPagadorGasto", 0);


        TextView titulo = findViewById(R.id.titulo);
        SpannableString content = new SpannableString("Proyecto " + idProyecto);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        titulo.setText(content);

        TextView titulo2 = findViewById(R.id.titulo2);
        SpannableString content2 = new SpannableString("Modificar Gasto " + conceptoGasto);
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        titulo2.setText(content2);

        EditText importe = findViewById(R.id.importe);
        SpannableString content3 = new SpannableString("" + importeGasto);
        importe.setText(content3);

        EditText descripcion = findViewById(R.id.descripcion);
        SpannableString content4 = new SpannableString(conceptoGasto);
        descripcion.setText(content4);

        EditText proyectoId = findViewById(R.id.proyectoId);
        SpannableString content5 = new SpannableString("" + idProyecto);
        proyectoId.setText(content5);

        idPagadorGastoModificado = idPagador;

        final UsarProyecto usarCliente = new Retrofit.Builder().baseUrl("http://rnunher1411.eu.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UsarProyecto.class);

        Spinner caja = findViewById(R.id.pagador);

        usarCliente.UsarUsuario().enqueue(new Callback<Respuesta3>() {
            @Override
            public void onResponse(Call<Respuesta3> call, Response<Respuesta3> response) {

                ArrayList<Usuario> respuesta3  = response.body().data;

                PagadorAdapter puAdapter = new PagadorAdapter(MainActivityModificarGasto.this, respuesta3);
                caja.setAdapter(puAdapter);

                int posicionPagador = 0;

                for (int x=0; x < respuesta3.size(); x++) {

                    Usuario usuario = respuesta3.get(x);

                    if (usuario.getId() == idPagador) {

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

        final UsarProyecto crearGasto = new Retrofit.Builder().baseUrl("http://rnunher1411.eu.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UsarProyecto.class);

        caja.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                PagadorAdapter puAdapter = (PagadorAdapter) caja.getAdapter();
                Usuario usuario = (Usuario) parent.getItemAtPosition(position);
                idPagadorGastoModificado = usuario.getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button botonAceptar = findViewById(R.id.modificar);

        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText importe = findViewById(R.id.importe);
                String valorStringImporte = importe.getText().toString();
                Float valorImporte = parseFloat(valorStringImporte);

                EditText concepto = findViewById(R.id.descripcion);
                String textoConcepto = concepto.getText().toString();

                EditText proyectoId = findViewById(R.id.proyectoId);
                String numeroId = proyectoId.getText().toString();
                int idProyectoGastoModificado = parseInt(numeroId);

                GastoUsuarios GastoModificado = new GastoUsuarios(textoConcepto, valorImporte, idProyectoGastoModificado, idPagadorGastoModificado);

                crearGasto.ModificarGasto(idGasto, GastoModificado).enqueue(new Callback<RespuestaModificarGasto>() {
                    @Override
                    public void onResponse(Call<RespuestaModificarGasto> call, Response<RespuestaModificarGasto> response) {

                        GastoUsuarios respuestaModificarGasto = response.body().data;

                    }

                    @Override
                    public void onFailure(Call<RespuestaModificarGasto> call, Throwable t) {

                    }
                });

                final Intent intent = new Intent(MainActivityModificarGasto.this, MainActivity3.class);
                intent.putExtra("idGasto", idGasto);
                intent.putExtra("conceptoGasto", textoConcepto);
                intent.putExtra("importeGasto", valorImporte);
                intent.putExtra("idProyectoGasto", idProyectoGastoModificado);
                intent.putExtra("idPagadorGasto", idPagadorGastoModificado);
                startActivity(intent);

            }
        });

        Button botonCancelar = findViewById(R.id.cancelar);

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(MainActivityModificarGasto.this, MainActivity3.class);
                intent.putExtra("idGasto", idGasto);
                intent.putExtra("conceptoGasto", conceptoGasto);
                intent.putExtra("importeGasto", importeGasto);
                intent.putExtra("idProyectoGasto", idProyecto);
                intent.putExtra("idPagadorGasto", idPagador);
                startActivity(intent);
            }
        });

    }

    /*public void seleccionarUsuario(Usuario usuario) {

        usuariosSeleccionados.add(usuario.getId());

    }*/

}