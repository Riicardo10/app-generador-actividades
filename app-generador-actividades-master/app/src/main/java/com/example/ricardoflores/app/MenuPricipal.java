package com.example.ricardoflores.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuPricipal extends AppCompatActivity  implements View.OnClickListener{

    Button btt_menu;
    TextView txt_nombre_docente, txt_nombre_escuela;
    String id, nombre, apellidos, email, cct, escuela, grado, grupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pricipal);
        txt_nombre_docente = (TextView)findViewById(R.id.txt_nombre_docente_MEN_PRI);
        txt_nombre_escuela = (TextView)findViewById(R.id.txt_nombre_escuela_MEN_PRI);
        btt_menu = (Button)findViewById(R.id.btt_menu_usuario_MEN_PRI);
        btt_menu.setOnClickListener(this);

        id = getIntent().getStringExtra("id");
        nombre = getIntent().getStringExtra("nombre");
        apellidos = getIntent().getStringExtra("apellidos");
        email = getIntent().getStringExtra("email");
        cct = getIntent().getStringExtra("cct");
        escuela = getIntent().getStringExtra("escuela");
        grado = getIntent().getStringExtra("grado");
        grupo = getIntent().getStringExtra("grupo");
        //Toast.makeText(this, "MENU\n" + id + " " + nombre + "\n" + apellidos + " " + email + "\n" + cct + " " + escuela + "\n" + grado + " " + grupo, Toast.LENGTH_SHORT).show();
        txt_nombre_docente.setText( nombre + " " + apellidos );
        txt_nombre_escuela.setText( escuela );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btt_menu_usuario_MEN_PRI:
                startActivity(new Intent(getApplicationContext(), MenuUsuario.class));
                break;
        }
    }
}
