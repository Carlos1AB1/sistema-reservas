package com.hotel.reserva;

import com.hotel.modelo.Cliente;
import com.hotel.modelo.Habitacion;
import com.hotel.pago.MetodoPago;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una reserva de hotel.
 * 
 * SRP: Esta clase tiene una única responsabilidad: gestionar la información
 * y el estado de una reserva de hotel.
 * 
 * DIP: Depende de la abstracción MetodoPago en lugar de implementaciones concretas,
 * permitiendo cambiar el método de pago sin modificar esta clase.
 */
public class Reserva {
    private String idReserva;
    private Cliente cliente;
    private List<Habitacion> habitaciones;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private MetodoPago metodoPago;
    private double precioTotal;
    private boolean pagada;
    
    /**
     * Constructor de la clase Reserva.
     * 
     * @param idReserva Identificador único de la reserva
     * @param cliente Cliente que realiza la reserva
     * @param fechaInicio Fecha de inicio de la reserva
     * @param fechaFin Fecha de fin de la reserva
     * @param metodoPago Método de pago a utilizar (abstracción)
     */
    public Reserva(String idReserva, Cliente cliente, LocalDate fechaInicio, 
                   LocalDate fechaFin, MetodoPago metodoPago) {
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.habitaciones = new ArrayList<>();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.metodoPago = metodoPago;
        this.pagada = false;
        calcularPrecioTotal();
    }
    
    /**
     * Agrega una habitación a la reserva.
     * 
     * @param habitacion Habitación a agregar
     */
    public void agregarHabitacion(Habitacion habitacion) {
        if (habitacion != null && habitacion.isDisponible()) {
            habitaciones.add(habitacion);
            calcularPrecioTotal();
        }
    }
    
    /**
     * Calcula el precio total de la reserva basado en las habitaciones
     * y el número de noches.
     */
    private void calcularPrecioTotal() {
        if (habitaciones.isEmpty() || fechaInicio == null || fechaFin == null) {
            precioTotal = 0.0;
            return;
        }
        
        int numeroNoches = (int) java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        precioTotal = 0.0;
        
        for (Habitacion habitacion : habitaciones) {
            precioTotal += habitacion.calcularPrecioTotal(numeroNoches);
        }
    }
    
    /**
     * Cambia la fecha de la reserva.
     * 
     * @param nuevaFechaInicio Nueva fecha de inicio
     * @param nuevaFechaFin Nueva fecha de fin
     * @return true si el cambio fue exitoso, false en caso contrario
     */
    public boolean cambiarFecha(LocalDate nuevaFechaInicio, LocalDate nuevaFechaFin) {
        if (nuevaFechaInicio == null || nuevaFechaFin == null) {
            return false;
        }
        
        if (nuevaFechaInicio.isAfter(nuevaFechaFin)) {
            return false;
        }
        
        this.fechaInicio = nuevaFechaInicio;
        this.fechaFin = nuevaFechaFin;
        calcularPrecioTotal();
        return true;
    }
    
    /**
     * Procesa el pago de la reserva usando el método de pago configurado.
     * 
     * @return true si el pago fue exitoso, false en caso contrario
     */
    public boolean procesarPago() {
        if (pagada) {
            System.out.println("La reserva ya ha sido pagada");
            return false;
        }
        
        if (metodoPago == null) {
            System.out.println("No se ha configurado un método de pago");
            return false;
        }
        
        boolean exito = metodoPago.procesarPago(precioTotal);
        if (exito) {
            pagada = true;
            System.out.println("Pago procesado exitosamente");
        }
        
        return exito;
    }
    
    // Getters y Setters
    public String getIdReserva() {
        return idReserva;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public List<Habitacion> getHabitaciones() {
        return new ArrayList<>(habitaciones); // Retorna una copia para proteger la encapsulación
    }
    
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }
    
    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }
    
    public double getPrecioTotal() {
        return precioTotal;
    }
    
    public boolean isPagada() {
        return pagada;
    }
    
    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva='" + idReserva + '\'' +
                ", cliente=" + cliente.getNombre() +
                ", numeroHabitaciones=" + habitaciones.size() +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", precioTotal=" + precioTotal +
                ", pagada=" + pagada +
                '}';
    }
}

