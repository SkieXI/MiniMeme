package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class TwitterItems {
	
	
	private String tweet;
	private String screenName;
	private int retweetCount;
	private String hashtags;
	private String language;
	private int likes;
	
	public TwitterItems(String tweet, String screenName, int retweetCount, int likes, String language) {

		this.tweet = tweet;
		this.screenName = screenName;
		this.retweetCount = retweetCount;
		//this.hashtags = hashtags;
		this.language = language;
		this.likes = likes;
	}
	
	public TwitterItems()
	{
		tweet = "";
		screenName = "";
		retweetCount = 0;
		//hashtags = "";
		language = "";
		likes = 0;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public int getRetweetCount() {
		return retweetCount;
	}

	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}

	public String getHashtags() {
		return hashtags;
	}

	public void setHashtags(String hashtags) {
		this.hashtags = hashtags;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	public String toString() {
		////return "{pressure: "+this.pressure+" tempC: "+this.tempC+ " tempF: "+this.tempF+" humidity: "+this.humidity+"}";
		return "{screenname: "+this.screenName + " tweet: " + this.tweet + " retweetCount: " + this.retweetCount + " likes: " + this.likes + " hashtags: " + this.hashtags + " language: " + this.language + "}";
	}
}
