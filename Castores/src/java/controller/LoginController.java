package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Usuario;
import model.UsuarioDAO;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String correo = request.getParameter("user");
        String contra = request.getParameter("pass");
        UsuarioDAO usuario = new UsuarioDAO();
        Usuario user = usuario.consultUsuario(correo, contra);
        
        if(user != null){
            int id = user.getId();
            String nombre = user.getNombre();
            int rol = user.getId_rol();
            int estatus = user.getEstatus();
            HttpSession session = request.getSession();
            session.setAttribute("id", id);
            session.setAttribute("nombre", nombre);
            session.setAttribute("rol", rol);
            session.setAttribute("estatus", estatus);
            
            response.sendRedirect("InventarioController");
        } else {
            request.setAttribute("error", "Credenciales invalidas");
            request.getRequestDispatcher("Login/login.jsp").forward(request, response);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
