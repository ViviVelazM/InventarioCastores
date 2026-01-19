package model;

public class Usuario {
    
    private int id,  id_rol, estatus;
    private String nombre, correo, contra;

    public Usuario(int id, String nombre, String correo, String contra, int id_rol, int estatus) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contra = contra;
        this.id_rol = id_rol;
        this.estatus = estatus;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
    
    
}
