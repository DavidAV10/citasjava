package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uml.Usuario;
import model.dao.UsuarioDAO;

public class ServlUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServMedico</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServMedico at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } */
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Si la condición se cumple se registrará el doctor
        if (request.getParameter("reg_usuario") != null) {
            UsuarioDAO daoUsuario = new UsuarioDAO();
            int codigoNuevo = Integer.parseInt(request.getParameter("cod_usuario"));
            String nombres = new String(request.getParameter("nombres").getBytes("ISO-8859-1"), "UTF-8");
            String apellidos = new String(request.getParameter("ape_usuario").getBytes("ISO-8859-1"), "UTF-8");
            String correo = new String(request.getParameter("cor_usuario").getBytes("ISO-8859-1"), "UTF-8");
            String fechaNacimiento = new String(request.getParameter("fech_nac_usuario").getBytes("ISO-8859-1"), "UTF-8");
            String nombreUsuario = new String(request.getParameter("nom_usuario").getBytes("ISO-8859-1"), "UTF-8");
            String clave = new String(request.getParameter("clave_usuario").getBytes("ISO-8859-1"), "UTF-8");

            Usuario usuario = new Usuario(codigoNuevo, nombres, apellidos, correo, fechaNacimiento, nombreUsuario, clave);

            daoUsuario.insertar(usuario);

            //request.setAttribute("respuesta", "hola mundo");
            request.getRequestDispatcher("proceso-exitoso.jsp").forward(request, response); // Redirigir a la página web
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
