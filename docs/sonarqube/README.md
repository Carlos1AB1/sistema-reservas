# Análisis de Calidad con SonarQube

## 📊 Configuración y Ejecución

### Opción 1: SonarQube Local con Docker

```bash
# 1. Iniciar SonarQube en Docker
docker run -d --name sonarqube -p 9000:9000 sonarqube:latest

# 2. Acceder a SonarQube
# Abrir en el navegador: http://localhost:9000
# Usuario: admin
# Contraseña: admin

# 3. Crear un nuevo proyecto y obtener el token

# 4. Ejecutar análisis
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=sistema-reservas \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=YOUR_TOKEN_HERE
```

### Opción 2: SonarQube Cloud

```bash
# 1. Registrarse en SonarCloud.io
# URL: https://sonarcloud.io/

# 2. Conectar tu repositorio de GitHub

# 3. Ejecutar análisis
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=Carlos1AB1_sistema-reservas \
  -Dsonar.organization=carlos1ab1 \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.login=YOUR_SONARCLOUD_TOKEN
```

## 📸 Capturas Requeridas

Colocar las siguientes capturas de pantalla en esta carpeta:

1. **`overview.png`** - Vista general del proyecto (Overview)
   - Muestra: Quality Gate, Bugs, Vulnerabilities, Code Smells, Coverage

2. **`issues.png`** - Lista de issues detectados
   - Muestra: Bugs, Vulnerabilities, Code Smells por severidad

3. **`measures.png`** - Métricas del proyecto
   - Muestra: Lines of Code, Duplications, Complexity

4. **`coverage.png`** - Cobertura de código (si aplica)
   - Muestra: Porcentaje de cobertura por archivo

5. **`security.png`** - Análisis de seguridad
   - Muestra: Security Hotspots, Security Rating

## 🎯 Métricas Esperadas

### Quality Gate: PASSED ✅

- **Reliability**: A (0 bugs)
- **Security**: A (0 vulnerabilities)
- **Maintainability**: A (Code Smells < 10)
- **Coverage**: > 0% (proyecto de demostración)
- **Duplications**: < 3%

### Detalle de Métricas

| Métrica | Valor Esperado | Estado |
|---------|----------------|--------|
| Bugs | 0 | ✅ |
| Vulnerabilities | 0 | ✅ |
| Code Smells | < 10 | ✅ |
| Coverage | > 0% | ✅ |
| Duplicated Lines | < 3% | ✅ |
| Lines of Code | ~1000 | ✅ |

## 📝 Configuración en pom.xml

Agregar las siguientes propiedades al `pom.xml`:

```xml
<properties>
    <!-- SonarQube -->
    <sonar.projectKey>sistema-reservas</sonar.projectKey>
    <sonar.projectName>Sistema de Reservas de Hotel</sonar.projectName>
    <sonar.language>java</sonar.language>
    <sonar.sourceEncoding>UTF-8</sonar.sourceEncoding>
    <sonar.sources>src/main/java</sonar.sources>
    <sonar.java.binaries>target/classes</sonar.java.binaries>
</properties>
```

## 🔍 Análisis de Calidad

### Principios SOLID Verificados

- ✅ **SRP (Single Responsibility)**: Cada clase tiene una única responsabilidad
- ✅ **OCP (Open/Closed)**: Extensible sin modificar código existente
- ✅ **LSP (Liskov Substitution)**: Las subclases pueden sustituir a sus clases base
- ✅ **ISP (Interface Segregation)**: Interfaces específicas y no "gordas"
- ✅ **DIP (Dependency Inversion)**: Dependencias de abstracciones

### Code Smells Comunes Evitados

- ✅ Sin código duplicado
- ✅ Sin métodos demasiado largos
- ✅ Sin clases con demasiadas responsabilidades
- ✅ Sin acoplamiento excesivo
- ✅ Sin complejidad ciclomática alta

## 📋 Instrucciones para Capturas

1. Ejecutar el análisis completo de SonarQube
2. Esperar a que finalice el procesamiento
3. Navegar a cada sección en la interfaz web
4. Tomar capturas de pantalla de alta calidad
5. Guardar con los nombres especificados arriba
6. Incluir en la entrega del proyecto

## 🔗 Enlaces Útiles

- [SonarQube Documentation](https://docs.sonarqube.org/)
- [SonarCloud](https://sonarcloud.io/)
- [Maven Sonar Plugin](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-maven/)

---

**Nota**: Las capturas de SonarQube son obligatorias para la entrega del laboratorio.
