package game.uno.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.websocket.Session;
import Model.*;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

@ApplicationScoped
public class ChatRooms {

    Desk d = new Desk();

    public Map<String, List<Session>> getTable() {
        return table;
    }

    public void setTable(Map<String, List<Session>> table) {
        this.table = table;
    }

    private Map<String, List<Session>> table = new HashMap<>();
    private Map<String, List<Session>> playerList = new HashMap<>();
    private Map<String, Desk> desklist = new HashMap<>();
    private Map<String, Session> playerSession = new HashMap<>();
    ArrayList<String> listB = new ArrayList<String>();
    ArrayList<String> listD = new ArrayList<String>();
    int current = 0;
    private Lock lock = new ReentrantLock();

    private ArrayList<String> cidlist = new ArrayList<String>();

    private ArrayList<String> balcardlist = new ArrayList<String>();

    private ArrayList<Card> discardlist = new ArrayList<Card>();

    public Map<String, Desk> getDesklist() {
        return desklist;
    }

    public void setDesklist(Map<String, Desk> desklist) {
        this.desklist = desklist;
    }

    public Map<String, Session> getPlayerSession() {
        return playerSession;
    }

    public void setPlayerSession(Map<String, Session> playerSession) {
        this.playerSession = playerSession;
    }

    public List<String> getListB() {
        return (listB);
    }

    public List<String> getListD() {
        return (listD);
    }

    private ArrayList<String> drawPileCard = new ArrayList<String>();

