/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;



/**
 *
 * @author burningk
 */
public class responseDTO {

    private String responeCode;
    private String responseData;
    private ArrayList<responseDTO> respList;

    public responseDTO() {
    }

    /**
     * @return the responeCode
     */
    public String getResponeCode() {
        return responeCode;
    }

    /**
     * @param responeCode the responeCode to set
     */
    public void setResponeCode(String responeCode) {
        this.responeCode = responeCode;
    }

    /**
     * @return the responseData
     */
    public String getResponseData() {
        return responseData;
    }

    /**
     * @param responseData the responseData to set
     */
    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    /**
     * @return the respList
     */
    public ArrayList<responseDTO> getRespList() {
        return respList;
    }

    /**
     * @param respList the respList to set
     */
    public void setRespList(ArrayList<responseDTO> respList) {
        this.respList = respList;
    }
}
