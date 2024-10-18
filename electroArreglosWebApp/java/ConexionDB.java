import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
   private static final String URL = "jdbc:mysql://localhost:3306/electro_arreglos";
   private static final String USER = "root";
   private static final String PASS = "";

   public ConexionDB() {
   }

   public static Connection getConnection() {
      Connection var0 = null;

      try {
         var0 = DriverManager.getConnection("jdbc:mysql://localhost:3306/electro_arreglos", "root", "");
         System.out.println("Conexi\u00f3n exitosa a la base de datos.");
      } catch (SQLException var2) {
         var2.printStackTrace();
         System.out.println("Error al conectar a la base de datos.");
      }

      return var0;
   }
}