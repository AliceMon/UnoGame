package game.uno.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import game.uno.servlet.ChatRooms;

@RequestScoped
@Path("/rooms")
public class ChatRoomResource {

	//@Inject private ChatRooms rooms;
	@Inject private ChatRooms rooms;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {

		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (String rn: rooms.roomNames())
                 
		builder.add(rn);
                
		return (Response.ok(builder.build()).build());

	}
	
}
