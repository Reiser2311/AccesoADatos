import java.io.Serializable;

public class Jugador implements Serializable {
    String nombre;
    String apodo;
    String puesto;
    String dorsal;
    String descripcion;

    public Jugador(String nombre, String apodo, String puesto, String dorsal, String descripcion) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.puesto = puesto;
        this.dorsal = dorsal;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Jugador{\n" +
                "Nombre: " + nombre + '\n' +
                "Apodo: " + apodo + '\n' +
                "Puesto: " + puesto + '\n' +
                "Dorsal: " + dorsal + '\n' +
                "Descripcion: " + descripcion + '\n' +
                '}';
    }
}
