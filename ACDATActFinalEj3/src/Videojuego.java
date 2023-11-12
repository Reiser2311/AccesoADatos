import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Videojuego {
    private String titulo;
    private String semilla;
    private String estado;
    private String palabrasClave;

    @XmlElement(name = "Título")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @XmlElement(name = "Semilla")
    public String getSemilla() {
        return semilla;
    }

    public void setSemilla(String semilla) {
        this.semilla = semilla;
    }

    @XmlElement(name = "Estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlElement(name = "Palabras_clave")
    public String getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }
}
