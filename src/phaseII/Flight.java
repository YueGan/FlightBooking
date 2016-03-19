package phaseII;

import java.io.Serializable;
import java.util.Arrays;

/**
 * A class that represent a Flight. This class is responsible for calculating
 * the travel from the orIgin to the destination.
 * 
 * @author Marhababanu
 */

public class Flight implements Serializable {
	/** Flight number of the Flight */
	private String flightNum;
	/** Year part of the departure date of the flight */
	private int depYear;
	/** Month part of the departure date of the flight */
	private int depMonth;
	/** Day part of the departure date of the flight */
	private int depDay;
	/** Hour part of the departure time of the flight */
	private int depHour;
	/** Minutes part of the departure time of the flight */
	private int depMinute;
	/** Year part of the arrival date of the flight */
	private int airYear;
	/** Month part of the arrival date of the flight */
	private int airMonth;
	/** Day part of the arrival date of the flight */
	private int airDay;
	/** Hour part of the arrival time of the flight */
	private int airHour;
	/** Minute part of the arrival time of the flight */
	private int airMinute;
	/** String representation of the departure date of the flight */
	private String depDate;
	/** String representation of the departure time of the flight */
	private String depTime;
	/** String representation of the arrival date of the flight */
	private String airDate;
	/** String representation of the arrival time of the flight */
	private String airTime;
	/** airline company of the flight */
	private String airline;
	/** Destination of the flight */
	private String destination;
	/** Origin of the flight */
	private String origin;
	/** Time taken (in mins)to get to destination from origin */
	private int travelTime;
	/** Price of the flight ticket */
	private Double price;
	/** Number of seat available in Flight*/
	private int numSeat;
	/** List of departure Date, origin and destination */
	private String[] searchInfo = new String[3];

	/**
	 * Creates a new Flight with the given flightNum, departureDateTime,
	 * arrivalDateTime airline, origin, destination and price. Also set the
	 * travel time of the Flight by calculate it using the departureDateTime and
	 * arrivalDateTime. throws InvalidInputException if any of the parameter is
	 * not in a valid format.
	 * 
	 * @param flightNum
	 *            represent the flight number of the Flight
	 * @param departureDateTime
	 *            represents the departure date and time of the Flight
	 * @param arrivalDateTime
	 *            represents the arrival date and time of the Flight
	 * @param airline
	 *            represents the airline company of the Flight
	 * @param origin
	 *            represents origin of the Flight
	 * @param destination
	 *            represents the destination of the Flight
	 * @param price
	 *            represents price of the ticket of the Flight
	 * @throws InvalidInputException
	 */
	public Flight(String flightNum, String departureDateTime,
			String arrivalDateTime, String airline, String origin,
			String destination, Double price, int numSeat) throws InvalidInputException {

		ValidityOfDepartureAndArrivalTime c1 = new ValidityOfDepartureAndArrivalTime(
				departureDateTime, arrivalDateTime);

		if (!flightNum.matches("[a-zA-Z0-9 ]*")
				|| !airline.matches("[a-zA-Z ]+")
				|| !origin.matches("[a-zA-Z ]+")
				|| !destination.matches("[a-zA-Z ]+") || !c1.isValid()
				|| !(departureDateTime.length() == 16)
				|| !(arrivalDateTime.length() == 16)

		) {
			throw new InvalidInputException("INVALID INPUT OF FLIGHT INFO");

		} else {
			this.flightNum = flightNum;
			this.airline = airline;
			this.origin = origin;
			this.destination = destination;
			this.price = price;
			this.numSeat = numSeat;
			setDepartureInfo(departureDateTime);
			setArrivalInfo(arrivalDateTime);
			setTravelTime();
			setSearchInfo();
		}
	}

	// below are the setter methods for the instance variables//

	/**
	 * Sets the Flight number of the Flight
	 * 
	 * @param newFlightNum
	 *            represents Flight Number of the Flight
	 */
	public void setFlightNum(String newFlightNum) {
		this.flightNum = newFlightNum;
	}

	/**
	 * Sets depDate, depTime, depYear, depMonth, depDay, depHour, depMinute of
	 * the flight
	 * 
	 * @param departureInfo
	 *            represents a String that contain departure time and date
	 *            information.
	 */
	public void setDepartureInfo(String departureInfo) {
		this.depDate = departureInfo.substring(0, 10);
		this.depTime = departureInfo.substring(11, 16);
		this.depYear = Integer.parseInt(departureInfo.substring(0, 4));
		this.depMonth = Integer.parseInt(departureInfo.substring(5, 7));
		this.depDay = Integer.parseInt(departureInfo.substring(8, 10));
		this.depHour = Integer.parseInt(departureInfo.substring(11, 13));
		this.depMinute = Integer.parseInt(departureInfo.substring(14, 16));
	}

