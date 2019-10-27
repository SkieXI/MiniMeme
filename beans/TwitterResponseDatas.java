package beans;

import java.util.List;

public class TwitterResponseDatas 
{
	private List<TwitterItems> items;
	
	public TwitterResponseDatas()
	{
		super();
		this.items = null;
	}
	public TwitterResponseDatas(List<TwitterItems> items)
	{
		super();
		this.items = items;
	}

	public List<TwitterItems> getItems() {
		return items;
	}

	public void setItems(List<TwitterItems> items) {
		this.items = items;
	}
	
	

}
