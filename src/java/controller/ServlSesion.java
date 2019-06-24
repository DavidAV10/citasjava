package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.Acceso;
import uml.Doctor;
import uml.Usuario;

public class ServlSesion extends HttpServlet {

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
            out.println("<title>Servlet ServlSesion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServlSesion at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("btn_iniciar") != null) {
            Acceso acc = new Acceso();

            String nombreUsuario = new String(request.getParameter("nom_usu").getBytes("ISO-8859-1"), "UTF-8");
            String contra = new String(request.getParameter("clave").getBytes("ISO-8859-1"), "UTF-8");
            Object cuenta = acc.validarCuenta(nombreUsuario, contra);

            if (cuenta instanceof Doctor) {
                HttpSession iniciarSesion = request.getSession();
                Doctor doctor = (Doctor) cuenta;
                int cedula = doctor.getCedula();
                String nombres = doctor.getNombres();
                String apellidos = doctor.getApellidos();
                String nombreCompleto = nombres + " " + apellidos;

                iniciarSesion.setAttribute("sesion_iniciada", true);
                iniciarSesion.setAttribute("cedula", cedula);
                iniciarSesion.setAttribute("nombre_completo", nombreCompleto);
                iniciarSesion.setAttribute("es_doctor", true);

                request.getRequestDispatcher("index.jsp").forward(request, response);

            } else if (cuenta instanceof Usuario) {
                HttpSession iniciarSesion = request.getSession();
                Usuario usuario = (Usuario) cuenta;
                int cedula = usuario.getCedula();
                String nombres = usuario.getNombres();
                String apellidos = usuario.getApellidos();
                String nombreCompleto = nombres + " " + apellidos;

                iniciarSesion.setAttribute("sesion_iniciada", true);
                iniciarSesion.setAttribute("cedula", cedula);
                iniciarSesion.setAttribute("nombre_completo", nombreCompleto);
                iniciarSesion.setAttribute("es_doctor", false);

                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("mensaje-incorrecto.jsp").forward(request, response);
            }
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
