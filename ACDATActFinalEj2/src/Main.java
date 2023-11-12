import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        File archivo = new File("jugadores.obj");
        ArrayList<Jugador> jugadores = new ArrayList<>();
        if (archivo.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("jugadores.obj"))) {
                jugadores = (ArrayList<Jugador>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            JAXBContext context = JAXBContext.newInstance(Jugadores.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            Jugadores j = new Jugadores();
            j.setJugadores(jugadores);
            marshaller.marshal(j, new File("jugadores.xml"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}