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
public class ActionCard extends Card {
    private String Description;
    private String Color;

    public ActionCard(String cardid) {
        super(cardid);
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public ActionCard(String Description, String Color, String cardid, int point, String Image) {
        super(cardid, point, Image);
        this.Description = Description;
        this.Color = Color;
    }

    public ActionCard(String Description, String Color) {
        this.Description = Description;
        this.Color = Color;
    }

  
    public ActionCard() {
    }
    
    
}
