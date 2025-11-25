# Explicación Detallada de los Principios SOLID

Este documento explica en detalle cómo cada principio SOLID está implementado en el sistema de reservas de hotel.

## 1. SRP (Single Responsibility Principle) - Principio de Responsabilidad Única

**Definición**: Una clase debe tener una, y solo una, razón para cambiar.

### Implementación en el Sistema

#### ✅ Cliente.java
- **Responsabilidad única**: Representar la información de un cliente
- **Razón de cambio**: Solo si cambia la estructura de datos del cliente
- **No gestiona**: Reservas, habitaciones, pagos

#### ✅ Habitacion.java y sus subclases
- **Responsabilidad única**: Representar la información de una habitación
- **Razón de cambio**: Solo si cambia la estructura de datos de las habitaciones
- **No gestiona**: Reservas, clientes, pagos

#### ✅ GestorClientes.java
- **Responsabilidad única**: Gestionar el registro y búsqueda de clientes
- **Razón de cambio**: Solo si cambia la lógica de gestión de clientes
- **No gestiona**: Reservas, habitaciones

#### ✅ GestorHabitaciones.java
- **Responsabilidad única**: Gestionar el inventario de habitaciones
- **Razón de cambio**: Solo si cambia la lógica de gestión de habitaciones
- **No gestiona**: Reservas, clientes

#### ✅ GestorReservas.java
- **Responsabilidad única**: Gestionar el ciclo de vida de las reservas
- **Razón de cambio**: Solo si cambia la lógica de gestión de reservas
- **No gestiona**: Clientes directamente, habitaciones directamente

#### ✅ Reserva.java
- **Responsabilidad única**: Gestionar la información y estado de una reserva
- **Razón de cambio**: Solo si cambia la estructura o lógica de una reserva
- **No gestiona**: Múltiples reservas, inventario de habitaciones

**Beneficio**: Si necesitamos cambiar cómo se gestionan los clientes, solo modificamos `GestorClientes`. Si necesitamos cambiar cómo se gestionan las reservas, solo modificamos `GestorReservas`.

---

## 2. OCP (Open/Closed Principle) - Principio Abierto/Cerrado

**Definición**: Las entidades de software deben estar abiertas para extensión, pero cerradas para modificación.

### Implementación en el Sistema

#### ✅ Sistema de Pagos

**Interfaz `MetodoPago`**:
```java
public interface MetodoPago {
    boolean procesarPago(double monto);
    String getNombreMetodo();
    boolean estaDisponible();
}
```

**Implementaciones existentes**:
- `TarjetaCredito.java`
- `TransferenciaBancaria.java`

**Nueva implementación agregada sin modificar código existente**:
- `Criptomoneda.java` ✅

**Cómo funciona OCP aquí**:
1. La clase `Reserva` depende de la interfaz `MetodoPago`, no de implementaciones concretas
2. Para agregar un nuevo método de pago (ej: PayPal, efectivo), solo necesitamos:
   - Crear una nueva clase que implemente `MetodoPago`
   - **NO** modificar `Reserva`, `TarjetaCredito`, `TransferenciaBancaria`, ni ninguna otra clase existente

**Ejemplo de extensión futura**:
```java
// Nueva clase sin modificar código existente
public class PayPal implements MetodoPago {
    // Implementación...
}
```

**Beneficio**: El sistema es extensible sin riesgo de romper código existente.

---

## 3. LSP (Liskov Substitution Principle) - Principio de Sustitución de Liskov

**Definición**: Los objetos de una superclase deben poder ser reemplazados por objetos de sus subclases sin que el programa deje de funcionar.

### Implementación en el Sistema

#### ✅ ReservaVIP extiende Reserva

**Clase base `Reserva`**:
- Tiene métodos: `getPrecioTotal()`, `procesarPago()`, `cambiarFecha()`, etc.
- Define un contrato que debe cumplirse

**Clase derivada `ReservaVIP`**:
- Extiende `Reserva`
- Sobrescribe `getPrecioTotal()` aplicando un descuento del 15%
- Extiende `procesarPago()` agregando beneficios VIP
- **Mantiene el contrato**: El precio total sigue siendo un `double`, el pago sigue retornando `boolean`

**Demostración de LSP en `SistemaReservasHotel.java`**:
```java
private static void procesarReserva(Reserva reserva) {
    // Este método acepta Reserva pero funciona perfectamente con ReservaVIP
    // ReservaVIP puede reemplazar Reserva sin problemas
}
```

**Reglas cumplidas**:
1. ✅ Precondiciones no pueden ser más fuertes en la subclase
2. ✅ Postcondiciones no pueden ser más débiles en la subclase
3. ✅ Los invariantes de la clase base se mantienen
4. ✅ Los métodos sobrescritos mantienen el mismo contrato

**Beneficio**: Podemos usar `ReservaVIP` en cualquier lugar donde se espere una `Reserva`, sin romper el sistema.

