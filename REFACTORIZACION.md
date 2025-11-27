# Refactorización: Eliminación de Datos Quemados

## ❌ Problema: Datos Quemados (Hard-coded Data)

### ¿Qué son los datos quemados?
Los datos quemados son valores constantes escritos directamente en el código fuente, lo que constituye una **mala práctica** de programación porque:

1. **Dificulta el mantenimiento**: Cualquier cambio requiere recompilar el código
2. **Reduce la flexibilidad**: No se pueden ajustar valores sin modificar el código
3. **Complica las pruebas**: Difícil probar con diferentes conjuntos de datos
4. **Viola el principio de separación de responsabilidades**: El código de lógica contiene datos de configuración
5. **Riesgo de seguridad**: Credenciales o datos sensibles pueden quedar expuestos en el código

### Ejemplo de código con datos quemados (ANTES):
```java
// ❌ MALA PRÁCTICA
Cliente cliente1 = new Cliente("C001", "María Victoria", 
                              "maria.victoria@email.com", "3001234567");
Cliente cliente2 = new Cliente("C002", "Carlos Arturo Barón", 
                              "carlos.baron@email.com", "3002345678");

Habitacion habitacion1 = new HabitacionEstandar("101", 50000, 2);
Habitacion habitacion2 = new HabitacionEstandar("102", 50000, 2);
```

## ✅ Solución: Carga de Datos Externos

### Implementación de buenas prácticas:

1. **Archivos de configuración** (`aplicacion.properties`)
   - Configuración general del sistema
   - Precios y parámetros ajustables
   - Rutas a archivos de datos

2. **Archivos CSV para datos**
   - `clientes.csv`: Información de clientes
   - `habitaciones.csv`: Catálogo de habitaciones

3. **Clases especializadas** (siguiendo SRP)
   - `CargadorDatos`: Lee configuración desde properties
   - `CargadorClientes`: Lee clientes desde CSV
   - `CargadorHabitaciones`: Lee habitaciones desde CSV

### Ejemplo de código refactorizado (DESPUÉS):
```java
// ✅ BUENA PRÁCTICA
CargadorClientes cargadorClientes = new CargadorClientes();
List<Cliente> clientes = cargadorClientes.cargarClientes();

CargadorHabitaciones cargadorHabitaciones = new CargadorHabitaciones();
List<Habitacion> habitaciones = cargadorHabitaciones.cargarHabitaciones();
```

## 📂 Estructura de Archivos

```
src/main/resources/config/
├── aplicacion.properties    # Configuración general
├── clientes.csv            # Datos de clientes
└── habitaciones.csv        # Datos de habitaciones
```

## 🎯 Beneficios de la Refactorización

1. ✅ **Mantenibilidad**: Cambios de datos sin recompilar
2. ✅ **Flexibilidad**: Fácil actualización de configuración
3. ✅ **Testabilidad**: Archivos diferentes para pruebas
4. ✅ **Escalabilidad**: Fácil migrar a base de datos
5. ✅ **Seguridad**: Datos sensibles fuera del código
6. ✅ **Principios SOLID**: Cumple con SRP (Single Responsibility Principle)

## 📝 Formato de Archivos CSV

### clientes.csv
```csv
id,nombre,email,telefono
C001,María Victoria,maria.victoria@email.com,3001234567
```

### habitaciones.csv
```csv
numero,tipo,precio,capacidad,jacuzzi,vistaMar
101,estandar,50000,2,false,false
201,suite,150000,4,true,true
```

## 🔄 Cómo Agregar Nuevos Datos

1. **Agregar un cliente**: Editar `clientes.csv`
2. **Agregar una habitación**: Editar `habitaciones.csv`
3. **Cambiar precios**: Editar `aplicacion.properties`
4. **Ejecutar el sistema**: Los cambios se cargan automáticamente

## 🚀 Ejecución

```bash
mvn clean compile exec:java -Dexec.mainClass=com.hotel.SistemaReservasHotel
```

El sistema ahora carga todos los datos desde archivos externos, eliminando por completo los datos quemados del código.
