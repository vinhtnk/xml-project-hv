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
public class StockDTO implements Serializable{
private int stockId;
    private int gameId;
    private String gameKey;

    public StockDTO() {
    }

    public StockDTO(int stockId, int gameId, String gameKey) {
        this.stockId = stockId;
        this.gameId = gameId;
        this.gameKey = gameKey;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameKey() {
        return gameKey;
    }

    public void setGameKey(String gameKey) {
        this.gameKey = gameKey;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

}
