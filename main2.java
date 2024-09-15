import java.util.Scanner;
import java.util.Stack;

class Cancion {
    public String nombre;
    public int duracion;
    public String artista;
    public Cancion next;

    public Cancion(String nombre, int duracion, String artista) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.artista = artista;
        this.next = null;
    }
    String getNombre() {
        return nombre;
    }

    int getDuracion() {
        return duracion;
    }

    String getArtista() {
        return artista;
    }
}
class ColaDeCanciones {
    public Cancion inicio;
    public Cancion fin;
    public Stack<Cancion> historial;

    public ColaDeCanciones() {
        this.inicio = null;
        this.fin = null;
        this.historial = new Stack<>();
    }

    public void agregarCancion(String nombre, int duracion, String artista) {
        Cancion nuevaC = new Cancion(nombre, duracion, artista);
        if (fin == null) {
            inicio = nuevaC;
            fin = nuevaC;
        } else {
            fin.next = nuevaC;
            fin = nuevaC;
        }
    }
    public void eliminarCancion(String nombre, String artista) {
        Cancion aux = inicio;
        Cancion anterior = null;

        while (aux != null) {
            if (aux.getNombre().equals(nombre) && aux.getArtista().equals(artista)) {
                if (anterior == null) {
                    inicio = aux.next;
                    if (inicio == null) {
                        fin = null;
                    }
                } else {
                    anterior.next = aux.next;
                    if (anterior.next == null) {
                        fin = anterior;
                    }
                }
                return;
            }
            anterior = aux;
            aux = aux.next;
        }
    }

    public void reproducirCancion() {
        if (inicio == null) {
            System.out.println("No hay canciones a reproducir");
            fin = null;
        } else {
            Cancion aux = inicio;
            System.out.println("Reproduciendo: ");
            System.out.println("Nombre: " + aux.getNombre());
            System.out.println("Artista: " + aux.getArtista());
            historial.push(aux);
            inicio = inicio.next;
        }
    }

    public void estadistica() {
        Stack<Cancion> aux = (Stack<Cancion>) historial.clone();
        int tiempoEscuchado = 0;

        while (!aux.empty()) {
            Cancion temp = aux.pop();
            tiempoEscuchado += temp.getDuracion();
        }

        int horas = tiempoEscuchado / 60;
        int minutos = tiempoEscuchado % 60;

        System.out.println("Duración total de reproducción: " + horas + " horas, " + minutos + " minutos");
    }

    public void mostrarSiguientes() {
        if (inicio == null) {
            System.out.println("No hay canciones a mostrar");
        } else {
            Cancion aux = inicio;
            int contador = 0;
            System.out.println("-----------------------Mostrando prox 10 canciones a reproducir--------------------------------");
            while (aux != null && contador < 10) {
                System.out.println("Nombre: " + aux.getNombre() + ", Artista: " + aux.getArtista() + ", Duracion: " + aux.getDuracion());
                aux = aux.next;
                contador++;
            }
            System.out.println("------------------------------------------------------------------------------------------------");
        }
    }

    public void mostrarHistorial() {
        Stack<Cancion> aux = (Stack<Cancion>) historial.clone();
        int contador = 0;
        System.out.println("---------------------------Historial---------------------------");
        while (!aux.empty() && contador < 10) {
            Cancion temp = aux.pop();
            System.out.println("Nombre: " + temp.getNombre());
            System.out.println("Artista: " + temp.getArtista());
            System.out.println("Duracion: " + temp.getDuracion());
            System.out.println("--------------------------------------------------------------------------");
            contador++;
        }
    }

    public void buscarCancion(String nombre, String artista) {
        Stack<Cancion> aux = (Stack<Cancion>) historial.clone();
        Cancion actual = inicio;
        boolean encontrado = false;

        System.out.println("Buscando en la cola...........");

        while (actual != null) {
            if (actual.getNombre().equals(nombre) || actual.getArtista().equals(artista)) {
                System.out.println("Nombre: " + actual.getNombre());
                System.out.println("Artista: " + actual.getArtista());
                System.out.println("Duracion: " + actual.getDuracion());
                encontrado = true;
            }
            actual = actual.next;
        }

        if (!encontrado) {
            System.out.println("Cancion en la cola de reproduccion no encontrada");
        }

        encontrado = false; // se reutiliza para mostrar si hay cancion en el historial o no
        System.out.println("Buscando en el historial....................");

        while (!aux.empty()) {
            Cancion temp = aux.pop();
            if (temp.getNombre().equals(nombre) || temp.getArtista().equals(artista)) {
                System.out.println("Cancion encontrada en el historial");
                System.out.println("Nombre: " + temp.getNombre());
                System.out.println("Artista: " + temp.getArtista());
                System.out.println("Duracion: " + temp.getDuracion());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Cancion en el historial no encontrada");
        }
    }
}

class NodoBiblioteca {
    public ColaDeCanciones cola;
    public NodoBiblioteca siguiente;

    public NodoBiblioteca(ColaDeCanciones cola) {
        this.cola = cola;
        this.siguiente = null;
    }
}

class Bibliotecas {
    private NodoBiblioteca head;
    private NodoBiblioteca tail;

    public Bibliotecas() {
        this.head = null;
        this.tail = null;
    }

