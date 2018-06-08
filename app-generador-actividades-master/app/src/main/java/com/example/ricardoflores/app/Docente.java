package com.example.ricardoflores.app;

public class Docente {
    String id;
    String nombre;
    String apellidos;
    String email;
    String cct;
    String grado;
    String grupo;

    public Docente(String id, String nombre, String apellidos, String email, String cct, String grado, String grupo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.cct = cct;
        this.grado = grado;
        this.grupo = grupo;
    }
    public Docente(String nombre, String apellidos, String email, String cct, String grado, String grupo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.cct = cct;
        this.grado = grado;
        this.grupo = grupo;
    }
    public Docente(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCct() {
        return cct;
    }

    public void setCct(String cct) {
        this.cct = cct;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
