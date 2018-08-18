/** A JUnit test class to test the class ApptTest. */

package calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import java.util.Arrays;
import java.io.*;
import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;
public class ApptTest  {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      appt0.setValid();
      assertEquals(15, appt0.getStartHour());
      assertEquals(30, appt0.getStartMinute());
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals(0, appt0.getRecurIncrement());
      assertEquals("Birthday Party",appt0.getTitle());
      assertTrue(!appt0.getValid());
      
  }
  
@Test(timeout = 4000)
  public void test01()  throws Throwable  {
      Appt appt1 = new Appt(25, 2, 2019, "Next Birthday Party", "Birthday party next year", "xyz@gmail.com");
           appt1.setValid();
      assertEquals(2, appt1.getRecurBy());
      assertFalse(appt1.isRecurring());
	assertEquals(25, appt1.getStartDay());
	assertEquals(2, appt1.getStartMonth());
        assertEquals(2019, appt1.getStartYear());
	assertEquals("Birthday party next year", appt1.getDescription());
        assertEquals("xyz@gmail.com", appt1.getEmailAddress());
      assertEquals(0, appt1.getRecurIncrement());
      assertEquals("Next Birthday Party",appt1.getTitle());
      assertTrue(!appt1.getValid()); 
  }
//---------setValid()
  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      Appt appt2 = new Appt(24, 00, 9, 14, 2018, "Midnight", "This is time for midnight", "xyz@gmail.com");
      appt2.setValid();
//	appt2.setStartHour(-1);
//	appt2.setValid();
//	appt2.setStartMinute(-1);
//	appt2.setValid();
//	appt2.setStartMonth(-1);
//	appt2.setValid();
      assertFalse(appt2.getValid());
  }

  @Test(timeout = 4000)
  public void test02a()  throws Throwable  {
	Appt appt2a = new Appt(23, 61, 9, 14, 2018, "minutes-test", "This is minutes-test", "xyz@gmail.com");
	appt2a.setValid();
	assertFalse(appt2a.getValid());
  }

  @Test(timeout = 4000)
  public void test02b()  throws Throwable  {
        Appt appt2b = new Appt(23, 59, 33, 5, 2018, "Day-test", "This is day-test", "xyz@gmail.com");
        appt2b.setValid();
        assertFalse(appt2b.getValid());
  }

  @Test(timeout = 4000)
  public void test02c()  throws Throwable  {
        Appt appt2c = new Appt(23, 56, 9, 33, 2018, "month-test", "This is month-test", "xyz@gmail.com");
        appt2c.setValid();
        assertFalse(appt2c.getValid());
  }
  @Test(timeout = 4000)
  public void test02d()  throws Throwable  {
        Appt appt2d = new Appt(23, 22, 9, 9, -1, "year-test", "This is year-test", "xyz@gmail.com");
        appt2d.setValid();
        assertFalse(appt2d.getValid());
  }

  @Test(timeout = 4000)
  public void test02e()  throws Throwable  {
        Appt appt2e = new Appt(23, 20, 29, 2, 2018, "dayofmonth-test", "This is minutes-test", "xyz@gmail.com");
        appt2e.setValid();
        assertFalse(appt2e.getValid());
  }

  @Test(timeout = 4000)
  public void test02ee()  throws Throwable  {
        Appt appt2ee = new Appt(23, 20, 26, 2, 2018, "dayofmonth-testt", "This is minutes-testt", "xyz@gmail.com");
        appt2ee.setValid();
        assertFalse(!appt2ee.getValid());
  }


  @Test(timeout = 4000)
  public void test02f()  throws Throwable  {
      Appt appt2f = new Appt(15, 30, 9, 12, 2018, null, null, null);
	appt2f.setValid();
  }





