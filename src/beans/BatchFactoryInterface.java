package beans;

public interface BatchFactoryInterface<T>
{
	public T getItems();

	public void setItems(T i);
	
	public int getStatus();

	public void setStatus(int status);

	public String getMessage();

	public void setMessage(String message);

}
