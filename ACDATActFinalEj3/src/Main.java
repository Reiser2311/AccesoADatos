import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("videojuegos.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(VideojuegosList.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            VideojuegosList videojuegosList = (VideojuegosList) jaxbUnmarshaller.unmarshal(file);

            List<Videojuego> videojuegos = videojuegosList.getItems();

            for (Videojuego videojuego : videojuegos) {
                if ("Activa".equals(videojuego.getEstado())) {
                    System.out.println("Título: " + videojuego.getTitulo());
                    System.out.println("Semilla: " + videojuego.getSemilla());
                    System.out.println("Palabras clave: " + videojuego.getPalabrasClave());
                    System.out.println();
                }
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}