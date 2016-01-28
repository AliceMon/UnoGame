/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.*;
import javax.websocket.Session;

/**
 *
 * @author Team4
 */
public class Desk {

    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    private ArrayList<Card> clist = new ArrayList<Card>();

    private ArrayList<Card> totalcardlist = new ArrayList<Card>();

    private ArrayList<Card> pileCard = new ArrayList<Card>();

    private Player p;

    public ArrayList<String> getTempPlayerList() {
        return tempPlayerList;
    }

    public void setTempPlayerList(ArrayList<String> tempPlayerList) {
        this.tempPlayerList = tempPlayerList;
    }

    
    //Update
    private ArrayList<String> tempPlayerList = new ArrayList<String>();
    //private ArrayList<Card> pileCard;
    private ArrayList<Card> discardedCard = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private String status;

    public ArrayList<Card> getPileCard() {
        return pileCard;
    }

    public void setPileCard(ArrayList<Card> pileCard) {
        this.pileCard = pileCard;
    }

    public ArrayList<Card> getDiscardedCard() {
        return discardedCard;
    }

    public void setDiscardedCard(ArrayList<Card> discardedCard) {
        this.discardedCard = discardedCard;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Desk(ArrayList<Card> pileCard, ArrayList<Card> discardedCard, ArrayList<Player> players, String status) {
        this.pileCard = pileCard;
        this.discardedCard = discardedCard;
        this.players = players;
        this.status = status;
    }

    public Desk() {
    }

    public Desk(Session s) {
        this.session = s;
        clist = new ArrayList<Card>();
        totalcardlist = new ArrayList<Card>();
        pileCard = new ArrayList<Card>();
        discardedCard = new ArrayList<>();
        players = new ArrayList<Player>();
        tempPlayerList = new ArrayList<String>();
    }

    //*******Number Card input***\\
    public ArrayList<NumberCard> numberCardList() {
        Card yc;
        Card gc;
        Card rc;
        Card bc;
        ArrayList<NumberCard> nclist = new ArrayList<NumberCard>();
        //ArrayList<ActionCard> aclist =  new ArrayList<ActionCard>();

        for (int j = 0; j < 2; j++) {

            for (int i = j; i < 10; i++) {

                yc = new NumberCard("yellow", i, "y" + i, 19, "../images/y" + i + ".png");
                gc = new NumberCard("green", i, "g" + i, 19, "../images/g" + i + ".png");
                rc = new NumberCard("red", i, "r" + i, 19, "../images/r" + i + ".png");
                bc = new NumberCard("blue", i, "b" + i, 19, "../images/b" + i + ".png");

                nclist.add((NumberCard) yc);
                nclist.add((NumberCard) gc);
                nclist.add((NumberCard) rc);
                nclist.add((NumberCard) bc);

            }
        }

        return nclist;
    }

    //*******Action Card input***\\
    public ArrayList<ActionCard> actionCardList() {
        Card plus2;
        Card skip;
        Card reverse;

        ArrayList<ActionCard> aclist = new ArrayList<ActionCard>();

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 2; j++) {

                String color;
                String id;
                switch (i) {
                    case 0:
                        color = "yellow";
                        id = "y";
                        plus2 = new ActionCard("plus2", color, id + "P", 8, "../images/" + id + "P" + ".png");
                        skip = new ActionCard("skip", color, id + "S", 8, "../images/" + id + "S" + ".png");
                        reverse = new ActionCard("reverse", color, id + "R", 8, "../images/" + id + "R" + ".png");
                        aclist.add((ActionCard) plus2);
                        aclist.add((ActionCard) skip);
                        aclist.add((ActionCard) reverse);
                        break;
                    case 1:
                        color = "green";
                        id = "g";
                        plus2 = new ActionCard("plus2", color, id + "P", 8, "../images/" + id + "P" + ".png");
                        skip = new ActionCard("skip", color, id + "S", 8, "../images/" + id + "S" + ".png");
                        reverse = new ActionCard("reverse", color, id + "R", 8, "../images/" + id + "R" + ".png");

                        aclist.add((ActionCard) plus2);
                        aclist.add((ActionCard) skip);
                        aclist.add((ActionCard) reverse);
                        break;
                    case 2:
                        color = "blue";
                        id = "b";
                        plus2 = new ActionCard("plus2", color, id + "P", 8, "../images/" + id + "P" + ".png");
                        skip = new ActionCard("skip", color, id + "S", 8, "../images/" + id + "S" + ".png");
                        reverse = new ActionCard("reverse", color, id + "R", 8, "../images/" + id + "R" + ".png");
                        aclist.add((ActionCard) plus2);
                        aclist.add((ActionCard) skip);
                        aclist.add((ActionCard) reverse);

                        break;

                    case 3:
                        color = "red";
                        id = "r";
                        plus2 = new ActionCard("plus2", color, id + "P", 8, "../images/" + id + "P" + ".png");
                        skip = new ActionCard("skip", color, id + "S", 8, "../images/" + id + "S" + ".png");
                        reverse = new ActionCard("reverse", color, id + "R", 8, "../images/" + id + "R" + ".png");

                        aclist.add((ActionCard) plus2);
                        aclist.add((ActionCard) skip);
                        aclist.add((ActionCard) reverse);
                        break;

                }
            }
        }
        return aclist;
    }

    //*******Wild Card input***\\
    public ArrayList<WildCard> wildCardList() {
        Card wc4;
        Card wcJ;

        ArrayList<WildCard> wclist = new ArrayList<WildCard>();

        for (int j = 0; j < 4; j++) {

            wcJ = new WildCard("jackpot", "wJ", 50, "../images/wJ" + ".png");
            wc4 = new WildCard("plus4", "w4", 50, "../images/w4" + ".png");

            wclist.add((WildCard) wc4);
            wclist.add((WildCard) wcJ);

        }
        return wclist;
    }

    public ArrayList<Card> ClassifyCard() {
        clist.addAll(numberCardList());
        clist.addAll(actionCardList());
        clist.addAll(wildCardList());
        Collections.shuffle(clist);

        return clist;
    }

    public ArrayList<Card> DiscardedCards() {
        
        totalcardlist.addAll(ClassifyCard());
        discardedCard.add(totalcardlist.get(totalcardlist.size() - 1));
        totalcardlist.remove(totalcardlist.get(totalcardlist.size() - 1));
        pileCard.addAll(totalcardlist);
        return discardedCard;
    }

    public  ArrayList<String> play(String pid){
        
         ArrayList<String> cardid = new ArrayList<String>();
         System.out.println("==========");
            System.out.println("Pile Card size"+pileCard.size());
            System.out.println("==========");
                 for(int j = 0; j<7; j++)
                 {
                    p = new Player(pid, pileCard.get(0).getCardid());
                    players.add(p);
                    cardid.add(pileCard.get(0).getCardid());
                    pileCard.remove(0);
                  }
                 
                 System.out.println("totalcardlist====" +pileCard.size());
                return cardid;  
    }

    public ArrayList<Card> calculatebalance() {

        ArrayList<Card> clist = new ArrayList<Card>();

        clist.addAll(pileCard);

        return clist;
    }

    public ArrayList<String> balCards() {

        ArrayList<String> cid = new ArrayList<String>();
        System.out.println("bal cardlist====" + pileCard.size());

        for (int j = 0; j < pileCard.size(); j++) {

            cid.add(pileCard.get(j).getCardid());

        }
        System.out.println("bal cardlist====" + cid);

        return cid;
    }

