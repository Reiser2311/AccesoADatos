import java.io.IOException;
import java.util.Scanner;

public class Main{
    protected static Scanner sc;
    protected static ListaContacto lista;
    protected static final String nombreArchivo = "contactos.obj";
    protected static int menu() {
        System.out.println("""
                -------------MENU-------------
                1.Añadir contacto
                2.Mostrar lista
                3.Buscar contacto
                0.Terminar
                """);

        while (!sc.hasNextInt()) {
            System.out.println("Elija una opcion");
            sc.next();
        }

        int opcion = sc.nextInt();
        sc.nextLine();
        return opcion;
    }

    public static Contacto leerContacto() {
        Contacto contacto = new Contacto();

        System.out.println("Nombre: ");
        contacto.setNombre(sc.nextLine());
        System.out.println("Apellidos: ");
        contacto.setApellido(sc.nextLine());
        System.out.println("Email: ");
        contacto.setEmail(sc.nextLine());
        System.out.println("Telefono: ");
        contacto.setTelefono(sc.nextLine());
        System.out.println("Descripcion: ");
        contacto.setDescripcion(sc.nextLine());

        return contacto;
    }

    public static void mostrarContacto (Contacto contacto) {
        System.out.print("Nombre: ");
        System.out.println(contacto.getNombre());
        System.out.print("Apellidos: ");
        System.out.println(contacto.getApellido());
        System.out.print("Email: ");
        System.out.println(contacto.getEmail());
        System.out.print("Telefono: ");
        System.out.println(contacto.getTelefono());
        System.out.print("Descripcion: ");
        System.out.println(contacto.getDescripcion());
    }

    public static Contacto buscarContacto() {
        String buscarTipo;
        do {
            System.out.println("Buscar por nombre (N) o telefono (F)?");
            buscarTipo = sc.next().toLowerCase();
        } while (!buscarTipo.equals("n") && !buscarTipo.equals("t"));

        System.out.println("Dato a buscar: ");
        String buscar = sc.next();
        for (Contacto c : lista) {
            String buscando = buscarTipo.equals("n") ? c.getNombre() + c.getApellido() : c.getTelefono();
            if (buscando.contains(buscar)) {
                return c;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        lista = new ListaContacto();
        sc = new Scanner(System.in);

        try {
            lista.cargar(nombreArchivo);

            int opcion = menu();
            while (opcion != 0) {
                switch (opcion) {
                    case 1:
                        lista.add(leerContacto());
                        break;
                    case 2:
                        for (Contacto contacto : lista) {
                            mostrarContacto(contacto);
                        }
                        break;
                    case 3:
                        Contacto c = buscarContacto();
                        if (c != null) {
                            mostrarContacto(c);
                        } else {
                            System.out.println("Contacto no encontrado");
                        }
                }
                opcion = menu();
            }

            lista.guardar(nombreArchivo);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }

    }
}