//---------setRecurrence()
  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      Appt appt3 = new Appt(14, 30, 8, 3, 2018, "Appointment", "Appointment with Nick", "xzy@gmail.com");
      int [] num = null;
      appt3.setRecurrence(num, 2, 1, 1);
      assertTrue(appt3.isRecurring());
      assertEquals(2, appt3.getRecurBy());
      assertEquals(1, appt3.getRecurNumber());
      assertEquals(1, appt3.getRecurIncrement());
      assertTrue(appt3.getValid());
      
  }
 @Test(timeout = 4000)
  public void test04()  throws Throwable  
  {
      Appt appt4 = new Appt(12, 12, 2018, "Go hiking", "This is hiking day", "xzy@gmail.com");
      String string0 = appt4.toString();
      assertEquals("\t12/12/2018 at -1:-1am ,Go hiking, This is hiking day\n", string0);
  }    

 @Test(timeout = 4000)
  public void test05()  throws Throwable
  {
      Appt appt5 = new Appt(15, 30, 12, 12, 2018, "Go hiking", "This is hiking day", "xzy@gmail.com");
      String string0 = appt5.toString();
      assertEquals("\t12/12/2018 at 3:30pm ,Go hiking, This is hiking day\n", string0);
  }

 @Test(timeout = 4000)
  public void test055()  throws Throwable
  {
      Appt appt5 = new Appt(5, 30, 12, 12, 2018, "Go hiking", "This is hiking day", "xzy@gmail.com");
	String string0 = appt5.toString();
	assertEquals("\t12/12/2018 at 5:30am ,Go hiking, This is hiking day\n", string0);
  }


 @Test(timeout = 4000)
  public void test06()  throws Throwable
  {
      Appt appt6 = new Appt(9, 30, 12, 12, 2018, "Go hiking", "This is hiking day", "xzy@gmail.com");
      String string0 = appt6.toString();
      assertEquals("\t12/12/2018 at 9:30am ,Go hiking, This is hiking day\n", string0);
  }
 @Test(timeout = 4000)
  public void test07()  throws Throwable
  {
      Appt appt7 = new Appt(0, 30, 12, 12, 2018, "Go hiking", "This is hiking day", "xzy@gmail.com");
      String string0 = appt7.toString();
      assertEquals("\t12/12/2018 at 12:30am ,Go hiking, This is hiking day\n", string0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
        Appt appt8 = new Appt(-1, 59, 33, 5, 2018, "Day-test", "This is day-test", "xyz@gmail.com");
        appt8.setValid();
        assertFalse(appt8.getValid());
  }
  @Test(timeout = 4000)
  public void test08a()  throws Throwable  {
        Appt appt8a = new Appt(11, -1, 33, 5, 2018, "Day-test", "This is day-test", "xyz@gmail.com");
        appt8a.setValid();
        assertFalse(appt8a.getValid());
  }

  @Test(timeout = 4000)
  public void test08b()  throws Throwable  {
        Appt appt8b = new Appt(11, 59, -1, 5, 2018, "Day-test", "This is day-test", "xyz@gmail.com");
        appt8b.setValid();
        assertFalse(appt8b.getValid());
  }

  @Test(timeout = 4000)
  public void test08c()  throws Throwable  {
        Appt appt8c = new Appt(11, 59, 5, -1, 2018, "Day-test", "This is day-test", "xyz@gmail.com");
        appt8c.setValid();
        assertFalse(appt8c.getValid());
  }
 @Test(timeout = 4000)
  public void test09()  throws Throwable
  {
      Appt appt9 = new Appt(0, 30, 12, 12, 2018, "Go hiking", "This is hiking day", "xzy@gmail.com");
      assertTrue(appt9.isOn(12,12, 2018));
  }

  @Test(timeout = 4000)
  public void test09a()  throws Throwable  {
      Appt appt0 = new Appt(10, 45, 10, 15, 2020, "Meeting", "This is a meeting", "work@gmail.com");
      assertFalse(appt0.isOn(5, 15, 2020));
  }

  @Test(timeout = 4000)
  public void test09b()  throws Throwable  {
      Appt appt0 = new Appt(10, 45, 10, 15, 2020, "Meeting", "This is a meeting", "work@gmail.com");
      assertFalse(appt0.isOn(10, 5, 2020));
  }

  @Test(timeout = 4000)
  public void test09c()  throws Throwable  {
      Appt appt0 = new Appt(10, 45, 10, 15, 2020, "Meeting", "This is a meeting", "work@gmail.com");
      assertFalse(appt0.isOn(10, 15, 2010));
  }


  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 5, 13, 5, "Meeting", "This is a meeting", "work@gmail.com");
            appt0.setValid();
      assertFalse(appt0.getValid());
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      Appt appt0 = new Appt(25, 9, 5, 13, 2015, "Meeting", "This is a meeting", "work@gmail.com");
            appt0.setValid();
      assertFalse(appt0.getValid());
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      Appt appt0 = new Appt(5, 100, 5, 13, 5, "Meeting", "This is a meeting", "work@gmail.com");
            appt0.setValid();
      assertFalse(appt0.getValid());
  }

 @Test(timeout = 4000)
  public void test13()  throws Throwable
  {
      Appt appt7 = new Appt(14, 30, 12, 12, 2018, "Go hiking", "This is hiking day", "xzy@gmail.com");
      String string0 = appt7.toString();
      assertEquals("\t12/12/2018 at 2:30pm ,Go hiking, This is hiking day\n", string0);
  }

 @Test(timeout = 4000)
  public void test13a()  throws Throwable
  {
      Appt appt7 = new Appt(1, 30, 12, 12, 2018, "Go hiking", "This is hiking day", "xzy@gmail.com");
      String string0 = appt7.toString();
      assertEquals("\t12/12/2018 at 1:30am ,Go hiking, This is hiking day\n", string0);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 33, 11, 5, "Meeting", "This is a meeting", "work@gmail.com");
            appt0.setValid();
      assertFalse(appt0.getValid());
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      Appt appt0 = new Appt(-1, 5, 5, 13, 2015, "Meeting", "This is a meeting", "work@gmail.com");
            appt0.setValid();
      assertFalse(appt0.getValid());
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 5, 13, -1, "Meeting", "This is a meeting", "work@gmail.com");
        appt0.setValid();
      assertFalse(appt0.getValid());
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      Appt appt0 = new Appt(5, -1, 5, 13, 2015, "Meeting", "This is a meeting", "work@gmail.com");
        appt0.setValid();
      assertFalse(appt0.getValid());
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 5, 5, -1, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      Appt appt0 = new Appt(0, 0, 10, -100, 2018, "Party", "Some party", "party@gmail.com");
      appt0.setValid();
      String string0 = appt0.toString();
      assertFalse(appt0.getValid());
  }

  @Test(timeout = 4000)
  public void test199()  throws Throwable  {
      Appt appt0 = new Appt(0, 0, 10, 10, -1, "Party", "Some party", "party@gmail.com");
      appt0.setValid();
      String string0 = appt0.toString();
      assertFalse(appt0.getValid());
  }


 @Test(timeout = 4000)
  public void test20()  throws Throwable
  {
      Appt appt4 = new Appt(8, 30, 12, 12, 2018, "Go hiking", "This is hiking day", "xzy@gmail.com");
      String string0 = appt4.toString();
      assertEquals("\t12/12/2018 at 8:30am ,Go hiking, This is hiking day\n", string0);
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      Appt appt0 = new Appt(2, 25, 2019, "Birthday", "Next birthday", "xzy@gmail.com");
      assertFalse(appt0.isRecurring());
  }
  
  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, CalendarUtil.NumDaysInMonth(5, 5-1), 5, 5, "Meeting", "This is a meeting", "xzy@gmail.com");
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      Appt appt0 = new Appt(10, 50, 31, 12, 2018, "Last day", "Last day in 2018", "xzy@gmail.com");
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      Appt appt0 = new Appt(0, 25, 3, 3, 2018, "Meeting", "This is a meeting", "xzy@gmail.com");
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      Appt appt0 = new Appt(20, 0, 3, 9, 2018, "Meeting", "This is a meeting", "xzy@gmail.com");
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      Appt appt0 = new Appt(20, 59, 10, 11, 2019, "Special", "This is a special day", "xzy@gmail.com");
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  @Test(timeout = 4000)
  public void test27()  throws Throwable  {
      Appt appt0 = new Appt(14, 30, 7, 11, 0, "Appointment with Nick", "Remember this appointment", "xzy@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }



}
