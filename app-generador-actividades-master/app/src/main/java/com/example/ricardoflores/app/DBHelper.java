package com.example.ricardoflores.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "app.db";
    private static final int DB_SCHEME_VERSION = 1;

    String tabla_docente = "CREATE TABLE docentes (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre Text, apellidos Text, email Text, contrasenia Text, cct Text, nombre_escuela Text, grado Text, grupo Text)";
    String tabla_alumno = "CREATE TABLE alumnos (id INTEGER PRIMARY KEY AUTOINCREMENT, matricula Text, nombre Text, apellidos Text, edad Text, serial Text)";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( tabla_docente );
        db.execSQL( tabla_alumno );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
}
