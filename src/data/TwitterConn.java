/**Joe Leon
**CST-361
**9-26-19
**This assignment was completed in collaboration with Joe Leon, and Lewis Brown.
**This class was around since Milestone 2 or 3 and is the core of the project. 
*/
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
	
	/**wordSearch more or less takes in a word and processes it and sends it off to the TwitterSearch engine.
	 * From there, it returns a list of Tweets and wordSearch processes the results of those tweets to send back to MemeSurpreme.
	 * @param String word
	 * @return BatchItems.
	 */
	@Override
	public BatchItems wordSearch(String word)
	{
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
					
					//query is then processed by the API. 
					QueryResult qr = twitter.search(query);
						
					//New list is made from the resutls of hte query when it was processed.
					List<Status> tw = qr.getTweets();
					//count is needed for the total number of tweets. 
					int count = 0;
					
					//For each result, the number of likes and retweets are collected and added together, then put into a
					//object of BatchItems that is then sent off to MemeSurpreme.
		            for (Status tweet : tw ) 
		            {
		            	//The int for the number of retweets an individual tweet gets.
		            	int retweetCount = tweet.getRetweetCount();
		            	
		            	///the Int of likes that a individual tweet gets.
		            	int favorites = tweet.getFavoriteCount();
		            	
		            	//Plus 1.
		            	++count;
		            	//Debuing code. 
		            	System.out.println(count);
		            	
		            	//A seperate method that does all the math.
		            	BI = math(retweetCount, favorites, BI);
		            	
		            }
		            return BI;
				}
				catch(TwitterException e)
				{
					e.printStackTrace();
				}
				return  BI;
			}		
	/** Takes in the likes and retweets from wordSearch, and add the previous tweets total with the current total. 
	 * 
	 * @param int a, int b, BatchItems bi
	 * @return BatchItems.
	 */
	public BatchItems math(int a, int b, BatchItems bi)
	{
		//a = retweet | b = favorites | c= tweet count | BI = bi
		//Test to make sure its working.
		bi.setLikesTotal(bi.getRetweetTotal() + a); 
		bi.setRetweetTotal(bi.getLikesTotal() + b);
		return bi;
	}
	
	/**This is what we had set up prior to Milestone 5.
	 * What this does is save each aspect of a tweet to a list of TwitterItems and then it gets sent off to MemeSurprme. 
	 * @param String word.
	 * @return List<TwitterItems>
	 */
	@Override
	public List<TwitterItems> dataOutput(String word)
	{
		//New list of TwitterItems called itemList.
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
			//query.count(count);
			
			QueryResult qr = twitter.search(query);
				
			List<Status> tw = qr.getTweets();
			
			//For reach tweet found in the search, its aspects are saved to itemList.
            for (Status tweet : tw ) 
            {
            	//New object of TwitterItems. 
            	TwitterItems items = new TwitterItems();
            	
            	//TwitterItems.ScreenName
            	items.setScreenName(tweet.getUser().getScreenName());
            	
            	//TwitterItems.Text
            	items.setTweet(tweet.getText());
            	
            	//TwitterItems.RetweetCount
            	items.setRetweetCount(tweet.getRetweetCount());
            	
            	//TwitterItems.FavoriteCounnt
            	items.setLikes(tweet.getFavoriteCount());
            	
            	//TwitterItems.Lang
            	items.setLanguage(tweet.getLang());
            	
            	//Once all the individual elements are set, it is then added to the List of itemList.
            	itemList.add(items);
            	
            	//For some fun, you can see the whole tweet by uncommiting the line below.
                //System.out.println("Name: @" + tweet.getUser().getScreenName() + "| Text: " + tweet.getText() + "|Retweets: " + tweet.getRetweetCount() + "|Favorites: " + tweet.getFavoriteCount() + "|Language: " + tweet.getLang());
            }
            
            //Return the TwitterItem object itemList.
            return itemList;
		}
		catch(TwitterException e)
		{
			e.printStackTrace();
		}
		return itemList;
	}		

}
