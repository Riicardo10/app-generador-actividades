package com.example.ricardoflores.app;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistroActividades extends AppCompatActivity {

    EditText txt_nombre_actividad;
    Button btt_menu, btt_agregar_tag, btt_agregar_actividad, btt_lista_actividades;
    Spinner spinner_momentos, spinner_tags;
    String items_momentos[] = new String[3];
    String items_tags[];
    boolean bandera_momentos = true;
    String tag_a_agregar = "";
    String momento = "";
    ArrayList<String> tags_activity = new ArrayList<>();
    ListView lista_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_actividades);
        final HelperDB helper = new HelperDB(this);

        txt_nombre_actividad = (EditText)findViewById(R.id.txt_nombre_actividad_REG_ACT);

        // SPINNER MOMENTO
        items_momentos[0] = "Momento 1";
        items_momentos[1] = "Momento 2";
        items_momentos[2] = "Momento 3";
        spinner_momentos = (Spinner)findViewById(R.id.spinner_momentos_REG_ACT);
        ArrayAdapter<String> adapter_momentos = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, items_momentos);
        adapter_momentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_momentos.setAdapter(adapter_momentos);
        spinner_momentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long id) {
                momento = items_momentos[posicion] + "";
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // SPINNER TAG
        items_tags = cargarListaTag(helper);
        spinner_tags = (Spinner)findViewById(R.id.spinner_tags_REG_ACT);
        cargarListaTag( helper );
        ArrayAdapter<String> adapter_tags = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, items_tags);
        adapter_tags.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tags.setAdapter(adapter_tags);
        spinner_tags.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long id) {
                tag_a_agregar = items_tags[posicion] + "";
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // LISTA
        lista_2 = (ListView) findViewById(R.id.lista_tags_2_REG_ACT);

        // BOTON MENU
        btt_menu = (Button)findViewById(R.id.btt_menu_principal_REG_ACT);
        btt_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(), MenuUsuario.class) );
                finish();
            }
        });

        // BOTON AGREGAR TAG A ACTIVIDAD
        btt_agregar_tag = (Button)findViewById(R.id.btt_agregar_tag_REG_ACT);
        btt_agregar_tag .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean bandera = true;
                for (int i=0; i<tags_activity.size(); i++ ){
                    if(tags_activity.get(i).toString().equals( tag_a_agregar )){
                        bandera = false;
                        break;
                    }
                }
                if(bandera){
                    tags_activity.add( tag_a_agregar );
                    cargarLista( tags_activity );
                }
                else {
                    Toast.makeText(RegistroActividades.this, "El elemento " + tag_a_agregar + " ya se encuentra en la lista", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // BOTON AGREGAR ACTIVIDAD
        btt_agregar_actividad = (Button)findViewById(R.id.btt_agregar_actividad_REG_ACT);
        btt_agregar_actividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre_actividad = txt_nombre_actividad.getText().toString();
                if( !nombre_actividad.isEmpty() ){

                    SQLiteDatabase db = helper.getWritableDatabase();
                    String data = "";

                    if(db != null) {

                        for (int i = 0; i < tags_activity.size(); i++) {
                            ContentValues values = new ContentValues();
                            values.put( EstructuraDB.CAMPO_1_ACTIVIDADES_NOMBRE, nombre_actividad );
                            values.put( EstructuraDB.CAMPO_2_ACTIVIDADES_MOMENTO, momento );
                            values.put( EstructuraDB.CAMPO_3_ACTIVIDADES_TAG, tags_activity.get(i).toString() );
                            long r = db.insert( EstructuraDB.TABLA_ACTIVIDADES, null, values );
                            data += r + " - ";
                        }
                        Toast.makeText(getApplicationContext(), "Registro guardado " + data, Toast.LENGTH_LONG).show();

                    }
                }
                else{
                    Toast.makeText(RegistroActividades.this, "Ingresa nombre de la actividad.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // LISTA DE ACTIVIDADES
        btt_lista_actividades = (Button)findViewById(R.id.btt_lista_actividades_REG_ACT);
        btt_lista_actividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(), ListaActividades.class) );
            }
        });
    }

    public void cargarLista(ArrayList arreglo){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arreglo);
        lista_2.setAdapter(adapter);
    }

    public String[] cargarListaTag(HelperDB helper){
        SQLiteDatabase db = helper.getReadableDatabase();
        if(db != null){
            Cursor c = db.rawQuery("SELECT * FROM tags", null);
            String items_tags[] = new String[c.getCount()];
            int i = 0;
            if(c.moveToFirst()){
                do {
                    String linea = c.getString(0) + " - " + c.getString(2);
                    items_tags[i] = linea;
                    i++;
                } while (c.moveToNext());
            }
            return items_tags;
        }
        return null;
    }
}
