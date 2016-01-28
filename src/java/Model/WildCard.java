/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ahtinlin
 */
public class WildCard extends Card{
    private String Description;

    public WildCard(String cardid) {
        super(cardid);
    }
    
    public WildCard(String Description, String cardid, int point, String Image) {
        super(cardid, point, Image);
        this.Description = Description;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public WildCard() {
    }
    
    
}
