/**
CST-361
9-29-2019
This assignment was completed in collaboration with Joe Leon, and Lewis Brown.
This class is used for REST services.
**/
package beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class BatchDTO implements BatchFactoryInterface
{
	//Variables with a int for a status, and a String for a message.
	private int status;
	private String message;
	private BatchItems items;
	
	//Non-default constructor.
	public BatchDTO(int status, String message, BatchItems items)
	{
		super();
		this.status = status;
		this.message = message;
		this.setItems(items);
	}
	public BatchDTO()
	{
		super();
		message = "";
		status = 0;
		items = null;
	}
	
	@Override
	public int getStatus() {
		return status;
	}
	@Override
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String getMessage() {
		return message;
	}
	@Override
	public void setMessage(String message) {
		this.message = message;
	}
	public BatchItems getItems() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setItems(BatchItems items) {
		this.items = items;
	}
}
