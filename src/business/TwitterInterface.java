package business;

import java.util.List;

import beans.BatchItems;

public interface TwitterInterface <T>
{
	public List<T> dataOutput();
	public BatchItems wordSearch(String a, int b);
	public T timer(String a, int b);
}
