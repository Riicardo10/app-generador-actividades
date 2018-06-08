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

public class ActualizarAlumno extends AppCompatActivity {

    String nombre, apellidos, matricula, edad, serial;
    EditText txt_matricula, txt_nombre, txt_apellidos, txt_edad, txt_serial;
    Button btt_actualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_alumno);
        final HelperDB helper = new HelperDB(this);

        matricula = getIntent().getStringExtra("matricula");
        nombre = getIntent().getStringExtra("nombre");
        apellidos = getIntent().getStringExtra("apellidos");
        edad = getIntent().getStringExtra("edad");
        serial = getIntent().getStringExtra("serial");

        // TEXT FIELD
        txt_matricula = (EditText) findViewById(R.id.txt_matricula_ACT_ALU);
        txt_nombre = (EditText) findViewById(R.id.txt_nombre_ACT_ALU);
        txt_apellidos = (EditText) findViewById(R.id.txt_apellidos_ACT_ALU);
        txt_edad = (EditText) findViewById(R.id.txt_edad_ACT_ALU);
        txt_serial = (EditText) findViewById(R.id.txt_serial_ACT_ALU);

        txt_matricula.setText( matricula );
        txt_nombre.setText( nombre );
        txt_apellidos.setText( apellidos);
        txt_edad.setText( edad );
        txt_serial.setText( serial );

        btt_actualizar = (Button)findViewById(R.id.btt_actualizar_alumno_ACT_ALU);

        btt_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActualizarAlumno.this, "Actualizar: " + txt_matricula.getText().toString(), Toast.LENGTH_SHORT).show();
                SQLiteDatabase db = helper.getWritableDatabase();
                if(db != null) {
                    //db.execSQL(  );
                    //String sql = "UPDATE alumnos SET nombre='" + nombre + "', apellidos = '" + apellidos + "', edad = '" + edad + "', serial = '" + serial + "' WHERE matricula = '" + matricula + "'";
                    //db.execSQL( sql );
                    ContentValues valores = new ContentValues();
                    valores.put("nombre", txt_nombre.getText().toString());
                    valores.put("apellidos", txt_apellidos.getText().toString());
                    valores.put("edad", txt_edad.getText().toString());
                    valores.put("serial", txt_serial.getText().toString());
                    db.update("alumnos", valores, "matricula='" + matricula + "'", null);
                    Toast.makeText(ActualizarAlumno.this, "Registro actualizado.", Toast.LENGTH_LONG).show();
                    startActivity( new Intent(getApplicationContext(), ListaAlumnos.class) );
                    finish();
                }
            }
        });


    }
}
