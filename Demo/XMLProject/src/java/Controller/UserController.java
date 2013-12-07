/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import DAO.UserDAO;
import DTO.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Quaikiet
 */
@WebServlet("/UserController/*")
public class UserController extends HttpServlet {

    String homePage = "index.jsp";
    String adminPage = "admin2.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String btnAction = request.getParameter("btnAction");
            if (null != btnAction) {
                if (btnAction.equalsIgnoreCase("Login")) {

                    String username = request.getParameter("txtUsername");
                    String password = request.getParameter("txtPassword");
                    if (username == null || password == null) {
                        return;
                    } else {
                        username = username.trim();
                        password = password.trim();
                    }
                    String fullName = "";
                    String role = ""; // Admin or User
                    int userId = 0;// userID
                    //boolean isAdmin = false;
                    boolean isUser = false;

                    // Firstly, check pair(username, password) is user
                    UserDAO userDao = new UserDAO();
                    isUser = userDao.isUser(username, password);
                    if (isUser) {
                        // get fullname and role 
                        UserDTO user = userDao.getUserInfo(username);
                        fullName = user.getFullName();
                        role = user.getRole().toString();
                        userId = user.getUserId();
                        HttpSession session = request.getSession(true);
                        session.setAttribute("FULLNAME", fullName);
                        session.setAttribute("USER", username);
                        session.setAttribute("ROLE", role);
                        session.setAttribute("UID", userId);
                        response.setContentType("text/plain");
                        response.setCharacterEncoding("UTF-8");
                        if (role.equals("User")) {
                            out.write("Login successful!");
                        } else if (role.equals("Admin")) {
                            out.write("Login Admin successful!");
                        }
                
                    } else {
                       
                        out.write("wrong username or password. please try again");
                    }
                } else if (btnAction.equalsIgnoreCase("Register")) {
                   insertUser(request);
                } else if (btnAction.equalsIgnoreCase("Logout")) {
                    HttpSession session = request.getSession();
                    session.invalidate();
                 
                    out.write("logout successful!");
                }
            }
            //response.sendRedirect(homePage);
        } finally {
            out.close();
        }
    }

    public void insertUser(HttpServletRequest request) {
        // get parameter
        synchronized (this) {
            String txtUsername = request.getParameter("txtUserName");
            String txtPassword = request.getParameter("txtPassword");
            String txtEmail = request.getParameter("txtEmail");
            String txtFirstName = request.getParameter("txtFirstName");
            String txtMiddleName = request.getParameter("txtMiddleName");
            String txtLastName = request.getParameter("txtLastName");
            String txtPhone = request.getParameter("txtPhone");
            // insert database
            UserDTO user = new UserDTO();
            user.setUsername(txtUsername);
            user.setPassword(txtPassword);
            user.setEmail(txtEmail);
            user.setFullName(txtFirstName + txtMiddleName + txtLastName);
            user.setPhone(txtPhone);
            UserDAO userDao = new UserDAO();
            if (!userDao.isExistedUser(user)) {
                userDao.insert(user);
            } else {
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
