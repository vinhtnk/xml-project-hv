/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

/**
 *
 * @author Hoang
 */
public class UserDTO {
    private int userID;
    private String userName;
    private String gender;
    private String phone;
    private String address;
    private String email;
    private String pwd;
    private String role;
    

    public UserDTO() {
    }

    public UserDTO(int userID, String userName, String gender, String phone, String address, String email, String pwd, String role) {
        this.userID = userID;
        this.userName = userName;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.pwd = pwd;
        this.role = role;
        
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    

}
