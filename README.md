# 🛒 Sistema de Ventas

Aplicación de escritorio desarrollada en **Java Swing** con conexión a **MySQL**, que permite gestionar un sistema de ventas básico: clientes, productos, categorías, usuarios y reportes en PDF.

---

## 🚀 Características
- Login con usuarios registrados (incluye un usuario por defecto).
- Gestión de **clientes, categorías, productos y ventas**.
- Generación de reportes en **PDF** con iText.
- Interfaz gráfica sencilla con **Java Swing**.
- Base de datos **MySQL** integrada.

---

## 🛠️ Requisitos previos
Para ejecutar el proyecto necesitas:
- **Java JDK 17** o superior  
- **MySQL Server** (ej. XAMPP, WAMP, o instalación nativa)  
- **Eclipse IDE** (o cualquier IDE de tu preferencia)  

---

## ⚙️ Instalación
1. Clona este repositorio:
   ```bash
   git clone https://github.com/tuusuario/sistema-ventas.git
   ````

## Importar BBDD

Dentro de la carpeta database esta el script para crear la bbdd con sus respectibas tablas

## Configurar la conexion

En src/conexion/Conexion.java configurar la conexion con la BBDD. 
```bash
Connection cn = DriverManager.getConnection(
    "jdbc:mysql://localhost/bd_sistema_ventas", "root", ""
); 
````
Aqui cambiar los parametros con la ruta , usuario y contraseña.
