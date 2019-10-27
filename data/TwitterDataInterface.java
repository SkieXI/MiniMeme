package data;

import java.util.List;

import beans.TwitterItems;
import beans.TwitterResponseData;
import beans.TwitterResponseDatas;

public interface TwitterDataInterface 
{
	@SuppressWarnings("rawtypes")
	//List wordSearch(String word, int Limit);
	public boolean create(TwitterResponseData data);
	public TwitterItems findBy(TwitterItems ti);
	public List<TwitterItems> findAll();
}
