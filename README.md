# Documentación del Proyecto

## Estudiante:
- **Nombre:** Luis Eduardo Vásquez Villalta
- **Carné:** VV121782
- **Universidad Don Bosco**
- **Video de defensa:** https://youtu.be/C_VsXI5pjOs

---

## Estructura del Proyecto

Este proyecto está basado en **Kotlin** y tiene como objetivo **gestionar una aplicación de compras**. A continuación, se describe la estructura principal del código y los módulos clave:

### **1. Estructura de Carpetas y Archivos:**

- **src/**: Contiene todo el código fuente de la aplicación.
  - **com/miempresa/app/**: Paquete principal del proyecto.
    - **Main.kt**: Punto de entrada de la aplicación, contiene el flujo de ejecución y el menú principal.
    - **Model/**: Contiene las clases que representan los objetos del sistema.
      - **Item.kt**: Representa un producto en la tienda, con atributos como `id`, `nombre`, `precio` y `unidades`.
      - **ItemReservado.kt**: Contiene los detalles de los productos reservados por el usuario, incluyendo la cantidad reservada.
    - **Service/**: Contiene las clases que gestionan la lógica de negocio.
      - **ItemService.kt**: Clase que gestiona las operaciones relacionadas con los productos, como agregar, eliminar y consultar productos.
      - **CarritoService.kt**: Gestiona las operaciones relacionadas con el carrito de compras (agregar productos al carrito, calcular el total, etc.).
    - **Utils/**: Funciones auxiliares para tareas comunes.
      - **ConsoleUtils.kt**: Funciones para interactuar con la consola, como limpiar la pantalla y mostrar mensajes.

---

## Cómo Ejecutar la Aplicación

Para ejecutar este proyecto en tu entorno local, sigue estos pasos:

### **1. Clonar el repositorio:**
Primero, clona el repositorio a tu máquina local utilizando Git. Abre la terminal y ejecuta el siguiente comando:

```bash
git clone https://github.com/luvasquez/ShoppingCarKotlin.git
```

### **2. Abrir en IntelliJ IDEA:**
1. Abre **IntelliJ IDEA**.
2. Ve a **File > Open** y selecciona la carpeta del proyecto que acabas de clonar.
3. IntelliJ debería reconocer el proyecto como un proyecto Kotlin automáticamente.

### **3. Ejecutar el Proyecto:**
1. Ve a **Main.kt** (ubicado en la carpeta `src/com/miempresa/app`).
2. Haz clic en el botón de ejecución (el ícono de un triángulo verde en la parte superior derecha) o usa la opción **Run > Run 'Main'**.
3. La aplicación debería iniciarse en la consola y mostrar el menú principal.

### **4. Interacción con la Consola:**
Cuando la aplicación se ejecute, el usuario podrá interactuar con un menú que le permitirá:
- **Ver listado de productos** disponibles.
- **Agregar productos al carrito** de compras.
- **Eliminar productos** del carrito.
- **Ver el carrito** con los productos seleccionados.
- **Pagar el carrito** (calculando el total de la compra).

---

## Requisitos Previos

Para ejecutar este proyecto, asegúrese de tener instalado lo siguiente:
- **Java
