package com.hotel.pago;

/**
 * Implementación del método de pago con tarjeta de crédito.
 * 
 * OCP: Esta es una implementación concreta que puede ser agregada sin modificar
 * el código existente del sistema de reservas.
 */
public class TarjetaCredito implements MetodoPago {
    private String numeroTarjeta;
    private String nombreTitular;
    private boolean disponible;
    
    /**
     * Constructor de la clase TarjetaCredito.
     * 
     * @param numeroTarjeta Número de la tarjeta de crédito
     * @param nombreTitular Nombre del titular de la tarjeta
     */
    public TarjetaCredito(String numeroTarjeta, String nombreTitular) {
        this.numeroTarjeta = numeroTarjeta;
        this.nombreTitular = nombreTitular;
        this.disponible = true;
    }
    
    @Override
    public boolean procesarPago(double monto) {
        if (!estaDisponible()) {
            System.out.println("Tarjeta de crédito no disponible");
            return false;
        }
        
        // Simulación del procesamiento del pago
        System.out.println("Procesando pago de $" + monto + " con tarjeta de crédito " + 
                          numeroTarjeta.substring(numeroTarjeta.length() - 4));
        System.out.println("Titular: " + nombreTitular);
        
        // En una implementación real, aquí se haría la conexión con el procesador de pagos
        return true;
    }
    
    @Override
    public String getNombreMetodo() {
        return "Tarjeta de Crédito";
    }
    
    @Override
    public boolean estaDisponible() {
        return disponible;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}

