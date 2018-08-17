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

/*
  @Test(timeout = 4000)
  public void test01a  throws Throwable  {
	GergorianCalendar gday = new GregorianCalendar(2018, 9, 9);
	CalDay cday = new CalDay(gday);
	Appt appt0 = new Appt(10, 10, 2018, "Go to school", "Appointment with nick", "xzy@gmail.com");
	GergorianCalendar gday1 = new GregorianCalendar(2018, 10, 9);
        CalDay cday1 = new CalDay(gday1);
        Appt appt1 = new Appt(10, 10, 2018, "Go to school", "Appointment with nick", "xzy@gmail.com");
	cday.addAppt(appt0);
	cday1.addAppt(appt1);
  }
*/

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

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(someday);
      Appt appt0 = new Appt(10, 5, 7, 6, 2018, "Meeting1", "This is a meeting", "work@gmail.com");
      Appt appt1 = new Appt(8, 5, 7, 6, 2018, "Meeting2", "This is a meeting", "work@gmail.com");
      Appt appt2 = new Appt(14, 5, 7, 6, 2018, "Meeting3", "This is a meeting", "work@gmail.com");
      Appt appt3 = new Appt(14, 5, 7, 6, 2018, "Meeting4", "This is a meeting", "work@gmail.com");
      Appt appt4 = new Appt(23, 5, 7, 6, 2018, "Meeting5", "This is a meeting", "work@gmail.com");
      day0.addAppt(appt0);
      day0.addAppt(appt1);
      day0.addAppt(appt2);
      day0.addAppt(appt3);
      day0.addAppt(appt4);
      String string0 = day0.toString();
      assertEquals("\t --- 7/10/2018 --- \n --- -------- Appointments ------------ --- \n" + "\t6/7/2018 at 8:5am ,Meeting2, This is a meeting\n" + " \t6/7/2018 at 10:5am ,Meeting1, This is a meeting\n" + " \t6/7/2018 at 2:5pm ,Meeting3, This is a meeting\n" + " \t6/7/2018 at 2:5pm ,Meeting4, This is a meeting\n" + " \t6/7/2018 at 11:5pm ,Meeting5, This is a meeting\n" + " \n", string0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(someday);
      Appt appt0 = new Appt(7, 6, 2018, "Meeting No Time", "This is a meeting with no set time.", "work@gmail.com");
      Appt appt1 = new Appt(13, 5, 7, 6, 2018, "Meeting Afternoon", "This is a meeting.", "work@gmail.com");
      Appt appt2 = new Appt(20, 10, 7, 6, 2018, "Meeting Late At Night", "This is a meeting.", "work@gmail.com");
      day0.addAppt(appt0);
      day0.addAppt(appt1);
      day0.addAppt(appt2);
      String string0 = day0.getFullInfomrationApp(day0);
      assertEquals("6-10-2018 \n\tMeeting No Time This is a meeting with no set time. \n\t1:05PM Meeting Afternoon This is a meeting. \n\t8:10PM Meeting Late At Night This is a meeting. ", string0);
  }


  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(someday);
      Appt appt0 = new Appt(7, 6, 2018, "Meeting No Time", "This is a meeting with no set time.", "work@gmail.com");
      Appt appt1 = new Appt(12, 5, 7, 6, 2018, "Meeting Early", "This is a meeting.", "work@gmail.com");
      Appt appt2 = new Appt(0, 5, 7, 6, 2018, "Meeting Early", "This is a meeting.", "work@gmail.com");
      Appt appt3 = new Appt(20, 10, 7, 6, 2018, "Meeting Late At Night", "This is a meeting.", "work@gmail.com");
      day0.addAppt(appt0);
      day0.addAppt(appt1);
      day0.addAppt(appt2);
      day0.addAppt(appt3);
      String string0 = day0.getFullInfomrationApp(day0);
      assertEquals("6-10-2018 \n\tMeeting No Time This is a meeting with no set time. \n\t12:05AM Meeting Early This is a meeting. \n\t0:05AM Meeting Early This is a meeting. \n\t8:10PM Meeting Late At Night This is a meeting. ", string0);
  }

}
