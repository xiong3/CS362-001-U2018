
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
import java.lang.reflect.*;

import java.util.GregorianCalendar;
import java.util.LinkedList;


public class DataHandlerTest{

private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
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
//	int[] num = {2, 4, 6};
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
      Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Get up", "Time to get up", "xzy@gmail.com");
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

        GregorianCalendar gday = new GregorianCalendar(2018, 5, 10);
        CalDay cday = new CalDay(gday);
        Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
        cday.addAppt(appt0);
        DataHandler data0 = new DataHandler("calendar_test.xml");
        data0.saveAppt(appt0);
        assertTrue(data0.deleteAppt(appt0));
  }

     @Test(timeout = 4000)
    public void test07()  throws Throwable  {
        GregorianCalendar gday = new GregorianCalendar(2018, 5, 10);
        CalDay cday = new CalDay(gday);
        Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
        cday.addAppt(appt0);
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
            for(int j = 0; j < appts.size(); j++) {
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
            for(int j = 0; j < appts.size(); j++) {
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
            for(int j = 0; j < appts.size(); j++) {
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
            for(int j = 0; j < appts.size(); j++) {
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
            for(int j = 0; j < appts.size(); j++) {
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
            for(int j = 0; j < appts.size(); j++) {
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
            for(int j = 0; j < appts.size(); j++) {
                numberappt++;
            }
        }
        assertEquals(2, numberappt);
    }


    @Test(expected = NullPointerException.class)
    public void test18()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        data0 = null;
        GregorianCalendar firstday = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar lastday = new GregorianCalendar(2019, 10, 20);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);
        fail();
    }


    @Test(timeout = 4000)
    public void test19()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 1, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "Event", "This is an event.", "home@yahoo.com");
        int[] recurDaysArr = {1};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 1, 1);
        appt0.setValid();
        assertTrue(data0.saveAppt(appt0));
    }

    @Test(timeout = 4000)
    public void test20()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml", false);
        GregorianCalendar firstday = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 1, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "Event", "This is an event.", "home@yahoo.com");
        appt0.setValid();
        data0.saveAppt(appt0);
        File testfile = new File("calendar_test.xml");
        assertFalse(testfile.delete());
    }

    @Test(timeout = 4000)
    public void test21()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 1, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "Event", "This is an event.", "home@yahoo.com");
        appt0.setValid();
        data0.saveAppt(appt0);
        File testfile = new File("calendar_test.xml");
        assertTrue(testfile.delete());
    }



    @Test(timeout = 4000)
    public void test22()  throws Throwable  {
        DataHandler data = new DataHandler("calendar_test.xml");
        Field f1 = data.getClass().getDeclaredField("valid");
        f1.setAccessible(true);
        f1.set(data, false);
        GregorianCalendar day1 = new GregorianCalendar(2018, 2, 1);
        GregorianCalendar day2 = new GregorianCalendar(2018, 5, 1);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data.getApptRange(day1, day2);
    }

    @Test(timeout = 4000)
    public void test23()  throws Throwable  {
        DataHandler data = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 5, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "Birthday", "This is Nick's Birthday.", "xzy@yahoo.com");
        int[] recurDaysArr = {1};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 4, Appt.RECUR_NUMBER_FOREVER);
        appt0.setValid();
        data.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data.getApptRange(firstday, lastday);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int j = 0; j < appts.size(); j++) {
                numberappt++;
            }
        }
        assertEquals(22, numberappt);
    }

    @Test(timeout = 4000)
    public void test24()  throws Throwable  {
        DataHandler data = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 1, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "Event", "This is an event.", "home@yahoo.com");
        appt0.setValid();
        data.saveAppt(appt0);
        data.deleteAppt(appt0);
        assertNull(appt0.getXmlElement());
    }

    @Test(timeout = 4000)
    public void test25()  throws Throwable  {
        DataHandler data = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 1, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "Birthday", "This is Nick's Birthday.", "xzy@yahoo.com");
        int[] recurDaysArr = {8};
        appt0.setRecurrence(recurDaysArr, -404, 1, 100);
        appt0.setValid();
        data.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data.getApptRange(firstday, lastday);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int j = 0; j < appts.size(); j++) {
                numberappt++;
            }
        }
        assertEquals(1, numberappt);
    }

    @Test(timeout = 4000)
    public void test26()  throws Throwable  {
        DataHandler data = new DataHandler("calendar_test.xml", false);
        GregorianCalendar firstday = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 1, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "Birthday", "This is Nick's Birthday.", "xzy@yahoo.com");
        appt0.setValid();
        data.saveAppt(appt0);
        data.deleteAppt(appt0);
        File testfile = new File("calendar_test.xml");
        assertFalse(testfile.delete());
    }


    @Test(timeout = 4000)
    public void test27()  throws Throwable  {
        DataHandler data = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar lastday = new GregorianCalendar(2050, 10, 20);
        Appt appt0 = new Appt(8, 10, 1, 5, 2000, "Meeting", "This is a meeting", "work@gmail.com");
        appt0.setValid();
        data.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data.getApptRange(firstday, lastday);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int j = 0; j < appts.size(); j++) {
                numberappt++;
            }
        }
        assertEquals(0, numberappt);
    }


    @Test(timeout = 4000)
    public void test28()  throws Throwable  {
        DataHandler data = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 1, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "Event", "This is an event.", "home@yahoo.com");
        appt0.setValid();
        data.saveAppt(appt0);
        data.deleteAppt(appt0);
        File testfile = new File("calendar_test.xml");
        assertTrue(testfile.delete());
    }


    @Test(timeout = 4000)
    public void test29()  throws Throwable  {
        DataHandler data = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 1, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "Event", "This is an event.", "home@yahoo.com");
        int[] recurDaysArr = {7};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 1, 100);
        appt0.setValid();
        data.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data.getApptRange(firstday, lastday);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int j = 0; j < appts.size(); j++) {
                numberappt++;
            }
        }
        assertEquals(5, numberappt);
    }


    @Test(timeout = 4000)
    public void test30()  throws Throwable  {
        System.setOut(new PrintStream(outContent));
        DataHandler data = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 1, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "Event", "This is an event.", "home@yahoo.com");
        int[] recurDaysArr = {1};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 1, 1);
        appt0.setValid();
        data.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data.getApptRange(firstday, lastday);
        assertEquals("", outContent.toString());
    }

    @Test(timeout = 4000)
    public void test31()  throws Throwable  {
        GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
        CalDay day0 = new CalDay(someday);
        Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
        day0.addAppt(appt0);
        DataHandler data0 = new DataHandler("calendar_test.xml", false);
        assertFalse(data0.deleteAppt(appt0));
    }



}
