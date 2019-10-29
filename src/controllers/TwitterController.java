/**CST-361
 * 10-27-19
 * This assignment was completed in collaboration with Joe Leon, and Lewis Brown.
 * This is suppose to contain a public static void main method that would be ran when MiniMeme is first started up.
 * Needless to say... it didn't work.
 * 
 */

package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import beans.Search;
import beans.TwitterItems;
import beans.TwitterResponseData;
import business.TwitterManager;

@ManagedBean
@ViewScoped
public class TwitterController {

	/**The main method of MiniMeme. It is suppose to run all the methods in the business layer, and the REST service.
	 * 
	 * @param args
	 */
		public static void main(String[] args) 
		{
		// TODO Auto-generated method stub
			
			System.out.println("Mini Test 1");
			TwitterManager tw = new TwitterManager();
			TwitterItems ti = new TwitterItems();
			System.out.println("Mini Test 2");
			TwitterResponseData TRD = new TwitterResponseData();
			Search s = new Search();
			
			System.out.println("Mini Test 3");
			//tw.PullnSave(s.getSearch(), s.getCount());
			tw.SaveNSave(ti);
			tw.getAllData();
	}

}
