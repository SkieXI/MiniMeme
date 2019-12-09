package util;

import beans.BatchDTO;
import beans.BatchItems;

public class DataFactory {
	//use getUser method to get object of type shape 
	public BatchDTO getBatchInterface(String batchData)
	{
		//First Error: Nothing at all.
		if(batchData ==null)
		{
			return new BatchDTO(-2,"Null data.", new BatchItems());
		}
		
		//Second Error: No data found.
		if(batchData.equalsIgnoreCase("NODATAFOUND"))
		{
			return new BatchDTO(-2, "Batch data not found.", new BatchItems());
		}
		//Third Error: This isn't an error.
		if (batchData.equalsIgnoreCase("DATAFOUND"))
		{
			return new BatchDTO(0, "Data Found!", new BatchItems());
		}
		//Fourth Error: Everything else.
		return new BatchDTO(-2, "I have no idea what went wrong to be honest.", new BatchItems());
	}
}
