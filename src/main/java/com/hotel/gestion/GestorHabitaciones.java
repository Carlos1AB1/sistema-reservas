package com.hotel.gestion;

import com.hotel.modelo.Habitacion;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase responsable de gestionar las habitaciones del hotel.
 * 
 * SRP: Esta clase tiene una única responsabilidad: gestionar el inventario
 * y disponibilidad de las habitaciones. No se encarga de reservas ni clientes.
 */
public class GestorHabitaciones {
    private List<Habitacion> habitaciones;
    
    /**
     * Constructor de la clase GestorHabitaciones.
     */
    public GestorHabitaciones() {
        this.habitaciones = new ArrayList<>();
    }
    
    /**
     * Agrega una habitación al inventario del hotel.
     * 
     * @param habitacion Habitación a agregar
     * @return true si fue agregada exitosamente, false en caso contrario
     */
    public boolean agregarHabitacion(Habitacion habitacion) {
        if (habitacion == null) {
            return false;
        }
        
        // Verificar que no exista una habitación con el mismo número
        boolean existe = habitaciones.stream()
                .anyMatch(h -> h.getNumero().equals(habitacion.getNumero()));
        
        if (existe) {
            System.out.println("Ya existe una habitación con el número " + habitacion.getNumero());
            return false;
        }
        
        habitaciones.add(habitacion);
        System.out.println("Habitación agregada: " + habitacion.getNumero());
        return true;
    }
    
    /**
     * Busca una habitación por su número.
     * 
     * @param numero Número de la habitación
     * @return La habitación encontrada o null si no existe
     */
    public Habitacion buscarHabitacion(String numero) {
        return habitaciones.stream()
                .filter(h -> h.getNumero().equals(numero))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Obtiene todas las habitaciones disponibles.
     * 
     * @return Lista de habitaciones disponibles
     */
    public List<Habitacion> obtenerHabitacionesDisponibles() {
        return habitaciones.stream()
                .filter(Habitacion::isDisponible)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtiene todas las habitaciones de un tipo específico.
     * 
     * @param tipo Tipo de habitación (ej: "Estándar", "Suite")
     * @return Lista de habitaciones del tipo especificado
     */
    public List<Habitacion> obtenerHabitacionesPorTipo(String tipo) {
        return habitaciones.stream()
                .filter(h -> h.getTipo().equals(tipo))
                .collect(Collectors.toList());
    }
    
    /**
     * Obtiene todas las habitaciones del inventario.
     * 
     * @return Lista de todas las habitaciones
     */
    public List<Habitacion> obtenerTodasLasHabitaciones() {
        return new ArrayList<>(habitaciones);
    }
}

