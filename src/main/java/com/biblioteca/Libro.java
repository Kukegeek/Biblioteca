// src/main/java/com/example/biblioteca/Libro.java
package com.biblioteca;


public class Libro {
    private final String titulo;
    private final String autor;
    private int ejemplaresDisponibles;

    public Libro(String titulo, String autor, int ejemplaresDisponibles) {
        this.titulo = titulo;
        this.autor = autor;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getEjemplaresDisponibles() { return ejemplaresDisponibles; }

    public boolean estaDisponible() {
        return ejemplaresDisponibles > 0;
    }

    public void presta() {
        if (!estaDisponible()) {
            throw new IllegalStateException("No quedan ejemplares de " + titulo);
        }
        ejemplaresDisponibles--;
    }

    public void devuelve() {
        ejemplaresDisponibles++;
    }
}
