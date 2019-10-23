package business;

import javax.ejb.EJB;

import data.TwitterDTO;
import data.TwitterDataInterface;

public class TwitterManager 
{
	@EJB 
	TwitterDataInterface TDA;
	
	public void PullnSave()
	{
		//'word' is a variable that was used in the business layer of things.
		TDA.wordSearch(word, 10);
		
		
	}

}
