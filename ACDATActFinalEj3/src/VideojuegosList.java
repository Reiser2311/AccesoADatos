import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "list")
public class VideojuegosList {
    private List<Videojuego> items;

    @XmlElement(name = "item")
    public List<Videojuego> getItems() {
        return items;
    }

    public void setItems(List<Videojuego> items) {
        this.items = items;
    }
}
