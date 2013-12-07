/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package h2store.jws;

import DAO.ProductDAO;
import DTO.ProductDTO;
import com.ConnectDB;
import java.beans.Statement;
import java.sql.*;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Hoang
 */
@WebService()
public class AppWebService {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listAllProduct")
    public void listAllProduct() {
       ProductDAO productDAO = new ProductDAO();
       productDAO.getAllProduct();
    }

}
