package com.hotel.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Clase responsable de cargar configuraciones desde archivos externos.
 * Implementa SRP: tiene una única responsabilidad - cargar datos de configuración.
 */
public class CargadorDatos {
    
    private Properties propiedades;
    private static final String ARCHIVO_CONFIG = "config/aplicacion.properties";
    
    public CargadorDatos() {
        this.propiedades = new Properties();
        cargarPropiedades();
    }
    
    /**
     * Carga las propiedades desde el archivo de configuración.
     */
    private void cargarPropiedades() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(ARCHIVO_CONFIG)) {
            if (input == null) {
                System.out.println("No se pudo encontrar " + ARCHIVO_CONFIG + ", usando valores por defecto");
                cargarValoresPorDefecto();
                return;
            }
            propiedades.load(input);
            System.out.println("Configuración cargada desde " + ARCHIVO_CONFIG);
        } catch (IOException ex) {
            System.err.println("Error al cargar configuración: " + ex.getMessage());
            cargarValoresPorDefecto();
        }
    }
    
    /**
     * Carga valores por defecto si no se encuentra el archivo de configuración.
     */
    private void cargarValoresPorDefecto() {
        propiedades.setProperty("hotel.nombre", "Hotel Grand Palace");
        propiedades.setProperty("habitacion.estandar.precio", "50000");
        propiedades.setProperty("habitacion.suite.precio", "150000");
        propiedades.setProperty("reserva.vip.descuento", "0.15");
        propiedades.setProperty("datos.archivo.clientes", "config/clientes.csv");
        propiedades.setProperty("datos.archivo.habitaciones", "config/habitaciones.csv");
    }
    
    /**
     * Obtiene el valor de una propiedad.
     * 
     * @param clave Clave de la propiedad
     * @return Valor de la propiedad o null si no existe
     */
    public String obtenerPropiedad(String clave) {
        return propiedades.getProperty(clave);
    }
    
    /**
     * Obtiene el valor de una propiedad como número entero.
     * 
     * @param clave Clave de la propiedad
     * @param valorDefecto Valor por defecto si no existe o hay error
     * @return Valor de la propiedad como entero
     */
    public int obtenerPropiedadInt(String clave, int valorDefecto) {
        try {
            String valor = propiedades.getProperty(clave);
            return valor != null ? Integer.parseInt(valor) : valorDefecto;
        } catch (NumberFormatException e) {
            return valorDefecto;
        }
    }
    
    /**
     * Obtiene el valor de una propiedad como número decimal.
     * 
     * @param clave Clave de la propiedad
     * @param valorDefecto Valor por defecto si no existe o hay error
     * @return Valor de la propiedad como double
     */
    public double obtenerPropiedadDouble(String clave, double valorDefecto) {
        try {
            String valor = propiedades.getProperty(clave);
            return valor != null ? Double.parseDouble(valor) : valorDefecto;
        } catch (NumberFormatException e) {
            return valorDefecto;
        }
    }
}