---

## 4. ISP (Interface Segregation Principle) - Principio de Segregación de Interfaces

**Definición**: Los clientes no deben ser forzados a depender de interfaces que no usan.

### Implementación en el Sistema

#### ✅ Tipos de Habitaciones

**Clase abstracta `Habitacion`**:
- Define funcionalidad común a todas las habitaciones
- Métodos básicos: `getTipo()`, `calcularPrecioTotal()`, `isDisponible()`

**`HabitacionEstandar`**:
- Extiende `Habitacion`
- Implementa solo lo necesario para habitaciones estándar
- **No tiene**: Jacuzzi, bar, ni otras características de suites

**`HabitacionSuite`**:
- Extiende `Habitacion`
- Agrega características específicas: `tieneJacuzzi()`, `tieneBar()`
- **No fuerza** estas características en `HabitacionEstandar`

**Por qué cumple ISP**:
- Si tuviéramos una interfaz `IHabitacionLujo` con métodos `tieneJacuzzi()` y `tieneBar()`, solo `HabitacionSuite` la implementaría
- `HabitacionEstandar` no estaría forzada a implementar métodos que no necesita
- Cada tipo de habitación solo implementa lo que realmente necesita

**Beneficio**: Las clases no están sobrecargadas con métodos innecesarios, haciendo el código más limpio y mantenible.

---

## 5. DIP (Dependency Inversion Principle) - Principio de Inversión de Dependencias

**Definición**: Los módulos de alto nivel no deben depender de módulos de bajo nivel. Ambos deben depender de abstracciones.

### Implementación en el Sistema

#### ✅ Reserva depende de MetodoPago (abstracción)

**Clase de alto nivel `Reserva`**:
```java
public class Reserva {
    private MetodoPago metodoPago; // Depende de la abstracción, no de implementaciones
    
    public Reserva(..., MetodoPago metodoPago) {
        this.metodoPago = metodoPago; // Inyección de dependencia
    }
    
    public boolean procesarPago() {
        return metodoPago.procesarPago(precioTotal); // Usa la abstracción
    }
}
```

**Abstracción `MetodoPago`** (interfaz):
- Define el contrato que deben cumplir los métodos de pago

**Implementaciones concretas** (módulos de bajo nivel):
- `TarjetaCredito`
- `TransferenciaBancaria`
- `Criptomoneda`

**Cómo funciona DIP**:
1. `Reserva` (alto nivel) depende de `MetodoPago` (abstracción)
2. Las implementaciones concretas también dependen de `MetodoPago` (abstracción)
3. `Reserva` no conoce ni depende de `TarjetaCredito`, `Criptomoneda`, etc.
4. Podemos cambiar el método de pago sin modificar `Reserva`

**Beneficio**: 
- Desacoplamiento: `Reserva` no está acoplada a implementaciones concretas
- Flexibilidad: Fácil cambiar o agregar métodos de pago
- Testabilidad: Podemos crear mocks de `MetodoPago` para testing

---

## Verificación de que los Principios no se Rompen entre Sí

### ✅ SRP + OCP
- Cada clase tiene una responsabilidad única (SRP)
- Pero podemos extender funcionalidad sin modificar (OCP)
- **No hay conflicto**: Agregar nuevos métodos de pago no viola SRP porque cada método de pago tiene su propia responsabilidad

### ✅ SRP + LSP
- `ReservaVIP` tiene responsabilidad única: gestionar reservas VIP (SRP)
- Pero puede reemplazar `Reserva` (LSP)
- **No hay conflicto**: La responsabilidad única se mantiene en la jerarquía

### ✅ ISP + OCP
- Interfaces segregadas permiten implementar solo lo necesario (ISP)
- Pero podemos agregar nuevas implementaciones sin modificar (OCP)
- **No hay conflicto**: Las nuevas implementaciones solo implementan lo que necesitan

### ✅ DIP + OCP
- Dependemos de abstracciones (DIP)
- Podemos agregar nuevas implementaciones sin modificar (OCP)
- **No hay conflicto**: DIP facilita OCP al depender de abstracciones

### ✅ DIP + SRP
- Cada clase tiene responsabilidad única (SRP)
- Dependemos de abstracciones (DIP)
- **No hay conflicto**: Las abstracciones ayudan a mantener responsabilidades separadas

---

## Conclusión

Todos los principios SOLID están correctamente implementados y trabajan juntos de manera armoniosa:

1. **SRP** asegura que cada clase tenga una responsabilidad clara
2. **OCP** permite extender el sistema sin modificar código existente
3. **LSP** garantiza que las subclases puedan reemplazar a las clases base
4. **ISP** evita interfaces sobrecargadas con métodos innecesarios
5. **DIP** desacopla las clases de alto nivel de las de bajo nivel

El sistema es **extensible**, **mantenible**, **testeable** y **robusto**.

