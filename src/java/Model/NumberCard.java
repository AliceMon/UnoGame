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

public class NumberCard extends Card {
    private String  Color;
    private int Description;

    public NumberCard(String cardid) {
        super(cardid);
    }
    
    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public int getDescription() {
        return Description;
    }

    public void setDescription(int Description) {
        this.Description = Description;
    }

    public NumberCard(String Color, int Description, String cardid, int point, String Image) {
        super(cardid, point, Image);
        this.Color = Color;
        this.Description = Description;
    }

    public NumberCard(String Color, int Description) {
        this.Color = Color;
        this.Description = Description;
    }
    public NumberCard(){
    }

    @Override
    public String toString() {
        return "NumberCard{" + "Color=" + Color + ", Description=" + Description + '}';
    }

    

  
    
     
}
