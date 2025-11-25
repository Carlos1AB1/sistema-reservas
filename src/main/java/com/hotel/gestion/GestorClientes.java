package com.hotel.gestion;

import com.hotel.modelo.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase responsable de gestionar los clientes del hotel.
 * 
 * SRP: Esta clase tiene una única responsabilidad: gestionar el registro
 * y búsqueda de clientes. No se encarga de reservas ni habitaciones.
 */
public class GestorClientes {
    private List<Cliente> clientes;
    
    /**
     * Constructor de la clase GestorClientes.
     */
    public GestorClientes() {
        this.clientes = new ArrayList<>();
    }
    
    /**
     * Registra un nuevo cliente en el sistema.
     * 
     * @param cliente Cliente a registrar
     * @return true si el cliente fue registrado exitosamente, false en caso contrario
     */
    public boolean registrarCliente(Cliente cliente) {
        if (cliente == null) {
            return false;
        }
        
        // Verificar que no exista un cliente con el mismo ID
        boolean existe = clientes.stream()
                .anyMatch(c -> c.getId().equals(cliente.getId()));
        
        if (existe) {
            System.out.println("Ya existe un cliente con el ID " + cliente.getId());
            return false;
        }
        
        clientes.add(cliente);
        System.out.println("Cliente registrado exitosamente: " + cliente.getNombre());
        return true;
    }
    
    /**
     * Busca un cliente por su ID.
     * 
     * @param id ID del cliente a buscar
     * @return El cliente encontrado o null si no existe
     */
    public Cliente buscarCliente(String id) {
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Busca un cliente por su email.
     * 
     * @param email Email del cliente a buscar
     * @return El cliente encontrado o null si no existe
     */
    public Cliente buscarClientePorEmail(String email) {
        return clientes.stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Obtiene todos los clientes registrados.
     * 
     * @return Lista de todos los clientes
     */
    public List<Cliente> obtenerTodosLosClientes() {
        return new ArrayList<>(clientes);
    }
    
    /**
     * Obtiene el número total de clientes registrados.
     * 
     * @return Número total de clientes
     */
    public int obtenerNumeroTotalClientes() {
        return clientes.size();
    }
}

