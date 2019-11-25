package business;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.BatchDTO;
import beans.BatchFactoryInterface;
import beans.BatchItems;
import beans.Search;
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
	
	@Inject
	TwitterInterface<BatchItems> TI;
	
	DataFactory dFactory = new DataFactory();
	
	@GET
	@Path("/outboundData")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BatchFactoryInterface outboundData(Search se)
	{
		System.out.println("REST TEST");
		BatchItems bi = TI.wordSearch(se.getSearch(), se.getCount());
		BatchFactoryInterface BFI = dFactory.getBatchInterface("DATAFOUND");
		BFI.setItems(bi);
		return BFI;
	}	
	
	@GET
	@Path("/inboundData/{word}/{count}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BatchFactoryInterface inboundData(@PathParam("word") String word, @PathParam("count") int count)
	{
		Timer timer = new Timer();
		TimerTask TT = new TimerScheduleFixedRateDelay();
		
		BatchItems bi = new BatchItems();
		try
		{
		System.out.println("REST TEST");
		
		
		bi = TI.wordSearch(word, count);
		BatchFactoryInterface BFI = dFactory.getBatchInterface("DATAFOUND");
		BFI.setItems(bi);
		return BFI;
		}
		catch (Exception e)
		{
			BatchFactoryInterface BFI = dFactory.getBatchInterface("NODATAFOUND");
			return BFI;
		}
	}
}
