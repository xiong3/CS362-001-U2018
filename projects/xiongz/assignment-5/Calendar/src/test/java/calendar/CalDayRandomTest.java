package calendar;


import java.util.Calendar;
import java.util.Random;
import calendar.Appt;
import calendar.CalDay;
import java.util.*;

import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	private static final long TestTimeout = 15 * 500 * 1;
    /**
     * Generate Random Tests that tests CalDay Class.
     */
	 @Test
	  public void radnomtest()  throws Throwable  {
		 
		long startTime = Calendar.getInstance().getTimeInMillis();
 		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
 		System.out.println("Start testing...");

 		try{
 			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed = System.currentTimeMillis();		 
				Random random = new Random(randomseed);
		  		int startHour=ValuesGenerator.getRandomIntBetween(random, -1, 24);
		  		int startMinute=ValuesGenerator.getRandomIntBetween(random, -1, 60);
		  		int startDay=ValuesGenerator.getRandomIntBetween(random, -1, 32);
		  		int startMonth=ValuesGenerator.getRandomIntBetween(random, -1, 13);
		  		int startYear=ValuesGenerator.getRandomIntBetween(random, -2018, 2018);
				String title="Birthday Party";
				String description="This is my birthday party.";
				String emailAddress="xyz@gmail.com";
		        	Appt appt0 = new Appt(startHour, 
						      startMinute, 
						      startDay ,
						      startMonth ,
						      startYear ,
						      title, 
						      description, 
						      emailAddress);
		 		appt0.setValid();

				startHour=ValuesGenerator.getRandomIntBetween(random, -30, 30);
		 		startMinute=ValuesGenerator.getRandomIntBetween(random, -100, 100);
			 	Appt appt1 = new Appt(startHour, startMinute, startDay,startMonth,startYear,title,description,emailAddress);
				appt1.setValid();

			 	startHour=ValuesGenerator.getRandomIntBetween(random, -30, 30);
		 		startMinute=ValuesGenerator.getRandomIntBetween(random, -100, 100);
			 	Appt appt2 = new Appt(startHour, startMinute, startDay,startMonth,startYear,title,description,emailAddress);
				appt2.setValid();

				GregorianCalendar g_day = new GregorianCalendar(startYear, startMonth, startDay);
	      			CalDay c_day = new CalDay(g_day);			 	
				c_day.addAppt(appt1);
		  		c_day.addAppt(appt2);
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			   	if((iteration%10000)==0 && iteration!=0 )
					System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			}
		}catch(NullPointerException e){
		}

		System.out.println("Done testing...");
 	}

	
}
