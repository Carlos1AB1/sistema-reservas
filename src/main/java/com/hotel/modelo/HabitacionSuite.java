package com.hotel.modelo;

/**
 * Clase que representa una suite del hotel.
 * 
 * ISP: Implementa interfaces específicas para suites, incluyendo características
 * adicionales que solo las suites tienen.
 */
public class HabitacionSuite extends Habitacion {
    private boolean tieneJacuzzi;
    private boolean tieneBar;
    
    /**
     * Constructor de la clase HabitacionSuite.
     * 
     * @param numero Número identificador de la suite
     * @param precioPorNoche Precio por noche de la suite
     * @param capacidad Capacidad máxima de personas
     * @param tieneJacuzzi Indica si la suite tiene jacuzzi
     * @param tieneBar Indica si la suite tiene bar
     */
    public HabitacionSuite(String numero, double precioPorNoche, int capacidad, 
                          boolean tieneJacuzzi, boolean tieneBar) {
        super(numero, precioPorNoche, capacidad);
        this.tieneJacuzzi = tieneJacuzzi;
        this.tieneBar = tieneBar;
    }
    
    @Override
    public String getTipo() {
        return "Suite";
    }
    
    // Métodos específicos de Suite que no están en HabitacionEstandar
    public boolean tieneJacuzzi() {
        return tieneJacuzzi;
    }
    
    public boolean tieneBar() {
        return tieneBar;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
                ", tieneJacuzzi=" + tieneJacuzzi +
                ", tieneBar=" + tieneBar +
                '}';
    }
}

