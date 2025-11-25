package com.hotel.modelo;

/**
 * Clase base que representa una habitación del hotel.
 * 
 * SRP: Esta clase tiene una única responsabilidad: representar la información
 * básica de una habitación del hotel.
 */
public abstract class Habitacion {
    protected String numero;
    protected double precioPorNoche;
    protected int capacidad;
    protected boolean disponible;
    
    /**
     * Constructor de la clase Habitacion.
     * 
     * @param numero Número identificador de la habitación
     * @param precioPorNoche Precio por noche de la habitación
     * @param capacidad Capacidad máxima de personas
     */
    public Habitacion(String numero, double precioPorNoche, int capacidad) {
        this.numero = numero;
        this.precioPorNoche = precioPorNoche;
        this.capacidad = capacidad;
        this.disponible = true;
    }
    
    /**
     * Método abstracto para obtener el tipo de habitación.
     * Cada tipo de habitación debe implementar este método.
     * 
     * @return Tipo de habitación
     */
    public abstract String getTipo();
    
    /**
     * Calcula el precio total para un número determinado de noches.
     * 
     * @param numeroNoches Número de noches
     * @return Precio total
     */
    public double calcularPrecioTotal(int numeroNoches) {
        return precioPorNoche * numeroNoches;
    }
    
    // Getters y Setters
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public double getPrecioPorNoche() {
        return precioPorNoche;
    }
    
    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }
    
    public int getCapacidad() {
        return capacidad;
    }
    
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    public boolean isDisponible() {
        return disponible;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    @Override
    public String toString() {
        return "Habitacion{" +
                "numero='" + numero + '\'' +
                ", tipo='" + getTipo() + '\'' +
                ", precioPorNoche=" + precioPorNoche +
                ", capacidad=" + capacidad +
                ", disponible=" + disponible +
                '}';
    }
}

