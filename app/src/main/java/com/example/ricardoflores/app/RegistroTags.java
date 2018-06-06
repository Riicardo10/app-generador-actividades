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

public class RegistroTags extends AppCompatActivity {

    EditText txt_serial, txt_nombre_objeto;
    Button btt_registrar, btt_lista, btt_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tags);
        final HelperDB helper = new HelperDB(this);

        //txt_id = (EditText)findViewById(R.id.txt_id_REG_TAG);
        txt_serial = (EditText)findViewById(R.id.txt_serial_REG_TAG);
        txt_nombre_objeto = (EditText)findViewById(R.id.txt_tag_REG_TAG);

        btt_registrar = (Button)findViewById(R.id.btt_registrar_REG_TAG);
        btt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String id = txt_id.getText().toString();
                String serial = txt_serial.getText().toString();
                String nombre_objeto = txt_nombre_objeto.getText().toString();

                if(camposLlenos(new String[]{ serial, nombre_objeto })){
                    SQLiteDatabase db = helper.getWritableDatabase();
                    if(db != null){
                        ContentValues values = new ContentValues();
                        //values.put( EstructuraDB.CAMPO_1_TAGS_ID, id);
                        values.put( EstructuraDB.CAMPO_2_TAGS_SERIAL, serial);
                        values.put( EstructuraDB.CAMPO_3_TAGS_NOMBRE_OBJETO, nombre_objeto);
                        long i = db.insert( EstructuraDB.TABLA_TAGS, null, values );
                        Toast.makeText(getApplicationContext(), "Registro guardado", Toast.LENGTH_LONG).show();
                        //txt_id.setText("");
                        txt_serial.setText("");
                        txt_nombre_objeto.setText("");
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Rellena los campos solicitados.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btt_lista = (Button)findViewById(R.id.btt_lista_tags_REG_TAG);
        btt_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(), ListaTags.class) );
                //Toast.makeText(RegistroTags.this, "LISTA", Toast.LENGTH_SHORT).show();
            }
        });

        btt_menu = (Button)findViewById(R.id.btt_menu_principal_REG_TAG);
        btt_menu.setOnClickListener(new View.OnClickListener() {
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
