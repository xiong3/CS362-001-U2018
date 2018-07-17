
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import java.io.*;
import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.GregorianCalendar;
import java.util.LinkedList;


public class DataHandlerTest{
  @Before
  public void setUp() {
      File testfile = new File("calendar_test.xml");
      testfile.delete();
      testfile = new File("calendar.xml");
      testfile.delete();
  }

  @After
  public void tearDown() {
      File testfile = new File("calendar_test.xml");
      testfile.delete();
      testfile = new File("calendar.xml");
      testfile.delete();
  }

// save appointment 
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
	DataHandler dhandler = new DataHandler();
	Appt appt0 = new Appt(11, 30, 18, 7, 2018, "Lunch time", "Have lunch with my friends", "xyz@gmail.com");
	boolean output = dhandler.saveAppt(appt0);
	assertTrue("saved", output);
	Appt appt1 = new Appt(11, 30, 7, 18, 2018, "lunch time", "have lunch with my friends", "xyz@gmail.com");
	appt1.setValid();
	assertFalse("invalid appointment", appt1.getValid());
	output = dhandler.saveAppt(appt1);
	assertFalse("saved bad appointment", output);
  }

//save recurrence appointment
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      GregorianCalendar gday = new GregorianCalendar(2018, 5, 10);
      CalDay cday = new CalDay(gday);
      Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Get up", "Time to get up", "xzy@gmail.com");
      int[] recurDaysArr = {2, 3, 5};
      appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
      cday.addAppt(appt0);
      DataHandler data0 = new DataHandler("calendar_test.xml");
      assertTrue(data0.saveAppt(appt0)); 

  }  

//save appointment
  @Test(timeout = 4000)
  public void test011()  throws Throwable  {
        GregorianCalendar gday = new GregorianCalendar(2018, 5, 10);
      CalDay cday = new CalDay(gday);
      Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
      cday.addAppt(appt0);
      DataHandler data0 = new DataHandler("calendar_test.xml");
      assertTrue(data0.saveAppt(appt0));
  }

//delete appointment
  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
  	DataHandler dhandler = new DataHandler();
	Appt appt1 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	boolean output = dhandler.saveAppt(appt1);
	assertTrue("save3", output);
	output = dhandler.deleteAppt(appt1);
	assertTrue("save4", output);

  }

//save recurrence appointment 
    @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      GregorianCalendar gday = new GregorianCalendar(2018, 5, 10);
      CalDay cday = new CalDay(gday);
      Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
      int[] recurDaysArr = {2, 3, 5};
      appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
      cday.addAppt(appt0);
      DataHandler data0 = new DataHandler("calendar_test.xml");
      assertTrue(data0.saveAppt(appt0));

  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
		DataHandler dhandler = new DataHandler("calendar2.xml",false);
		Appt appt1 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		boolean output = dhandler.saveAppt(appt1);
		assertTrue("no autosave", output);
		output = dhandler.deleteAppt(appt1);
		assertTrue("no autosave", output);

  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(someday);
      Appt appt0 = new Appt(-100, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      day0.addAppt(appt0);
      DataHandler data0 = new DataHandler("calendar_test.xml");
      assertFalse(data0.saveAppt(appt0));

  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {

        GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
        CalDay day0 = new CalDay(someday);
        Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
        day0.addAppt(appt0);
        DataHandler data0 = new DataHandler("calendar_test.xml");
        data0.saveAppt(appt0);
        assertTrue(data0.deleteAppt(appt0));
  }

     @Test(timeout = 4000)
    public void test07()  throws Throwable  {
        GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
        CalDay day0 = new CalDay(someday);
        Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
        day0.addAppt(appt0);
        DataHandler data0 = new DataHandler("calendar_test.xml", false);
        data0.saveAppt(appt0);
        assertTrue(data0.deleteAppt(appt0));
    }

//delete invalid appointment 
    @Test(timeout = 4000)
    public void test08()  throws Throwable  {
        GregorianCalendar gday = new GregorianCalendar(2018, 5, 10);
        CalDay cday = new CalDay(gday);
        Appt appt0 = new Appt(-100, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
        appt0.setValid();
        cday.addAppt(appt0);
        DataHandler data0 = new DataHandler("calendar_test.xml", false);
        data0.saveAppt(appt0);
        assertFalse(data0.deleteAppt(appt0));
    }

// range -> first day is after last day as input
    @Test(expected = DateOutOfRangeException.class)
    public void test9()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 5, 5);
        GregorianCalendar lastday = new GregorianCalendar(2018, 5, 10);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(lastday, firstday);
        fail();
    }

// range -> first day is after last day
    @Test(timeout = 4000)
    public void test10()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 10, 20);
        Appt appt0 = new Appt(8, 10, 1, 5, 2050, "Meeting", "This is a meeting", "work@gmail.com");
        int[] recurDaysArr = {2,3,4};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_YEARLY, 2, Appt.RECUR_NUMBER_FOREVER);
        appt0.setValid();
        data0.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                numberappt++;
            }
        }
        assertEquals(0, numberappt);
    }
// range -> first day is before start day
    @Test(timeout = 4000)
    public void test11()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar lastday = new GregorianCalendar(2050, 10, 20);
        Appt appt0 = new Appt(8, 10, 1, 5, 2000, "Meeting", "This is a meeting", "work@gmail.com");
        int[] recurDaysArr = {2,3,4};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_YEARLY, 2, Appt.RECUR_NUMBER_FOREVER);
        appt0.setValid();
        data0.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                numberappt++;
            }
        }
        assertEquals(33, numberappt);
    }

// range -> recurDay not defined
    @Test(timeout = 4000)
    public void test12()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 1, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "Event", "This is an event.", "home@yahoo.com");
        int[] recurDaysArr = {};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
        appt0.setValid();
        data0.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                numberappt++;
            }
        }
        assertEquals(5, numberappt);
    }


// range -> recurDay out of valid range 
    @Test(timeout = 4000)
    public void test13()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 10, 20);
        Appt appt0 = new Appt(8, 10, 1, 5, 2000, "Meeting", "This is a meeting", "work@gmail.com");
        int[] recurDaysArr = {100, 200, 300};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
        appt0.setValid();
        data0.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                numberappt++;
            }
        }
        assertEquals(0, numberappt);
    }

// range -> tester define recurDays
    @Test(timeout = 4000)
    public void test14()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar lastday = new GregorianCalendar(2019, 10, 20);
        Appt appt0 = new Appt(8, 10, 1, 5, 2000, "Meeting", "This is a meeting", "work@gmail.com");
        int[] recurDaysArr = {1, 2, 3, 4, 5};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_MONTHLY, 2, Appt.RECUR_NUMBER_FOREVER);
        appt0.setValid();
        data0.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                numberappt++;
            }
        }
        assertEquals(22, numberappt);
    }

// range -> no appointments

    @Test(timeout = 4000)
    public void test15()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar lastday = new GregorianCalendar(2019, 10, 20);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                numberappt++;
            }
        }
        assertEquals(0, numberappt);
    }

// range -> reccrue once
    @Test(timeout = 4000)
    public void test16()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 1, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "Event", "This is an event.", "home@yahoo.com");
        int[] recurDaysArr = {1};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 1, 1);
        appt0.setValid();
        data0.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                numberappt++;
            }
        }
        assertEquals(2, numberappt);
    }

}