    /*public JsonObject getJsonForStartGame(String roomId){
            lock.lock();
            
            
            ArrayList<String> cardInHand = new ArrayList<String>();

            for( String p: playerList.keySet())
            {
                //String name = p.
                System.out.println(">>>checking playerList.keyset()<<<");
                List<Session> psession = playerList.get(p);
                
                //cardInHand = d.play(p, name, roomId);
                for (int i = 0; i < 7; i++)
                {
                    
                }
            }
        }*/
    //<<<<<< PLAYER CREATION>>>>> LIKE A BROADCAST
    public void add(ArrayList<String> tempPList, String room) {
        lock.lock();

        System.out.println(">>>>> in sze add method <<<<<");

        JsonObjectBuilder innercardJsonBuilder = Json.createObjectBuilder();
        JsonObject innercard = null;

        JsonArrayBuilder arraycardJsonBuilder = Json.createArrayBuilder();
        JsonArray arraycard = null;

        JsonObjectBuilder outercardJsonBuilder = Json.createObjectBuilder();
        JsonObject outercard = null;

        JsonObjectBuilder balJsonBuilder = Json.createObjectBuilder();
        JsonObject balcard = null;

        JsonArrayBuilder balarrayJsonBuilder = Json.createArrayBuilder();
        JsonArray balcards = null;

        JsonObjectBuilder discardJsonBuilder = Json.createObjectBuilder();
        JsonObject discard = null;

        JsonArrayBuilder discardarrayJsonBuilder = Json.createArrayBuilder();
        JsonArray discards = null;

        try {

            //System.out.println(">>>>>player number<<<<<" + d.getPlayers().size());
            System.out.println(">>>>>player number<<<<<" + playerList.toString());
            //playerlist.put(pid, psession);
            System.out.println(">>>>>>count in playerlist after put<<<<<<" + playerList.size());
            discardlist = d.DiscardedCards();
            for (String s : tempPList) {
                cidlist = d.play(s);

                balcardlist = d.balCards();

                System.out.println(">>>>>>clist<<<<<<<" + cidlist);

                for (String sp : cidlist) {
                    innercardJsonBuilder.add("cardid", sp);
                    innercard = innercardJsonBuilder.build();

                    arraycardJsonBuilder.add(innercard);
                }

                arraycard = arraycardJsonBuilder.build();

                for (String c : balcardlist) {

                    balJsonBuilder.add("balcards", c);
                    balcard = balJsonBuilder.build();

                    balarrayJsonBuilder.add(balcard);

                }

                balcards = balarrayJsonBuilder.build();

                //discardlist = d.DiscardedCards();
                for (Card c : discardlist) {

                    discardJsonBuilder.add("discard", c.getCardid());
                    discard = discardJsonBuilder.build();

                    discardarrayJsonBuilder.add(discard);
                }

                discards = discardarrayJsonBuilder.build();

                System.out.println("JSON ARRARCARD" + arraycard.toString());

                outercardJsonBuilder.add("id", s);
                outercardJsonBuilder.add("cardid", arraycard);
                outercardJsonBuilder.add("balcards", balcards);
                outercardJsonBuilder.add("discard", discards);
                //editing by alice
                outercardJsonBuilder.add("cmd", "startGame");

                outercard = outercardJsonBuilder.build();
                listB.add(balcards.toString());
                listD.add(discards.toString());
                System.out.println("outercard ARRARCARD" + outercard.toString());

                System.out.println("room name >>> pid " + s);
                if (null == playerSession) {
                    System.out.println(">>>>>psession is null<<<<<<<<");
                    return;
                }

                try {
                    for (Session se : playerSession.values()) {
                        System.out.println("================");
                        System.out.println("Session id" + se.getId());

                        se.getBasicRemote().sendText(outercard.toString());
                        System.out.println("JSON ARRAY" + outercard.toString());
                    }

                    List<Session> tableSession = table.get(room);
                    if (tableSession == null) {
                        System.out.println(">>>>>in startGame<<<<<>>>>>>table sesssiom is null<<<<<");
                        return;
                    }
                    for (Session ts : tableSession) {
                        ts.getBasicRemote().sendText(outercard.toString());
                    }

                } catch (IOException ex) {
                    System.out.println("Exception in sendGameDeskList");
                    ex.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    //<<<<<<FOR CREATION GAME ROOM>>>>>
    public void broadcast(String roomName, JsonObject uno) {
        System.out.println(">>>>>>>>in brocast<<<<<<<<<roomName>>>>>" + roomName);
        lock.lock();
        try {
            List<Session> psessions = playerList.get(roomName);
            List<Session> sessions = table.get(roomName);
            System.out.println(">>>>>>>roomName<<<<<<<<<" + table.get(roomName));
            System.out.println("in broadcast session>>>>>> " + sessions);
            String cmd = uno.getString("cmd");
            if (cmd.equals("startGame")) {
                if (null == psessions) {
                    System.out.println("playerlists sessions is null");
                    return;
                } else {
                    System.out.println("playerlists sessions is not null");
                    final String msgToRoom = uno.toString();
                    for (Session p : psessions) {
                        try {
                            System.out.println("in broadcast, in a session loop>>>>> " + p.getId());

                            p.getBasicRemote().sendText(msgToRoom);
                            //table.put(roomName,sessions); //adding previous groupName to "table"
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } else {
                if (null == sessions) {
                    System.out.println("table sessions is null");
                    return;
                }

                System.out.println("table sessions is not null");
                final String msgToRoom = uno.toString();
                for (Session s : sessions) {
                    try {
                        System.out.println("in broadcast, in a session loop>>>>> " + s.getId());
                        s.getBasicRemote().sendText(msgToRoom);
                        //table.put(roomName,sessions); //adding previous groupName to "table"
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // public void add room getting roomname from textbox()
    public void addrooms(String roomName, Session session) {

        System.out.println(" add table cards (" + roomName + ")");
        lock.lock();

        try {
            List<Session> sessions = table.get(roomName);
            if (null == sessions) {
                sessions = new LinkedList<>();
                table.put(roomName, sessions);
                System.out.println(" add table cards " + roomName);

            }
            sessions.add(session);
            System.out.println(">>>>>>>>>in addrooms CHECKING SESSION<<<<<<<<<" + session);

            System.out.println(" addrooms cards " + table.toString());
        } finally {
            lock.unlock();
        }
    }

    public Set<String> roomNames() {
        lock.lock();
        try {

            return (table.keySet());
        } finally {
            lock.unlock();
        }
    }

    public List<String> getRoomName() {

        List<String> names = new LinkedList<>();
        names.addAll(roomNames());
        System.out.println(">>>>>>getRoomName <<<<<<<<<" + names);
        return names;
    }
    //sze playerlist 

    public Set<String> playerNames() {
        lock.lock();
        try {
            return (playerList.keySet());
        } finally {
            lock.unlock();
        }
    }
    //sze playerlist 

    public List<String> getPlayerNames() {
        List<String> players = new LinkedList<>();
        players.addAll(playerNames());
        System.out.println(">>>>>>getPlayerName <<<<<<<<<" + players);
        return players;
    }

    public void playerlist(String pid, Session s) {

        System.out.println(">>>>>>>in playerlist<<<<<");
        lock.lock();

        try {
            List<Session> sessions = playerList.get(pid);
            if (null == sessions) {
                sessions = new LinkedList<>();
                playerList.put(pid, sessions);
                System.out.println(">>>when player join,in playerlist, currently join player<<<<" + pid);

            }
            sessions.add(s);
            System.out.println(">>>>>>>>after putting player<<<<<" + playerList.toString());
        } finally {
            lock.unlock();
        }
    }

    public Set<String> pidlist() {
        lock.lock();
        try {

            return (playerList.keySet());
        } finally {
            lock.unlock();
        }
    }

    //////////////////////////
    public void addToDiscardFromInHand(String pId, String cardId) {
        ArrayList<Player> plist = new ArrayList<>();
        plist.addAll(d.getPlayers());
        System.out.println("******plist******" + plist);
        for (Player p : plist) {
            if (p.getPid().equals(pId)) {
                System.out.println("******current discarded card p******" + p);
                d.addToDiscardFromInHand(pId, p.getName(), p.getRoom(), p.getScore(), cardId);
            }
            break;
        }
        //edit by Alice

        JsonObjectBuilder innercardJsonBuilder = Json.createObjectBuilder();
        JsonObject innercard = null;

        JsonArrayBuilder arraycardJsonBuilder = Json.createArrayBuilder();
        JsonArray arraycard = null;

        JsonObjectBuilder balJsonBuilder = Json.createObjectBuilder();
        JsonObject balcard = null;

        JsonArrayBuilder balarrayJsonBuilder = Json.createArrayBuilder();
        JsonArray balcards = null;

        JsonObjectBuilder discardJsonBuilder = Json.createObjectBuilder();
        JsonObject discard = null;

        JsonObjectBuilder outercardJsonBuilder = Json.createObjectBuilder();
        JsonObject outercard = null;

        JsonArrayBuilder discardarrayJsonBuilder = Json.createArrayBuilder();
        JsonArray discards = null;

        ArrayList<Player> player = new ArrayList<Player>();
        player.addAll(d.getPlayers());

        for (Player sp : player) {
            innercardJsonBuilder.add("cardid", sp.getInhand());
            innercard = innercardJsonBuilder.build();

            arraycardJsonBuilder.add(innercard);
        }

        arraycard = arraycardJsonBuilder.build();

        for (Card c : discardlist) {

            discardJsonBuilder.add("discard", c.getCardid());
            discard = discardJsonBuilder.build();

            discardarrayJsonBuilder.add(discard);
        }

        discards = discardarrayJsonBuilder.build();

        for (String c : balcardlist) {

            balJsonBuilder.add("balcards", c);
            balcard = balJsonBuilder.build();

            balarrayJsonBuilder.add(balcard);

        }

        balcards = balarrayJsonBuilder.build();

        outercardJsonBuilder.add("cardid", arraycard);
        outercardJsonBuilder.add("balcards", balcards);
        outercardJsonBuilder.add("discard", discards);
        outercardJsonBuilder.add("cmd", "startGame");
        outercard = outercardJsonBuilder.build();

        if (desklist.values() == null) {
            System.out.println(">>>>>in startGame<<<<<>>>>>>table sesssiom is null<<<<<");
            return;
        }
        if (null == playerSession) {
            System.out.println(">>>>>psession is null<<<<<<<<");
            return;
        }

        try {

            for (Session se : playerSession.values()) {
                System.out.println("================");
                System.out.println("Session id" + se.getId());

                se.getBasicRemote().sendText(outercard.toString());
                System.out.println("JSON ARRAY" + outercard.toString());
            }
            for (Desk d : desklist.values()) {
                Session s = d.getSession();
                s.getBasicRemote().sendText(outercard.toString());

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //session.getBasicRemote().sendText(json3.toString());
        /////////////
    }

    public void addToInhandFromPile(String pId) {

        ArrayList<Player> plist = new ArrayList<>();
        plist.addAll(d.getPlayers());
        for (Player p : plist) {
            if (p.getPid().equals(pId)) {
                d.addToInhandFromPile(pId, p.getName(), p.getRoom(), p.getScore());
            }
            break;
        }
        /////////////////////
        //edit by Alice

        JsonObjectBuilder innercardJsonBuilder = Json.createObjectBuilder();
        JsonObject innercard = null;

        JsonArrayBuilder arraycardJsonBuilder = Json.createArrayBuilder();
        JsonArray arraycard = null;

        JsonObjectBuilder balJsonBuilder = Json.createObjectBuilder();
        JsonObject balcard = null;

        JsonArrayBuilder balarrayJsonBuilder = Json.createArrayBuilder();
        JsonArray balcards = null;

        JsonObjectBuilder discardJsonBuilder = Json.createObjectBuilder();
        JsonObject discard = null;

        JsonObjectBuilder outercardJsonBuilder = Json.createObjectBuilder();
        JsonObject outercard = null;

        JsonArrayBuilder discardarrayJsonBuilder = Json.createArrayBuilder();
        JsonArray discards = null;

        ArrayList<Player> player = new ArrayList<Player>();
        player.addAll(d.getPlayers());

        for (Player sp : player) {
            innercardJsonBuilder.add("cardid", sp.getInhand());
            innercard = innercardJsonBuilder.build();

            arraycardJsonBuilder.add(innercard);
        }

        arraycard = arraycardJsonBuilder.build();

        for (Card c : discardlist) {

            discardJsonBuilder.add("discard", c.getCardid());
            discard = discardJsonBuilder.build();

            discardarrayJsonBuilder.add(discard);
        }

        discards = discardarrayJsonBuilder.build();

        for (String c : balcardlist) {

            balJsonBuilder.add("balcards", c);
            balcard = balJsonBuilder.build();

            balarrayJsonBuilder.add(balcard);

        }

        balcards = balarrayJsonBuilder.build();

        outercardJsonBuilder.add("cardid", arraycard);
        outercardJsonBuilder.add("balcards", balcards);
        outercardJsonBuilder.add("discard", discards);
        outercardJsonBuilder.add("cmd", "startGame");
        outercard = outercardJsonBuilder.build();

        if (desklist.values() == null) {
            System.out.println(">>>>>in startGame<<<<<>>>>>>table sesssiom is null<<<<<");
            return;
        }
        if (null == playerSession) {
            System.out.println(">>>>>psession is null<<<<<<<<");
            return;
        }

        try {

            for (Session se : playerSession.values()) {
                System.out.println("================");
                System.out.println("Session id" + se.getId());

                se.getBasicRemote().sendText(outercard.toString());
                System.out.println("JSON ARRAY" + outercard.toString());
            }
            for (Desk d : desklist.values()) {
                Session s = d.getSession();
                s.getBasicRemote().sendText(outercard.toString());

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        /////////////////////

    }

    void addToInhandFromDiscard(String pId) {
        ArrayList<Player> plist = new ArrayList<>();
        plist.addAll(d.getPlayers());
        for (Player p : plist) {
            if (p.getPid().equals(pId)) {
                d.addToInhandFromDiscard(pId, p.getName(), p.getRoom(), p.getScore());
            }
            break;

        }
        JsonObjectBuilder innercardJsonBuilder = Json.createObjectBuilder();
        JsonObject innercard = null;

        JsonArrayBuilder arraycardJsonBuilder = Json.createArrayBuilder();
        JsonArray arraycard = null;

        JsonObjectBuilder balJsonBuilder = Json.createObjectBuilder();
        JsonObject balcard = null;

        JsonArrayBuilder balarrayJsonBuilder = Json.createArrayBuilder();
        JsonArray balcards = null;

        JsonObjectBuilder discardJsonBuilder = Json.createObjectBuilder();
        JsonObject discard = null;

        JsonObjectBuilder outercardJsonBuilder = Json.createObjectBuilder();
        JsonObject outercard = null;

        JsonArrayBuilder discardarrayJsonBuilder = Json.createArrayBuilder();
        JsonArray discards = null;

        ArrayList<Player> player = new ArrayList<Player>();
        player.addAll(d.getPlayers());

        for (Player sp : player) {
            innercardJsonBuilder.add("cardid", sp.getInhand());
            innercard = innercardJsonBuilder.build();

            arraycardJsonBuilder.add(innercard);
        }

        arraycard = arraycardJsonBuilder.build();

        for (Card c : discardlist) {

            discardJsonBuilder.add("discard", c.getCardid());
            discard = discardJsonBuilder.build();

            discardarrayJsonBuilder.add(discard);
        }

        discards = discardarrayJsonBuilder.build();

        for (String c : balcardlist) {

            balJsonBuilder.add("balcards", c);
            balcard = balJsonBuilder.build();

            balarrayJsonBuilder.add(balcard);

        }

        balcards = balarrayJsonBuilder.build();

        outercardJsonBuilder.add("cardid", arraycard);
        outercardJsonBuilder.add("balcards", balcards);
        outercardJsonBuilder.add("discard", discards);
        outercardJsonBuilder.add("cmd", "startGame");
        outercard = outercardJsonBuilder.build();

        if (desklist.values() == null) {
            System.out.println(">>>>>in startGame<<<<<>>>>>>table sesssiom is null<<<<<");
            return;
        }
        if (null == playerSession) {
            System.out.println(">>>>>psession is null<<<<<<<<");
            return;
        }

        try {

            for (Session se : playerSession.values()) {
                System.out.println("================");
                System.out.println("Session id" + se.getId());

                se.getBasicRemote().sendText(outercard.toString());
                System.out.println("JSON ARRAY" + outercard.toString());
            }
            for (Desk d : desklist.values()) {
                Session s = d.getSession();
                s.getBasicRemote().sendText(outercard.toString());

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    ////////////////////////
}
