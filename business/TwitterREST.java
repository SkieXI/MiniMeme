//Joe Leon
//CST-361
//9-26-19
//This assignment was completed in collaboration with Joe Leon, and Lewis Brown.
//We used source code from the following websites to complete this assignment:
// https://developer.twitter.com/en/docs/tweets/search/api-reference/get-search-tweets
// https://dzone.com/articles/java-twitter-integration
package business;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.TwitterResponseData;
import beans.TwitterItems;
import data.TwitterDTO;
import data.TwitterDataInterface;

@RequestScoped
@Path("/Tweets")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class TwitterREST 
{
	TwitterDTO DTO = new TwitterDTO();
	
	@GET
	@Path("/getalldata")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TwitterItems> getAllData() 
	{
		TwitterItemsList TIL = new TwitterItemsList();
		
		TIL = DTO.findAll();
		
		List<TwitterItems> items = TIL.getItems();
		return items;
	}
}