    public void agregarBiblioteca(ColaDeCanciones nuevaCola) {
        NodoBiblioteca nuevoNodo = new NodoBiblioteca(nuevaCola);

        if (head == null) {
            head = nuevoNodo;
            tail = nuevoNodo;
        } else {
            tail.siguiente = nuevoNodo;
            tail = nuevoNodo;
        }
    }
    public void eliminarBiblioteca(int i) {
        if (head == null) {
            System.out.println("No hay biblioteca");
            return;
        }

        if (i == 0) {
            head = head.siguiente;
            if (head == null) {
                tail = null;
            }
            System.out.println("Biblioteca 1 eliminada");
            return;
        }

        NodoBiblioteca actual = head;
        NodoBiblioteca anterior = null;
        int contador = 0;

        while (actual != null) {
            if (contador == i) {
                System.out.println("Biblioteca " + (i + 1) + " eliminada");
                if (anterior != null) {
                    anterior.siguiente = actual.siguiente; // se elimina el actual
                }
                if (actual == tail) { // caso eliminar el ultimo en la cola
                    tail = anterior;
                }
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
            contador++;
        }

        System.out.println("Biblioteca no encontrada");
    }

    public void reproducirBiblioteca() {
        if (head == null) {
            System.out.println("No hay biblioteca para reproducir.");
            return;
        }

        System.out.println("Reproduciendo biblioteca actual:");

        while (head.cola.inicio != null) { //
            head.cola.reproducirCancion();
        }

        head = head.siguiente;

        if (head == null) {
            System.out.println("No hay más bibliotecas para reproducir.");
        }
    }

    public void ColaDeReproduccionPersonalizada() {
        Scanner sc = new Scanner(System.in);
        ColaDeCanciones nuevaColaDeCanciones = new ColaDeCanciones();
        boolean key = true;

        while (key) {
            System.out.println("Ingrese una opción:");
            System.out.println("1. Agregar una canción");
            System.out.println("2. Salir");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Ingrese artista: ");
                    String artista = sc.nextLine();
                    System.out.println("Ingrese duracion: ");
                    int duracion = sc.nextInt();
                    sc.nextLine();
                    nuevaColaDeCanciones.agregarCancion(nombre, duracion, artista);
                    break;
                case 2:
                    key = false;
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
        System.out.println("Creando nueva biblioteca...");
        agregarBiblioteca(nuevaColaDeCanciones);
    }

    public ColaDeCanciones obtenerCola(int indice) {
        NodoBiblioteca actual = head;
        int contador = 0;
        while (actual != null) {
            if (contador == indice) {
                return actual.cola;
            }
            actual = actual.siguiente;
            contador++;
        }
        return null;
    }
}

public class main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bibliotecas bibliotecas = new Bibliotecas();
        boolean exit = false;

        while (!exit) {
            System.out.println("Menú:"); // menu de bibliotecas
            System.out.println("1. Crear nueva biblioteca personalizada");
            System.out.println("2. Seleccionar biblioteca");
            System.out.println("3. Eliminar una biblioteca");
            System.out.println("4. Reproducir siguiente biblioteca");
            System.out.println("5. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    bibliotecas.ColaDeReproduccionPersonalizada();
                    break;
                case 2:
                    System.out.println("Ingrese el índice de la biblioteca a seleccionar:");
                    int indiceSeleccionar = sc.nextInt();
                    sc.nextLine();
                    ColaDeCanciones colaSeleccionada = bibliotecas.obtenerCola(indiceSeleccionar);

                    if (colaSeleccionada != null) {
                        boolean salirBiblioteca = false;

                        while (!salirBiblioteca) {
                            System.out.println("Menú Biblioteca:");
                            System.out.println("1. Agregar canción a la cola");
                            System.out.println("2. Eliminar canción de la cola");
                            System.out.println("3. Reproducir canción de la cola");
                            System.out.println("4. Mostrar estadísticas de la cola");
                            System.out.println("5. Mostrar las siguientes canciones en la cola");
                            System.out.println("6. Mostrar historial de reproducción de la cola");
                            System.out.println("7. Buscar canción en la cola y en el historial");
                            System.out.println("8. Salir de la biblioteca");

                            int opcionBiblioteca = sc.nextInt();
                            sc.nextLine();

                            switch (opcionBiblioteca) { // menu de cola de canciones para la biblioteca elegida por el ususario
                                case 1:
                                    System.out.println("Ingrese nombre de la canción:");
                                    String nombre = sc.nextLine();
                                    System.out.println("Ingrese artista:");
                                    String artista = sc.nextLine();
                                    System.out.println("Ingrese duración en minutos:");
                                    int duracion = sc.nextInt();
                                    sc.nextLine();
                                    colaSeleccionada.agregarCancion(nombre, duracion, artista);
                                    break;
                                case 2:
                                    System.out.println("Ingrese nombre de la canción a eliminar:");
                                    String nombreEliminar = sc.nextLine();
                                    System.out.println("Ingrese artista de la canción a eliminar:");
                                    String artistaEliminar = sc.nextLine();
                                    colaSeleccionada.eliminarCancion(nombreEliminar, artistaEliminar);
                                    break;
                                case 3:
                                    colaSeleccionada.reproducirCancion();
                                    break;
                                case 4:
                                    colaSeleccionada.estadistica();
                                    break;
                                case 5:
                                    colaSeleccionada.mostrarSiguientes();
                                    break;
                                case 6:
                                    colaSeleccionada.mostrarHistorial();
                                    break;
                                case 7:
                                    System.out.println("Ingrese nombre de la canción a buscar:");
                                    String nombreBuscar = sc.nextLine();
                                    System.out.println("Ingrese artista de la canción a buscar:");
                                    String artistaBuscar = sc.nextLine();
                                    colaSeleccionada.buscarCancion(nombreBuscar, artistaBuscar);
                                    break;
                                case 8:
                                    salirBiblioteca = true;
                                    break;
                                default:
                                    System.out.println("Opción inválida");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Biblioteca no encontrada");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el índice de la biblioteca a eliminar:");
                    int indiceEliminar = sc.nextInt();
                    sc.nextLine();
                    bibliotecas.eliminarBiblioteca(indiceEliminar);
                    break;
                case 4:
                    bibliotecas.reproducirBiblioteca();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }
}