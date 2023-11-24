import java.sql.ResultSet;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MiBD db = new MiBD("empleados");
        db.openConnection();
        ResultSet rs;

/*        db.createTable("departamento", """
                  id SERIAL PRIMARY KEY,
                  nombre VARCHAR(100) NOT NULL,
                  presupuesto DOUBLE PRECISION NOT NULL,
                  gastos DOUBLE PRECISION NOT NULL
                """);

        db.createTable("empleado", """
                  id SERIAL PRIMARY KEY,
                  nif VARCHAR(9) NOT NULL UNIQUE,
                  nombre VARCHAR(100) NOT NULL,
                  apellido1 VARCHAR(100) NOT NULL,
                  apellido2 VARCHAR(100),
                  id_departamento INT,
                  FOREIGN KEY (id_departamento) REFERENCES departamento(id)
                """);

        db.updateData("""
                INSERT INTO departamento VALUES(1, 'Desarrollo', 120000, 6000);
                INSERT INTO departamento VALUES(2, 'Sistemas', 150000, 21000);
                INSERT INTO departamento VALUES(3, 'Recursos Humanos', 280000, 25000);
                INSERT INTO departamento VALUES(4, 'Contabilidad', 110000, 3000);
                INSERT INTO departamento VALUES(5, 'I+D', 375000, 380000);
                INSERT INTO departamento VALUES(6, 'Proyectos', 0, 0);
                INSERT INTO departamento VALUES(7, 'Publicidad', 0, 1000);

                INSERT INTO empleado VALUES(1, '32481596F', 'Aarón', 'Rivero', 'Gómez', 1);
                INSERT INTO empleado VALUES(2, 'Y5575632D', 'Adela', 'Salas', 'Díaz', 2);
                INSERT INTO empleado VALUES(3, 'R6970642B', 'Adolfo', 'Rubio', 'Flores', 3);
                INSERT INTO empleado VALUES(4, '77705545E', 'Adrián', 'Suárez', NULL, 4);
                INSERT INTO empleado VALUES(5, '17087203C', 'Marcos', 'Loyola', 'Méndez', 5);
                INSERT INTO empleado VALUES(6, '38382980M', 'María', 'Santana', 'Moreno', 1);
                INSERT INTO empleado VALUES(7, '80576669X', 'Pilar', 'Ruiz', NULL, 2);
                INSERT INTO empleado VALUES(8, '71651431Z', 'Pepe', 'Ruiz', 'Santana', 3);
                INSERT INTO empleado VALUES(9, '56399183D', 'Juan', 'Gómez', 'López', 2);
                INSERT INTO empleado VALUES(10, '46384486H', 'Diego','Flores', 'Salas', 5);
                INSERT INTO empleado VALUES(11, '67389283A', 'Marta','Herrera', 'Gil', 1);
                INSERT INTO empleado VALUES(12, '41234836R', 'Irene','Salas', 'Flores', NULL);
                INSERT INTO empleado VALUES(13, '82635162B', 'Juan Antonio','Sáez', 'Guerrero', NULL);
                """);*/

        //1.	Lista el primer apellido de todos los empleados.
        rs = db.executeQuery("SELECT nombre, apellido1 from empleado;");
        System.out.println("Nombre\tApellido 1");
        System.out.println("----------------------------------------------------");
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
        }
        System.out.println();

        //2.	Lista el primer apellido de los empleados eliminando los apellidos que estén repetidos.
        rs = db.executeQuery("select apellido1 from empleado group by apellido1");
        System.out.println("Apellido");
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        System.out.println();

        //3.	Devuelve una lista con el nombre y el gasto, de los 2 departamentos que tienen menor gasto.
        rs = db.executeQuery("select nombre, gastos from departamento order by gastos limit 2");
        System.out.println("Nombre\tGastos");
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
        }
        System.out.println();

        //4.	Devuelve una lista con el nombre de los departamentos y el presupuesto, de aquellos que tienen un presupuesto mayor o igual a 150000 euros.
        rs = db.executeQuery("select nombre, presupuesto from departamento where presupuesto >= 150000");
        System.out.println("Nombre\tPresupuesto");
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
        }
        System.out.println();

        //5.	Devuelve un listado con los empleados y los datos de los departamentos donde trabaja cada uno.
        rs = db.executeQuery("select e.*, d.* from empleado e join departamento d on e.id_departamento = d.id");
        while (rs.next()) {
            for (int i = 0; i < 10; i++) {
                System.out.print(rs.getString(i + 1) + "\t");
            }
            System.out.println();
        }
        System.out.println();

        //6.	Devuelve un listado con los empleados y los datos de los departamentos donde trabaja cada uno.
        // Ordena el resultado, en primer lugar por el nombre del departamento (en orden alfabético) y en segundo lugar por los apellidos y el nombre de los empleados.
        rs = db.executeQuery("select e.*, d.* from empleado e join departamento d on e.id_departamento = d.id order by d.nombre asc, e.apellido1, e.apellido2, e.nombre");
        while (rs.next()) {
            for (int i = 0; i < 10; i++) {
                System.out.print(rs.getString(i + 1) + "\t");
            }
            System.out.println();
        }
        System.out.println();

        //7.	Devuelve un listado con el identificador y el nombre del departamento, solamente de aquellos departamentos que tienen empleados.
        rs = db.executeQuery("select distinct d.id, d.nombre from departamento d join empleado e on d.id = e.id_departamento");
        System.out.println("Id\tNombre");
        while (rs.next()) {
            for (int i = 0; i < 2; i++) {
                System.out.print(rs.getString(i + 1) + "\t");
            }
            System.out.println();
        }
        System.out.println();

        //8.	Devuelve el nombre del departamento donde trabaja el empleado que tiene el nif 38382980M.
        rs = db.executeQuery("select d.nombre from departamento d join empleado e on d.id = e.id_departamento where e.nif = '38382980M'");
        while (rs.next()) {
            System.out.print(rs.getString(1));
            System.out.println();
        }
        System.out.println();

        //9.	Calcula la suma del presupuesto de todos los departamentos.
        rs = db.executeQuery("select sum(presupuesto) from departamento");
        while (rs.next()) {
            System.out.print(rs.getString(1));
            System.out.println();
        }
        System.out.println();

        db.closeConnection();
    }
}