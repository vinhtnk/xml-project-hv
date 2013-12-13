/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.UserDTO;
import com.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoang
 */
public class UserDAO {

    Connection con =  null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    String query = "";

     public boolean checkLogin(String email, String pwd) {
        con = ConnectDB.getCon();

        try {
            query = "Select * from user where email=? and password=?";
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            stm.setString(2, pwd);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public List<UserDTO> getAllUser() {
        con = ConnectDB.getCon();
        List<UserDTO> listUser = new ArrayList<UserDTO>();
        try {
            query = "Select * from user";
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();
             
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUserID(rs.getInt("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setGender(rs.getString("Gender"));
                user.setPhone(rs.getString("Phone"));
                user.setAddress(rs.getString("Address"));
                user.setEmail(rs.getString("Email"));
                user.setPwd(rs.getString("Password"));
                user.setRole(rs.getString("Role"));
                
                listUser.add(user);
            }
            return listUser;

        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<UserDTO> searchUserByName(String key) {
        con = ConnectDB.getCon();
        List<UserDTO> listUser = new ArrayList<UserDTO>();
        try {
            query = "Select * from user where userName like ? and role='customer'";
            stm = con.prepareStatement(query);
            stm.setString(1, "%" + key + "%");
            rs = stm.executeQuery();
             
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUserID(rs.getInt("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setGender(rs.getString("Gender"));
                user.setPhone(rs.getString("Phone"));
                user.setAddress(rs.getString("Address"));
                user.setEmail(rs.getString("Email"));
                user.setPwd(rs.getString("Password"));
                user.setRole(rs.getString("Role"));
                listUser.add(user);
            }
            return listUser;

        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean addNewUser(UserDTO user) {
        con = ConnectDB.getCon();
        try {
            query = "insert into user (userName,Gender,Phone,Address,userEmail,Password, "
                    + "Role)values (?,?,?,?,?,?,?)";
            stm = con.prepareStatement(query);
            stm.setString(1, user.getUserName());
            stm.setString(2, user.getGender());
            stm.setString(3, user.getPhone());
            stm.setString(4, user.getAddress());
            stm.setString(5, user.getEmail());
            stm.setString(6, user.getPwd());
            stm.setString(7, user.getRole());
            

            int row = stm.executeUpdate();
            if (row > 1) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean updateuser(UserDTO user) {
        con = ConnectDB.getCon();
        try {
            query = "update user set userName = ?, Gender =?,Phone=?,Address=?,"
                    + "userEmail=?,Password=? where userid=?";

            stm = con.prepareStatement(query);
            stm.setString(1, user.getUserName());
            stm.setString(2, user.getGender());
            stm.setString(3, user.getPhone());
            stm.setString(4, user.getAddress());
            stm.setString(5, user.getEmail());
            stm.setString(6, user.getPwd());
            
            stm.setInt(7, user.getUserID());
            int row = stm.executeUpdate();
            if (row > 1) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean deleteuser(UserDTO user) {
        con = ConnectDB.getCon();
        try {
            query = "delete from user where userid=?";

            stm = con.prepareStatement(query);
            stm.setInt(1, user.getUserID());
            int row = stm.executeUpdate();
            if (row > 1) {
                return true;
            }

        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public UserDTO getUserInfo(String email){
        con = ConnectDB.getCon();
        
        try {
            query = "Select * from user where email=?";
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            rs = stm.executeQuery(query);
            UserDTO user = new UserDTO();
            while (rs.next()) {

                user.setUserID(rs.getInt("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setGender(rs.getString("Gender"));
                user.setPhone(rs.getString("Phone"));
                user.setAddress(rs.getString("Address"));
                user.setEmail(rs.getString("Email"));
                user.setPwd(rs.getString("Password"));
                user.setRole(rs.getString("Role"));
                
            }
            return user;

        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isExistedUser(UserDTO user) {
        if (user == null) {
            return true;
        }

        try {
            query = "Select * From user Where username = '?' AND email ='?'";
            stm = con.prepareStatement(query);
            stm.setString(1, user.getUserName());
            stm.setString(2, user.getEmail());
            rs = stm.executeQuery(query);

            while (rs.next()) {
                rs.close();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
