package Managers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import phaseII.Flight;
import phaseII.InvalidInputException;

/**
 * A class that represent a collection of Flight store in Map. In the Map the
 * key are String array that contains a particular departure date, origin and
 * destination. And the value is list of Flights that have the same departure
 * date, origin and destination.
 * 
 * @author Marhababanu
 *
 */

public class FlightMap implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2680371276645298552L;
	/**
	 * 
	 */
	/** Map that store the Flights */
	private Map<String[], List<Flight>> flightList = new HashMap<String[], List<Flight>>();

	/**
	 * Creates an instance of FlightMap by reading the File at filepath where
	 * the file should be in format
	 * Number,DepartureDateTime,ArrivalDateTime,Airline
	 * ,Origin,Destination,Price.
	 * 
	 * @param filepath
	 *            represent the path of the flight the flight informaiton are
	 *            stored.
	 * @throws IOException
	 * @throws InvalidInputException
	 * @throws NumberFormatException
	 */
	public FlightMap(String filepath) throws IOException,
			NumberFormatException, InvalidInputException {
		this.flightList = new HashMap();
		loadFlightFromFile(filepath);
	}
	
	public FlightMap() {
		this.flightList = new HashMap();
	}
	
	public void serializeMap(String path){
		try {
			FileOutputStream fileOut = new FileOutputStream (path);
			ObjectOutputStream ou = new ObjectOutputStream(fileOut);
			System.out.println("About to write");
			ou.writeObject(this.flightList);
			ou.close();
			fileOut.close();
			System.out.println("Data has been written to the file");
		}
		catch (IOException i){
			i.printStackTrace();
		}
	}
	public void deserializeMap(String path){
		try {
		 	FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.flightList = (HashMap) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Data has been read from the file");
		}
		catch(IOException i){
			i.printStackTrace();
			return;
			}
		catch(ClassNotFoundException c){
			System.out.println("class not found");
			c.printStackTrace();
			return;
			}
	}
	
	public void loadFlightFromFile(String filepath) throws IOException, NumberFormatException, InvalidInputException{
		// initializes an empty maps
				// helping variable that are used to generate FlightMap
				String[] temp;
				String line;
				BufferedReader br = new BufferedReader(new FileReader(filepath));
				line = br.readLine();
				// while the whole file is not read create line is read and create an
				// instance of flight using the information in that line
				// the flight is then added to the FlightMap
				while (line != null && line != "") {
					// split the line in piece of information
					temp = line.split(",");
					// create an instance of Flight
					Flight tempFlight = new Flight(temp[0], temp[1], temp[2], temp[3],
							temp[4], temp[5], Double.parseDouble(temp[6]), Integer.parseInt(temp[7]));
					addFlight(tempFlight);
					// reads the next line in the file
					line = br.readLine();
				}
	}

	/**
	 * Adds the given flight1 to the FlightMap.
	 * 
	 * @param flight1
	 *            represent Flight that is to be added in the FlightMap.
	 */
	public void addFlight(Flight flight1) {
		// get the basic search info key so the it is easier to check if the
		// key already exists in the FlightMap

		String[] key = flight1.getSearchInfo();
		// initialize a List of Flight in case a new pair of Key and value is
		// to added in the FlightMap
		List<Flight> value = new ArrayList<Flight>();
		/*
		 * if the FlightMap is not empty and the Key already exits that the
		 * given flight1 is just appended to the List of Flight at that key
		 */
		if ((this.flightList != null) && (containKey(key))) {
			getValue(key).add(flight1);
		}
		/*
		 * else a new pair of key and value is added to the Flight where is key
		 * is flight1's String array of basic search info and the value is that
		 * list containing flight1
		 */

		else {
			value.add(flight1);
			this.flightList.put(key, value);
		}
	}

	/**
	 * Returns a list of Flight that have the same basic search info as the one
	 * passed in a form of String array. If no such Flight exits then null is
	 * return.
	 * 
	 * @param searchInfo
	 *            represent a String array which the arguments passed in for
	 *            searching Flights.
	 * @return represent a List of Flight that have the same departure date,
	 *         origin and destination as the one passed in in for of String
	 *         array.
	 */
	public List<Flight> searchFlight(String[] searchInfo) {
		// remember the searchInfo contains date at index 0, origin at index 1,
		// destination at index 2
		List<Flight> result = (List<Flight>) ((ArrayList<Flight>) getValue(searchInfo))
				.clone();
		return result;
	}

	/**
	 * Returns a value store the given key.
	 * 
	 * @param key
	 *            represent the key the of value if asked.
	 * @return represent value stored at the key.
	 */
	private List<Flight> getValue(String[] key) {
		List<Flight> result = null;
		// get all the keys of the maps
		Set<String[]> tempSet = getKeys();
		// if the passed in key exits then only the value is returned
		if (containKey(key)) {
			// find the actual key in the map and get the value at that key
			for (String[] tempKey : tempSet) {
				if (tempKey[0].equals(key[0]) && tempKey[1].equals(key[1])
						&& tempKey[2].equals(key[2])) {
					result = this.flightList.get(tempKey);
				}
			}
		}
		return result;
	}

	/**
	 * returns a list of Flight that have the same basic search info but only
	 * the departure date and origin has to be same as the one passed in a form
	 * of String array. If no such Flight exits then null is return.
	 * 
	 * @param searchInfo
	 *            represent a String array that contains the basic string
	 *            information.
	 * @return represent a List of Flight that have the same departure date,
	 *         origin as the one passed in in for of String array.
	 */
	public List<Flight> searchDateOrigin(String[] searchInfo) {
		List<Flight> result = new ArrayList<Flight>();
		// here is searchInfo has only two element Date and Origin
		for (String[] key : this.flightList.keySet()) {
			if (key[0].equals(searchInfo[0]) && key[1].equals(searchInfo[1])) {
				result.addAll(this.flightList.get(key));
			}
		}
		return result;
	}

	/**
	 * Returns list of Flight that have the same origin and Destination.
	 * 
	 * @param searchInfo
	 *            represents array of string that contians information for
	 *            searching flights.
	 * @return a List of Flight searched by origin and destination.
	 */
	public List<Flight> searchOriginDes(String[] searchInfo) {
		List<Flight> result = new ArrayList<Flight>();
		// here is searchInfo has only two element Date and Origin
		for (String[] key : this.flightList.keySet()) {
			if (key[1].equals(searchInfo[1]) && key[2].equals(searchInfo[2])) {
				result.addAll(this.flightList.get(key));
			}
		}
		return result;
	}

	/**
	 * Returns true if the given key is one of the key in the Map.
	 * 
	 * @param key
	 *            represent the key.
	 * @return return true the given key exits in the maps otherwise return
	 *         false.
	 */
	public boolean containKey(String[] key) {
		boolean result = false;
		// gets all the keys so that it is easier to loop through the map
		Set<String[]> keys = getKeys();
		// checking whether the key is not empty
		if (keys != null) {
			for (String[] tempKey : keys) {
				// if the content of the key and content is tempKey are same
				// then the key exits in the map
				if (tempKey[0].equals(key[0]) && tempKey[1].equals(key[1])
						&& tempKey[2].equals(key[2])) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * Returns whether a Flight that has the same given depdate, origin,
	 * destination and flightNum exit in the FlightMap.
	 * 
	 * @param dep
	 *            represent the departure of the Flight.
	 * @param origin
	 *            represent the origin of the Flight.
	 * @param destination
	 *            represent destination of the Flight.
	 * @param flightNum
	 *            represent flight number of the Flight.
	 * @return represent true if the flight with the similar departure date
	 *         origin, destination and FlightNum exits otherwise return false.
	 */
	public boolean containFlight(String dep, String origin, String destination,
			String flightNum) {
		// create a basic search info key
		String[] tempKey = { dep, origin, destination };
		// checks if that key exits in the map
		boolean keyResult = containKey(tempKey);
		boolean result = false;
		List<Flight> tempList = new ArrayList<Flight>();
		// if it does exits then Flight with same flight number as
		// flightNum is being in the list that is at key tempKey
		if (keyResult) {
			tempList = searchFlight(tempKey);
			for (Flight tempFlight : tempList) {
				if (tempFlight.getFlightNum().equals(flightNum)) {
					result = true;
				}
			}
		}
		return (keyResult && result);

	}

	/**
	 * Returns a Single and first occurance Flight that has the same given
	 * depDate, origin, destination and flightNum.
	 * 
	 * @param dep
	 *            represent the departure of the Flight.
	 * @param origin
	 *            represent the origin of the Flight.
	 * @param destination
	 *            represent destination of the Flight.
	 * @param flightNum
	 *            represent flight number of the Flight.
	 * @return represent the Flight with the similar departure date origin,
	 *         destination and FlightNum.
	 */
	public Flight getFlight(String dep, String origin, String destination,
			String flightNum) {
		String[] tempKey = { dep, origin, destination };
		// check whether flights going on dep from origin destination exits
		boolean keyResult = containKey(tempKey);
		Flight result = null;
		List<Flight> tempList = new ArrayList<Flight>();
		// if such flights exits then in that list of Flight with same FlightNum
		// is being return
		if (keyResult) {
			tempList = searchFlight(tempKey);
			for (Flight tempFlight : tempList) {
				if (tempFlight.getFlightNum() == flightNum) {
					result = tempFlight;
				}
			}
		}
		return result;
	}

	/**
	 * Returns a set of key of the FlightMap.
	 * 
	 * @return represent set of String array that are key of the FlightMap.
	 */
	public Set<String[]> getKeys() {
		return this.flightList.keySet();
	}

	/**
	 * Represent String representation of the FlightMap.
	 */
	@Override
	public String toString() {
		String result = "";
		for (String[] key : this.flightList.keySet()) {
			result = result + key[0] + " " + key[1] + " " + key[2] + "\n"
					+ "\t";

			for (Flight f : this.flightList.get(key)) {
				result = result + f.toString() + "\n" + "\t";
			}
			// remove the tab
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}
}
