package com.hotel.modelo;

/**
 * Clase que representa un cliente del hotel.
 * 
 * SRP: Esta clase tiene una única responsabilidad: representar la información
 * de un cliente del hotel.
 */
public class Cliente {
    private String id;
    private String nombre;
    private String email;
    private String telefono;
    
    /**
     * Constructor de la clase Cliente.
     * 
     * @param id Identificador único del cliente
     * @param nombre Nombre completo del cliente
     * @param email Correo electrónico del cliente
     * @param telefono Número de teléfono del cliente
     */
    public Cliente(String id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }
    
    // Getters y Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}

