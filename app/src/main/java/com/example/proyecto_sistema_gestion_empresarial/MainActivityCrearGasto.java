package com.example.proyecto_sistema_gestion_empresarial;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivityCrearGasto extends AppCompatActivity {

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

    }
}