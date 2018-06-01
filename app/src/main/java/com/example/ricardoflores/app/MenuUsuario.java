package com.example.ricardoflores.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MenuUsuario extends AppCompatActivity implements View.OnClickListener{

    ImageView btt_asistencia, btt_actividades, btt_evaluacion, btt_alumnos, btt_tags, btt_actividad;
    TextView txt_titulo_MENUSU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);
        btt_asistencia = (ImageView)findViewById(R.id.btt_asistencia_MENUSU);
        btt_actividades = (ImageView)findViewById(R.id.btt_actividades_MENUSU);
        btt_evaluacion = (ImageView)findViewById(R.id.btt_evaluacion_MENUSU);
        btt_alumnos = (ImageView)findViewById(R.id.btt_registrar_alumno_MENUSU);
        btt_tags = (ImageView)findViewById(R.id.btt_registrar_tags_MENUSU);
        btt_actividad = (ImageView)findViewById(R.id.btt_registrar_actividades_MENUSU);
        txt_titulo_MENUSU = (TextView)findViewById(R.id.txt_titulo_MENUSU);
        btt_asistencia.setOnClickListener(this);
        btt_actividades.setOnClickListener(this);
        btt_evaluacion.setOnClickListener(this);
        btt_alumnos.setOnClickListener(this);
        btt_tags.setOnClickListener(this);
        btt_actividad .setOnClickListener(this);
        txt_titulo_MENUSU.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btt_asistencia_MENUSU:
                 Toast.makeText(this, "ASISTENCIA", Toast.LENGTH_LONG).show();
                break;
            case R.id.btt_actividades_MENUSU:
                Toast.makeText(this, "ACTIVIDADES", Toast.LENGTH_LONG).show();
                break;
            case R.id.btt_evaluacion_MENUSU:
                Toast.makeText(this, "EVALUACION", Toast.LENGTH_LONG).show();
                break;
            case R.id.btt_registrar_alumno_MENUSU:
                startActivity( new Intent(getApplicationContext(), RegistroAlumnos.class) );
                break;
            case R.id.btt_registrar_tags_MENUSU:
                startActivity( new Intent(getApplicationContext(), RegistroTags.class) );
                break;
            case R.id.btt_registrar_actividades_MENUSU:
                startActivity( new Intent(getApplicationContext(), RegistroActividades.class) );
                break;
            case R.id.txt_titulo_MENUSU:
                startActivity( new Intent(getApplicationContext(), MenuPricipal.class) );
                break;
        }
    }
}
