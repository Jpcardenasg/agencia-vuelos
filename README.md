# Proyecto Agencia de Vuelos Globales

## Descripción del Proyecto

Este proyecto es una aplicación desarrollada en Java para gestionar las operaciones de una agencia de vuelos globales. La aplicación está diseñada con una arquitectura hexagonal y utiliza slicing vertical para organizar las funcionalidades. La base de datos está alojada en Railway y utiliza MySQL. Además, incluye un sistema de inicio de sesión que redirige a los usuarios a diferentes menús según su rol (administrador, agente de ventas, técnico de mantenimiento).

## Tabla de Contenidos

- [Descripción del Proyecto](#descripción-del-proyecto)
- [Características](#características)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Arquitectura](#arquitectura)
- [Base de Datos](#base-de-datos)

## Características

- Gestión de aviones y modelos.
- Mantenimiento y revisiones de aeronaves.
- Gestión de tripulación.
- Gestión de rutas y escalas.
- Reservas de vuelos.
- Sistema de inicio de sesión con redirección basada en roles.

## Requisitos Previos

- Java 11 o superior.
- Maven 3.6.3 o superior.
- MySQL 8.0 o superior.
- Cuenta en Railway para el hosting de la base de datos.

## Instalación

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/Jpcardenasg/agencia-vuelos
   cd agencia-vuelos
   ```

### Inicio de Sesión

El sistema de inicio de sesión permite a los usuarios ingresar con su cédula y contraseña. Dependiendo del rol asignado al usuario, se le redirige a un menú específico. Los roles disponibles son:

- **Administrador:** Tiene acceso completo a todas las funcionalidades de la aplicación, incluyendo la gestión de usuarios, aviones, tripulación, rutas y reservas.
- **Agente de Ventas:** Puede gestionar reservas de vuelos y consultar información relacionada con los clientes y las rutas.
- **Técnico de Mantenimiento:** Tiene acceso a la gestión de mantenimiento y revisiones de las aeronaves.

### Menús por Rol

- **Menú Administrador:**
  - Gestión de Usuarios.
  - Gestión de Aviones.
  - Gestión de Tripulación.
  - Gestión de Rutas.
  - Gestión de Reservas.
- **Menú Agente de Ventas:**
  - Gestión de Reservas.
  - Consulta de Información de Clientes.
  - Consulta de Rutas Disponibles.
- **Menú Técnico de Mantenimiento:**
  - Gestión de Mantenimiento de Aeronaves.
  - Consulta de Historial de Revisiones.

## Arquitectura

### Arquitectura Hexagonal

El proyecto utiliza una arquitectura hexagonal, también conocida como arquitectura de puertos y adaptadores. Este diseño permite que los componentes de la aplicación sean altamente desacoplados y fáciles de mantener. Las principales capas son:

- **Núcleo (Core):** Contiene la lógica de negocio y las entidades principales.
- **Aplicación (Application):** Contiene los casos de uso y la lógica de aplicación.
- **Infraestructura (Infrastructure):** Contiene las implementaciones de los adaptadores necesarios para interactuar con el exterior, como bases de datos y servicios externos.

### Vertical Slicing

El slicing vertical se refiere a la división de la aplicación en features verticales, donde cada feature incluye todas las capas necesarias para funcionar de manera independiente. Esto facilita el desarrollo, pruebas y mantenimiento.

## Base de Datos

La base de datos MySQL utilizada en este proyecto está alojada en Railway. Los principales componentes gestionados en la base de datos incluyen:

- **Aviones:** Información sobre aviones, modelos y fabricantes.
- **Mantenimiento:** Historial de revisiones de aviones.
- **Tripulación:** Datos de empleados y sus roles.
- **Rutas y Escalas:** Información sobre trayectos y escalas.
- **Reservas y Clientes:** Detalles de reservas y clientes.