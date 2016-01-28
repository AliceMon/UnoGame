///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Model;
//
//import static java.lang.System.out;
//import org.apache.taglibs.standard.tag.el.core.OutTag;
//
///**
// *
// * @author NanOopyin
// */
//public class test {
//    
//    public static void main(String[] args) {
//        Desk d =  new Desk();
//        
//        out.println(d.play(1, "kyaw", "a"));
//        
//    }
//    
//    public String sendGameDeskList() {
//        int size = 0;
//
//        JsonObjectBuilder innerJsonBuilder = Json.createObjectBuilder();
//        JsonArrayBuilder arrayJsonBuilder = Json.createArrayBuilder();
//        JsonObjectBuilder outerJsonBuilder = Json.createObjectBuilder();
//
//        JsonObject innerJson = null;
//        JsonArray arrayJson = null;
//        JsonObject outerJson = null;
//
//        System.out.println("gamedesklist " + gameDeskList.toString());
//        for (String key : gameDeskList.keySet()) {
//            System.out.println("===========|||||========");
//            System.out.println("No of Max Player " + gameDeskList.get(key).getNoOfMaxPlayer());
//            System.out.println("Player list size " + gameDeskList.get(key).getPlayers().size());
//            System.out.println("==========|||||=========");
//            if (gameDeskList.get(key).getPlayers().size() < gameDeskList.get(key).getNoOfMaxPlayer()) {
//                System.out.println("now sending");
//                size = gameDeskList.get(key).getPlayers().size();
//                innerJsonBuilder.add("GameID", key);
//                System.out.println("Game ID : " + key);
//                innerJsonBuilder.add("Description", gameDeskList.get(key).getDescription());
//                innerJsonBuilder.add("NoOfPlayer", size);
//                System.out.println("No Of Player : " + size);
//                innerJson = innerJsonBuilder.build();
//                System.out.println("json - " + innerJson.toString());
//                arrayJsonBuilder.add(innerJson);
//            }
//        }
//        arrayJson = arrayJsonBuilder.build();
//        System.out.println("builder - " + arrayJson.toString());
//        outerJsonBuilder.add("ConnectType", "GameList");
//        outerJsonBuilder.add("ConnectBy", "Server");
//        if(arrayJson.isEmpty())
//            outerJson = outerJsonBuilder.add("Data", "Empty").build();
//        else
//            outerJson = outerJsonBuilder.add("Data", arrayJson).build();
//        System.out.println("outerJson - " + outerJson.toString());
//        try {
//            System.out.println(">>unconnectedlist size - " + unconnectedlist.size());
//            for (Session s : unconnectedlist) {
//                s.getBasicRemote().sendText(outerJson.toString());
//            }
//        } catch (Exception ex) {
//            System.out.println("Exception in sendGameDeskList");
//            return "Exception";
//        }
//        System.out.println("JSON is sent to all unconnectList");
//        return "Success";
//    }
//}
