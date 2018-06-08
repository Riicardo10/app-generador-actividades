package com.example.ricardoflores.app;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActualizarDocente extends AppCompatActivity {

    EditText txt_nombre, txt_apellidos, txt_email, txt_contrasenia_1, txt_contrasenia_2, txt_cct, txt_nombre_escuela, txt_grado, txt_grupo;
    Button btt_actualizar;
    String id, nombre, apellidos, email, contrasenia, cct, grado, grupo, nombre_escuela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_docente);
        final HelperDB helper = new HelperDB(this);

        id = getIntent().getStringExtra("id");
        nombre = getIntent().getStringExtra("nombre");
        apellidos = getIntent().getStringExtra("apellidos");
        email = getIntent().getStringExtra("email");
        contrasenia  = getIntent().getStringExtra("contrasenia");
        cct  = getIntent().getStringExtra("cct");
        nombre_escuela = getIntent().getStringExtra("nombre_escuela");
        grado = getIntent().getStringExtra("grado");
        grupo= getIntent().getStringExtra("grupo");

        // TEXT FIELD
        txt_nombre = (EditText) findViewById(R.id.txt_nombre_ACT_DOC);
        txt_apellidos = (EditText) findViewById(R.id.txt_apellidos_ACT_DOC);
        txt_email = (EditText) findViewById(R.id.txt_email_ACT_DOC);
        txt_contrasenia_1 = (EditText) findViewById(R.id.txt_contrasenia_1_ACT_DOC);
        txt_contrasenia_2 = (EditText) findViewById(R.id.txt_contrasenia_2_ACT_DOC);
        txt_cct = (EditText) findViewById(R.id.txt_cct_ACT_DOC);
        txt_nombre_escuela = (EditText) findViewById(R.id.txt_nombre_escuela_ACT_DOC);
        txt_grado = (EditText) findViewById(R.id.txt_grado_ACT_DOC);
        txt_grupo = (EditText) findViewById(R.id.txt_grupo_ACT_DOC);


        txt_nombre.setText( nombre );
        txt_apellidos.setText( apellidos );
        txt_email.setText( email );
        txt_contrasenia_1.setText( contrasenia);
        txt_cct.setText( cct );
        txt_nombre_escuela.setText( nombre_escuela );
        txt_grado.setText( grado );
        txt_grupo.setText( grupo );

        // BUTTON
        btt_actualizar = (Button)findViewById(R.id.btt_actualizar_ACT_DOC);
        btt_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = helper.getWritableDatabase();
                if(db != null) {
                    ContentValues valores = new ContentValues();
                    valores.put(EstructuraDB.CAMPO_2_DOCENTES_NOMBRE, txt_nombre.getText().toString());
                    valores.put(EstructuraDB.CAMPO_3_DOCENTES_APELLIDOS, txt_apellidos.getText().toString());
                    valores.put(EstructuraDB.CAMPO_4_DOCENTES_EMAIL, txt_email.getText().toString());
                    valores.put(EstructuraDB.CAMPO_5_DOCENTES_CONTRASENIA, txt_contrasenia_1.getText().toString());
                    valores.put(EstructuraDB.CAMPO_6_DOCENTES_CCT, txt_cct.getText().toString());
                    valores.put(EstructuraDB.CAMPO_7_DOCENTES_ESCUELA, txt_nombre_escuela.getText().toString());
                    valores.put(EstructuraDB.CAMPO_8_DOCENTES_GRADO, txt_grado.getText().toString());
                    valores.put(EstructuraDB.CAMPO_9_DOCENTES_GRUPO, txt_grupo.getText().toString());
                    db.update(EstructuraDB.TABLA_DOCENTES, valores, "id='" + id + "'", null);
                    Toast.makeText(ActualizarDocente.this, "Registro actualizado.", Toast.LENGTH_LONG).show();
                    startActivity( new Intent(getApplicationContext(), ListaDocentes.class) );
                    finish();
                }
            }
        });
    }
}
