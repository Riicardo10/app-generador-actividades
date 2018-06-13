package com.example.ricardoflores.app;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroAlumnos extends AppCompatActivity {

    EditText txt_matricula, txt_nombre, txt_apellidos, txt_edad, txt_serial;
    Button btt_registrar, btt_menu, btt_lista_alumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_alumnos);
        // TEXT FIELD
        txt_matricula = (EditText) findViewById(R.id.txt_matricula_REG_ALU);
        txt_nombre = (EditText) findViewById(R.id.txt_nombre_REG_ALU);
        txt_apellidos = (EditText) findViewById(R.id.txt_apellidos_REG_ALU);
        txt_edad = (EditText) findViewById(R.id.txt_edad_REG_ALU);
        txt_serial = (EditText) findViewById(R.id.txt_serial_REG_ALU);
        // BUTTON
        btt_registrar = (Button) findViewById(R.id.btt_registrar_REG_ALU);
        btt_menu = (Button) findViewById(R.id.btt_menu_principal_REG_ALU);
        btt_lista_alumnos = (Button) findViewById(R.id.btt_lista_alumnos_REG_ALU);
        // BD ESCRIBIBLE
        final HelperDB helper = new HelperDB(this);
        // REGISTRAR
        btt_registrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = helper.getWritableDatabase();
                String matricula = txt_matricula.getText() + "";
                String nombre = txt_nombre.getText() + "";
                String apellidos = txt_apellidos.getText() + "";
                String edad = txt_edad.getText() + "";
                String serial = txt_serial.getText() + "";
                if(camposLlenos(new String[]{matricula, nombre, apellidos, edad, serial})){
                    if(db != null){
                        ContentValues values = new ContentValues();
                        values.put( EstructuraDB.CAMPO_2_ALUMNOS_MATRICULA, matricula);
                        values.put( EstructuraDB.CAMPO_3_ALUMNOS_NOMBRE, nombre);
                        values.put( EstructuraDB.CAMPO_4_ALUMNOS_APELLIDOS, apellidos);
                        values.put( EstructuraDB.CAMPO_5_ALUMNOS_EDAD, edad);
                        values.put( EstructuraDB.CAMPO_6_ALUMNOS_SERIAL, serial);
                        long i = db.insert( EstructuraDB.TABLA_ALUMNOS, null, values );
                        Toast.makeText(getApplicationContext(), "Registro guardado", Toast.LENGTH_LONG).show();
                        txt_matricula.setText("");
                        txt_nombre.setText("");
                        txt_apellidos.setText("");
                        txt_edad.setText("");
                        txt_serial.setText("");
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Rellena los campos solicitados.", Toast.LENGTH_LONG).show();
                }

            }
        });
        // LISTA ALUMNOS
        btt_lista_alumnos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(), ListaAlumnos.class) );
            }
        });
        // MENU
        btt_menu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(), MenuUsuario.class) );
            }
        });
    }
    public boolean camposLlenos( String arreglo[] ){
        for (int i = 0; i < arreglo.length; i++){
            if(arreglo[i].toString().equals(""))
                return false;
        }
        return true;
    }
}