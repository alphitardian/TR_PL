/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import daos.UserDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author kelvi
 */
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
        UserDao userDao = new UserDao();
        User user;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("username : " + username);
        System.out.println("password : " + password);

        user = userDao.getByUsernameAndPassword(username, password);

        if (user != null) {
            HttpSession session = request.getSession(true);

            session.setAttribute("id", user.getId());
            session.setAttribute("name", user.getName());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("password", user.getPassword());
            session.setAttribute("role", user.getRole());
            session.setAttribute("method", "signIn");
            System.out.println(user.getRole());
            if (user.getRole() == 0) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else if (user.getRole() == 1) {
                response.sendRedirect(request.getContextPath() + "/user/index.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/user/index.jsp?err=error");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
