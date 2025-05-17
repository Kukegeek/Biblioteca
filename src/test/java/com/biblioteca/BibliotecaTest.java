// src/test/java/com/example/biblioteca/BibliotecaTest.java
package com.biblioteca;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class BibliotecaTest {
    private Biblioteca biblioteca;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        biblioteca = new Biblioteca();
        usuario = new Usuario("u1", "Ana");
    }

    @Test
    void testAlquilerExitosoDisminuyeStockYAgregaAlUsuario() {
        biblioteca.alquilarLibroAUsuario("1984", usuario);
        assertEquals(2, biblioteca.buscarPorTitulo("1984").get().getEjemplaresDisponibles());
        assertTrue(usuario.getLibrosAlquilados()
                          .stream().anyMatch(l -> l.getTitulo().equals("1984")));
    }

    @Test
    void testAlquilerLibroNoExistenteLanzaExcepcion() {
        assertThrows(NoSuchElementException.class, 
                     () -> biblioteca.alquilarLibroAUsuario("No existe", usuario));
    }

    @Test
    void testAlquilerSinDisponibilidadLanzaEstado() {
        // "Don Juan" solo tiene 1 ejemplar
        biblioteca.alquilarLibroAUsuario("Don Juan", usuario);
        assertThrows(IllegalStateException.class,
                     () -> biblioteca.alquilarLibroAUsuario("Don Juan", usuario));
    }

    @Test
    void testDevolucionReincrementeStockYLoQuiteDelUsuario() {
        biblioteca.alquilarLibroAUsuario("El Principito", usuario);
        biblioteca.devolverLibroDeUsuario("El Principito", usuario);
        assertEquals(5, biblioteca.buscarPorTitulo("El Principito").get().getEjemplaresDisponibles());
        assertTrue(usuario.getLibrosAlquilados().isEmpty());
    }

    @Test
    void testGetLibrosDisponiblesFiltraCorrectamente() {
        // Alquilamos un libro para que no esté disponible
        biblioteca.alquilarLibroAUsuario("La Odisea", usuario);
        assertTrue(biblioteca.getLibrosDisponibles()
                              .stream()
                              .anyMatch(l -> l.getTitulo().equals("La Odisea")));
    }
}
