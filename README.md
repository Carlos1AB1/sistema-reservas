# Sistema de Reservas de Hotel

## Descripción

Sistema de reservas de hotel desarrollado en Java que permite a los clientes realizar reservas de una o varias habitaciones y cambiar las fechas de las reservas. El sistema está diseñado aplicando correctamente todos los principios SOLID.

## Autores

- María Victoria
- Carlos Arturo Barón
- Carlos Augusto Aranzazu

## Principios SOLID Implementados

### 1. SRP (Single Responsibility Principle) - Principio de Responsabilidad Única

Cada clase tiene una única responsabilidad:

- **`Cliente`**: Representa únicamente la información de un cliente
- **`Habitacion`**: Representa únicamente la información de una habitación
- **`GestorClientes`**: Gestiona únicamente el registro y búsqueda de clientes
- **`GestorHabitaciones`**: Gestiona únicamente el inventario de habitaciones
- **`GestorReservas`**: Gestiona únicamente el ciclo de vida de las reservas
- **`Reserva`**: Gestiona únicamente la información y estado de una reserva

### 2. OCP (Open/Closed Principle) - Principio Abierto/Cerrado

El sistema permite agregar nuevos métodos de pago sin modificar el código existente:

- **`MetodoPago`**: Interfaz que define el contrato para métodos de pago
- **`TarjetaCredito`**: Implementación de pago con tarjeta de crédito
- **`TransferenciaBancaria`**: Implementación de pago con transferencia bancaria
- **`Criptomoneda`**: Nueva implementación de pago con criptomonedas (BTC, ETH, etc.)

Para agregar un nuevo método de pago, solo se necesita implementar la interfaz `MetodoPago` sin modificar ninguna clase existente.

### 3. LSP (Liskov Substitution Principle) - Principio de Sustitución de Liskov

La clase `ReservaVIP` puede reemplazar a `Reserva` en cualquier contexto sin que el sistema falle:

- `ReservaVIP` extiende `Reserva` y mantiene la compatibilidad total
- Sobrescribe métodos manteniendo el contrato de la clase base
- Agrega funcionalidad adicional (descuentos VIP, beneficios) sin romper el comportamiento esperado
- Puede ser usada en cualquier lugar donde se espere una `Reserva`

### 4. ISP (Interface Segregation Principle) - Principio de Segregación de Interfaces

Diferentes tipos de habitaciones implementan solo lo necesario:

- **`Habitacion`**: Clase abstracta base con funcionalidad común
- **`HabitacionEstandar`**: Implementa solo lo necesario para habitaciones estándar
- **`HabitacionSuite`**: Implementa funcionalidad adicional específica de suites (jacuzzi, bar) sin forzar estas características en habitaciones estándar

Cada tipo de habitación solo implementa los métodos que necesita, evitando interfaces "gordas" con métodos innecesarios.

### 5. DIP (Dependency Inversion Principle) - Principio de Inversión de Dependencias

Las clases de alto nivel dependen de abstracciones, no de implementaciones concretas:

- **`Reserva`** depende de la interfaz `MetodoPago`, no de implementaciones concretas como `TarjetaCredito` o `Criptomoneda`
- Esto permite cambiar el método de pago sin modificar la clase `Reserva`
- Facilita el testing y la extensibilidad del sistema

## Estructura del Proyecto

```
src/main/java/com/hotel/
├── modelo/
│   ├── Cliente.java
│   ├── Habitacion.java
│   ├── HabitacionEstandar.java
│   └── HabitacionSuite.java
├── pago/
│   ├── MetodoPago.java (interfaz)
│   ├── TarjetaCredito.java
│   ├── TransferenciaBancaria.java
│   └── Criptomoneda.java
├── reserva/
│   ├── Reserva.java
│   └── ReservaVIP.java
├── gestion/
│   ├── GestorClientes.java
│   ├── GestorHabitaciones.java
│   └── GestorReservas.java
└── SistemaReservasHotel.java (clase principal)
```

## Funcionalidades

1. **Registro de Clientes**: Los clientes pueden ser registrados en el sistema
2. **Gestión de Habitaciones**: Se pueden agregar diferentes tipos de habitaciones al inventario
3. **Creación de Reservas**: Los clientes pueden hacer reservas de una o varias habitaciones
4. **Múltiples Métodos de Pago**: Soporte para tarjeta de crédito, transferencia bancaria y criptomonedas
5. **Reservas VIP**: Sistema de reservas VIP con descuentos y beneficios adicionales
6. **Cambio de Fechas**: Los clientes pueden cambiar las fechas de sus reservas
7. **Procesamiento de Pagos**: Sistema de procesamiento de pagos usando diferentes métodos

## Compilación y Ejecución

### Requisitos
- Java JDK 8 o superior

### Compilación

```bash
# Compilar todos los archivos Java
javac -d build src/main/java/com/hotel/**/*.java src/main/java/com/hotel/*.java

# O compilar desde el directorio src/main/java
cd src/main/java
javac com/hotel/**/*.java com/hotel/*.java
```

### Ejecución

```bash
# Ejecutar la clase principal
java -cp build com.hotel.SistemaReservasHotel

# O desde el directorio src/main/java
java com.hotel.SistemaReservasHotel
```

## Ejemplo de Uso

El sistema incluye una clase principal (`SistemaReservasHotel`) que demuestra:

1. Registro de clientes
2. Agregado de habitaciones (estándar y suites)
3. Creación de reservas normales y VIP
4. Procesamiento de pagos con diferentes métodos
5. Cambio de fechas de reservas
6. Demostración del principio LSP

## Características Destacadas

- ✅ Código completamente comentado en español
- ✅ Aplicación correcta de todos los principios SOLID
- ✅ Sin violaciones entre principios SOLID
- ✅ Extensible y mantenible
- ✅ Fácil de entender y documentar

## Notas de Diseño

- El sistema está diseñado para ser extensible: nuevos métodos de pago, tipos de habitaciones y tipos de reservas pueden agregarse fácilmente
- La separación de responsabilidades facilita el mantenimiento y testing
- El uso de abstracciones permite cambiar implementaciones sin afectar el código cliente
- Los comentarios en el código explican cómo cada principio SOLID se aplica en cada clase

