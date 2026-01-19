package config;

import java.sql.*;

public class Conexion {
    
    public static Connection getConnection() {
        String ruta  = "jdbc:mysql://localhost:3306/almacen";
        String usuario = "root";
        String contra = "12345";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(ruta, usuario, contra);
            System.out.println("Conexion Exitosa");
            return conexion;
        }catch(SQLException e){
            System.out.println(e.toString());
            return null;
        } catch (ClassNotFoundException ex) {
            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return null;
        }
    }
}
