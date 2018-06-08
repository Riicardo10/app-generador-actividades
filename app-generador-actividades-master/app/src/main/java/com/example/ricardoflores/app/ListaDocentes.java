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
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaDocentes extends AppCompatActivity {

    ListView lista;
    int index = -1;
    Button btt_eliminar_docente, btt_actualizar_docente;
    String data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_docentes);
        final HelperDB helper = new HelperDB(this);
        lista = (ListView) findViewById(R.id.lista_docentes_LIS_DOC);
        cargarListaDocente();
        btt_eliminar_docente = findViewById(R.id.btt_eliminar_docente_LIS_DOC);
        btt_eliminar_docente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index == -1){
                    Toast.makeText(ListaDocentes.this, "Selecciona registro a eliminar", Toast.LENGTH_SHORT).show();
                }
                else{
                    String[] parts_data = data.split(":");
                    String[] parts = parts_data[1].split(" ");
                    String id = parts[1].replace("\nNombre", "");
                    SQLiteDatabase db = helper.getWritableDatabase();
                    if(db != null) {
                        db.execSQL( "DELETE FROM docentes WHERE id='" + id + "'" );
                        cargarListaDocente();
                    }
                }
            }
        });

        btt_actualizar_docente = findViewById(R.id.btt_actualizar_docente_LIS_DOC);
        btt_actualizar_docente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index == -1){
                    Toast.makeText(ListaDocentes.this, "Selecciona registro a eliminar", Toast.LENGTH_SHORT).show();
                }
                else{
                    String[] parts_data = data.split(":");
                    String[] parts = parts_data[1].split(" ");
                    String id = parts[1].replace("\nNombre", "");
                    SQLiteDatabase db = helper.getWritableDatabase();
                    if(db != null) {
                        String sql = "SELECT * FROM docentes WHERE id= '" + id + "'";
                        Cursor c = db.rawQuery(sql, null);
                        int cantidad = c.getCount();
                        // aqui error
                        Intent intent = new Intent(ListaDocentes.this, ActualizarDocente.class);
                        if(c.moveToFirst()){
                            do {
                                intent.putExtra("id", c.getString(0));
                                intent.putExtra("nombre", c.getString(1));
                                intent.putExtra("apellidos", c.getString(2));
                                intent.putExtra("email", c.getString(3));
                                intent.putExtra("contrasenia", c.getString(4));
                                intent.putExtra("cct", c.getString(5));
                                intent.putExtra("nombre_escuela", c.getString(6));
                                intent.putExtra("grado", c.getString(7));
                                intent.putExtra("grupo", c.getString(8));
                            } while (c.moveToNext());
                        }
                        startActivity( intent );
                        finish();
                    }
                }
            }
        });


    }

    public void cargarListaDocente(){
        DBHelper helper = new DBHelper(this, "GENERADOR", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        if(db != null){
            Cursor c = db.rawQuery("SELECT * FROM docentes", null);
            int cantidad = c.getCount();
            String[] arreglo = new String[cantidad];
            int i = 0;
            if(c.moveToFirst()){
                do {
                    String linea = "ID: " + c.getInt(0) + "\n";
                    linea += "Nombre: " + c.getString(1) + " " + c.getString(2) + "\n";
                    linea += "Email: " + c.getString(3);
                    arreglo[i] = linea;
                    i++;
                } while (c.moveToNext());
            }
            ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arreglo);
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
