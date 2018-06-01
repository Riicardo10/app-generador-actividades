package com.example.ricardoflores.app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistroActividades extends AppCompatActivity {

    Button btt_menu;
    Spinner spinner_momentos, spinner_tags;
    String items_momentos[] = new String[3];
    String items_tags[];
    boolean bandera_momentos = true;
    boolean bandera_tags = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_actividades);
        final HelperDB helper = new HelperDB(this);

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
                if(bandera_momentos){
                    bandera_momentos = false;
                }
                else{
                    Toast.makeText(RegistroActividades.this, items_momentos[posicion], Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // SPINNER TAG
        /*items_tags[0] = "TAG 1";
        items_tags[1] = "TAG 2";
        items_tags[2] = "TAG 3";
        items_tags[3] = "TAG 4";
        items_tags[4] = "TAG 5";
        items_tags[5] = "TAG 6";*/
        items_tags = cargarListaTag(helper);
        spinner_tags = (Spinner)findViewById(R.id.spinner_tags_REG_ACT);
        cargarListaTag( helper );
        ArrayAdapter<String> adapter_tags = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, items_tags);
        adapter_tags.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tags.setAdapter(adapter_tags);
        spinner_tags.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long id) {
                if(bandera_tags){
                    bandera_tags = false;
                }
                else{
                    Toast.makeText(RegistroActividades.this, items_tags[posicion], Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btt_menu = (Button)findViewById(R.id.btt_menu_principal_REG_ACT);
        btt_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(), MenuUsuario.class) );
            }
        });

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
