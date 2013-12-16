/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.UserDAO;
import DTO.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hoang
 */
public class UserController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           String action = request.getParameter("action");
            if (null != action) {
                if (action.equalsIgnoreCase("login")) {

                    String email = request.getParameter("txtEmail");
                    String password = request.getParameter("txtPassword");
                    if (email == null || password == null) {
                        return;
                    } else {
                        email = email.trim();
                        password = password.trim();
                    }
                    String username = "";
                    String role = ""; 
                    int userId = 0;
                    boolean isUser = false;

                    
                    UserDAO userDao = new UserDAO();
                    isUser = userDao.checkLogin(email, password);
                    if (isUser) {                        
                        UserDTO user = userDao.getUserInfo(email);
                        username = user.getUserName();
                        role = user.getRole().toString();
                        userId = user.getUserID();
                        HttpSession session = request.getSession(true);
                        session.setAttribute("USERNAME", username);
                        session.setAttribute("USER", username);
                        session.setAttribute("ROLE", role);
                        session.setAttribute("UID", userId);
                        response.setContentType("text/plain");
                        response.setCharacterEncoding("UTF-8");
                        if (role.equals("User")) {
                            out.write("Login successful!");
                        } else if (role.equals("admin")) {
                            out.write("Login Admin successful!");
                        }

                    } else {
                        out.write("Email or password invalid. please try again");
                    }
                } else if (action.equalsIgnoreCase("Register")) {
                   createNewUser(request);
                } else if (action.equalsIgnoreCase("Logout")) {
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

    private void createNewUser(HttpServletRequest request) {
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
            user.setUserName(txtUsername);
            user.setPwd(txtPassword);
            user.setEmail(txtEmail);
            //user.setFullName(txtFirstName + txtMiddleName + txtLastName);
            user.setPhone(txtPhone);
            UserDAO userDao = new UserDAO();
            if (!userDao.isExistedUser(user)) {
                userDao.addNewUser(user);
            } else {
            }
        }
    }

}
