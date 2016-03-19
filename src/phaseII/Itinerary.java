package phaseII;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class that represents an itinerary
 * 
 * @author Sara
 */
public class Itinerary {

	/** Total cost of the Itinerary. */
	private double cost;
	/** Total travel time of the Itinerary. */
	private int travelTime;
	/** Origin of the Itinerary. */
	private String travelOrigin;
	/** Destination of the Itinerary. */
	private String destination;
	/** Arraylist of all Flights in the Itinerary. */
	private ArrayList<Flight> flights;

	/**
	 * Create an instance of Itinerary by giving the an array of Flight, origin
	 * and destination.
	 * 
	 * @param flights
	 *            a collection of flights in the Itinerary.
	 * @param travelOrigin
	 *            origin of the Itinerary.
	 * @param destination
	 *            destination of the Itinerary.
	 */
	public Itinerary(ArrayList<Flight> flights, String travelOrigin,
			String destination) {
		this.flights = flights;
		this.travelOrigin = travelOrigin;
		this.destination = destination;
		totalTravelTime();
		totalCost();
	}

	/**
	 * Create an instance of Itinerary by giving the a Flight, origin and
	 * destination. The flight will be added into the ArrayList.
	 * 
	 * @param flights
	 *            a collection of flights in the Itinerary.
	 * @param travelOrigin
	 *            origin of the Itinerary.
	 * @param destination
	 *            destination of the Itinerary.
	 */
	public Itinerary(Flight flight, String travelOrigin, String destination) {
		this.flights = new ArrayList();
		this.flights.add(flight);
		this.travelOrigin = travelOrigin;
		this.destination = destination;
		totalTravelTime();
		totalCost();
	}

	/**
	 * Returns all the Flights in Itinerary.
	 * 
	 * @return An ArrayList of Flight.
	 */
	public ArrayList<Flight> getFlights() {
		return flights;
	}

	/**
	 * Returns true/false if Itinerary is empty.
	 * 
	 * @return Return true/false if flights.isEmpty().
	 */
	public boolean isEmpty() {
		return flights.isEmpty();
	}

	/**
	 * Add flight in the front of Itinerary.
	 * 
	 * @param flight
	 *            A flight Object.
	 */
	public void add(Flight flight) {
		flights.add(0, flight);
		cost = 0;
		travelTime = 0;
		totalCost();
		totalTravelTime();
	}

	/**
	 * Clear the Itinerary.
	 */
	public void clear() {
		flights.clear();
	}

	/**
	 * Returns the size of Itinerary.
	 * 
	 * @return size of Itinerary.
	 */
	public int size() {
		return flights.size();
	}

	/**
	 * 
	 * @param flights
	 */
	public void setFlights(ArrayList<Flight> flights) {
		this.flights = flights;
	}

	/**
	 * Returns the total cost of the Itinerary.
	 * 
	 * @return Total cost of the Itinerary.
	 */
	public double getCost() {
		return this.cost;
	}

	/**
	 * Set the cost of the Itinerary.
	 * 
	 * @param cost
	 *            double cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * Set the total cost of the Itinerary for all Flight in Itinerary.
	 */

	public void totalCost() {

		ArrayList<Flight> flightList = this.getFlights();
		Iterator<Flight> it = flightList.iterator();
		while (it.hasNext()) {
			Flight f = it.next();
			this.cost += f.getPrice();
		}
	}

	/**
	 * Return Travel time of Itinerary.
	 * 
	 * @return Travel time of Itinerary.
	 */
	public int getTravelTime() {
		return travelTime;
	}

	/**
	 * Set Travel Time if Itinerary.
	 * 
	 * @param travelTime
	 *            int travelTime.
	 */
	public void setTravelTime(int travelTime) {
		this.travelTime = travelTime;
	}

	/**
	 * Set the total travel time of the Itinerary for all Flight in Itinerary.
	 */
	private void totalTravelTime() {
		ArrayList<Flight> flightList = this.getFlights();
		Iterator<Flight> it = flightList.iterator();
		while (it.hasNext()) {
			Flight f = it.next();
			this.travelTime += f.getTravelTime();
		}
	}

	/**
	 * Return the destination of the Itinerary.
	 * 
	 * @return destination of Itinerary.
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Set the destination of Itinerary.
	 * 
	 * @param destination
	 *            String destination.
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * Return the Origin of the Itinerary.
	 * 
	 * @return travelOrigin of Itinerary.
	 */
	public String getTravelOrigin() {
		return travelOrigin;
	}

	/**
	 * Set the destination of Itinerary.
	 * 
	 * @param travelOrigin
	 *            String travelOrigin.
	 */
	public void setTravelOrigin(String travelOrigin) {
		this.travelOrigin = travelOrigin;
	}

	/**
	 * Return travel time of Itinerary in hours.
	 * 
	 * @return travel time of Itinerary in hours.
	 */
	private String getTravelHours() {
		String hrs = "";
		if ((this.travelTime / 60) < 10) {
			hrs = "0" + Integer.toString(this.travelTime / 60);
		} else {
			hrs = Integer.toString(this.travelTime / 60);
		}
		return hrs;
	}

	/**
	 * Return travel time of Itinerary in minute.
	 * 
	 * @return travel time of Itinerary in minute.
	 */
	private String getTravelMinutes() {
		String min = "";
		if ((this.travelTime % 60) < 10) {
			min = "0" + Integer.toString(this.travelTime % 60);
		} else {
			min = Integer.toString(this.travelTime % 60);
		}
		return min;
	}

	
	/**
	 * Return a string representation of this Itinerary. 
	 * @return a String representation of this Itinerary.
	 */
	  public String toString(){
	  
		  String s = "";
		  ArrayList<Flight> flightList = this.getFlights();
		  Iterator<Flight> it = flightList.iterator(); 
		  while(it.hasNext()){ 
			  Flight f = it.next();
			  s += (f.getFlightNum() + "," + f.getDepartureDate() + " " + f.getDepartureTime() + 
					  "," + f.getArrivalDate() + " " + f.getArrivalTime() + "," + 
					  f.getAirline() + "," + f.getOrigin() + "," + f.getDestination() + "\n");
	  		}
		  s += ("$" + Double.toString(getCost()) + "\n");
		  s += (this.getTravelHours() + ":" + this.getTravelMinutes()) + "\n";
		  return s;
		}
}
