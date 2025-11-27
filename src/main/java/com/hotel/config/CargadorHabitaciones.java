package com.hotel.config;

import com.hotel.modelo.Habitacion;
import com.hotel.modelo.HabitacionEstandar;
import com.hotel.modelo.HabitacionSuite;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase responsable de cargar habitaciones desde archivos CSV.
 * Implementa SRP: tiene una única responsabilidad - cargar datos de habitaciones.
 */
public class CargadorHabitaciones {
    
    private static final String ARCHIVO_HABITACIONES = "config/habitaciones.csv";
    
    /**
     * Carga la lista de habitaciones desde el archivo CSV.
     * 
     * @return Lista de habitaciones cargadas
     */
    public List<Habitacion> cargarHabitaciones() {
        List<Habitacion> habitaciones = new ArrayList<>();
        
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(ARCHIVO_HABITACIONES);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            
            if (is == null) {
                System.out.println("No se encontró " + ARCHIVO_HABITACIONES + ", usando habitaciones por defecto");
                return obtenerHabitacionesPorDefecto();
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
                    String numero = datos[0].trim();
                    String tipo = datos[1].trim();
                    double precio = Double.parseDouble(datos[2].trim());
                    int capacidad = Integer.parseInt(datos[3].trim());
                    
                    if ("suite".equalsIgnoreCase(tipo) && datos.length >= 6) {
                        boolean jacuzzi = Boolean.parseBoolean(datos[4].trim());
                        boolean vista = Boolean.parseBoolean(datos[5].trim());
                        habitaciones.add(new HabitacionSuite(numero, precio, capacidad, jacuzzi, vista));
                    } else {
                        habitaciones.add(new HabitacionEstandar(numero, precio, capacidad));
                    }
                }
            }
            
            System.out.println("Habitaciones cargadas desde " + ARCHIVO_HABITACIONES + ": " + habitaciones.size());
            
        } catch (IOException | NullPointerException | NumberFormatException e) {
            System.err.println("Error al cargar habitaciones: " + e.getMessage());
            return obtenerHabitacionesPorDefecto();
        }
        
        return habitaciones.isEmpty() ? obtenerHabitacionesPorDefecto() : habitaciones;
    }
    
    /**
     * Retorna una lista de habitaciones por defecto si no se puede cargar el archivo.
     * 
     * @return Lista de habitaciones por defecto
     */
    private List<Habitacion> obtenerHabitacionesPorDefecto() {
        List<Habitacion> habitaciones = new ArrayList<>();
        habitaciones.add(new HabitacionEstandar("101", 50000, 2));
        habitaciones.add(new HabitacionEstandar("102", 50000, 2));
        habitaciones.add(new HabitacionSuite("201", 150000, 4, true, true));
        habitaciones.add(new HabitacionSuite("202", 150000, 4, true, false));
        return habitaciones;
    }
}
