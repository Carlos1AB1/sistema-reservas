package com.hotel.config;

import com.hotel.modelo.Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase responsable de cargar clientes desde archivos CSV.
 * Implementa SRP: tiene una única responsabilidad - cargar datos de clientes.
 */
public class CargadorClientes {
    
    private static final String ARCHIVO_CLIENTES = "config/clientes.csv";
    
    /**
     * Carga la lista de clientes desde el archivo CSV.
     * 
     * @return Lista de clientes cargados
     */
    public List<Cliente> cargarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(ARCHIVO_CLIENTES);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            
            if (is == null) {
                System.out.println("No se encontró " + ARCHIVO_CLIENTES + ", usando clientes por defecto");
                return obtenerClientesPorDefecto();
            }
            
            String linea;
            boolean primeraLinea = true;
            
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false; // Saltar encabezado
                    continue;
                }
                
                String[] datos = linea.split(",");
                if (datos.length >= 4) {
                    String id = datos[0].trim();
                    String nombre = datos[1].trim();
                    String email = datos[2].trim();
                    String telefono = datos[3].trim();
                    
                    clientes.add(new Cliente(id, nombre, email, telefono));
                }
            }
            
            System.out.println("Clientes cargados desde " + ARCHIVO_CLIENTES + ": " + clientes.size());
            
        } catch (IOException | NullPointerException e) {
            System.err.println("Error al cargar clientes: " + e.getMessage());
            return obtenerClientesPorDefecto();
        }
        
        return clientes.isEmpty() ? obtenerClientesPorDefecto() : clientes;
    }
    
    /**
     * Retorna una lista de clientes por defecto si no se puede cargar el archivo.
     * 
     * @return Lista de clientes por defecto
     */
    private List<Cliente> obtenerClientesPorDefecto() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("C001", "María Victoria", "maria.victoria@email.com", "3001234567"));
        clientes.add(new Cliente("C002", "Carlos Arturo Barón", "carlos.baron@email.com", "3002345678"));
        clientes.add(new Cliente("C003", "Carlos Augusto Aranzazu", "carlos.aranzazu@email.com", "3003456789"));
        return clientes;
    }
}
