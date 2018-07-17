/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import java.util.*;

public class CalDayTest{

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      GregorianCalendar gday = new GregorianCalendar(2018, 7, 15);
      CalDay cday = new CalDay(gday);
      assertTrue(cday.isValid());
      String string0 = cday.toString();
      String string1 = "\t --- 9/15/2018 --- \n --- -------- Appointments ------------ --- \n\n";
      assertTrue(string0.equals(string1));

  }
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      GregorianCalendar gday = new GregorianCalendar(2018, 9, 9);
      CalDay cday = new CalDay(gday);
      Appt appt0 = new Appt(10, 10, 2018, "Go to school", "Appointment with nick", "xzy@gmail.com");
      Appt appt1 = new Appt(9, 55, 10, 10, 2018, "Go to library", "Study with john", "xzy@gmail.com");
      Appt appt2 = new Appt(20, 10, 10, 10, 2018, "Go to mix", "Have dinner with my parents", "xzy@gmail.com");
      cday.addAppt(appt0);
      cday.addAppt(appt1);
      cday.addAppt(appt2);
      String string0 = cday.getFullInfomrationApp(cday);
	assertEquals(3, cday.getSizeAppts());
      assertEquals("10-9-2018 \n\tGo to school Appointment with nick \n\t9:55AM Go to library Study with john \n\t8:10PM Go to mix Have dinner with my parents ", string0);

  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  
  {
     CalDay cday = new CalDay();
     assertFalse(cday.isValid());
     assertNull(cday.iterator());
     String str0 = cday.toString();
     assertEquals(0, str0.length());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      GregorianCalendar gday = new GregorianCalendar(2018, 9, 9);
      CalDay cday = new CalDay(gday);
      assertEquals(0, cday.getSizeAppts());
  }


  @Test(timeout = 4000)
  public void test04()  throws Throwable  
  {
      DataHandler dhandler = new DataHandler();
      GregorianCalendar calendar = new GregorianCalendar(2020, 4, 20);
      CalDay cday = new CalDay(calendar);
      Appt appt4 = new Appt(15, 30, 11, 11, 2018, "Single Party", "This is my single party", "xyz@gmail.com");
      cday.addAppt(appt4);
      cday.getFullInfomrationApp(cday);
      assertEquals(1, cday.getSizeAppts());
      String string1 = cday.toString();
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable
  {
      DataHandler dhandler = new DataHandler();
      GregorianCalendar calendar = new GregorianCalendar(2020, 4, 20);
      CalDay cday = new CalDay(calendar);
      Appt appt4 = new Appt(0, 30, 9, 10, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      cday.addAppt(appt4);
      cday.getFullInfomrationApp(cday);
      assertEquals(1, cday.getSizeAppts());
      String string1 = cday.toString();
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable
  {
      DataHandler dhandler = new DataHandler();
      GregorianCalendar calendar = new GregorianCalendar(2020, 4, 20);
      CalDay cday = new CalDay(calendar);
      Appt appt4 = new Appt(0, 9, 9, 10, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      cday.addAppt(appt4);
      cday.getFullInfomrationApp(cday);
      assertEquals(1, cday.getSizeAppts());
      String string1 = cday.toString();
  }


}
