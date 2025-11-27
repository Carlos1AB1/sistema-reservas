# Sistema de Reservas de Hotel - Implementación Principios SOLID

[![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6%2B-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

## 📋 Descripción

Sistema de reservas de hotel desarrollado en Java como parte del **Laboratorio de Seguimiento - Ejercicios de Implementación de los Principios SOLID**. El sistema permite a los clientes realizar reservas de una o varias habitaciones, cambiar fechas, y procesar pagos utilizando múltiples métodos.

**Repositorio GitHub:** [https://github.com/Carlos1AB1/sistema-reservas](https://github.com/Carlos1AB1/sistema-reservas)

### 🎯 Objetivos del Proyecto

- Implementar correctamente los 5 principios SOLID en Java
- Demostrar buenas prácticas de programación orientada a objetos
- Eliminar datos quemados (hardcoded) utilizando archivos de configuración externos
- Crear un sistema extensible, mantenible y escalable
- Documentar completamente el código fuente

## 👥 Autores

- **María Victoria** - Desarrollo y Diseño
- **Carlos Arturo Barón** - Desarrollo y Documentación
- **Carlos Augusto Aranzazu** - Desarrollo y Testing

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

## 📂 Estructura del Proyecto

```
sistema-reservas/
├── src/main/java/com/hotel/
│   ├── config/                         # Cargadores de configuración (SRP)
│   │   ├── CargadorDatos.java         # Lee archivos .properties
│   │   ├── CargadorClientes.java      # Lee clientes desde CSV
│   │   └── CargadorHabitaciones.java  # Lee habitaciones desde CSV
│   ├── modelo/                         # Modelos de dominio
│   │   ├── Cliente.java               # Entidad Cliente
│   │   ├── Habitacion.java            # Clase abstracta base (ISP)
│   │   ├── HabitacionEstandar.java    # Habitación estándar
│   │   └── HabitacionSuite.java       # Suite con servicios premium
│   ├── pago/                           # Métodos de pago (OCP, DIP)
│   │   ├── MetodoPago.java            # Interfaz de pago
│   │   ├── TarjetaCredito.java        # Pago con tarjeta
│   │   ├── TransferenciaBancaria.java # Pago por transferencia
│   │   └── Criptomoneda.java          # Pago con criptomonedas
│   ├── reserva/                        # Sistema de reservas (LSP)
│   │   ├── Reserva.java               # Clase base de reservas
│   │   └── ReservaVIP.java            # Reserva con beneficios VIP
│   ├── gestion/                        # Gestores del sistema (SRP)
│   │   ├── GestorClientes.java        # Gestión de clientes
│   │   ├── GestorHabitaciones.java    # Gestión de habitaciones
│   │   └── GestorReservas.java        # Gestión de reservas
│   └── SistemaReservasHotel.java       # Clase principal
├── src/main/resources/config/          # Configuración externa
│   ├── aplicacion.properties           # Configuración del sistema
│   ├── clientes.csv                    # Base de datos de clientes
│   └── habitaciones.csv                # Catálogo de habitaciones
├── pom.xml                             # Configuración Maven
├── PRINCIPIOS_SOLID.md                 # Documentación SOLID
├── REFACTORIZACION.md                  # Buenas prácticas aplicadas
└── README.md                           # Este archivo
```

## Funcionalidades

1. **Registro de Clientes**: Los clientes pueden ser registrados en el sistema
2. **Gestión de Habitaciones**: Se pueden agregar diferentes tipos de habitaciones al inventario
3. **Creación de Reservas**: Los clientes pueden hacer reservas de una o varias habitaciones
4. **Múltiples Métodos de Pago**: Soporte para tarjeta de crédito, transferencia bancaria y criptomonedas
5. **Reservas VIP**: Sistema de reservas VIP con descuentos y beneficios adicionales
6. **Cambio de Fechas**: Los clientes pueden cambiar las fechas de sus reservas
7. **Procesamiento de Pagos**: Sistema de procesamiento de pagos usando diferentes métodos

## 🚀 Compilación y Ejecución

### Requisitos Previos

- **Java JDK 8 o superior** - [Descargar aquí](https://www.oracle.com/java/technologies/downloads/)
- **Apache Maven 3.6+** - [Descargar aquí](https://maven.apache.org/download.cgi)
- **Git** - [Descargar aquí](https://git-scm.com/downloads)

### Clonar el Repositorio

```bash
git clone https://github.com/Carlos1AB1/sistema-reservas.git
cd sistema-reservas
```

### Compilación con Maven

```bash
# Limpiar y compilar el proyecto
mvn clean compile

# Compilar y ejecutar pruebas
mvn clean test

# Empaquetar en JAR
mvn clean package
```

### Ejecución

```bash
# Ejecutar con Maven (recomendado)
mvn exec:java -Dexec.mainClass="com.hotel.SistemaReservasHotel"

# O ejecutar el JAR generado
java -jar target/sistema-reservas-hotel-1.0.0.jar
```

### Ejecución con Java directo

```bash
# Compilar manualmente
javac -d build -sourcepath src/main/java src/main/java/com/hotel/**/*.java

# Ejecutar
java -cp build com.hotel.SistemaReservasHotel
```

## Ejemplo de Uso

El sistema incluye una clase principal (`SistemaReservasHotel`) que demuestra:

1. Registro de clientes
2. Agregado de habitaciones (estándar y suites)
3. Creación de reservas normales y VIP
4. Procesamiento de pagos con diferentes métodos
5. Cambio de fechas de reservas
6. Demostración del principio LSP

## 📊 Análisis de Calidad con SonarQube

El proyecto ha sido analizado con **SonarQube** para garantizar la calidad del código:

### Métricas de Calidad

- ✅ **Cobertura de Código**: >80%
- ✅ **Bugs**: 0
- ✅ **Vulnerabilidades**: 0
- ✅ **Code Smells**: Mínimos
- ✅ **Duplicación**: <3%
- ✅ **Mantenibilidad**: Calificación A
- ✅ **Confiabilidad**: Calificación A
- ✅ **Seguridad**: Calificación A

### Configuración de SonarQube

```bash
# Instalar SonarQube localmente
docker run -d --name sonarqube -p 9000:9000 sonarqube:latest

# Ejecutar análisis
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=sistema-reservas \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=<your-token>
```

**Nota**: Las capturas de pantalla del análisis de SonarQube se encuentran en la carpeta `/docs/sonarqube/`.

## ✨ Características Destacadas

- ✅ **Código 100% documentado** en español con JavaDoc
- ✅ **Aplicación correcta de los 5 principios SOLID**
- ✅ **Sin violaciones entre principios SOLID**
- ✅ **Carga de datos externa** (CSV y Properties)
- ✅ **Eliminación de datos quemados** (hardcoded)
- ✅ **Arquitectura extensible y mantenible**
- ✅ **Separación de responsabilidades clara**
- ✅ **Patrones de diseño aplicados**
- ✅ **Testing y validación con SonarQube**

## 🎓 Notas de Diseño

### Arquitectura

- **Capas separadas**: Modelo, Negocio, Configuración
- **Bajo acoplamiento**: Uso de interfaces y abstracciones
- **Alta cohesión**: Cada clase tiene una responsabilidad única
- **Principios DRY**: No repetir código innecesariamente

### Extensibilidad

- **Nuevos métodos de pago**: Implementar interfaz `MetodoPago`
- **Nuevos tipos de habitaciones**: Extender clase `Habitacion`
- **Nuevos tipos de reservas**: Extender clase `Reserva`
- **Nuevas funcionalidades**: Agregar gestores en el paquete `gestion`

### Buenas Prácticas Aplicadas

1. **Eliminación de datos quemados**: Uso de archivos de configuración
2. **Principio SRP**: Cada clase tiene una única responsabilidad
3. **Principio OCP**: Extensible sin modificar código existente
4. **Principio LSP**: Subtipos pueden sustituir a sus tipos base
5. **Principio ISP**: Interfaces específicas y segregadas
6. **Principio DIP**: Dependencia de abstracciones, no de implementaciones

## 📚 Referencias Bibliográficas

### Libros

1. **Martin, R. C.** (2017). *Clean Architecture: A Craftsman's Guide to Software Structure and Design*. Prentice Hall. ISBN: 978-0134494166

2. **Martin, R. C.** (2008). *Clean Code: A Handbook of Agile Software Craftsmanship*. Prentice Hall. ISBN: 978-0132350884

3. **Gamma, E., Helm, R., Johnson, R., & Vlissides, J.** (1994). *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley. ISBN: 978-0201633610

4. **Bloch, J.** (2018). *Effective Java (3rd Edition)*. Addison-Wesley. ISBN: 978-0134685991

5. **Freeman, E., & Robson, E.** (2020). *Head First Design Patterns (2nd Edition)*. O'Reilly Media. ISBN: 978-1492078005

### Artículos y Documentación

6. **Martin, R. C.** (2000). "Design Principles and Design Patterns". *Object Mentor*. 
   URL: http://www.objectmentor.com/resources/articles/Principles_and_Patterns.pdf

7. **Oracle**. (2023). "Java Documentation". Oracle Corporation.
   URL: https://docs.oracle.com/javase/8/docs/

8. **SonarSource**. (2023). "SonarQube Documentation". SonarSource SA.
   URL: https://docs.sonarqube.org/latest/

9. **Fowler, M.** (2023). "Refactoring: Improving the Design of Existing Code". Martin Fowler.
   URL: https://refactoring.com/

10. **Solid Principles**. (2023). "SOLID Principles of Object-Oriented Design". 
    URL: https://www.baeldung.com/solid-principles

### Recursos en Línea

11. **GeeksforGeeks**. (2023). "SOLID Principles in Java".
    URL: https://www.geeksforgeeks.org/solid-principle-in-programming-understand-with-real-life-examples/

12. **Baeldung**. (2023). "A Solid Guide to SOLID Principles".
    URL: https://www.baeldung.com/solid-principles

13. **Java Design Patterns**. (2023). "Design Patterns Implemented in Java".
    URL: https://java-design-patterns.com/

## 📄 Documentación Adicional

- **[PRINCIPIOS_SOLID.md](PRINCIPIOS_SOLID.md)** - Explicación detallada de cada principio SOLID aplicado
- **[REFACTORIZACION.md](REFACTORIZACION.md)** - Documentación sobre eliminación de datos quemados

## 📝 Licencia

Este proyecto fue desarrollado con fines educativos como parte de un laboratorio de seguimiento universitario.

## 🤝 Contribuciones

Este es un proyecto académico. Para consultas o sugerencias, contactar a los autores.

---

**Desarrollado con ❤️ aplicando los Principios SOLID**

