package model;

import config.Conexion;
import java.sql.*;

public class UsuarioDAO {
    Connection conexion;
    
    public UsuarioDAO(){
        Conexion con = new Conexion();
        conexion = con.getConnection();
    }
    
    public Usuario consultUsuario(String _correo, String _contra){
        PreparedStatement query;
        ResultSet rs;
        Usuario usuario = null;
        
        try{
            query = conexion.prepareStatement("SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?");
            query.setString(1, _correo);
            query.setString(2, _contra);
            rs = query.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("Id_Usuario");
                String nombre = rs.getString("Nombre");
                String correo = rs.getString("Correo");
                String contra = rs.getString("Contrasena");
                int rol = rs.getInt("Id_Rol");
                int estatus = rs.getInt("Estatus");
                
                usuario = new Usuario(id, nombre, correo, contra, rol, estatus);
            }
            return usuario;
            
        }catch(SQLException e){
            System.out.println(e.toString());
            return null;
        }
    }
}