//    public ArrayList<String> nextplayer(int pid, String name,String room){
//        
//    
//   //  cardlist.addAll(balcardlist);
//         ArrayList<String> balcardid = new ArrayList<String>();
//
//                 for(int j = 0; j<7; j++)
//                 {
//
//                       p = new Player(pid, name, room, 0, balcardlist.get(j).getCardid());
//                       players.add(p);
//                       balcardid.add(balcardlist.get(j).getCardid());
//                  
//                       balcardlist.remove(j);
//
//                  }
//                 
//                 System.out.println("bal cardlist====" +balcardlist.size());
//                
//                 
//                 return balcardid; 
//    }
    public ArrayList<Player> gameplayerslist() {

        ArrayList<Player> plist = new ArrayList<Player>();
        plist.addAll(players);

        return plist;

    }

    public ArrayList<String> undistinctplayerslist() {

        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<String> plist = new ArrayList<String>();

        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                do {
                    plist.add(players.get(i).getPid());
                } while (players.get(i).getPid() != players.get(i++).getPid());

            }
        }

//        for(int i = 0; i< players.size();i++){
//            
//            if(players.get(i).getPid() == players.get(i++).getPid()){
//            //  list.add(players.get(i).getPid());
//             list.add(players.get(i).getPid());
//
//                }   
//            else if(players.get(i).getPid() != players.get(i++).getPid()){
//                plist.add(players.get(i).getPid());
//            }
//        }
        return plist;

    }

    // DRAW A CARD 
    public void Draw2(String Description, String Color) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if (Description == "Draw2" || Color == "Color") {

                } else {

                }
            }

        }
    }

    public void Reverse(String Description, String Color) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                if (Description == "Reverse" && Color == "Color") {

                }
            }
        }

    }

    public void GiveNormalCard() {
        // Check Card First 

    }
