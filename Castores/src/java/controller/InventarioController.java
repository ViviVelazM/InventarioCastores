package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import model.Historial;
import model.HistorialDAO;
import model.Inventario;
import model.InventarioDAO;

@WebServlet(name = "InventarioController", urlPatterns = {"/InventarioController"})
public class InventarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        if(session == null || session.getAttribute("rol") == null){
            response.sendRedirect("Login/login.jsp");
            return;
        }
        
        InventarioDAO inventario = new InventarioDAO();
        HistorialDAO historial =  new HistorialDAO();
        String accion;
        RequestDispatcher dp = null;
        accion = request.getParameter("accion");
        int rol = Integer.parseInt(session.getAttribute("rol").toString());
        int usuario = Integer.parseInt(session.getAttribute("id").toString());
        
        if(accion == null || accion.isEmpty() || accion.equals("inventario")){
            dp = request.getRequestDispatcher("Inventario/inventario.jsp");
            ArrayList<Inventario> lista = inventario.mostrarInventario();
            request.setAttribute("lista", lista);
            
        } else if(accion.equals("add")){
            String nombre = request.getParameter("nombre").toUpperCase();
            if(rol == 1){
                Inventario producto = new Inventario(0, nombre, 0, 1);
                inventario.insertProducto(producto);
            }
            
            dp = request.getRequestDispatcher("Inventario/inventario.jsp");
            ArrayList<Inventario> lista = inventario.mostrarInventario();
            request.setAttribute("lista", lista);
            
        } else if(accion.equals("save") && rol == 1){
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre").toUpperCase();
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            Inventario pro = inventario.consultProducto(id);
            int estatus = pro.getEstatus();
            int movimiento = 1;
            LocalDate fecha = LocalDate.now();
            LocalTime hora = LocalTime.now();
            int can_act = pro.getCantidad();
            
            if(cantidad >= 0){
                can_act += cantidad;
                Historial hist = new Historial(usuario, id, movimiento, cantidad, fecha, hora);
                historial.insertHistorial(hist);

                Inventario producto = new Inventario(id, nombre, can_act, estatus);
                inventario.updateProducto(producto);
                
                request.setAttribute("success", "Actualizado");
            } else {
                request.setAttribute("error", "La cantidad no puede ser negativa");
            }
            
            dp = request.getRequestDispatcher("Inventario/inventario.jsp");
            ArrayList<Inventario> lista = inventario.mostrarInventario();
            request.setAttribute("lista", lista);
            
        } else if(accion.equals("saveS") && rol == 2){
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre").toUpperCase();
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            Inventario pro = inventario.consultProducto(id);
            int estatus = pro.getEstatus();
            int movimiento = 0;
            LocalDate fecha = LocalDate.now();
            LocalTime hora = LocalTime.now();
            int can_act = pro.getCantidad();
            
            if (cantidad <= can_act && cantidad >= 0){
                can_act -= cantidad;
                Historial hist = new Historial(usuario, id, movimiento, cantidad, fecha, hora);
                historial.insertHistorial(hist);

                Inventario producto = new Inventario(id, nombre, can_act, estatus);
                inventario.updateProducto(producto);
                request.setAttribute("success", "Actualizado");
            } else {
                request.setAttribute("error", "No hay la suficiente cantidad a retirar");
            }
            
            dp = request.getRequestDispatcher("Inventario/salida.jsp");
            ArrayList<Inventario> lista = inventario.mostrarEstatus(1);
            request.setAttribute("lista", lista);
            
        } else if(accion.equals("cambiaEst")){
            int id = Integer.parseInt(request.getParameter("id"));
            Inventario pro = inventario.consultProducto(id);
            int estatus = pro.getEstatus();
            String nombre = pro.getNombre();
            int cantidad = pro.getCantidad();
            Inventario producto = null;
            
            if(estatus == 0){
                producto = new Inventario(id, nombre, cantidad, 1);
            } else if(estatus == 1){
                producto = new Inventario(id, nombre, cantidad, 0);
            }
            
            inventario.updateProducto(producto);
            
            dp = request.getRequestDispatcher("Inventario/inventario.jsp");
            ArrayList<Inventario> lista = inventario.mostrarInventario();
            request.setAttribute("lista", lista);
            
        } else if(accion.equals("editar")){
            int id = Integer.parseInt(request.getParameter("id"));
            Inventario producto = inventario.consultProducto(id);
            producto.setCantidad(0);
            request.setAttribute("producto", producto);
            
            dp = request.getRequestDispatcher("Inventario/inventario.jsp");
            ArrayList<Inventario> lista = inventario.mostrarInventario();
            request.setAttribute("lista", lista);
            
        } else if(accion.equals("editarS")){
            int id = Integer.parseInt(request.getParameter("id"));
            Inventario producto = inventario.consultProducto(id);
            producto.setCantidad(0);
            request.setAttribute("producto", producto);
            
            dp = request.getRequestDispatcher("Inventario/salida.jsp");
            ArrayList<Inventario> lista = inventario.mostrarEstatus(1);
            request.setAttribute("lista", lista);
            
        } else if(accion.equals("salida") && rol == 2){
            dp = request.getRequestDispatcher("Inventario/salida.jsp");
            ArrayList<Inventario> lista = inventario.mostrarEstatus(1);
            request.setAttribute("lista", lista);
            
        } else if(accion.equals("historial") && rol == 1){
            dp = request.getRequestDispatcher("Inventario/historial.jsp");
            ArrayList<Historial> lista = historial.mostrarHistorial();
            request.setAttribute("lista", lista);
            
        } else if(accion.equals("filtrar")){
            String estatus = request.getParameter("estatus");
            ArrayList<Historial> lista;
            
            if(estatus == null || estatus.equals("todo")){
                lista = historial.mostrarHistorial();
            } else {
                int estat = Integer.parseInt(estatus);
                lista = historial.mostrarHistorialEst(estat);
            }
            
            dp = request.getRequestDispatcher("Inventario/historial.jsp");
            request.setAttribute("lista", lista);
            
        } else{
            dp = request.getRequestDispatcher("Inventario/inventario.jsp");
            ArrayList<Inventario> lista = inventario.mostrarInventario();
            request.setAttribute("lista", lista);
        }
        dp.forward(request, response);
        
    }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
