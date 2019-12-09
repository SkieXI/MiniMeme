package business;

import java.util.List;

import beans.BatchItems;
import beans.TwitterItems;

public interface TwitterInterface <T>
{
	public T wordSearch(String a);
	List<TwitterItems> dataOutput(String a);
}
