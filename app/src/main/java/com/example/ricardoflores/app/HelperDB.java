package com.example.ricardoflores.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelperDB extends SQLiteOpenHelper {

    public static final int VERSION_DB = 1;
    public static final String NOMBRE_DB = "app.db";

    public HelperDB(Context context){
        super(context, NOMBRE_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( EstructuraDB.SQL_CREAR_TABLA_DOCENTES );
        db.execSQL( EstructuraDB.SQL_CREAR_TABLA_ALUMNOS );
        db.execSQL( EstructuraDB.SQL_CREAR_TABLA_TAGS );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL( EstructuraDB.SQL_ELIMINAR_TABLA_DOCENTES );
        db.execSQL( EstructuraDB.SQL_ELIMINAR_TABLA_ALUMNOS );
        db.execSQL( EstructuraDB.SQL_ELIMINAR_TABLA_TAGS );
        onCreate( db );
    }
}



// ES LA BUENA