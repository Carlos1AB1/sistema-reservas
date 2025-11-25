package com.hotel.pago;

/**
 * Implementación del método de pago mediante transferencia bancaria.
 * 
 * OCP: Nueva implementación de método de pago que puede ser agregada sin
 * modificar el código existente.
 */
public class TransferenciaBancaria implements MetodoPago {
    private String numeroCuenta;
    private String banco;
    private boolean disponible;
    
    /**
     * Constructor de la clase TransferenciaBancaria.
     * 
     * @param numeroCuenta Número de cuenta bancaria
     * @param banco Nombre del banco
     */
    public TransferenciaBancaria(String numeroCuenta, String banco) {
        this.numeroCuenta = numeroCuenta;
        this.banco = banco;
        this.disponible = true;
    }
    
    @Override
    public boolean procesarPago(double monto) {
        if (!estaDisponible()) {
            System.out.println("Transferencia bancaria no disponible");
            return false;
        }
        
        // Simulación del procesamiento del pago
        System.out.println("Procesando transferencia bancaria de $" + monto);
        System.out.println("Banco: " + banco);
        System.out.println("Cuenta: " + numeroCuenta);
        
        // En una implementación real, aquí se haría la conexión con el banco
        return true;
    }
    
    @Override
    public String getNombreMetodo() {
        return "Transferencia Bancaria";
    }
    
    @Override
    public boolean estaDisponible() {
        return disponible;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}

