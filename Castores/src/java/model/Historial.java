package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Historial {
    private String producto, usuario;
    private int id_usuario, id_producto, movimiento, cantidad;
    private LocalDate fecha;
    private LocalTime hora;

    public Historial(int id_producto, String producto, String usuario, int movimiento, int cantidad, LocalDate fecha, LocalTime hora) {
        this.id_producto = id_producto;
        this.producto = producto;
        this.usuario = usuario;
        this.movimiento = movimiento;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Historial(int id_usuario, int id_producto, int movimiento, int cantidad, LocalDate fecha, LocalTime hora) {
        this.id_usuario = id_usuario;
        this.id_producto = id_producto;
        this.movimiento = movimiento;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    
    
    
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(int movimiento) {
        this.movimiento = movimiento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
