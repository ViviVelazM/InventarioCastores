package model;

import config.Conexion;
import java.util.ArrayList;
import java.sql.*;

public class InventarioDAO {
    private Connection conexion;
    
    public InventarioDAO(){
        this.conexion = Conexion.getConnection();
    }
    
    public ArrayList<Inventario> mostrarInventario(){
        PreparedStatement query;
        ResultSet rs;
        
        ArrayList<Inventario> lista = new ArrayList<>();
        
        try{
            query = conexion.prepareStatement("SELECT * FROM productos");
            rs = query.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("Id_Producto");
                String nombre = rs.getString("Nombre");
                int cantidad = rs.getInt("Cantidad");
                int estatus = rs.getInt("Estatus");
                
                Inventario producto = new Inventario(id, nombre, cantidad, estatus);
                lista.add(producto);
            }
            return lista;
        }catch(SQLException e){
            System.out.println(e.toString());
            return null;
        }
    }
    
    public ArrayList<Inventario> mostrarEstatus(int _estatus){
        PreparedStatement query;
        ResultSet rs;
        
        ArrayList<Inventario> lista = new ArrayList<>();
        
        try{
            query = conexion.prepareStatement("SELECT * FROM productos WHERE Estatus = ?");
            query.setInt(1, _estatus);
            rs = query.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("Id_Producto");
                String nombre = rs.getString("Nombre");
                int cantidad = rs.getInt("Cantidad");
                int estatus = rs.getInt("Estatus");
                
                Inventario producto = new Inventario(id, nombre, cantidad, estatus);
                lista.add(producto);
            }
            return lista;
        }catch(SQLException e){
            System.out.println(e.toString());
            return null;
        }
    }
    
    public Inventario consultProducto(int _id){
        PreparedStatement query;
        ResultSet rs;
        Inventario producto = null;
        
        try{
            query = conexion.prepareStatement("SELECT * FROM productos WHERE Id_Producto = ?");
            query.setInt(1, _id);
            rs = query.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("Id_Producto");
                String nombre = rs.getString("Nombre");
                int cantidad = rs.getInt("Cantidad");
                int estatus = rs.getInt("Estatus");
                
                producto = new Inventario(id, nombre, cantidad, estatus);
            }
            return producto;
            
        }catch(SQLException e){
            System.out.println(e.toString());
            return null;
        }
    }
    
    public boolean insertProducto(Inventario producto){
        PreparedStatement query;
        
        String nombre = producto.getNombre();
        int cantidad = producto.getCantidad();
        int estatus = producto.getEstatus();
        
        try{
            query = conexion.prepareStatement("INSERT INTO productos(Nombre, Cantidad, Estatus) VALUES (?, ?, ?)");
            query.setString(1, nombre);
            query.setInt(2, cantidad);
            query.setInt(3, estatus);
            query.execute();
            return true;
            
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean updateProducto(Inventario producto){
        PreparedStatement query;
        
        int id = producto.getId();
        int cantidad = producto.getCantidad();
        int estatus = producto.getEstatus();
        
        try{
            query = conexion.prepareStatement("UPDATE productos SET Cantidad = ?, Estatus = ? WHERE Id_Producto = ?");
            query.setInt(1, cantidad);
            query.setInt(2, estatus);
            query.setInt(3, id);
            query.execute();
            return true;
            
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
}
