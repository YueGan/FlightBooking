package phaseII;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * a class to check if the expiry date of creadit card
 * is a valid expiry date.
 * @author jeff
 *
 */
public class ValidityOfExpiryDate {
	
	private Calendar currentDate;
	private Calendar expiryDateObject;
	private int maxDay;
	private int day;
	private int month;
	private int year;
	/**
	 * takes expiry year, month and day, creates a new calendar object of it.
	 * @param expiryYear 
	 * @param expiryMonth
	 * @param expiryDay
	 * @throws InvalidInputException
	 */
	public  ValidityOfExpiryDate(String expiryYear,String expiryMonth, String expiryDay) throws InvalidInputException{
		if (expiryYear.matches("[0-9]+")&&
			expiryYear.matches("[0-9]+")&&
			expiryYear.matches("[0-9]+")
			){

			year = Integer.parseInt(expiryYear);
			month = Integer.parseInt(expiryMonth);
			day = Integer.parseInt(expiryDay);
			currentDate = Calendar.getInstance();
			
			if(month == 1){
				expiryDateObject = new GregorianCalendar(year,Calendar.JANUARY,day);
				}
			if (month == 2){
			 	expiryDateObject = new GregorianCalendar(year,Calendar.FEBRUARY,day);
				}
			if (month == 3){
			 	expiryDateObject = new GregorianCalendar(year,Calendar.MARCH,day);
				}
			if (month == 4){
			 	expiryDateObject = new GregorianCalendar(year,Calendar.APRIL,day);
				}
			if (month == 5){
			 	expiryDateObject = new GregorianCalendar(year,Calendar.MAY,day);
				}
			if (month == 6){
			 	expiryDateObject = new GregorianCalendar(year,Calendar.JUNE,day);
				}
			if (month == 7){
			 	expiryDateObject = new GregorianCalendar(year,Calendar.JULY,day);
				}
			if (month == 8){
			 	expiryDateObject = new GregorianCalendar(year,Calendar.AUGUST,day);
				}
			if (month == 9){
			 	expiryDateObject = new GregorianCalendar(year,Calendar.SEPTEMBER,day);
				}
			if (month == 10){
			 	expiryDateObject = new GregorianCalendar(year,Calendar.OCTOBER,day);
				}
			if (month == 11){
			 	expiryDateObject = new GregorianCalendar(year,Calendar.NOVEMBER,day);
				}
			if (month == 12){
			 	expiryDateObject = new GregorianCalendar(year,Calendar.DECEMBER,day);
				}
			if (month < 1||
			   month > 12)
				{
	        	throw new InvalidInputException();

			}
			
			//set maxDay as the max day of the month 
		 	maxDay = expiryDateObject.getActualMaximum(Calendar.DAY_OF_MONTH);		
		}

		else{
			throw new InvalidInputException();
		}
	}
		
		/**
		 * 
		 * @return a boolean that checks if expiry day is valid
		 */
		public Boolean isValid(){
			return currentDate.before(expiryDateObject)&&
					day <= maxDay&&
					day >= 1;
	}
		
}
