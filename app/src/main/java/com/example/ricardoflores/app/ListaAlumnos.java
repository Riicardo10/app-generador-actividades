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

public class ListaAlumnos extends AppCompatActivity {

    ListView lista;
    int index = -1;
    Button btt_eliminar_alumno, btt_actualizar_alumno;
    String data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alumnos);
        final HelperDB helper = new HelperDB(this);
        lista = (ListView) findViewById(R.id.lista_alumnos_LIS_ALU);
        cargarListaAlumno(helper);
        // EVENTOS
        btt_eliminar_alumno = findViewById(R.id.btt_eliminar_alumno_LIS_ALU);
        btt_eliminar_alumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index == -1){
                    Toast.makeText(ListaAlumnos.this, "Selecciona registro a eliminar", Toast.LENGTH_SHORT).show();
                }
                else{
                    String[] parts_data = data.split(":");
                    String[] parts = parts_data[1].split(" ");
                    String matricula = parts[1].replace("\nNombre", "");
                    SQLiteDatabase db = helper.getWritableDatabase();
                    if(db != null) {
                        db.execSQL( "DELETE FROM alumnos WHERE matricula='" + matricula + "'" );
                        cargarListaAlumno(helper);
                    }
                }
            }
        });
        btt_actualizar_alumno = findViewById(R.id.btt_actualizar_alumno_LIS_ALU);
        btt_actualizar_alumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index == -1){
                    Toast.makeText(ListaAlumnos.this, "Selecciona registro a actualizar", Toast.LENGTH_SHORT).show();
                }
                else{
                    String[] parts_data = data.split(":");
                    String[] parts = parts_data[1].split(" ");
                    String matricula = parts[1].replace("\nNombre", "");
                    SQLiteDatabase db = helper.getWritableDatabase();
                    if(db != null) {
                        String sql = "SELECT * FROM alumnos WHERE matricula = '" + matricula + "'";
                        Cursor c = db.rawQuery(sql, null);
                        int cantidad = c.getCount();
                        Intent intent = new Intent(ListaAlumnos.this, ActualizarAlumno.class);
                        if(c.moveToFirst()){
                            do {
                                intent.putExtra("matricula", c.getString(1));
                                intent.putExtra("nombre", c.getString(2));
                                intent.putExtra("apellidos", c.getString(3));
                                intent.putExtra("edad", c.getString(4));
                                intent.putExtra("serial", c.getString(5));
                            } while (c.moveToNext());
                        }
                        startActivity( intent );
                        finish();
                    }
                }
            }
        });

    }

    public void cargarListaAlumno(HelperDB helper){
        SQLiteDatabase db = helper.getReadableDatabase();
        if(db != null){
            Cursor c = db.rawQuery("SELECT * FROM alumnos", null);
            int cantidad = c.getCount();
            String[] arreglo = new String[cantidad];
            int i = 0;
            if(c.moveToFirst()){
                do {
                    String linea = "Matricula: " + c.getString(1) + "\n";
                    linea += "Nombre: " + c.getString(2) + " " + c.getString(3) + "\n";
                    linea += "Serial: " + c.getString(5);
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
