package model;

import config.Conexion;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class HistorialDAO{
    Connection conexion;
    
    public HistorialDAO(){
        conexion = Conexion.getConnection();
    }
    
    public ArrayList<Historial> mostrarHistorial(){
        CallableStatement query;
        ResultSet rs;
        
        ArrayList<Historial> lista = new ArrayList<>();
        
        try{
            query = conexion.prepareCall("CALL SP_HISTORIAL()");
            rs = query.executeQuery();
            
            while(rs.next()){
                int id_producto = rs.getInt("Id_Producto");
                String usuario = rs.getString("Usuario");
                String producto = rs.getString("Producto");
                int mov = rs.getInt("Movimiento");
                int cantidad = rs.getInt("Cantidad");
                LocalDate fecha = rs.getDate("Fecha").toLocalDate();
                LocalTime hora = rs.getTime("Hora").toLocalTime();
                
                Historial historial =  new Historial(id_producto, producto, usuario, mov, cantidad, fecha, hora);
                lista.add(historial);
            }
            
            return lista;
        }catch(SQLException e){
            System.out.println(e.toString());
            return null;
        }
    }
    
    public ArrayList<Historial> mostrarHistorialEst(int est){
        CallableStatement query;
        ResultSet rs;
        
        ArrayList<Historial> lista = new ArrayList<>();
        
        try{
            query = conexion.prepareCall("CALL SP_HISTORIAL_ESTATUS(?)");
            query.setInt(1, est);
            rs = query.executeQuery();
            
            while(rs.next()){
                int id_producto = rs.getInt("Id_Producto");
                String usuario = rs.getString("Usuario");
                String producto = rs.getString("Producto");
                int mov = rs.getInt("Movimiento");
                int cantidad = rs.getInt("Cantidad");
                LocalDate fecha = rs.getDate("Fecha").toLocalDate();
                LocalTime hora = rs.getTime("Hora").toLocalTime();
                
                Historial historial =  new Historial(id_producto, producto, usuario, mov, cantidad, fecha, hora);
                lista.add(historial);
            }
            
            return lista;
        }catch(SQLException e){
            System.out.println(e.toString());
            return null;
        }
    }
    
    public boolean insertHistorial(Historial historial){
        PreparedStatement query;
        
        int usuario = historial.getId_usuario();
        int producto = historial.getId_producto();
        int movimiento = historial.getMovimiento();
        int cantidad = historial.getCantidad();
        LocalDate fecha = historial.getFecha();
        LocalTime hora = historial.getHora();
        
        try{
            query = conexion.prepareStatement("INSERT INTO historial(Id_Usuario, Id_Producto, movimiento, cantidad, fecha, hora) VALUES (?, ?, ?, ?, ?, ?)");
            query.setInt(1, usuario);
            query.setInt(2, producto);
            query.setInt(3, movimiento);
            query.setInt(4, cantidad);
            query.setDate(5, java.sql.Date.valueOf(fecha));
            query.setTime(6, java.sql.Time.valueOf(hora));
            query.execute();
            return true;
            
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
}
