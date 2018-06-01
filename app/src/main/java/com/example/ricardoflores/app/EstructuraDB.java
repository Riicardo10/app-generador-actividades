package com.example.ricardoflores.app;

public class EstructuraDB {
    // constructor
    private EstructuraDB(){};
    // estructura DOCENTES
    public static final String TABLA_DOCENTES = "docentes";
    public static final String CAMPO_1_DOCENTES_ID = "id";
    public static final String CAMPO_2_DOCENTES_NOMBRE = "nombre";
    public static final String CAMPO_3_DOCENTES_APELLIDOS = "apellidos";
    public static final String CAMPO_4_DOCENTES_EMAIL = "email";
    public static final String CAMPO_5_DOCENTES_CONTRASENIA = "contrasenia";
    public static final String CAMPO_6_DOCENTES_CCT = "cct";
    public static final String CAMPO_7_DOCENTES_ESCUELA = "escuela";
    public static final String CAMPO_8_DOCENTES_GRADO= "grado";
    public static final String CAMPO_9_DOCENTES_GRUPO = "grupo";
    public static final String SQL_CREAR_TABLA_DOCENTES =
            "CREATE TABLE " + EstructuraDB.TABLA_DOCENTES + " ("  +
                    EstructuraDB.CAMPO_1_DOCENTES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EstructuraDB.CAMPO_2_DOCENTES_NOMBRE + " TEXT, " +
                    EstructuraDB.CAMPO_3_DOCENTES_APELLIDOS + " TEXT, " +
                    EstructuraDB.CAMPO_4_DOCENTES_EMAIL + " TEXT, " +
                    EstructuraDB.CAMPO_5_DOCENTES_CONTRASENIA + " TEXT, " +
                    EstructuraDB.CAMPO_6_DOCENTES_CCT + " TEXT, " +
                    EstructuraDB.CAMPO_7_DOCENTES_ESCUELA + " TEXT, " +
                    EstructuraDB.CAMPO_8_DOCENTES_GRADO + " TEXT, " +
                    EstructuraDB.CAMPO_9_DOCENTES_GRUPO + " TEXT)";
    public static final String SQL_ELIMINAR_TABLA_DOCENTES = "DROP TABLE IF EXISTS " + EstructuraDB.TABLA_DOCENTES;
    // estructura ALUMNOS
    public static final String TABLA_ALUMNOS= "alumnos";
    public static final String CAMPO_1_ALUMNOS_ID = "id";
    public static final String CAMPO_2_ALUMNOS_MATRICULA = "matricula";
    public static final String CAMPO_3_ALUMNOS_NOMBRE = "nombre";
    public static final String CAMPO_4_ALUMNOS_APELLIDOS = "apellidos";
    public static final String CAMPO_5_ALUMNOS_EDAD = "edad";
    public static final String CAMPO_6_ALUMNOS_SERIAL= "serial";
    public static final String SQL_CREAR_TABLA_ALUMNOS =
            "CREATE TABLE " + EstructuraDB.TABLA_ALUMNOS+ " ("  +
                    EstructuraDB.CAMPO_1_ALUMNOS_ID + " INTEGER PRIMARY KEY, " +
                    EstructuraDB.CAMPO_2_ALUMNOS_MATRICULA + " TEXT, " +
                    EstructuraDB.CAMPO_3_ALUMNOS_NOMBRE + " TEXT, " +
                    EstructuraDB.CAMPO_4_ALUMNOS_APELLIDOS + " TEXT, " +
                    EstructuraDB.CAMPO_5_ALUMNOS_EDAD + " TEXT, " +
                    EstructuraDB.CAMPO_6_ALUMNOS_SERIAL + " TEXT)";
    public static final String SQL_ELIMINAR_TABLA_ALUMNOS = "DROP TABLE IF EXISTS " + EstructuraDB.TABLA_ALUMNOS;
    // estructura TAGS
    public static final String TABLA_TAGS = "tags";
    public static final String CAMPO_1_TAGS_ID = "id";
    public static final String CAMPO_2_TAGS_SERIAL = "serial";
    public static final String CAMPO_3_TAGS_NOMBRE_OBJETO = "nombre_objeto";
    public static final String SQL_CREAR_TABLA_TAGS =
            "CREATE TABLE " + EstructuraDB.TABLA_TAGS+ " ("  +
                    EstructuraDB.CAMPO_1_TAGS_ID + " TEXT PRIMARY KEY, " +
                    EstructuraDB.CAMPO_2_TAGS_SERIAL + " TEXT, " +
                    EstructuraDB.CAMPO_3_TAGS_NOMBRE_OBJETO + " TEXT)";
    public static final String SQL_ELIMINAR_TABLA_TAGS = "DROP TABLE IF EXISTS " + EstructuraDB.TABLA_TAGS;


}
