package data;

import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import beans.BatchItems;
import beans.TwitterItems;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import business.TwitterInterface;


@Stateless
@Local(TwitterInterface.class)
@LocalBean
@ViewScoped
public class TwitterConn implements TwitterInterface<BatchItems> 
{
	//Consumer key
	private static String ConsumerKey = "SIAEvT52QzRmK5EWLBHjgdlZ1";
	private static String consumerSecret = "iwT6Wa0FMDRIoom1cxrNlkj0KXh4in5l4CNNMovoYmjzOnWuB6";
			
	//Access Token
	private static String accessTokens = "1177406911743848449-g0zFjCSVO5v73CryK9yU92EEor6Lqq";
	private static String accessSecret = "K2tnpFWyH8ORTd3pJGaCQgI7C97W0416lmvKO50sH09kt";
	
	public BatchItems wordSearch(String word, int count)
	{
				TwitterItems itemList = new TwitterItems(); 
				BatchItems BI = new BatchItems();
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
					query.count(count);
					
					QueryResult qr = twitter.search(query);
						
					List<Status> tw = qr.getTweets();
					
		            for (Status tweet : tw ) 
		            {

		            	String body = tweet.getText();
		            	int retweetCount = tweet.getRetweetCount();
		            	int favorites = tweet.getFavoriteCount();
		            	BI = math(retweetCount, favorites, count, BI);
		            	//itemList.add(new TwitterItems(tweet.getUser().getScreenName(), tweet.getText(), tweet.getRetweetCount(), tweet.getFavoriteCount(), tweet.getLang()));
		                //System.out.println("Name: @" + tweet.getUser().getScreenName() + "| Text: " + tweet.getText() + "|Retweets: " + tweet.getRetweetCount() + "|Favorites: " + tweet.getFavoriteCount() + "|Language: " + tweet.getLang());
		                
		                //return BI;
		            }
		            return BI;
				}
				catch(TwitterException e)
				{
					e.printStackTrace();
				}
				return  BI;
			}		
	
	public BatchItems math(int a, int b, int c, BatchItems bi)
	{
		//a = retweet | b = favorites | c= tweet count | BI = bi
		//Test to make sure its working.
		bi.setLikesTotal(bi.getRetweetTotal() + a); 

		bi.setRetweetTotal(bi.getLikesTotal() + b);
		return bi;
	}

	public List<BatchItems> dataOutput() {
		//This is suppose to return the list of everything...I THINK?????
		return null;
	}
}
