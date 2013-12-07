/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;

/**
 *
 * @author Quaikiet
 */
public class GenreDTO implements Serializable{

    private String genderId;
    private String genderName;

    public GenreDTO() {
    }

    public GenreDTO(String genderId, String genderName) {
        this.genderId = genderId;
        this.genderName = genderName;
    }

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }
}
