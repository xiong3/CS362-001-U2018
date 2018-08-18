package calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import java.util.Calendar;
import java.util.Random;

import calendar.Appt;
import calendar.CalDay;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;

import java.util.Random;

import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for DataHandler class.
 */

public class DataHandlerRandomTest {
	 private static final long TestTimeout = 60 * 500 * 1;
     
     @Before
     public void setUp() {
         File testfile = new File("calendar_test.xml");
         testfile.delete();
     }	

     @After
     public void tearDown() {
         File testfile = new File("calendar_test.xml");
         testfile.delete();
     }
 

	 @Test
	  public void radnomtest()  throws Throwable  {
		  long startTime = Calendar.getInstance().getTimeInMillis();
		  long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		  System.out.println("Start testing...");

		  for (int iteration = 0; elapsed < TestTimeout; iteration++) {
			  try{

				 long randomseed =System.currentTimeMillis();
 				 Random random = new Random(randomseed);
				// autosave
				 boolean autosaveSet=ValuesGenerator.getBoolean(.50f, random);		      
				// random start
 				 int startDay=ValuesGenerator.getRandomIntBetween(random, -1, 32);
 				 int startMonth=ValuesGenerator.getRandomIntBetween(random, -1, 13);
 				 int startYear=ValuesGenerator.getRandomIntBetween(random, 2018, 2019);
				// random end 
 				 int endDay=ValuesGenerator.getRandomIntBetween(random, -1, 32);
 				 int endMonth=ValuesGenerator.getRandomIntBetween(random, -1, 13);
 				 int endYear=ValuesGenerator.getRandomIntBetween(random, 2019, 2020);
				// random number of appointments
				 int numAppts=ValuesGenerator.getRandomIntBetween(random, 0, 10);
				// random first_day and last_day
				 DataHandler data0 = new DataHandler("calendar_test.xml", autosaveSet);
				 GregorianCalendar firstday = new GregorianCalendar(startYear, startMonth, startDay);
				 GregorianCalendar lastday = new GregorianCalendar(endYear, endMonth, endDay);
				//
				 if(ValuesGenerator.getBoolean(.10f, random)) {
				 	Field f1 = data0.getClass().getDeclaredField("valid");
		         	 f1.setAccessible(true);
		         	 f1.set(data0, false);
			 	 }

				 while(numAppts > 0){
					int apptHour=ValuesGenerator.getRandomIntBetween(random, -1, 24);
					int apptMinute=ValuesGenerator.getRandomIntBetween(random, -1, 60);
					int apptDay=ValuesGenerator.getRandomIntBetween(random, -1, 32);
					int apptMonth=ValuesGenerator.getRandomIntBetween(random, -1, 13);
					int apptYear=ValuesGenerator.getRandomIntBetween(random, 2018, 2020);

					Appt appt0 = new Appt(apptHour, apptMinute, apptDay, apptMonth, apptYear, "Event", "This is an event.", "home@yahoo.com");
					// set up the recurrence 
					int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
					int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
					int recur=ApptRandomTest.RandomSelectRecur(random);
					int recurIncrement = ValuesGenerator.RandInt(random);
					int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
					appt0.setRecurrence(recurDays, recur, recurIncrement, recurNumber);

				 	appt0.setValid();

					if(ValuesGenerator.getBoolean(.50f, random)) {
				 		data0.saveAppt(appt0);
					}

					if(ValuesGenerator.getBoolean(.10f, random)) {
						data0.deleteAppt(appt0);
					}

					numAppts--;
			 	 }

				 LinkedList<CalDay> calDays = new LinkedList<CalDay>();
				 calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);

				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				 if(iteration!=0)
					 System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);

	 			}catch(DateOutOfRangeException e){
	 			}


     }
		 
System.out.println("Done testing...");		 
	 }


	
}
