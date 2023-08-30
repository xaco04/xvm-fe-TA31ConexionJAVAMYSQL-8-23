package Ejercicio02;

public class Ejercicio02App {

    public static void main(String[] args) {

        // Crear la conexión
        Conector conexion = new Conector("jdbc:mysql://localhost:33060?useTimezone=true&serverTimezone=UTC", "root", "root");

        // Crear la base de datos
        conexion.crearDB("empleados");

        // Crear la tabla departamentos
        String queryDepartamentos = "CREATE TABLE departamentos "
                + "(codigo int, nombre nvarchar(100), presupuesto int, PRIMARY KEY (codigo));";
        conexion.crearTabla("empleados", queryDepartamentos);

        // Crear la tabla empleados
        String queryEmpleados = "CREATE TABLE empleados "
                + "(dni varchar(8), nombre nvarchar(100), apellidos nvarchar(255), departamento int, "
                + "PRIMARY KEY (dni), FOREIGN KEY (departamento) REFERENCES departamentos (codigo) "
                + "ON DELETE cascade ON UPDATE cascade);";
        conexion.crearTabla("empleados", queryEmpleados);

        // Insertar registros en la tabla departamentos
        String queryRegistrosDepartamentos = "INSERT INTO departamentos (codigo, nombre, presupuesto) VALUES "
                + "(1, 'Ventas', 50000), (2, 'Producción', 80000), (3, 'Recursos Humanos', 30000);";
        conexion.insertarDatos("empleados", queryRegistrosDepartamentos);

        // Insertar registros en la tabla empleados
        String queryRegistrosEmpleados = "INSERT INTO empleados (dni, nombre, apellidos, departamento) VALUES "
                + "('12345678', 'Xavi', 'Vico', 1), ('87654321', 'Roberto', 'Carrera', 2), "
                + "('45678912', 'Joan', 'de la Huerta', 3);";
        conexion.insertarDatos("empleados", queryRegistrosEmpleados);

        // Obtener datos de la tabla departamentos
        System.out.println("Departamentos");
        conexion.obtenerDatos("empleados", "departamentos", new String[]{"codigo", "nombre", "presupuesto"});
        System.out.println();

        // Obtener datos de la tabla empleados
        System.out.println("Empleados");
        conexion.obtenerDatos("empleados", "empleados", new String[]{"dni", "nombre", "apellidos", "departamento"});
        System.out.println();

        // Modificar datos de un empleado
        String queryEditarEmpleado = "UPDATE empleados SET nombre = 'Roberto' WHERE dni = '87654321'";
        conexion.modificarRegistro("empleados", queryEditarEmpleado);

        // Eliminar un empleado
        String queryEliminarEmpleado = "DELETE FROM empleados WHERE dni = '45678912'";
        conexion.eliminarRegistro("empleados", queryEliminarEmpleado);

        // Mostrar datos actualizados de departamentos
        System.out.println("----------------------");
        System.out.println("Departamentos");
        conexion.obtenerDatos("empleados", "departamentos", new String[]{"codigo", "nombre", "presupuesto"});
        System.out.println();

        // Mostrar datos actualizados de empleados
        System.out.println("Empleados");
        conexion.obtenerDatos("empleados", "empleados", new String[]{"dni", "nombre", "apellidos", "departamento"});
        System.out.println();

        // Cerrar la conexión
        conexion.cerrarConexion();
    }
}
