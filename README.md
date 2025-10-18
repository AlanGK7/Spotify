# Spotify
# Gestor de Listas de Reproducción en Consola

Este proyecto es un simulador de un reproductor de música basado en consola, escrito en Java. Permite gestionar múltiples "bibliotecas" (listas de reproducción), y cada biblioteca gestiona su propia cola de canciones y un historial de reproducción.

El programa utiliza un menú interactivo para navegar entre la gestión de bibliotecas y la gestión de canciones dentro de una biblioteca seleccionada.

## Características Principales

El sistema se divide en dos niveles de gestión:

### 1. Gestión de Bibliotecas (Nivel Superior)

* **Crear Biblioteca Personalizada:** Permite al usuario crear una nueva lista de reproducción (playlist) y agregarle canciones inmediatamente.
* **Seleccionar Biblioteca:** Permite al usuario "entrar" a una biblioteca existente (por su índice) para gestionarla.
* **Eliminar Biblioteca:** Borra una biblioteca completa de la lista.
* **Reproducir Siguiente Biblioteca:** Reproduce todas las canciones de la biblioteca que está al frente de la cola y la elimina, pasando a la siguiente.

### 2. Gestión de Canciones (Nivel de Biblioteca)

Una vez que se selecciona una biblioteca, el usuario puede:

* **Agregar Canción:** Añade una nueva canción al final de la cola de reproducción.
* **Eliminar Canción:** Busca y elimina una canción específica de la cola (por nombre y artista).
* **Reproducir Canción:** Reproduce la canción que está al inicio de la cola. La canción se elimina de la cola y se añade al historial de reproducción.
* **Mostrar Estadísticas:** Calcula y muestra el tiempo total de reproducción (en horas y minutos) basándose en el historial.
* **Mostrar Siguientes Canciones:** Muestra las próximas 10 canciones en la cola.
* **Mostrar Historial:** Muestra las últimas 10 canciones reproducidas.
* **Buscar Canción:** Busca una canción por nombre o artista tanto en la cola de reproducción actual como en el historial.

## Estructura del Código y Estructuras de Datos

El proyecto está organizado en clases que implementan listas enlazadas para simular el comportamiento de colas y pilas.

* **`Cancion`**:
    * Es el **nodo** básico de la lista enlazada que representa la cola de canciones.
    * Almacena los datos de la canción (`nombre`, `duracion`, `artista`) y un puntero `next`.

* **`ColaDeCanciones`**:
    * Implementa una **Cola (Queue)** usando una lista enlazada simple (con punteros `inicio` y `fin`).
    * Representa una lista de reproducción individual. Las canciones se añaden por el final (`fin`) y se reproducen desde el (`inicio`).
    * Contiene un **`Stack<Cancion>`** (Pila) llamado `historial`. Cuando una canción se reproduce, se saca de la cola (FIFO) y se mete en la pila (LIFO), guardando el historial.

* **`Bibliotecas`** (junto con `NodoBiblioteca`):
    * Implementa *otra* **Cola (Queue)** usando una lista enlazada (con punteros `head` y `tail`).
    * En lugar de canciones, esta cola almacena objetos `ColaDeCanciones`.
    * Permite al sistema gestionar una "cola de listas de reproducción".

* **`main2`**:
    * Contiene el método `main` que actúa como el controlador del programa.
    * Maneja toda la lógica de los menús interactivos y la entrada del usuario.

## Cómo Compilar y Ejecutar

El código está contenido en un solo archivo:

1.  **Compilar:**
    ```bash
    javac main2.java
    ```

2.  **Ejecutar:**
    ```bash
    java main2
    ```
