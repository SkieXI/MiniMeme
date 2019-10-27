package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import beans.TwitterItems;
import beans.TwitterResponseDatas;
import data.TwitterConnection;
import data.TwitterDTO;
import data.TwitterDataInterface;

public class TwitterManager 
{
	@EJB 
	TwitterDataInterface TDA;
	
	//public List<TwitterItems> PullnSave(String word, int count)
	public TwitterResponseDatas PullnSave(String word, int count)
	{
		//TwitterConnection TConn = new TwitterConnection();
		TwitterResponseDatas TDatas = new TwitterResponseDatas();
		TDatas.setItems(TDA.findAll());
		
		this.SaveNSave(TDatas);
		return TDatas;
		
		//WeatherDataObjects dataObjects = new WeatherDataObjects();
		//dataObjects.setDatas(weatherDao.findAll());
		//return dataObjects;
	}
	
	public void SaveNSave(TwitterResponseDatas datas)
	{
		TDA.create(datas);
	}
	
	public TwitterItems getAllData() 
	{
		TwitterItems twi = new TwitterItems();
		
		 twi = TDA.create(twi);
		 
		 return twi;
	}

}
