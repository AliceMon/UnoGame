/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Team4
 */

public class Card {
    
    private String cardid;
    private int point;
    private String Image;

    public Card(String cardid) {
        this.cardid = cardid;
    }

    /**
     * @return the cardid
     */
    
    
    
    public String getCardid() {
        return cardid;
    }

    /**
     * @param cardid the cardid to set
     */
    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    /**
     * @return the point
     */
    public int getPoint() {
        return point;
    }

    /**
     * @param point the point to set
     */
    public void setPoint(int point) {
        this.point = point;
    }

    /**
     * @return the Image
     */
    public String getImage() {
        return Image;
    }

    /**
     * @param Image the Image to set
     */
    public void setImage(String Image) {
        this.Image = Image;
    }

    public Card(String cardid, int point, String Image) {
        this.cardid = cardid;
        this.point = point;
        this.Image = Image;
    }

    public Card() {
    }

    @Override
    public String toString() {
        return "Card{" + "cardid=" + cardid + ", point=" + point + ", Image=" + Image + '}';
    }

    
    
    
}
