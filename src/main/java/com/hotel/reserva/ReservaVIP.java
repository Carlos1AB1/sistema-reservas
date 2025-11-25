package com.hotel.reserva;

import com.hotel.modelo.Cliente;
import com.hotel.modelo.Habitacion;
import com.hotel.pago.MetodoPago;
import java.time.LocalDate;

/**
 * Clase que representa una reserva VIP del hotel.
 * 
 * LSP: Esta clase puede reemplazar a Reserva en cualquier contexto sin que
 * el sistema falle. Extiende el comportamiento de Reserva agregando beneficios
 * adicionales para clientes VIP, pero mantiene la compatibilidad total con
 * la clase base.
 */
public class ReservaVIP extends Reserva {
    private static final double DESCUENTO_VIP = 0.15; // 15% de descuento
    private boolean incluyeDesayuno;
    private boolean accesoSalaVIP;
    
    /**
     * Constructor de la clase ReservaVIP.
     * 
     * @param idReserva Identificador único de la reserva
     * @param cliente Cliente VIP que realiza la reserva
     * @param fechaInicio Fecha de inicio de la reserva
     * @param fechaFin Fecha de fin de la reserva
     * @param metodoPago Método de pago a utilizar
     */
    public ReservaVIP(String idReserva, Cliente cliente, LocalDate fechaInicio, 
                     LocalDate fechaFin, MetodoPago metodoPago) {
        super(idReserva, cliente, fechaInicio, fechaFin, metodoPago);
        this.incluyeDesayuno = true;
        this.accesoSalaVIP = true;
    }
    
    /**
     * Calcula el precio total con el descuento VIP aplicado.
     * 
     * LSP: Este método sobrescribe el comportamiento de la clase base pero
     * mantiene la compatibilidad. El precio total siempre será menor o igual
     * al precio base, cumpliendo con el principio de sustitución de Liskov.
     * 
     * @return Precio total con descuento VIP aplicado
     */
    @Override
    public double getPrecioTotal() {
        double precioBase = super.getPrecioTotal();
        return precioBase * (1 - DESCUENTO_VIP);
    }
    
    /**
     * Verifica si la reserva incluye desayuno.
     * 
     * @return true si incluye desayuno
     */
    public boolean incluyeDesayuno() {
        return incluyeDesayuno;
    }
    
    /**
     * Verifica si la reserva incluye acceso a la sala VIP.
     * 
     * @return true si incluye acceso a sala VIP
     */
    public boolean tieneAccesoSalaVIP() {
        return accesoSalaVIP;
    }
    
    /**
     * Procesa el pago de la reserva VIP.
     * 
     * LSP: Este método mantiene el mismo contrato que el método de la clase base,
     * pero puede agregar funcionalidad adicional sin romper el comportamiento esperado.
     * 
     * @return true si el pago fue exitoso
     */
    @Override
    public boolean procesarPago() {
        boolean exito = super.procesarPago();
        if (exito) {
            System.out.println("Beneficios VIP activados:");
            System.out.println("- Desayuno incluido");
            System.out.println("- Acceso a sala VIP");
            System.out.println("- Descuento del " + (DESCUENTO_VIP * 100) + "% aplicado");
        }
        return exito;
    }
    
    @Override
    public String toString() {
        return "ReservaVIP{" +
                "idReserva='" + getIdReserva() + '\'' +
                ", cliente=" + getCliente().getNombre() +
                ", numeroHabitaciones=" + getHabitaciones().size() +
                ", fechaInicio=" + getFechaInicio() +
                ", fechaFin=" + getFechaFin() +
                ", precioTotal=" + getPrecioTotal() +
                ", pagada=" + isPagada() +
                ", incluyeDesayuno=" + incluyeDesayuno +
                ", accesoSalaVIP=" + accesoSalaVIP +
                '}';
    }
}

