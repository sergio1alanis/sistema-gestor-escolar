# 📚 Sistema de Gestión Escolar

Proyecto de evaluación académica desarrollado con **Spring Boot** y **MySQL**.

## 🚀 Funcionalidad

Este sistema permite:

- Calcular el promedio por alumno y asignatura.
- Determinar la escala de competencias (AD, SA, EP, NA).
- Calcular promedios por asignatura.
- Evaluar indicadores de rendimiento: exámenes parciales y semestrales.
- Calcular montos a pagar por evaluaciones.
- Generar un resumen general por alumno.

## ⚙️ Tecnologías usadas

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Lombok
- Postman (para pruebas)

## 📦 Estructura del proyecto

src/main/java/com/sge/sistema_gestor_escolar
├── controller ← Endpoints REST (CalculoController)
├── model ← Entidades (Alumno, Asignatura, etc.)
├── repository ← Acceso a la base de datos
├── service ← Lógica de negocio (CalculoService / Impl)



## 🧪 Ejemplo de uso (Postman)

POST http://localhost:8080/api/calculos/todo


📌 Devuelve un resumen completo de cálculos por alumno.

## 🛠 Cómo ejecutar

1. Importar el proyecto en IntelliJ o Spring Tool Suite.
2. Configurar `application.properties` con tu conexión MySQL.
3. Ejecutar la clase principal: `SistemaGestorEscolarApplication`.
4. Probar los endpoints desde Postman.

## 📝 Autor

Desarrollado por **Sergio Alanís** como parte de un ejercicio de aprendizaje.

---