	/**
	 * Sets airDate, airTime, airYear, airMonth, airDay, airpHour, airMinute of
	 * the flight
	 * 
	 * @param arrivalInfo
	 *            represents a String that contain arrival time and date
	 */
	public void setArrivalInfo(String arrivalInfo) {
		this.airDate = arrivalInfo.substring(0, 10);
		this.airTime = arrivalInfo.substring(11, 16);
		this.airYear = Integer.parseInt(arrivalInfo.substring(0, 4));
		this.airMonth = Integer.parseInt(arrivalInfo.substring(5, 7));
		this.airDay = Integer.parseInt(arrivalInfo.substring(8, 10));
		this.airHour = Integer.parseInt(arrivalInfo.substring(11, 13));
		this.airMinute = Integer.parseInt(arrivalInfo.substring(14, 16));
	}

	/**
	 * Sets airLine of the flight.
	 * 
	 * @param newAirline
	 *            represent the airline company of the Flight.
	 */
	public void setAirline(String newAirline) {
		this.airline = newAirline;
	}

	/**
	 * Sets the origin of the Flight.
	 * 
	 * @param newOrigin
	 *            represents the origin of the Flight.
	 */
	public void setOrigin(String newOrigin) {
		this.origin = newOrigin;
	}

	/**
	 * Sets the destination of the Flight.
	 * 
	 * @param newDestination
	 *            represents destination of the Flight.
	 */
	public void setDestination(String newDestination) {
		this.destination = newDestination;
	}

	/**
	 * Sets the price of the ticket of the Flight.
	 * 
	 * @param newPrice
	 *            represents the price of the Flight.
	 */
	public void setPrice(Double newPrice) {
		this.price = newPrice;
	}

	/**
	 * Sets the depYear (Departure Year) of the Flight.
	 * 
	 * @param year
	 *            represent the departure year of the Flight's departure date.
	 */
	public void setDepYear(int year) {
		this.depYear = year;
	}

	/**
	 * Sets the depMonth (Departure month) of the Flight.
	 * 
	 * @param month
	 *            represent the departure month of the Flight's departure date.
	 */
	public void setDepMonth(int month) {
		this.depMonth = month;
	}

	/**
	 * Sets the depDay (Departure day) of the Flight.
	 * 
	 * @param day
	 *            represent the departure day of the Flight's departure date
	 */
	public void setDepDay(int day) {
		this.depDay = day;
	}

	/**
	 * Sets the airYear (arrival Year) of the Flight.
	 * 
	 * @param year
	 *            represent the arrival year of the Flight's arrival date
	 */
	public void setAirYear(int year) {
		this.airYear = year;
	}

	/**
	 * Sets the airMonth (arrival Year) of the Flight.
	 * 
	 * @param year
	 *            represent the arrival Month of the Flight's arrival date
	 */
	public void setAirMonth(int month) {
		this.airMonth = month;
	}

	/**
	 * Sets the airDay (arrival day) of the Flight.
	 * 
	 * @param day
	 *            represent the arrival day of the Flight's arrival date
	 */
	public void setAirDay(int day) {
		this.airDay = day;
	}

	/**
	 * Sets the travelTime of the Flight by calculating it using departure and
	 * arrival time date.
	 */
	private void setTravelTime() {
		// helping variable to calculate the travel time.
		int tempDay;
		Integer[] oddMonths = { 1, 3, 5, 7, 8, 10, 12 };

		// assume depYear can never be greater than the airYear.
		if (this.depYear == this.airYear) {
			if (this.depMonth == this.airMonth) {
				this.travelTime = 1440 * (this.airDay - this.depDay) + 60
						* (this.airHour - this.depHour)
						+ (this.airMinute - this.depMinute);
			}
			// this.depMonth < this.airMonth -> this.depDay > this.airMonth
			else if (this.depMonth < this.airMonth) {
				if ((this.depMonth == 02) && (this.depMonth < this.airMonth)) {
					if (this.depYear % 4 == 0) {
						tempDay = this.airDay + 29;
					} else {
						tempDay = this.airDay + 28;
					}
				} else if (Arrays.asList(oddMonths).contains(this.depMonth)) {
					tempDay = this.airDay + 31;
				} else if (this.depMonth == 02) {
					tempDay = this.airDay + 28;
				} else {
					tempDay = this.airDay + 30;
				}
				this.travelTime = 1400 * (this.airDay - this.depDay) + 60
						* (this.airHour - this.depHour)
						+ (this.airMinute - this.depMinute);
			}
		}
		// this.depYear > this.airYear -> this.depMonth = 12 > this.airMonth = 1
		if (this.depYear > this.airYear) {
			if (this.depMonth > this.airMonth) {
				tempDay = this.airDay + 31;
				this.travelTime = 1400 * (this.airDay - this.depDay) + 60
						* (this.airHour - this.depHour)
						+ (this.airMinute - this.depMinute);
			}
		}
	}

