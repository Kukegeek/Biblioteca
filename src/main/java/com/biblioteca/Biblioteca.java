// src/main/java/com/example/biblioteca/Biblioteca.java
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class Biblioteca {
    private final List<Libro> libros;

    public Biblioteca() {
        // Lista inicial de 10 libros:
        libros = new ArrayList<>(List.of(
            new Libro("1984", "George Orwell", 3),
            new Libro("El Quijote", "Cervantes", 2),
            new Libro("Don Juan", "Zorrilla", 1),
            new Libro("Cien Años de Soledad", "Gabriel García Márquez", 4),
            new Libro("La Odisea", "Homero", 2),
            new Libro("Hamlet", "Shakespeare", 3),
            new Libro("Fausto", "Goethe", 2),
            new Libro("La Divina Comedia", "Dante", 1),
            new Libro("El Principito", "Saint-Exupéry", 5),
            new Libro("Matar a un ruiseñor", "Harper Lee", 2)
        ));
    }

    public Optional<Libro> buscarPorTitulo(String titulo) {
        return libros.stream()
                     .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                     .findFirst();
    }

    public void alquilarLibroAUsuario(String titulo, Usuario u) {
        Libro libro = buscarPorTitulo(titulo)
            .orElseThrow(() -> new NoSuchElementException("Libro no encontrado: " + titulo));
        u.alquilarLibro(libro);
    }

    public void devolverLibroDeUsuario(String titulo, Usuario u) {
        Libro libro = buscarPorTitulo(titulo)
            .orElseThrow(() -> new NoSuchElementException("Libro no encontrado: " + titulo));
        u.devolverLibro(libro);
    }

    public List<Libro> getLibrosDisponibles() {
        return libros.stream()
                     .filter(Libro::estaDisponible)
                     .collect(Collectors.toList());
    }
}
