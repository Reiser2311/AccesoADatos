import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        System.out.println("""
                    --------MENU--------
                    1. Crear jugador
                    2. Mostrar lista
                    3. Buscar jugador
                    4. Salir
                    """);
        opcion = sc.nextInt();
        return opcion;
    }

    public static void crearJugador(ArrayList<Jugador> jugadores) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce la informacion del jugador\nNombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apodo: ");
        String apodo = sc.nextLine();
        System.out.print("Puesto: ");
        String puesto= sc.nextLine();
        System.out.print("Dorsal: ");
        String dorsal = sc.nextLine();
        System.out.print("Descripcion: ");
        String descripcion = sc.nextLine();

        Jugador jugador = new Jugador(nombre, apodo, puesto, dorsal, descripcion);
        jugadores.add(jugador);

    }

    public static void mostrarLista (ArrayList<Jugador> jugadores) {
        for (Jugador j : jugadores) {
            System.out.println(j.toString());
        }
    }

    public static void buscarJugador (ArrayList<Jugador> jugadores) {
        boolean encontrado = false;
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca los datos del jugador que desea buscar\nNombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apodo: ");
        String apodo = sc.nextLine();
        System.out.print("Dorsal: ");
        String dorsal = sc.nextLine();

        for (Jugador j : jugadores) {
            if (j.getNombre().equalsIgnoreCase(nombre) && j.getApodo().equalsIgnoreCase(apodo) && j.getDorsal().equalsIgnoreCase(dorsal)){
                System.out.println(j);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Jugador no encontrado");
        }
    }

    public static void main(String[] args) {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        int opcion;

        File archivo = new File("jugadores.txt");

        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                String nombre = "";
                String apodo = "";
                String puesto = "";
                String dorsal = "";
                String descripcion = "";

                while ((linea = reader.readLine()) != null) {

                    String[] info = linea.split(": ");
                    switch (info[0]) {
                        case "Nombre" -> nombre = info[1];
                        case "Apodo" -> apodo = info[1];
                        case "Puesto" -> puesto = info[1];
                        case "Dorsal" -> dorsal = info[1];
                        case "Descripcion" -> descripcion = info[1];
                        case "}" -> {Jugador jugador = new Jugador(nombre, apodo, puesto, dorsal, descripcion);
                                        jugadores.add(jugador);}
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        do {
            opcion = menu();
            switch (opcion) {
                case 1 -> crearJugador(jugadores);
                case 2 -> mostrarLista(jugadores);
                case 3 -> buscarJugador(jugadores);
            }
        } while (opcion != 4);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jugadores.txt"))) {
            for (Jugador j : jugadores) {
                writer.write(j.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("jugaodores.obj"))) {
            out.writeObject(jugadores);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}