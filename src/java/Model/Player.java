/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javax.websocket.Session;

/**
 *
 * @author Team4
 */
public class Player {
    private String pid;
    private String name;
    private String room;
    private int score;
    private String inhand;
    
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

   

    public Player() {
    }

    public Player(String pid, String name, String room,int score, String inhand) {
        this.pid = pid;
        this.name = name;
        this.room = room;
        this.score = score;
        this.inhand = inhand;
        
    }

    public Player(String pid, String inhand) {
        this.pid = pid;
        this.inhand = inhand;
    }

    
    

    /**
     * @return the room
     */
    public String getRoom() {
        return room;
    }

    /**
     * @param room the room to set
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * @return the inhand
     */
    public String getInhand() {
        return inhand;
    }

    /**
     * @param inhand the inhand to set
     */
    public void setInhand(String inhand) {
        this.inhand = inhand;
    }

    @Override
    public String toString() {
        return "Player{" + "pid=" + pid + ", name=" + name + ", room=" + room + ", score=" + score + ", inhand=" + inhand + '}';
    }

}
