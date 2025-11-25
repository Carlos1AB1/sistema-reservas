package com.hotel.pago;

/**
 * Interfaz que define el contrato para los métodos de pago.
 * 
 * DIP: Esta abstracción permite que las clases de alto nivel (como Reserva)
 * dependan de esta interfaz en lugar de implementaciones concretas.
 * 
 * OCP: Permite agregar nuevos métodos de pago sin modificar el código existente,
 * solo implementando esta interfaz.
 */
public interface MetodoPago {
    
    /**
     * Procesa un pago por el monto especificado.
     * 
     * @param monto Monto a pagar
     * @return true si el pago fue exitoso, false en caso contrario
     */
    boolean procesarPago(double monto);
    
    /**
     * Obtiene el nombre del método de pago.
     * 
     * @return Nombre del método de pago
     */
    String getNombreMetodo();
    
    /**
     * Valida si el método de pago está disponible.
     * 
     * @return true si está disponible, false en caso contrario
     */
    boolean estaDisponible();
}

