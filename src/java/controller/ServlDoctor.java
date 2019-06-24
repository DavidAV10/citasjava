package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uml.Doctor;
import model.dao.DoctorDAO;

public class ServlDoctor extends HttpServlet {

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
            out.println("<title>Servlet ServDoctor</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServDoctor at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("reg_doctor") != null) {
            DoctorDAO daoDoctor = new DoctorDAO();
            int codigoNuevo = Integer.parseInt(request.getParameter("cod_doctor"));
            String nombres = new String(request.getParameter("nom_doctor").getBytes("ISO-8859-1"), "UTF-8");
            String apellidos = new String(request.getParameter("ape_doctor").getBytes("ISO-8859-1"), "UTF-8");
            String correo = new String(request.getParameter("cor_doctor").getBytes("ISO-8859-1"), "UTF-8");
            String especialidad = new String(request.getParameter("especialidad").getBytes("ISO-8859-1"), "UTF-8");
            String fechaNacimiento = new String(request.getParameter("fech_nac_doctor").getBytes("ISO-8859-1"), "UTF-8");
            String nombreUsuario = new String(request.getParameter("nom_usuario").getBytes("ISO-8859-1"), "UTF-8");
            String clave = new String(request.getParameter("clave_doctor").getBytes("ISO-8859-1"), "UTF-8");
            
            //System.out.println(Integer.parseInt(request.getParameter("cedula")));
            System.out.println(codigoNuevo);
            System.out.println(nombres);
            System.out.println(apellidos);
            System.out.println(correo);
            System.out.println(especialidad);
            System.out.println(fechaNacimiento);
            System.out.println(nombreUsuario);
            System.out.println(clave);

            Doctor doctor = new Doctor(codigoNuevo, nombres, apellidos, correo, especialidad, fechaNacimiento, nombreUsuario, clave);

            daoDoctor.insertar(doctor);

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
