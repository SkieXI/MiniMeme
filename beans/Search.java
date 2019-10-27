package beans;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@Stateless
@ViewScoped
public class Search 
{

	private String Search;
	private int count;
	
	public Search()
	{
		Search = "Idaho";
		count = 10;
	}
	public Search(String Search, int count)
	{
		this.Search = Search;
		this.count = count;
	}

	public String getSearch() {
		return Search;
	}

	public void setSearch(String search) {
		Search = search;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