// if condition check card first and  color  
// and if reverse GIVE NAME  AND Condition

    // Calculating  Scored to get Marks  need to put array outside 
    public int calculateNumberScored(int i) {
        int a = 0;
        ArrayList<Card> b = ClassifyCard();
        //ArrayList <NumberCard> nclist=ClassifyCard
        for (int j = 0; j < b.size(); j++) {

            if (i == b.get(j).getPoint()) {

                a = i;
            }
        }
        return a;
    }

    // Calculate WildScored Card  to get Score
    public int CalculateWildScored(String C) {
        int c = 0;
        ArrayList<Card> b = ClassifyCard();
        for (int p = 0; p < b.size(); p++) {
            if (C == "Wild") {
                c = 50;
            }
        }
        return c;
    }
// Calcualte ActionCard TO Get Score

    public int CalculateActionCard(String A, String B) {
        //  ArrayList<Card>g = ClassifyCard(0,"skip","color");
        int c = 0;
        String color;
        String id;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                switch (i) {
                    case 0:

                        ArrayList<Card> b = ClassifyCard();
                        for (int p = 0; p < b.size(); p++) {
                            if (A == "plus2" || B == "color") {
                                c = 20;
                            }

                        }
                        break;
                    case 1:
                        ArrayList<Card> g = ClassifyCard();
                        for (int p = 0; p < g.size(); p++) {
                            if (A == "skip" || B == "color") {
                                c = 20;
                            }
                        }
                        break;
                    case 2:
                        ArrayList<Card> r = ClassifyCard();
                        for (int s = 0; s < r.size(); s++) {
                            if (A == "reverse" || B == "color") {
                                c = 20;
                            }
                        }
                }
            }
        }
        return c;

    }

    public void addToDiscardFromInHand(String pId, String name, String room, int score, String cardId) {
        System.out.println(">>>>>>>>>> Add card to discard from inhand");
       
        String id = cardId.substring(1);
        String color = cardId.substring(0, 1);
        System.out.println("Cards In Hand >>>>>" +players);
        System.out.println("Card to be deleted>>>>> " +p.toString());
        ArrayList<Player> pl = new ArrayList<Player>();
        pl.addAll(players);
        for(Player p: pl){
            if(p.getPid().equals(pId)&&p.getInhand().equals(cardId)){
                players.remove(p);
                
            }
        }
        
        System.out.println("Cards In Hand After Deleted>>>>>" +players);
        
        if (cardId.equals("wJ") || cardId.equals("w4")) {
            System.out.println("Wilds Card");
            WildCard wc = new WildCard(cardId);
            discardedCard.add(wc);
            
        } else {
            try {
                int idN = Integer.parseInt(id);
                System.out.println("Number Card");
                NumberCard nc = new NumberCard(cardId);
                discardedCard.add(nc);
                
            } catch (NumberFormatException e) {
                System.out.println("Number Card");
                ActionCard ac = new ActionCard(cardId);
                discardedCard.add(ac);
            }
        }
        
        System.out.println("Discarded Cards Amount >>>>>"+discardedCard.size());
    }
    public void addToInhandFromPile(String pId, String name, String room, int score) {
        System.out.println(">>>>>>>>>> Add card to inhand from Pile");
        System.out.println("Pile cards Amount>>>>>"+pileCard.size());
        
        String cardId = pileCard.get(pileCard.size() - 1).getCardid();
        pileCard.remove(pileCard.get(pileCard.size() - 1));
        
        System.out.println("Pile cards Amount After Deleted>>>>>"+pileCard.size());
        
       
        
        Player p = new Player(pId, name, room, score, cardId);
        
        System.out.println(" Cards In Hand>>>>>"+players);
        players.add(p);
        System.out.println("Cards In Hand After Added>>>>>"+players);
    }
    
     public void addToInhandFromDiscard(String pId, String name, String room, int score) {
        System.out.println(">>>>>>>>>> Add card to inhand from discard");
        System.out.println("Discarded cards Amount>>>>>"+discardedCard.size());
        
        String cardId = discardedCard.get(discardedCard.size() - 1).getCardid();
        discardedCard.remove(discardedCard.get(discardedCard.size() - 1));
        
        System.out.println("Discarded cards Amount After Deleted>>>>>" +discardedCard.size());
        
        
        Player p = new Player(pId, name, room, score, cardId);
        
        System.out.println(" Cards In Hand>>>>>"+players);
        players.add(p);
        System.out.println("Cards In Hand After Added>>>>>"+players);
    }
    
}
