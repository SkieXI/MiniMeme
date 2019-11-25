package business;

import java.util.List;
import java.util.Timer;

import beans.BatchItems;

public class TwitterBusiness{


	public void timer()
	{
		TimerTask task = new TimerScheduleFixedRateDelay();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 900000);
	}

	pubic BatchItems(String a, int b)
	{
		
	}
}
