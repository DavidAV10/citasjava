package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uml.Cita;
import model.dao.CitaDAO;

public class ServlCita extends HttpServlet {

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
            out.println("<title>Servlet ServlCita</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServlCita at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("ped_cita") != null) {
            CitaDAO daoCita = new CitaDAO();
            int codigoCita = Integer.parseInt(request.getParameter("cita_disponible"));
            System.out.println("Codigo" + codigoCita);

            Cita registroActualizar = daoCita.filtrarPorCodigoCita(codigoCita);
            registroActualizar.setCitaPedida(true);
            System.out.println("Codigo 2 " + registroActualizar.getCodigoCita());

            registroActualizar.setCodigoUsuario((int) request.getSession().getAttribute("cedula"));

            daoCita.modificar(registroActualizar);

            // request.setAttribute("respuesta", "hola mundo");
            request.getRequestDispatcher("proceso-exitoso.jsp").forward(request, response); // Redirigir a la página web
        } else if (request.getParameter("crear_cita") != null) {
            CitaDAO daoCita = new CitaDAO();
            int nuevoCodigo = daoCita.getNumeroRegistros() + 1;
            String lugar = new String(request.getParameter("lugar_cita").getBytes("ISO-8859-1"), "UTF-8");
            String horaCita = new String(request.getParameter("hora_cita").getBytes("ISO-8859-1"), "UTF-8");
            String tipoCita = new String(request.getParameter("tipo_cita").getBytes("ISO-8859-1"), "UTF-8");
            int consultorio = Integer.parseInt(request.getParameter("consultorio"));

            daoCita.insertar(new Cita(nuevoCodigo, lugar, horaCita, tipoCita, consultorio, false, 0, (int) request.getSession().getAttribute("cedula")));

            // request.setAttribute("respuesta", "hola mundo");
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
