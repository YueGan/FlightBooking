package phaseII;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * a class that can take a String with the correct format and 
 * change it to a Calendar.
 * @author jeff
 */
public class NewCalendar {
	
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private Calendar dateCalendar;
	
	/**
	 *take a string with correct format and turn it into  a
	 *Calendar type.
	 * @param date: a string that have a format of "yyyy-MM-dd HH:mm" 
	 */
	
	public NewCalendar(String date){

		year =Integer.parseInt(date.substring(0, 4));
		month = Integer.parseInt(date.substring(5, 7));
		day = Integer.parseInt(date.substring(8, 10));
		hour = Integer.parseInt(date.substring(11, 13));
		minute = Integer.parseInt(date.substring(14, 16));
		dateCalendar  = new GregorianCalendar(year,(month-1),day,hour,minute);
	
		
	}
	
	/**
	 * 
	 * @return return the Calendar representation of the string
	 */
	public Calendar result(){
		return dateCalendar;
	}

		
		
}

	


