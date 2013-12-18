/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import DTO.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
            String action = request.getParameter("btnAction");
            if (null != action) {
                if (action.equalsIgnoreCase("login")) {

                    String txtemail = request.getParameter("txtEmail");
                    String password = request.getParameter("txtPassword");
                    if (txtemail == null || password == null) {
                        return;
                    } else {
                        txtemail = txtemail.trim();
                        password = password.trim();
                    }
                    String email = "";
                    String uname = "";
                    String role = "";
                    int userId = 0;
                    boolean isUser = false;


                    UserDAO userDao = new UserDAO();
                    isUser = userDao.checkLogin(txtemail, password);
                    if (isUser) {
                        UserDTO user = userDao.getUserInfo(txtemail);
                        email = user.getEmail();
                        uname = user.getUserName();
                        role = user.getRole().toString();
                        userId = user.getUserID();
                        HttpSession session = request.getSession(true);
                        session.setAttribute("EMAIL", email);
                        session.setAttribute("USER", uname);
                        session.setAttribute("ROLE", role);
                        session.setAttribute("UID", userId);
                        response.setContentType("text/plain");
                        response.setCharacterEncoding("UTF-8");
                        if (role.equalsIgnoreCase("Customer")) {
                            out.write("Login successful!");
                        } else if (role.equalsIgnoreCase("admin")) {
                            out.write("Login Admin successful!");
                        }

                    } else {
                        out.write("Email or password invalid. please try again");
                    }
                } else if (action.equalsIgnoreCase("Register")) {
                    String cNU = createNewUser(request);
                    out.write(cNU);
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

    private String createNewUser(HttpServletRequest request) throws UnsupportedEncodingException {
        synchronized (this) {
            request.setCharacterEncoding("UTF-8");
            String txtUsername = new String(request.getParameter("txtUsername").getBytes("iso-8859-1"), "UTF-8");
            String txtGender = new String(request.getParameter("txtGender").getBytes("iso-8859-1"), "UTF-8");
            String txtAddress = new String(request.getParameter("txtAddress").getBytes("iso-8859-1"), "UTF-8");
            String txtPhone = request.getParameter("txtPhone");
            String txtEmail = request.getParameter("txtEmail");
            String txtPassword = request.getParameter("txtPassword");
            // insert database
            UserDTO user = new UserDTO();
            user.setUserName(txtUsername);
            user.setGender(txtGender);
            user.setPwd(txtPassword);
            user.setEmail(txtEmail);
            user.setAddress(txtAddress);
            user.setPhone(txtPhone);
            user.setRole("Customer");
            UserDAO userDao = new UserDAO();
            if (!userDao.isExistedUser(user)) {
                if (userDao.addNewUser(user)) {
//                    HttpSession session = request.getSession(true);
//                    session.setAttribute("EMAIL", txtEmail);
//                    session.setAttribute("USER", txtUsername);
//                    session.setAttribute("ROLE", "Customer");
//                    response.setContentType("text/plain");
//                    response.setCharacterEncoding("UTF-8");
                    return "Register successful!";
                } else {
                    return "Error";
                }
            } else {
                return "Email existed!";
            }
        }
    }
}
