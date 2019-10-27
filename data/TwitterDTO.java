package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.TwitterItems;
import beans.TwitterResponseData;
import beans.TwitterResponseDatas;

public class TwitterDTO implements TwitterDataInterface
{

	String url = "jdbc:mysql://localhost:3306/memesupreme";
	String username = "root";
	String password = "root";
	
	public List<TwitterItems> findAll() {
		// TODO Auto-generated method stub
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/music";
		String username = "root";
		String password = "root";
		
		List<TwitterItems> items = new ArrayList<TwitterItems>();
		try
		{
			conn = DriverManager.getConnection(url, username, password);
			
			String sql1 = "SELECT * FROM TWITTER";
			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			while (rs1.next()) 
			{
				TwitterItems ti = new TwitterItems(rs1.getString("TWEET"), rs1.getString("SCREEN_NAME"), rs1.getInt("RT_COUNT"), rs1.getInt("Likes"), rs1.getString("LANG"));
				items.add(ti);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			//throw new DatabaseException(e);
		}
		finally
		{
			  if(conn != null)
	            {
	                try
	                {
	                    conn.close();
	                }
	                catch (SQLException e)
	                {
	                    e.printStackTrace();
	                    //throw new DatabaseException(e);
	                }
	            }
	        }
		return items;
	}
	
	
	public TwitterItems findBy(TwitterItems ti) {
		// TODO Auto-generated method stub
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/memesupreme";
		String username = "root";
		String password = "root";
		try
		{
			conn = DriverManager.getConnection(url, username, password);
			
			String sql1 = String.format("SELECT * FROM TWITTER WHERE TWEET='%s' AND SCREEN_NAME='%s' AND RT_COUNT=%d AND HASH_TAGS='%s' AND LANG='%s' AND LIKES=%d",
										ti.getTweet(),
										ti.getScreenName(),
										ti.getRetweetCount(),
										ti.getHashtags(),
										ti.getLanguage(),
										ti.getLikes());
			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			if(!rs1.next())
			{
				rs1.close();
				stmt1.close();
				return null;
			}
			
			ti.setTweet(rs1.getString("TWEET"));
			ti.setScreenName(rs1.getString("SCREEN_NAME"));
			ti.setRetweetCount(rs1.getInt("RT_COUNT"));
			ti.setHashtags(rs1.getString("HASH_TAGS"));
			ti.setLanguage(rs1.getString("LANG"));
			ti.setLikes(rs1.getInt("LIKES"));
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			//throw new DatabaseException(e);
		}
		finally
		{
			  if(conn != null)
	            {
	                try
	                {
	                    conn.close();
	                }
	                catch (SQLException e)
	                {
	                    e.printStackTrace();
	                    //throw new DatabaseException(e);
	                }
	            }
		}
		return ti;
	}

	public boolean create(TwitterResponseData data) {
		Connection conn = null;
		try
		{
		conn = DriverManager.getConnection(url, username, password);
		String sql1 = String.format("INSERT INTO TWITTER(TWEET, SCREEN_NAME, RT_COUNT, LIKES, LANG) VALUES('%s', '%s', %d, %d, '%s')",
				data.getData().getScreenName(),
				data.getData().getTweet(),
				data.getData().getRetweetCount(),
				data.getData().getLikes(),
				data.getData().getLanguage());
				
		System.out.println(sql1);
		Statement stmt1 = conn.createStatement();
		stmt1.executeUpdate(sql1);
		
		stmt1.close();
		}
		//Everything below this is copied from FindBYID();
		catch (SQLException e)
		{
			e.printStackTrace();
			//throw new DatabaseException(e);
		}
		finally
		{
			if(conn != null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					//throw new DatabaseException(e);
				}
			}
		}
		return true;
	}
	
}
