//Lewis Brown/ joe leon
//CST-361
//9-28-19
//This assignment was completed in collaboration with Joe Leon, and Lewis Brown.
//We used source code from the following websites to complete this assignment:
//WEBSITE 1 https://www.byteplusone.com/searching-twitter-with-java-twitter4j/
//WEBSITE 2 http://twitter4j.org/en/code-examples.html#search

package data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.TwitterItems;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;


@Stateless
@Local(TwitterDataInterface.class)
@LocalBean
public class TwitterConnection 
{
		
	//Consumer key
	private static String ConsumerKey = "SIAEvT52QzRmK5EWLBHjgdlZ1";
	private static String consumerSecret = "iwT6Wa0FMDRIoom1cxrNlkj0KXh4in5l4CNNMovoYmjzOnWuB6";
		
	//Access Token
	private static String accessTokens = "1177406911743848449-g0zFjCSVO5v73CryK9yU92EEor6Lqq";
	private static String accessSecret = "K2tnpFWyH8ORTd3pJGaCQgI7C97W0416lmvKO50sH09kt";
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	//used to seach for specific tweets by using twitters explore feature
	//to search for specific tweets
	public List wordSearch(String word, int limit)
	{
		List tweets = new ArrayList();
		TwitterItems items = new TwitterItems();
		List<TwitterItems> itemList = new ArrayList<TwitterItems>();
			
		try
		{
			// allows us to input the tokens and sends it to twitter for authentification
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setOAuthConsumerKey(ConsumerKey);
			cb.setOAuthConsumerSecret(consumerSecret);
			cb.setOAuthAccessToken(accessTokens);
			cb.setOAuthAccessTokenSecret(accessSecret);
			cb.setTweetModeExtended(true);
			Configuration config = cb.build();
				
			TwitterFactory tf = new TwitterFactory(config);				
				
			Twitter twitter = tf.getInstance(); 
				
			//"word" inside the parameter is the word to search
			Query query = new Query(word);
			QueryResult qr = twitter.search(query);
				
			//18 is a test number.
			//sets the number of tweets
			query.setCount(limit);
			
			
			//test to see if we have the correct keys used for debug
			System.out.println("key: " + twitter.getConfiguration().getOAuthConsumerKey());
			System.out.println("secret key: " + twitter.getConfiguration().getOAuthConsumerSecret());
			System.out.println("Token: " + twitter.getConfiguration().getOAuthAccessToken());
			System.out.println("Token secret: " + twitter.getConfiguration().getOAuthAccessTokenSecret());

			List<Status> tw = qr.getTweets();
			
            for (Status tweet : tw ) 
            {
            	/*
            	String sname= tweet.getUser().getScreenName();
            	String body = tweet.getText();
            	int retweetCount = tweet.getRetweetCount();
            	int favorites = tweet.getFavoriteCount();
            	String lang = tweet.getLang();
            	
            	//Saving it to items
            	
            	items.setScreenName(sname);
            	items.setTweet(body);
            	items.setRetweetCount(retweetCount);
            	items.setLikes(favorites);
            	items.setLanguage(lang);
            	
            	*/
            	itemList.add(new TwitterItems(tweet.getUser().getScreenName(), tweet.getText(), tweet.getRetweetCount(), tweet.getFavoriteCount(), tweet.getLang()));
                System.out.println("Name: @" + tweet.getUser().getScreenName() + "| Text: " + tweet.getText() + "|Retweets: " + tweet.getRetweetCount() + "|Favorites: " + tweet.getFavoriteCount() + "|Language: " + tweet.getLang());
            }
		}
		catch(TwitterException e)
		{
			e.printStackTrace();
		}
		return itemList;
	}		
}
		
		/*
		//code that doesnt work but used as a frame to build off on
		ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true).setOAuthConsumerKey("SIAEvT52QzRmK5EWLBHjgdlZ1")
	    .setOAuthConsumerSecret("iwT6Wa0FMDRIoom1cxrNlkj0KXh4in5l4CNNMovoYmjzOnWuB6")
	    .setOAuthAccessToken("1177406911743848449-g0zFjCSVO5v73CryK9yU92EEor6Lqq")
	    .setOAuthAccessTokenSecret("K2tnpFWyH8ORTd3pJGaCQgI7C97W0416lmvKO50sH09kt");
	    
	    TwitterFactory tf = new TwitterFactory(cb.build());
	    twitter4j.Twitter twitter=tf.getInstance();
	    
	    */


