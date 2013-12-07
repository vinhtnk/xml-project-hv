/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

/**
 *
 * @author Quaikiet
 */
public class AdminDTO {
    private String Username;
    private String Password;
    private String Role;
    private String Email;
    private String Phone;
    private String FullName;

    public AdminDTO() {
    }

    public AdminDTO(String Username, String Password, String Role, String Email, String Phone, String FullName) {
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
        this.Email = Email;
        this.Phone = Phone;
        this.FullName = FullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }


}
