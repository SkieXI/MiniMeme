package data;

import java.util.List;

public interface TwitterDataInterface 
{
	@SuppressWarnings("rawtypes")
	List wordSearch(String word, int Limit);
}
