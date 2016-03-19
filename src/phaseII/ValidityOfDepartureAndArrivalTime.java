package phaseII;

import java.util.Calendar;


/**
 * a class to check if the departure time is before
 * arrival time and if the time is valid.
 * @author jeff
 *
 */

public class ValidityOfDepartureAndArrivalTime {
		@SuppressWarnings("unused")
		private Calendar currentDate;
		private Calendar departureDateObject;
		private Calendar arrivalDateObject;
		private int maxDay1;
		private int maxDay2;
		private int airDay;
		private int depDay;
		
		/**
		 * 
		 * @param airYear arrival year
		 * @param airMonth arrival month
		 * @param airlDay arrival day
		 * @param airHour arrival hour
		 * @param airMinute arrival minute
		 * @param depYear
		 * @param depMonth
		 * @param airDay
		 * @param depHour
		 * @param depminute
		 * @throws InvalidInputException
		 */
		public ValidityOfDepartureAndArrivalTime(String departure, String arrival) throws InvalidInputException{
			
			//set departure date and arrival date to a calendar object
			NewCalendar c1 = new NewCalendar(departure);
			departureDateObject = c1.result();
			NewCalendar c2 = new NewCalendar(arrival);
			arrivalDateObject = c2.result();
			if( !(departureDateObject.get(Calendar.MONTH)<=12)||
				!(departureDateObject.get(Calendar.HOUR)<=24)||
				!(departureDateObject.get(Calendar.MINUTE)<=60)
				
					){
				throw new InvalidInputException("INVALID INPUT");
				
			} else{
			
				currentDate = Calendar.getInstance();
	  
				//set maxDay1  and maxDay2 as the maximum day of that month
			 	maxDay1 = departureDateObject.getActualMaximum(Calendar.DAY_OF_MONTH);
			 	maxDay2 = arrivalDateObject.getActualMaximum(Calendar.DAY_OF_MONTH);
			 	this.airDay = arrivalDateObject.get(Calendar.DAY_OF_MONTH);
			 	this.depDay = departureDateObject.get(Calendar.DAY_OF_MONTH);
			 	
			}
		}
			
		/**
		 * 
		 * @return a boolean tells if all the requirement satisfied.
		 */
		public Boolean isValid(){
			return departureDateObject.before(arrivalDateObject)&&
				   airDay < maxDay2 &&
				   depDay < maxDay1;
					
			
				   
		}
			
	
}

	


