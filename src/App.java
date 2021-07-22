import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class App {
    public static void main(String[] args) throws Exception {
        

        Connection connection;
        
        try {
            //Conexion a la Base de Datos HR.db
            connection = DriverManager.getConnection("jdbc:sqlite:hr.db");
            if(connection != null){
                System.out.println("Conexión exitosa");
            }

            //Creacion de la Sentencia para ejecutar las consultas
            Statement statement = connection.createStatement();

            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            // Creacion del conjunto de resultados (resultset) --> LA MATRIZ DONDE GUARDARA LOS RESULTADOS DEL while(rs.next())
            // en el guardo lo que ejecuta la consulta (executequery) sobre la sentencia (statement)
            // la sentencia es select * from employees = seleccione desde EMPLEADOS

            ResultSet rs = statement.executeQuery("select * from employees");

            // LLeno una matriz con los datos de empleados (emplyees) mientras existan datos
            
            while(rs.next()){                 
                // read the result set
                System.out.print(rs.getInt("employee_id"));
                System.out.print("\t" + rs.getString("first_name"));
                System.out.print(" " + rs.getString("last_name"));

                System.out.println();
            }
        }
        catch (Exception e){
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.out.println("Error en la conexion");
        }
    }           
}

       