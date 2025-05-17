// src/main/java/com/example/biblioteca/Usuario.java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private final String id;
    private final String nombre;
    private final List<Libro> librosAlquilados = new ArrayList<>();

    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public List<Libro> getLibrosAlquilados() { return librosAlquilados; }

    public void alquilarLibro(Libro libro) {
        libro.presta();
        librosAlquilados.add(libro);
    }

    public void devolverLibro(Libro libro) {
        if (!librosAlquilados.remove(libro)) {
            throw new IllegalArgumentException("El usuario no tiene ese libro alquilado");
        }
        libro.devuelve();
    }
}
