/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

/**
 *
 * @author Hoang
 */
public class CustomerDTO {
    private int customerID;
    private String customerName;
    private String gender;
    private String phone;
    private String address;
    private String email;
    private String pwd;
    private String question;
    private String answer;

    public CustomerDTO() {
    }

    public CustomerDTO(int customerID, String customerName, String gender, String phone, String address, String email, String pwd, String question, String answer) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.pwd = pwd;
        this.question = question;
        this.answer = answer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    

}
