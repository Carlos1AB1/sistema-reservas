package com.hotel.pago;

/**
 * Implementación del método de pago con criptomonedas.
 * 
 * OCP: Esta es una nueva implementación que demuestra cómo se pueden agregar
 * nuevos métodos de pago sin modificar el código existente, cumpliendo con
 * el principio Open/Closed.
 */
public class Criptomoneda implements MetodoPago {
    private String tipoCriptomoneda; // BTC, ETH, etc.
    private String direccionWallet;
    private boolean disponible;
    
    /**
     * Constructor de la clase Criptomoneda.
     * 
     * @param tipoCriptomoneda Tipo de criptomoneda (BTC, ETH, etc.)
     * @param direccionWallet Dirección de la wallet
     */
    public Criptomoneda(String tipoCriptomoneda, String direccionWallet) {
        this.tipoCriptomoneda = tipoCriptomoneda;
        this.direccionWallet = direccionWallet;
        this.disponible = true;
    }
    
    @Override
    public boolean procesarPago(double monto) {
        if (!estaDisponible()) {
            System.out.println("Pago con criptomoneda no disponible");
            return false;
        }
        
        // Simulación del procesamiento del pago
        System.out.println("Procesando pago de $" + monto + " con " + tipoCriptomoneda);
        System.out.println("Wallet: " + direccionWallet);
        
        // En una implementación real, aquí se haría la conexión con el exchange
        // o procesador de criptomonedas
        return true;
    }
    
    @Override
    public String getNombreMetodo() {
        return "Criptomoneda (" + tipoCriptomoneda + ")";
    }
    
    @Override
    public boolean estaDisponible() {
        return disponible;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    public String getTipoCriptomoneda() {
        return tipoCriptomoneda;
    }
}

