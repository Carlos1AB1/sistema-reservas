package com.hotel.modelo;

/**
 * Clase que representa una habitación estándar del hotel.
 * 
 * ISP: Implementa solo las interfaces necesarias para habitaciones estándar,
 * sin forzar la implementación de métodos que no necesita.
 */
public class HabitacionEstandar extends Habitacion {
    
    /**
     * Constructor de la clase HabitacionEstandar.
     * 
     * @param numero Número identificador de la habitación
     * @param precioPorNoche Precio por noche de la habitación
     * @param capacidad Capacidad máxima de personas
     */
    public HabitacionEstandar(String numero, double precioPorNoche, int capacidad) {
        super(numero, precioPorNoche, capacidad);
    }
    
    @Override
    public String getTipo() {
        return "Estándar";
    }
}

