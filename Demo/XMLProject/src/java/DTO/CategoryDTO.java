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
public class CategoryDTO implements Serializable{
 private int GameId;
    private int GenreId;

    public CategoryDTO() {
    }

    public CategoryDTO(int GameId, int GenreId) {
        this.GameId = GameId;
        this.GenreId = GenreId;
    }

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int GameId) {
        this.GameId = GameId;
    }

    public int getGenreId() {
        return GenreId;
    }

    public void setGenreId(int GenreId) {
        this.GenreId = GenreId;
    }
}
