/**CST-361
 * 10-27-19
 * This assignment was completed in collaboration with Joe Leon, and Lewis Brown.
 * This is suppose to contain a public static void main method that would be ran when MiniMeme is first started up.
 * Needless to say... it didn't work.
 * 
 */

package controllers;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import beans.BatchItems;
import beans.Search;
import beans.TwitterItems;
import business.TwitterInterface;
import business.TwitterREST;

@Named
@ViewScoped
@Stateless
public class TwitterController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	TwitterInterface<BatchItems> ti;

	//TwitterREST TR = new TwitterREST();

		public String JumpStart(Search search)// throws InterruptedException
		{
					FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("search", search);
					BatchItems bi = new BatchItems();
							
					bi = ti.wordSearch(search.getSearch(), search.getCount());
							
					//System.out.println(ti.wordSearch(search.getSearch(), search.getCount()));
					System.out.println(bi.getLikesTotal());
					//TR.outboundData(bi);
			return "JumpStartReply.xhtml";
		}
	}