	/**
	 * Sets search info of the flight.
	 * 
	 */
	private void setSearchInfo() {
		this.searchInfo[0] = this.depDate;
		this.searchInfo[1] = this.origin;
		this.searchInfo[2] = this.destination;
	}
	public void decreaseSeat(){
		if (0 < this.numSeat){
		this.numSeat = this.numSeat - 1;}
		//else { raise error }
	}
	// below are all the getter methods for variable of the instance//
	/**
	 * Returns flightNum of the Flight.
	 * 
	 * @return represents the Flight number of the Flight.
	 */
	public String getFlightNum() {
		return this.flightNum;
	}

	/**
	 * Returns depDate of the Flight.
	 * 
	 * @return represents the departure date of Flight.
	 */
	public String getDepartureDate() {
		return this.depDate;
	}

	/**
	 * Returns depTime of the Flight.
	 * 
	 * @return represents the departure time of the Flight.
	 */
	public String getDepartureTime() {
		return this.depTime;
	}

	/**
	 * Returns airDate of the Flight.
	 * 
	 * @return represents the arrival date of the Flight.
	 */
	public String getArrivalDate() {
		return this.airDate;
	}

	/**
	 * Returns airTime of the Flight.
	 * 
	 * @return represents the arrival time of the Flight.
	 */
	public String getArrivalTime() {
		return this.airTime;
	}

	/**
	 * Returns the airline of the Flight.
	 * 
	 * @return represents the airline company of the Flight.
	 */
	public String getAirline() {
		return this.airline;
	}

	/**
	 * Returns the origin of the Flight.
	 * 
	 * @return represents the origin of the Flight.
	 */
	public String getOrigin() {
		return this.origin;
	}

	/**
	 * Returns the destination of the Flight.
	 * 
	 * @return represents the destination of the Flight.
	 */
	public String getDestination() {
		return this.destination;
	}

	/**
	 * Returns the price of the ticket of the Flight.
	 * 
	 * @return represent the price of the ticket of the Flight.
	 */
	public Double getPrice() {
		return this.price;
	}

	/**
	 * Returns depYear of the Flight.
	 * 
	 * @return represents the year part of departure date.
	 */
	public int getDepYear() {
		return this.depYear;
	}

	/**
	 * Returns depMonth of the Flight.
	 * 
	 * @return represents the month part of departure date.
	 */
	public int getDepMonth() {
		return this.depMonth;
	}

	/**
	 * Returns depDay of the Flight.
	 * 
	 * @return represents the day part of departure date.
	 */
	public int getDepDay() {
		return this.depDay;
	}

	/**
	 * Returns depHour of the Flight.
	 * 
	 * @return represents the hour part of departure time.
	 */
	public int getDepHour() {
		return this.depHour;
	}

	/**
	 * Returns depMinute of the Flight.
	 * 
	 * @return represents the minute part of departure time.
	 */
	public int getDepMinute() {
		return this.depMinute;
	}

	/**
	 * Returns airYear of the Flight.
	 * 
	 * @return represents the year part of arrival date.
	 */
	public int getAirYear() {
		return this.airYear;
	}

	/**
	 * Returns airMonth of the Flight.
	 * 
	 * @return represents the Month part of arrival date.
	 */
	public int getAirDay() {
		return this.airDay;
	}

	/**
	 * Returns airDay of the Flight.
	 * 
	 * @return represents the day part of arrival date.
	 */
	public int getAirMonth() {
		return this.airMonth;
	}

	/**
	 * Returns airHour of the Flight.
	 * 
	 * @return represents the hour part of arrival time.
	 */
	public int getAirpHour() {
		return this.airHour;
	}

	/**
	 * Returns airMinute of the Flight
	 * 
	 * @return represents the Minute part of arrival time.
	 */
	public int getAirMinute() {
		return this.airMinute;
	}

	/**
	 * Returns the time taken to get from origin to destination.
	 * 
	 * @return represent the travel time of the flight.
	 */
	public int getTravelTime() {
		return this.travelTime;
	}
	
	/**
	 * Returns the number of seats.
	 * 
	 * @return represent the number of Seat.
	 */
	public int getNumSeat() {
		return this.numSeat;
	}

	/**
	 * Returns a array of String that contains departure date origin and
	 * destination is used to search for flight.
	 * 
	 * @return represent a String array that contains departure date, origin and
	 *         destination.
	 */
	public String[] getSearchInfo() {
		return this.searchInfo.clone();
	}

	/**
	 * Represent a String representation of the Flight object.
	 */
	public String toString() {
		String result;
		result = this.flightNum + "," + this.depDate + " " + this.depTime + ","
				+ this.airDate + " " + this.airTime + "," + this.airline + ","
				+ this.origin + "," + this.destination + "," + this.price + "," + this.numSeat;
		return result;
	}

}
