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

public class RegistroDocente extends AppCompatActivity implements OnClickListener {

    EditText txt_nombre, txt_apellidos, txt_email, txt_contrasenia_1, txt_contrasenia_2, txt_cct, txt_nombre_escuela, txt_grado, txt_grupo;
    Button btt_registrar, btt_menu, btt_lista_docentes;
    HelperDB helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_docente);
        helper = new HelperDB(this);
        // TEXT FIELD
        txt_nombre = (EditText) findViewById(R.id.txt_nombre_REGDOC);
        txt_apellidos = (EditText) findViewById(R.id.txt_apellidos_REGDOC);
        txt_email = (EditText) findViewById(R.id.txt_email_REGDOC);
        txt_contrasenia_1 = (EditText) findViewById(R.id.txt_contrasenia_1_REGDOC);
        txt_contrasenia_2 = (EditText) findViewById(R.id.txt_contrasenia_2_REGDOC);
        txt_cct = (EditText) findViewById(R.id.txt_cct_REGDOC);
        txt_nombre_escuela = (EditText) findViewById(R.id.txt_nombre_escuela_REGDOC);
        txt_grado = (EditText) findViewById(R.id.txt_grado_REGDOC);
        txt_grupo = (EditText) findViewById(R.id.txt_grupo_REGDOC);
        // BUTTON
        btt_registrar = (Button)findViewById(R.id.btt_registrar_REG_DOC);
        btt_registrar.setOnClickListener(this);
        btt_menu = (Button)findViewById(R.id.btt_menu_principal_REG_DOC);
        btt_menu.setOnClickListener(this);
        btt_lista_docentes = (Button)findViewById(R.id.btt_lista_docentes_REG_DOC);
        btt_lista_docentes.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btt_registrar_REG_DOC:
                guardarDocente();
                break;
            case R.id.btt_menu_principal_REG_DOC:
                startActivity( new Intent(getApplicationContext(), MainActivity.class) );
                break;
            case R.id.btt_lista_docentes_REG_DOC:
                startActivity( new Intent(getApplicationContext(), ListaDocentes.class) );
                break;
        }
    }



    // METODO PARA REGISTRO DE DOCENTE
    public void guardarDocente(){
        String nombre = txt_nombre.getText() + "";
        String apellidos = txt_apellidos.getText() + "";
        String email = txt_email.getText() + "";
        String contrasenia_1 = txt_contrasenia_1.getText() + "";
        String contrasenia_2 = txt_contrasenia_2.getText() + "";
        String cct = txt_cct.getText() + "";
        String nombre_escuela = txt_nombre_escuela.getText() + "";
        String grado = txt_grado.getText() + "";
        String grupo = txt_grupo.getText() + "";

        if(verificarContrasenia(contrasenia_1, contrasenia_2)){
            if(camposLlenos(new String[]{nombre, apellidos, email, contrasenia_1, contrasenia_2, cct, nombre_escuela, grado, grupo})){
                SQLiteDatabase db = helper.getWritableDatabase();
                if(db != null){
                    ContentValues registro = new ContentValues();
                    registro.put(EstructuraDB.CAMPO_2_DOCENTES_NOMBRE, nombre);
                    registro.put(EstructuraDB.CAMPO_3_DOCENTES_APELLIDOS, apellidos);
                    registro.put(EstructuraDB.CAMPO_4_DOCENTES_EMAIL, email);
                    registro.put(EstructuraDB.CAMPO_5_DOCENTES_CONTRASENIA, contrasenia_1);
                    registro.put(EstructuraDB.CAMPO_6_DOCENTES_CCT, cct);
                    registro.put(EstructuraDB.CAMPO_7_DOCENTES_ESCUELA, nombre_escuela);
                    registro.put(EstructuraDB.CAMPO_8_DOCENTES_GRADO, grado);
                    registro.put(EstructuraDB.CAMPO_9_DOCENTES_GRUPO, grupo);
                    long i = db.insert( EstructuraDB.TABLA_DOCENTES, null, registro );
                    Toast.makeText(getApplicationContext(), "Registro guardado", Toast.LENGTH_LONG).show();
                }
                /*SQLiteDatabase db = helper.getWritableDatabase();
                if(db != null){
                    ContentValues registro = new ContentValues();
                    registro.put(EstructuraDB.CAMPO_2_DOCENTES_NOMBRE, nombre);
                    registro.put(EstructuraDB.CAMPO_3_DOCENTES_APELLIDOS, apellidos);
                    registro.put(EstructuraDB.CAMPO_4_DOCENTES_EMAIL, email);
                    registro.put(EstructuraDB.CAMPO_5_DOCENTES_CONTRASENIA, contrasenia_1);
                    registro.put(EstructuraDB.CAMPO_6_DOCENTES_CCT, cct);
                    registro.put(EstructuraDB.CAMPO_7_DOCENTES_ESCUELA, nombre_escuela);
                    registro.put(EstructuraDB.CAMPO_8_DOCENTES_GRADO, grado);
                    registro.put(EstructuraDB.CAMPO_9_DOCENTES_GRUPO, grupo);
                    long i = db.insert("docentes", null, registro);
                    Toast.makeText(this, i + "", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(this, "Registro guardado.", Toast.LENGTH_SHORT).show();
                }*/
                /*DBHelper helper = new DBHelper(this, "GENERADOR", null, 1);
                SQLiteDatabase db = helper.getWritableDatabase();
                if(db != null){
                    ContentValues registro = new ContentValues();
                    registro.put("nombre", nombre);
                    registro.put("apellidos", apellidos);
                    registro.put("email", email);
                    registro.put("contrasenia", contrasenia_1);
                    registro.put("cct", cct);
                    registro.put("nombre_escuela", nombre_escuela);
                    registro.put("grado", grado);
                    registro.put("grupo", grupo);
                    long i = db.insert("docentes", null, registro);
                    if(i > 0){
                        Toast.makeText(this, "Registro guardado.", Toast.LENGTH_SHORT).show();
                        txt_nombre.setText("");
                        txt_apellidos.setText("");
                        txt_email.setText("");
                        txt_contrasenia_1.setText("");
                        txt_contrasenia_2.setText("");
                        txt_cct.setText("");
                        txt_nombre_escuela.setText("");
                        txt_grado.setText("");
                        txt_grupo.setText("");
                    }
                    else{
                        Toast.makeText(this, "Ocurrio un error.", Toast.LENGTH_LONG).show();
                    }
                }*/
            }
            else{
                Toast.makeText(this, "Rellena los campos solicitados.", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Verifica contrasenias.", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean camposLlenos( String arreglo[] ){
        for (int i = 0; i < arreglo.length; i++){
            if(arreglo[i].toString().equals(""))
                return false;
        }
        return true;
    }
    public boolean verificarContrasenia(String contra_1, String contra_2) {
        return contra_1.equals(contra_2) ? true : false;
    }
}



/*txt_nombre.setText("");
txt_apellidos.setText("");
txt_email.setText("");
txt_contrasenia_1.setText("");
txt_contrasenia_2.setText("");
txt_cct.setText("");
txt_nombre_escuela.setText("");
txt_grado.setText("");
txt_grupo.setText("");*/