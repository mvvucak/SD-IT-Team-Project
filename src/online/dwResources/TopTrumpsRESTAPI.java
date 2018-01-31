package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import logic.*;
import logic.Game.Round;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI extends View {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	private final int availableAI;
	private final String deckName;
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
		conf.getDeckFile();
		this.availableAI = conf.getNumAIPlayers();
		this.deckName = conf.getDeckFile();
	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	@GET
	@Path("/options")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String gameOptionJSON() throws IOException {
		Object[] optionsObj = {"gameName", this.deckName, "aiAvailable", this.availableAI};
		String json = oWriter.writeValueAsString(optionsObj);
		System.out.println(json);
		return json;
	}
	
	@GET
	@Path("/game/{gameid}/{playerid}/current")
	/**
	 * Fetches top facing card of a player's deck
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public Card getPlayerCurrentCard(@PathParam("gameid") int gameId, @PathParam("playerid") int playerId) throws IOException {
        Game game = Session.findGameById(gameId);
        Player player = game.getPlayer(playerId);
        Card card = player.getCurrentCard();
        return card;
	}
	
	@POST
	@Path("/game")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createGameSession(@QueryParam("aiselect") int aiSelect ) throws IOException {
	    // check validity of number 
		// if x < y Send 400 error msg 
		if(aiSelect > this.availableAI) return null;
		// try and create new game session for user
		 Game game = Session.createNewGame(aiSelect);
		 return oWriter.writeValueAsString(game);
	}

	@Override
	public int getCategory() {
		return 0;
	}

	@Override
	public void initalRoundInfo(Round rnd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayEndRound(Round rnd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayDraw() {
		// TODO Auto-generated method stub
		
	}
		
}
