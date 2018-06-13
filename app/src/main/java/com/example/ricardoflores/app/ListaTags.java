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
import android.widget.ListView;
import android.widget.Toast;

public class ListaTags extends AppCompatActivity {

    ListView lista;
    int index = -1;
    Button btt_eliminar_tag, btt_actualizar_tag;
    String data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tags);

        final HelperDB helper = new HelperDB(this);
        lista = (ListView) findViewById(R.id.lista_tags_LIS_TAG);
        cargarListaTag(helper);
        // EVENTOS
        btt_eliminar_tag = findViewById(R.id.btt_eliminar_tag_LIS_TAG);
        btt_eliminar_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index == -1){
                    Toast.makeText(getApplicationContext(), "Selecciona registro a eliminar", Toast.LENGTH_SHORT).show();
                }
                else{
                    String[] parts_data = data.split(":");
                    String[] parts = parts_data[1].split(" ");
                    String id = parts[1].replace("\nSerial", "");
                    SQLiteDatabase db = helper.getWritableDatabase();
                    if(db != null) {
                        db.execSQL( "DELETE FROM tags WHERE id='" + id+ "'" );
                        cargarListaTag(helper);
                    }
                }
            }
        });
        btt_actualizar_tag = findViewById(R.id.btt_actualizar_tag_LIS_TAG);
        btt_actualizar_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index == -1){
                    Toast.makeText(getApplicationContext(), "Selecciona registro a actualizar", Toast.LENGTH_SHORT).show();
                }
                else{
                    String[] parts_data = data.split(":");
                    String[] parts = parts_data[1].split(" ");
                    String id = parts[1].replace("\nSerial", "");
                    SQLiteDatabase db = helper.getWritableDatabase();
                    if(db != null) {
                        String sql = "SELECT * FROM tags WHERE id = '" + id+ "'";
                        Cursor c = db.rawQuery(sql, null);
                        Intent intent = new Intent(ListaTags.this, ActualizarTag.class);
                        if(c.moveToFirst()){
                            do {
                                intent.putExtra("id", c.getString(0));
                                intent.putExtra("serial", c.getString(1));
                                intent.putExtra("tag", c.getString(2));
                            } while (c.moveToNext());
                        }
                        startActivity( intent );
                        finish();
                    }
                }
            }
        });
    }
    public void cargarListaTag(HelperDB helper){
        SQLiteDatabase db = helper.getReadableDatabase();
        if(db != null){
            Cursor c = db.rawQuery("SELECT * FROM tags", null);
            int cantidad = c.getCount();
            String[] arreglo = new String[cantidad];
            int i = 0;
            if(c.moveToFirst()){
                do {
                    String linea = "ID: " + c.getString(0) + "\n";
                    linea += "Serial: " + c.getString(1) + "\n";
                    linea += "Nombre objeto: " + c.getString(2);
                    arreglo[i] = linea;
                    i++;
                } while (c.moveToNext());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arreglo);
            lista.setAdapter(adapter);
            lista.setOnItemClickListener( new ItemList() );
        }
    }
    class ItemList implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {
            String valor = (String)   lista.getItemAtPosition(posicion);
            data = valor;
            index = 1;
        }
    }
}
