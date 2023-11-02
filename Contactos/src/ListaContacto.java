import java.io.*;
import java.util.ArrayList;

public class ListaContacto extends ArrayList<Contacto> {

    public void cargar (String nombreArchivo) throws IOException, ClassNotFoundException {
        File archivo = new File(nombreArchivo);

        if (archivo.exists()) {
            ObjectInputStream objetoArchivo = new ObjectInputStream(new FileInputStream(archivo));
            int numObjetos = objetoArchivo.readInt();
            for (; numObjetos > 0; numObjetos--) {
                add((Contacto) objetoArchivo.readObject());
            }
            objetoArchivo.close();
        }
    }

    public void guardar (String nombreArchivo) throws IOException {
        ObjectOutputStream objetoArchivo = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
        objetoArchivo.writeInt(size());
        for (Contacto c : this)
            objetoArchivo.writeObject(c);
        objetoArchivo.close();
    }
}
