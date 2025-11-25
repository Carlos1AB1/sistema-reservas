package com.hotel;

import com.hotel.gestion.GestorClientes;
import com.hotel.gestion.GestorHabitaciones;
import com.hotel.gestion.GestorReservas;
import com.hotel.modelo.Cliente;
import com.hotel.modelo.Habitacion;
import com.hotel.modelo.HabitacionEstandar;
import com.hotel.modelo.HabitacionSuite;
import com.hotel.pago.Criptomoneda;
import com.hotel.pago.MetodoPago;
import com.hotel.pago.TarjetaCredito;
import com.hotel.pago.TransferenciaBancaria;
import com.hotel.reserva.Reserva;
import com.hotel.reserva.ReservaVIP;
import java.time.LocalDate;

/**
 * Clase principal que demuestra el funcionamiento del sistema de reservas de hotel.
 * 
 * Esta clase muestra cómo todos los principios SOLID trabajan juntos:
 * - SRP: Cada gestor tiene una responsabilidad única
 * - OCP: Se pueden agregar nuevos métodos de pago sin modificar código existente
 * - LSP: ReservaVIP puede reemplazar Reserva sin problemas
 * - ISP: Diferentes tipos de habitaciones implementan solo lo necesario
 * - DIP: Las reservas dependen de abstracciones (MetodoPago) no de implementaciones
 */
public class SistemaReservasHotel {
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE RESERVAS DE HOTEL ===\n");
        
        // Inicializar gestores (SRP: cada uno tiene una responsabilidad única)
        GestorClientes gestorClientes = new GestorClientes();
        GestorHabitaciones gestorHabitaciones = new GestorHabitaciones();
        GestorReservas gestorReservas = new GestorReservas();
        
        // Crear clientes
        System.out.println("--- Registrando Clientes ---");
        Cliente cliente1 = new Cliente("C001", "María Victoria", 
                                      "maria.victoria@email.com", "3001234567");
        Cliente cliente2 = new Cliente("C002", "Carlos Arturo Barón", 
                                      "carlos.baron@email.com", "3002345678");
        Cliente cliente3 = new Cliente("C003", "Carlos Augusto Aranzazu", 
                                      "carlos.aranzazu@email.com", "3003456789");
        
        gestorClientes.registrarCliente(cliente1);
        gestorClientes.registrarCliente(cliente2);
        gestorClientes.registrarCliente(cliente3);
        System.out.println();
        
        // Crear habitaciones (ISP: diferentes tipos de habitaciones)
        System.out.println("--- Agregando Habitaciones ---");
        Habitacion habitacion1 = new HabitacionEstandar("101", 50000, 2);
        Habitacion habitacion2 = new HabitacionEstandar("102", 50000, 2);
        Habitacion habitacion3 = new HabitacionSuite("201", 150000, 4, true, true);
        Habitacion habitacion4 = new HabitacionSuite("202", 150000, 4, true, false);
        
        gestorHabitaciones.agregarHabitacion(habitacion1);
        gestorHabitaciones.agregarHabitacion(habitacion2);
        gestorHabitaciones.agregarHabitacion(habitacion3);
        gestorHabitaciones.agregarHabitacion(habitacion4);
        System.out.println();
        
