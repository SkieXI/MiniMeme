package beans;

public class BatchItems 
{

	private int tweetsTotal;
	private int likesTotal;
	private int retweetTotal;
	
	public BatchItems()
	{
		tweetsTotal = 0;
		likesTotal = 0;
		retweetTotal = 0;
	}
	
	public BatchItems(int tweetsTotal, int likesTotal, int retweetTotal)
	{
		super();
		this.tweetsTotal = tweetsTotal;
		this.likesTotal = likesTotal;
		this.retweetTotal = retweetTotal;
		
	}
	
	
	public int getTweetsTotal() {
		return tweetsTotal;
	}
	public void setTweetsTotal(int tweetsTotal) {
		this.tweetsTotal = tweetsTotal;
	}
	public int getLikesTotal() {
		return likesTotal;
	}
	public void setLikesTotal(int likesTotal) {
		this.likesTotal = likesTotal;
	}
	public int getRetweetTotal() {
		return retweetTotal;
	}
	public void setRetweetTotal(int retweetTotal) {
		this.retweetTotal = retweetTotal;
	}
	
	public String toString()
	{
		return "{tweetsTotal: " + this.tweetsTotal + "likesTotal: " + this.likesTotal + "retweetTotal: " + this.retweetTotal + "}";
	}
}
