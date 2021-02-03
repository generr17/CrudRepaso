package com.example.crudrepaso;

public class Contacto {
    int id;
    String nombre;
    String telefono;
    String email;
    int edad;

    public Contacto() {

    }

    public Contacto(String nombre, String telefono, String email, int edad) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.edad = edad;
    }

    public Contacto(int id, String nombre, String telefono, String email, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.edad = edad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public int getEdad() {
        return edad;
    }
}
