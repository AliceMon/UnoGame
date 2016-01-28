<%-- 
    Document   : inhand
    Created on : Jan 18, 2016, 1:23:00 PM
    Author     : NanOopyin
--%>
<%@page import="java.awt.List"%>
<%@page import="Model.Card"%>
<%@page import="com.sun.javafx.scene.control.skin.VirtualFlow.ArrayLinkedList"%>
<%@ page import="game.uno.servlet.*,Model.*, java.util.*;"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    
           //*******Number Card input***\\
           //*************************************************************************
           
           Card yc;
           Card gc;
           Card rc;
           Card bc;
           ArrayList<NumberCard> nclist =  new ArrayList<NumberCard>();
            //ArrayList<ActionCard> aclist =  new ArrayList<ActionCard>();
            
            
           
           for(int j = 0;j<2;j++){
           
        
  
           for(int i =j; i< 10;i++){
            
           
            yc = new NumberCard("yellow", i , "y" + i,19, "../images/y"+i+".png");
            gc = new NumberCard("green", i , "g" + i,19, "../images/g"+i+".png");
            rc = new NumberCard("red", i , "r" + i,19, "../images/r"+i+".png");
            bc = new NumberCard("blue", i , "b" + i,19, "../images/b"+i+".png");

            nclist.add((NumberCard) yc);
            nclist.add((NumberCard)gc );
            nclist.add((NumberCard) rc);
            nclist.add((NumberCard) bc);
            
              
           }
           

        }
 
             //*******Action Card input***\\
             //************************************************************************
            Card plus2;
            Card skip;
            Card reverse;
            
            ArrayList<ActionCard> aclist = new ArrayList<ActionCard>();
            
             
             for(int i = 0; i< 4;i++){
                 
                 for(int j = 0;j<2;j++){
                     
                     
                 String color;
                 String id;
                 switch(i){
                       case 0:
                            color = "yellow";
                            id = "y";
                            plus2= new ActionCard("plus2", color, id+"P" , 8, "../images/"+id+"P"+".png");
                            skip= new ActionCard("skip", color, id+"S" , 8, "../images/"+id+"S"+".png");
                            reverse = new ActionCard("reverse", color, id+"R" , 8, "../images/"+id+"R"+".png");
                            aclist.add((ActionCard) plus2);
                            aclist.add((ActionCard) skip);
                            aclist.add((ActionCard) reverse);
                            break;
                        case 1:
                            color = "green";
                            id = "g";
                            plus2= new ActionCard("plus2", color, id+"P" , 8, "../images/"+id+"P"+".png");
                            skip= new ActionCard("skip", color, id+"S" , 8, "../images/"+id+"S"+".png");
                            reverse = new ActionCard("reverse", color, id+"R" , 8, "../images/"+id+"R"+".png");
                           
                            aclist.add((ActionCard) plus2);
                            aclist.add((ActionCard) skip);
                            aclist.add((ActionCard) reverse);
                            break;
                        case 2:
                            color = "blue";
                            id = "b";
                            plus2= new ActionCard("plus2", color, id+"P" , 8, "../images/"+id+"P"+".png");
                            skip= new ActionCard("skip", color, id+"S" , 8, "../images/"+id+"S"+".png");
                            reverse = new ActionCard("reverse", color, id+"R" , 8, "../images/"+id+"R"+".png");
                            aclist.add((ActionCard) plus2);
                            aclist.add((ActionCard) skip);
                            aclist.add((ActionCard) reverse);
                            
                            break;
                            
                         case 3:
                            color = "red";
                            id = "r";
                            plus2= new ActionCard("plus2", color, id+"P" , 8, "../images/"+id+"P"+".png");
                            skip= new ActionCard("skip", color, id+"S" , 8, "../images/"+id+"S"+".png");
                            reverse = new ActionCard("reverse", color, id+"R" , 8, "../images/"+id+"R"+".png");
                            aclist.add((ActionCard) plus2);
                            aclist.add((ActionCard) skip);
                            aclist.add((ActionCard) reverse);
                           break;
  
                    }           
                 }                   
             }
             
             
     
             
             Card wc4;
             Card wcJ;
             
             ArrayList<WildCard> wclist = new ArrayList<WildCard>();
             
             for(int j = 0;j<4;j++){
          
             wcJ = new WildCard("jackpot", "wild", 50, "../images/wJ"+".png");
             wc4 = new WildCard("plus4", "wild", 50, "../images/w4"+".png");
             
             wclist.add((WildCard) wc4);
             wclist.add((WildCard) wcJ);
               
             }
            
            ArrayList<Card> clist = new ArrayList<Card>();
            clist.addAll(nclist);
            clist.addAll(aclist);
            clist.addAll(wclist);
            Collections.shuffle(clist);
            
             for(Card ca: clist){
             out.println(ca.getImage());
             out.println("<img src=\""+ca.getImage()+"\""+"/>");

             }
            
//             
//             for(Card ca: nclist){
//             Collections.shuffle(nclist);
//             out.println("<img src=\""+((NumberCard) ca).getImage()+"\""+"/>");
//
//             }
//            
//             for(Card ca: aclist){
//             Collections.shuffle(aclist);
//             out.println("<img src=\""+((ActionCard) ca).getImage()+"\""+"/>");
//
//             }
//              for(Card ca: wclist){
//              out.println("<img src=\""+((WildCard) ca).getImage()+"\""+"/>");
//             }

    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
