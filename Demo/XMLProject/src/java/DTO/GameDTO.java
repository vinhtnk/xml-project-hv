/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Quaikiet
 */
public class GameDTO implements Serializable{
  private int GameId;
    private String name;
    private float price;
    private String Pinboard;
    private String Description;
    private String ImageUrl;
    private Date createdDate;
    private String GenreId;

    public GameDTO() {
    }

    public GameDTO(int GameId, String name, float price, String Pinboard, String Description, String ImageUrl, Date createdDate, String GenreId) {
        this.GameId = GameId;
        this.name = name;
        this.price = price;
        this.Pinboard = Pinboard;
        this.Description = Description;
        this.ImageUrl = ImageUrl;
        this.createdDate = createdDate;
        this.GenreId = GenreId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int GameId) {
        this.GameId = GameId;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    public String getPinboard() {
        return Pinboard;
    }

    public void setPinboard(String Pinboard) {
        this.Pinboard = Pinboard;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the GenreId
     */
    public String getGenreId() {
        return GenreId;
    }

    /**
     * @param GenreId the GenreId to set
     */
    public void setGenreId(String GenreId) {
        this.GenreId = GenreId;
    }
}
