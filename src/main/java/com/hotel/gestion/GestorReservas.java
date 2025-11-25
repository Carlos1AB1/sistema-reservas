package com.hotel.gestion;

import com.hotel.modelo.Cliente;
import com.hotel.modelo.Habitacion;
import com.hotel.reserva.Reserva;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase responsable de gestionar las reservas del hotel.
 * 
 * SRP: Esta clase tiene una única responsabilidad: gestionar el ciclo de vida
 * de las reservas (crear, buscar, cancelar, etc.). No se encarga de gestionar
 * clientes ni habitaciones directamente.
 */
public class GestorReservas {
    private List<Reserva> reservas;
    
    /**
     * Constructor de la clase GestorReservas.
     */
    public GestorReservas() {
        this.reservas = new ArrayList<>();
    }
    
    /**
     * Crea una nueva reserva y la agrega al sistema.
     * 
     * @param reserva Reserva a crear
     * @return true si la reserva fue creada exitosamente, false en caso contrario
     */
    public boolean crearReserva(Reserva reserva) {
        if (reserva == null) {
            return false;
        }
        
        // Verificar que las habitaciones estén disponibles
        for (Habitacion habitacion : reserva.getHabitaciones()) {
            if (!habitacion.isDisponible()) {
                System.out.println("La habitación " + habitacion.getNumero() + 
                                 " no está disponible");
                return false;
            }
        }
        
        // Marcar habitaciones como no disponibles
        for (Habitacion habitacion : reserva.getHabitaciones()) {
            habitacion.setDisponible(false);
        }
        
        reservas.add(reserva);
        System.out.println("Reserva creada exitosamente: " + reserva.getIdReserva());
        return true;
    }
    
    /**
     * Busca una reserva por su ID.
     * 
     * @param idReserva ID de la reserva a buscar
     * @return La reserva encontrada o null si no existe
     */
    public Reserva buscarReserva(String idReserva) {
        return reservas.stream()
                .filter(r -> r.getIdReserva().equals(idReserva))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Obtiene todas las reservas de un cliente.
     * 
     * @param cliente Cliente del cual se buscan las reservas
     * @return Lista de reservas del cliente
     */
    public List<Reserva> obtenerReservasPorCliente(Cliente cliente) {
        return reservas.stream()
                .filter(r -> r.getCliente().getId().equals(cliente.getId()))
                .collect(Collectors.toList());
    }
    
    /**
     * Cancela una reserva.
     * 
     * @param idReserva ID de la reserva a cancelar
     * @return true si la reserva fue cancelada exitosamente, false en caso contrario
     */
    public boolean cancelarReserva(String idReserva) {
        Reserva reserva = buscarReserva(idReserva);
        if (reserva == null) {
            System.out.println("Reserva no encontrada");
            return false;
        }
        
        // Liberar habitaciones
        for (Habitacion habitacion : reserva.getHabitaciones()) {
            habitacion.setDisponible(true);
        }
        
        reservas.remove(reserva);
        System.out.println("Reserva cancelada exitosamente: " + idReserva);
        return true;
    }
    
    /**
     * Obtiene todas las reservas del sistema.
     * 
     * @return Lista de todas las reservas
     */
    public List<Reserva> obtenerTodasLasReservas() {
        return new ArrayList<>(reservas);
    }
    
    /**
     * Obtiene el número total de reservas.
     * 
     * @return Número total de reservas
     */
    public int obtenerNumeroTotalReservas() {
        return reservas.size();
    }
}

