/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.GenreDTO;
import Utils.DBUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quaikiet
 */
public class GenresDAO {

    private Connection conn;
    private Statement stm;
    String sqlQuery;

    public GenresDAO() {
        this.conn = DBUtil.getDBConnection();
        sqlQuery = "";
    }
 public void insert(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(int objId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<GenreDTO> findAll() {
        sqlQuery = "SELECT * FROM tblGenres";
        List<GenreDTO> listGenre = new ArrayList<GenreDTO>();
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sqlQuery);
            GenreDTO genre = null;
            while (rs.next()) {
                genre = new GenreDTO();
                genre.setGenderId(Integer.toString(rs.getInt("GenreId")));
                genre.setGenderName(rs.getString("GenreName"));
                listGenre.add(genre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listGenre;
    }

    public Object findByKey(int userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
