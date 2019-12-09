/**Joe Leon
**CST-361
**9-26-19
**This assignment was completed in collaboration with Joe Leon, and Lewis Brown.
**This class is the REST service that is designed to pull various information from Twitter depending on what Minimeme is fed from
*Memesupreme. 
*/
package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.mail.iap.Response;

import beans.BatchDTO;
import beans.BatchFactoryInterface;
import beans.BatchItems;
import beans.Search;
import beans.TwitterItems;
import util.DataFactory;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;

@RequestScoped
@Path("/tweets")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class TwitterREST 
{
	//Inject the TwitterInterface.
	@Inject
	TwitterInterface<BatchItems> TI;
	
	//Setup the datafactory. 
	DataFactory dFactory = new DataFactory();
	
	/**This is the real meat and potatoes. 
	 * This takes in a {word} from a URL call and then sends that off to the TwitterConn. 
	 * In turn, this returns a list of the total stats from that particual search.
	 * @param word
	 * @return BatchFactoryInterface
	 */
	@GET
	@Path("/inboundData/{word}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BatchFactoryInterface inboundData(@PathParam("word") String word)
	{
		try
		{
		
		//Create a new object of BatchItems based on the results from wordSearc(word)
		BatchItems bi = TI.wordSearch(word);
		//Combine the BatchItems with the proper binding of the datafactory to make a BatchFactoryInterface DTO.
		BatchFactoryInterface<BatchItems> BFI = dFactory.getBatchInterface("DATAFOUND");
		
		//Set the BatchItems to the BatchFactoryInterface object and then send it out.
		BFI.setItems(bi);
		return BFI;
		}
		catch (Exception e)
		{
			//If failed, then return a blank Interface.
			BatchFactoryInterface BFI = dFactory.getBatchInterface("NODATAFOUND");
			return BFI;
		}
	}
	
	/** This takes in the word and send it off to the TwitterConn and then list out everything within the 
	 * tweets themselves to save to the database back in MeemSupreme.
	 * 
	 * @param word
	 * @return List<TwitterItems>
	 */
	@GET
	@Path("/fullData/{word}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<TwitterItems> fullData(@PathParam("word") String word)
	{
		//Tested and working.
		try
		{

		//Returns the list that is created in dataOutput by the TwitterConn.
		return TI.dataOutput(word);
		}
		catch (Exception e)
		{
			//If Failed, then return null.
			return null;
		}
	}
}