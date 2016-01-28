
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.uno.servlet;

import Model.*;
import java.util.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import static java.time.Clock.system;
import javax.inject.Inject;
import javax.enterprise.context.RequestScoped;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.websocket.OnClose;

/**
 *
 * @author NanOopyin
 */
@RequestScoped
@ServerEndpoint("/inhand/{room}")
public class InhandSocket {

    @Inject
    private ChatRooms rooms;

//    private ChatRooms playerList;
//    
//    private ChatRooms listB;
//    private ChatRooms listD;

    //  @Inject private Desk desk;
    private Session session;

    private static int count = 0;

    Desk d = new Desk();
    Player p = new Player();
    Card c = new Card();

    @OnMessage
    public void message(String msg) {

        String playerName = null;
        String group = null;
        int count = 0;

        System.out.println("in message function in inhandSocket");
        System.out.println("Message with toString >>>" + msg.toString());
        System.out.println("Message >>>" + msg);

        JsonReader reader = Json.createReader(
                new ByteArrayInputStream(msg.getBytes()));
        JsonObject json = reader.readObject();

        System.out.println(">>>>>>>>json in message<<<<<<<<" + json);

        String cmd = json.getString("cmd");
        try {
            if (cmd.equals("getPlayer"))
            {
                System.out.println(">>>>>cmd == getPlayer<<<<<<<");
                String playerId = json.getString("playerID");
                playerName = json.getString("playerName");

                /////<<<<<<>>>>>>>>
                System.out.println("<<in equal condition of getPlayer>>");

                //take room name
                rooms.getRoomName();
                List<String> names = rooms.getRoomName();
                int i = 0;
                for (String n : names) {
                    rooms.broadcast(n, json);
                    System.out.println(">>>>>after broadcast n <<<<<<<<" + n);
                    System.out.println(">>>>>before broadcast rooms (getPlayer)<<<<<<<<" + rooms.getRoomName().get(0));
                    JsonObject json2 = Json.createObjectBuilder()
                            //    .add("roomName", rooms.getRoomName().get(i))
                            .add("roomName", n) // <-------- testing
                            .add("cmd", "getPlayer").build();

                    session.getBasicRemote().sendText(json2.toString());
                }
            } 
            
            else if (cmd.equals("getRooms"))
            {
                System.out.println(">>>>>cmd != getPlayer<<<<<<<");
                group = json.getString("group");

                //<<<<<<<<<<>>>>>>>>>
                System.out.println("<<in equal condition of getRooms>>");
                rooms.broadcast(group, json);
                rooms.getRoomName();
                System.out.println(">>>>>before broadcast rooms (getRooms)<<<<<<<<" + rooms.toString());
                session.getBasicRemote().sendText(rooms.toString());

            } 
            else if (cmd.equals("joinGetPlayer")) 
            {
                String playerID = json.getString("joinPlayerID");
                String grp = json.getString("group");
                //for temp
                Desk d = rooms.getDesklist().get(grp);
                d.getTempPlayerList().add(playerID);
                
                rooms.playerlist(playerID, session);
                JsonObject json3 = Json.createObjectBuilder()
                        .add("playerList", rooms.pidlist().size()).build();
                session.getBasicRemote().sendText(json3.toString());
            } 
            else if (cmd.equals("startGame")) 
            {
                String room = json.getString("startRoomName");
                
                Desk d = rooms.getDesklist().get(room);
                ArrayList<String> tempPList = d.getTempPlayerList();
                
                
                rooms.add(tempPList , room);
               
            }
            ////////////////////
            
            else if (cmd.equals("inHandCards")) 
            {
                String cardid = json.getString("cardid");
                String id = json.getString("pid");
                System.out.println("*******"+cardid +"*******"+id);
                System.out.println("Image click data ==" + id +"*********"+ cardid);
                rooms.addToDiscardFromInHand(id, cardid);
            }
        
            else if (cmd.equals("pileCard")) 
            {
               String id = json.getString("playerID");
               
               //String id = json.getString("id");
               System.out.println( "*******"+id);
             //  String room = json.getString("room");
               
               System.out.println("Image click data ==" + id);

               rooms.addToInhandFromPile(id);
           }
            else if (cmd.equals("discardedCard")) 
            {
               String id = json.getString("playerID");
               System.out.println("*******"+id);
               System.out.println("Image click data ==" + id);
               rooms.addToInhandFromDiscard(id);
           }

            
            ////////////////////
   
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnOpen
    public void open(Session s, @PathParam("room") String room) {

        System.out.println(room + " >>> @OnOpen = " + s.getId());
        session = s;

//                JsonReader reader = Json.createReader(
//				new ByteArrayInputStream(room.getBytes()));
//                JsonObject json = reader.readObject();
        String regex = "\\d+";
        System.out.println("what is happening in room>>>>>>>>" + room);
        if (room.matches(regex)) {
            System.out.println(">>>>>>>in open function<<<<< PLAYER if>>>>>>>");
            rooms.getRoomName();
            //rooms.playerlist(room, session);
            rooms.getPlayerSession().put(room, session);
//                    players.getPlayerNames();
        } else {
            System.out.println(">>>>>>>in open function<<<<< ROOM else>>>>>>>");
            
            //Update
            rooms.getDesklist().put(room, new Desk(s));
            

//  players.getPlayerNames();
            rooms.addrooms(room, session);
            System.out.println("<<<<<<<<after addrooms>>>>>>>>>");
            rooms.getRoomName();

        }
    }

    @OnClose
    public void close() {
        System.out.println(">>> @OnClose = " + session.getId());

    }

}
