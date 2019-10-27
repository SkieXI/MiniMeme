package beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class TwitterResponseData extends ResponseModel
{
	private TwitterItems data;
	
	public TwitterResponseData()
	{
		super(0,"");
	}
	
	public TwitterResponseData(int status, String message, TwitterItems data, List<TwitterItems> items)
	{
		super(status, message);
		this.data = data;
	}

	public TwitterItems getData() {
		return data;
	}

	public void setData(TwitterItems data) {
		this.data = data;
	}
}