        // Crear métodos de pago (OCP: nuevos métodos de pago sin modificar código existente)
        System.out.println("--- Configurando Métodos de Pago ---");
        MetodoPago tarjetaCredito = new TarjetaCredito("1234567890123456", "María Victoria");
        MetodoPago transferencia = new TransferenciaBancaria("987654321", "Banco Nacional");
        MetodoPago criptomoneda = new Criptomoneda("BTC", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
        
        System.out.println("Métodos de pago disponibles:");
        System.out.println("- " + tarjetaCredito.getNombreMetodo());
        System.out.println("- " + transferencia.getNombreMetodo());
        System.out.println("- " + criptomoneda.getNombreMetodo());
        System.out.println();
        
        // Crear reservas normales
        System.out.println("--- Creando Reservas ---");
        LocalDate fechaInicio1 = LocalDate.now().plusDays(7);
        LocalDate fechaFin1 = LocalDate.now().plusDays(10);
        
        Reserva reserva1 = new Reserva("R001", cliente1, fechaInicio1, fechaFin1, tarjetaCredito);
        reserva1.agregarHabitacion(habitacion1);
        gestorReservas.crearReserva(reserva1);
        System.out.println("Precio total: $" + reserva1.getPrecioTotal());
        System.out.println();
        
        Reserva reserva2 = new Reserva("R002", cliente2, fechaInicio1, fechaFin1, transferencia);
        reserva2.agregarHabitacion(habitacion2);
        reserva2.agregarHabitacion(habitacion3); // Múltiples habitaciones
        gestorReservas.crearReserva(reserva2);
        System.out.println("Precio total: $" + reserva2.getPrecioTotal());
        System.out.println();
        
        // Crear reserva VIP (LSP: puede reemplazar Reserva sin problemas)
        System.out.println("--- Creando Reserva VIP ---");
        ReservaVIP reservaVIP = new ReservaVIP("R003", cliente3, fechaInicio1, fechaFin1, criptomoneda);
        reservaVIP.agregarHabitacion(habitacion4);
        gestorReservas.crearReserva(reservaVIP);
        System.out.println("Precio total (con descuento VIP): $" + reservaVIP.getPrecioTotal());
        System.out.println();
        
        // Procesar pagos (DIP: usando abstracciones)
        System.out.println("--- Procesando Pagos ---");
        System.out.println("Procesando pago de reserva R001:");
        reserva1.procesarPago();
        System.out.println();
        
        System.out.println("Procesando pago de reserva R002:");
        reserva2.procesarPago();
        System.out.println();
        
        System.out.println("Procesando pago de reserva VIP R003:");
        reservaVIP.procesarPago();
        System.out.println();
        
        // Cambiar fecha de reserva
        System.out.println("--- Cambiando Fecha de Reserva ---");
        LocalDate nuevaFechaInicio = LocalDate.now().plusDays(14);
        LocalDate nuevaFechaFin = LocalDate.now().plusDays(17);
        
        System.out.println("Cambiando fecha de reserva R001:");
        System.out.println("Fecha anterior: " + reserva1.getFechaInicio() + " a " + reserva1.getFechaFin());
        boolean cambioExitoso = reserva1.cambiarFecha(nuevaFechaInicio, nuevaFechaFin);
        if (cambioExitoso) {
            System.out.println("Fecha nueva: " + reserva1.getFechaInicio() + " a " + reserva1.getFechaFin());
            System.out.println("Nuevo precio total: $" + reserva1.getPrecioTotal());
        }
        System.out.println();
        
        // Demostrar LSP: usar ReservaVIP como Reserva
        System.out.println("--- Demostrando LSP (Liskov Substitution Principle) ---");
        System.out.println("Usando ReservaVIP como Reserva:");
        procesarReserva(reservaVIP); // Método que acepta Reserva pero funciona con ReservaVIP
        System.out.println();
        
        // Mostrar resumen
        System.out.println("--- Resumen del Sistema ---");
        System.out.println("Total de clientes: " + gestorClientes.obtenerNumeroTotalClientes());
        System.out.println("Total de habitaciones: " + gestorHabitaciones.obtenerTodasLasHabitaciones().size());
        System.out.println("Total de reservas: " + gestorReservas.obtenerNumeroTotalReservas());
        System.out.println("Habitaciones disponibles: " + gestorHabitaciones.obtenerHabitacionesDisponibles().size());
    }
    
    /**
     * Método que demuestra el principio LSP.
     * Acepta cualquier Reserva (incluyendo ReservaVIP) y funciona correctamente.
     * 
     * @param reserva Reserva a procesar (puede ser Reserva o ReservaVIP)
     */
    private static void procesarReserva(Reserva reserva) {
        System.out.println("Procesando reserva: " + reserva.getIdReserva());
        System.out.println("Cliente: " + reserva.getCliente().getNombre());
        System.out.println("Precio total: $" + reserva.getPrecioTotal());
        System.out.println("Método de pago: " + reserva.getMetodoPago().getNombreMetodo());
        
        // Si es ReservaVIP, mostrar información adicional
        if (reserva instanceof ReservaVIP) {
            ReservaVIP reservaVIP = (ReservaVIP) reserva;
            System.out.println("Tipo: Reserva VIP");
            System.out.println("Incluye desayuno: " + reservaVIP.incluyeDesayuno());
            System.out.println("Acceso a sala VIP: " + reservaVIP.tieneAccesoSalaVIP());
        } else {
            System.out.println("Tipo: Reserva Estándar");
        }
    }
}

