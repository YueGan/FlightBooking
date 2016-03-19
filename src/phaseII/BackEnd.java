package phaseII;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Managers.ClientMap;
import Managers.FlightMap;
/**
 * Creates an instance of BackEnd that is responsible for bring all the
 * different part of application together.
 * @author Marhababanu
 */

public class BackEnd implements Serializable {
	private static final long serialVersionUID = -3565465467648292917L;
	/** FlightMap that stores all the flight from the csv file*/
	private FlightMap storedFlight = new FlightMap();
	/**ClientMap that stores all the Client from the csv file*/
	private ClientMap storedClient = new ClientMap();
	/** Collection of Intinraries of the lastest search*/
	private ItineraryList latestSearch;
	private String serFlightFile;
	private String serUserFile;
	public Client loggedInUser;

	/**
	 * Creates an instance of BackEnd
	 * @param username represent the email of the user that is login in
	 * @param password represent the password of the user that is login in
	 * @param serUserFile represent path of file from where the ClientMap is deserialized
	 * @param serFlightFile represent path of file from where the FlightMap is deserialized
	 * @throws IOException [description]
	 * @throws ParseException 
	 * @throws InvalidInputException 
	 * @throws NumberFormatException 
	 */
	public BackEnd(String username, String password, String serUserFile, String serFlightFile) throws IOException, InvalidInputException, ParseException{
		this.serFlightFile = serFlightFile;
		this.serUserFile = serUserFile;
		//Deserializing FlightMap and ClientMap so they are ready to use in future. 
		this.storedClient.deserializeMap(this.serUserFile);
		this.storedFlight.deserializeMap(this.serFlightFile);
		//extracting User that is loginning from the ClientMap of the system.
		this.loggedInUser = this.storedClient.getValue(username);
	}
	/**
	 * Return the loggedInUser of the BackEnd Instance
	 * @return represent the User who logged In
	 */
	public Client getLoggedInUser(){
		return this.loggedInUser;
	}
	/**
	 * Return the FlightMap of the BackEnd Instance
	 * @return the deserialized FlightMap
	 */
	public FlightMap getFlights(){
		return this.storedFlight;
	}
	/**
	 * Serialized FlightMap and ClientMap so it can retrieve next time 
	 */
	public void saveData(){
		this.storedClient.serializeMap(this.serUserFile);
		this.storedFlight.serializeMap(this.serFlightFile);
	}

	/**
	 * 
	 * @param filepath
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws InvalidInputException
	 */
	public void loadFlights(String filepath) throws IOException, NumberFormatException, InvalidInputException{
		if (this.loggedInUser instanceof Admin){
			((Admin) this.loggedInUser).uploadFlight(filepath, this.storedFlight);
		}
	}
	
	/**
	 * Initialize storedClient ClientMap using given file
	 * @param  filepath represent the path where the file with all the client
	 * info is.
	 * @throws IOException [description]
	 * @throws InvalidInputException 
	 * @throws NumberFormatException 
	 */
	public void loadClient(String filepath) throws IOException, NumberFormatException, InvalidInputException{
		if (this.loggedInUser instanceof Admin){
			((Admin) this.loggedInUser).uploadClient(filepath, this.storedClient);
		}
	}
	/**
	 * Returns a client that has the email address as the given one
	 * @param  email represent email by which client is being searched
	 * @return represent client that has the same email as given
	 */
	public Client getClient(String email){
		Client tempClient = this.storedClient.getValue(email);
		return tempClient;
	}

	/**
	 * Returns a list of Flight that have the same departure Date, origin
	 * and destination as the one given
	 * @param  depDate represent departure date of Flight
	 * @param  origin  represent origin of Flight
	 * @param  destination represent destination of Flight
	 * @return represent list of Flights that have the same depdDate, origin
	 * and destination
	 */
	public List<Flight> searchFlight(String depDate, String origin,
		String destination){
		//invokes search method in the FlightMap class
		List<Flight> searchResult = 
		this.storedFlight.searchFlight(createKey(depDate, origin,destination));
		return searchResult;
	}

	/**
	 * Returns a String of Itineraries that have the same departure Date,
	 * origin
	 * and destination as the one given
	 * @param  depDate represent depDate by which Itineraries is being searched
	 * @param  origin  represent origin by which Itineraries is being searched
	 * @param  destination represent origin by which Itineraries is being 
	 * searched
	 * @return String representation of BuildItinraries that have the same 
	 * depDate, origin and destination
	 */
	public String searchItinerary(String depDate, String origin,
		String destination, int maxFlight){
		String result = "";
		//created/searching intinraries by the given depDate, origin, 
		//destination
		this.latestSearch = new ItineraryList(createKey(depDate, origin, 
			destination), maxFlight, this.storedFlight);
		result = this.latestSearch.toString();
		return result;
	}
	/**
	 * Returns a String representation of BuildItineraries is sorted  by Cost
	 * and that has same depDate, origin and Destination as given ones
	 * @param  depDate represents depDate of Itineraries
	 * @param  origin  represents origin of Itineraries
	 * @param  destination represents destination of Itineraries
	 * @return String representation of sorted Itineraries
	 */
	public String sortItinerariesByCost(String depDate, String origin, 
		String destination, int maxFlight){
		String result = "";
		this.latestSearch = new ItineraryList(createKey(depDate, origin,
			destination), maxFlight , this.storedFlight);
		//invoking sortByItinerariesCost method of BuildItinraries
		//this.latestSearch.sortByItinerariesCost();
		Itinerary iArray[] = this.latestSearch.toArray();
        this.latestSearch.mergesort(iArray, 1);

	    this.latestSearch.toArrayList(iArray);
	    
		result = this.latestSearch.toString();
		return result;
	}
	/**
	 * Returns a String representation of BuildItineraries is sorted  by time
	 * and that has same depDate, origin and Destination as given ones
	 * @param  depDate represents depDate of Itineraries
	 * @param  origin  represents origin of Itineraries
	 * @param  destination represents destination of Itineraries
	 * @return String representation of sorted Itineraries
	 */
	public String sortItinerariesByTime(String depDate, String origin, 
		String destination, int maxFlight){
		String result = "";
		this.latestSearch = new ItineraryList(createKey(depDate, origin,
			destination), maxFlight, this.storedFlight );
		//invoking sortByItinerariesTime method of BuildItinraries
		//this.latestSearch.sortByItinerariesCost();
		Itinerary iArray[] = this.latestSearch.toArray();
        this.latestSearch.mergesort(iArray, 0);
	    this.latestSearch.toArrayList(iArray);
		result = this.latestSearch.toString();
		return result;
	}
	/**
	 * A helper method that create a array of String. The element are date,
	 * origin and destination.
	 */
	private String [] createKey(String date, String origin, String destinatioen)
	{
		String [] result = {date, origin, destinatioen};
		return result;
	}

}
	

