package phaseII;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Arrays;

import Managers.FlightMap;

/**
 * A class that represent a collection of Itinerary stored in Arraylist.
 * 
 * @author Gavin and Sara.
 */
public class ItineraryList {

	/** Map of Flights. */
	private FlightMap flights;
	/** Arraylist of Itinerary. */
	private ArrayList<Itinerary> itineraries = new ArrayList<Itinerary>();
	/** Key of FlightMap for searches. */
	private String[] searchKeys;
	/** List of Flights collected from FlightMap by searching date and origin. */
	private List<Flight> dateOriginFlights;

	/** Itinerary array used to sort */
	private Itinerary[] array;

	/** Temporary Itinerary array used to sort */
	private Itinerary[] tempMergArr;

	/** Length of array used in sort */
	private int length;

	/**
	 * Create an instance of FlightMap by giving the search key, max number of
	 * Flights per Itinerary and the map of Flight.
	 * 
	 * @param key
	 *            a length 3 list consist of date, origin and destination.
	 * @param maxFlightsPerItinerary
	 *            max number of Flights permit per Itinerary.
	 * @param mapOfFlights
	 *            a map of Flights.
	 */
	public ItineraryList(String[] key, int maxFlightsPerItinerary,
			FlightMap mapOfFlights) {
		this.flights = mapOfFlights;
		this.searchKeys = key;
		this.dateOriginFlights = flights.searchDateOrigin(key);
		build(maxFlightsPerItinerary);
	}

	/**
	 * Return the Itineraries in this ItineraryList
	 * @return the Itineraries in this ItineraryList
	 */
	public ArrayList<Itinerary> getItineraries() {
		return itineraries;
	}
	
	/**
	 * Builds a Arraylist of Itinerary by giving max number of Flights per
	 * Itinerary.
	 * 
	 * @param maxFlights
	 *            max number of Flights permit per Itinerary.
	 */
	private void build(int maxFlights) {

		// for all the flights in the original date origin flight
		for (Flight flight : dateOriginFlights) {

			// make an new Itinerary Arraylist
			ArrayList<Itinerary> list = new ArrayList<Itinerary>();

			// list will become the return of build (which takes two parameter)
			list = build(list, flight, maxFlights - 1);

			// for all list in the return, add the original flight onto it
			for (Itinerary itinerary : list) {

				// if the list does not contains the current flight and the
				// destination of current flight matches
				// the origin of first flight in the itinerary.
				String a = itinerary.getFlights().get(0).getDepartureDate() + " "+ itinerary.getFlights().get(0).getDepartureTime();
				String b =  flight.getArrivalDate() + " "+ flight.getArrivalTime();
				
				if (!(itinerary.getFlights().contains(flight))
						&& itinerary.getFlights().get(0).getOrigin() == flight
								.getDestination() && validTime(b,a)){
					
					itinerary.add(flight);
				}
				// add the itinerary to itinerary list
				if (itinerary.getFlights().get(0).getOrigin() == searchKeys[1]
						&& itinerary.size() <= maxFlights
						&& validItinerary(itinerary))
					itineraries.add(itinerary);

			}
		}
	}

	/**
	 * Recursive part of Build, which takes an Arraylist, previous flight and
	 * the number of maxFlights
	 * 
	 * @param list
	 *            Arraylist of Itinerary
	 * @param preFlight
	 *            previous Flight.
	 * @param maxFlights
	 *            max number of Flights permit per Itinerary.
	 * 
	 * @return An ArrayList of Itinerary.
	 */
	private ArrayList<Itinerary> build(ArrayList<Itinerary> list,
			Flight preFlight, int maxFlights) {

		String date = preFlight.getArrivalDate();
		String dest = preFlight.getDestination();

		// if the previous flight's destination equals the destination we want,
		// then we create the Itinerary
		if (dest == searchKeys[2]) {

			// create itinerary
			Itinerary newItinerary = new Itinerary(preFlight, searchKeys[1],
					searchKeys[2]);
			// add the itinerary to the list
			list.add(newItinerary);

			// returns the list
			return list;
		}

		// if maxFlight is reached, return the same list
		else if (maxFlights == -1) {
			return list;
		} else {

			// creating a new Date Origin flight list
			String[] temp = { date, dest, "" };
			List<Flight> newDateOrigin = this.flights.searchDateOrigin(temp);

			// for all flights in the new list
			for (Flight flight : newDateOrigin) {

				String thisTime = flight.getDepartureDate() + " "
						+ flight.getDepartureTime();

				// retrieve the list of Itinerary
				list = build(list, flight, maxFlights - 1);

				// for all itinerary in the list.
				for (Itinerary itinerary : list) {

					// create a tempList that takes the first element in
					// itinerary.
					ArrayList<Flight> tempIt = new ArrayList<Flight>();
					tempIt.add(itinerary.getFlights().get(0));

					// if itinerary is not empty and the previous flight is not
					// the same as this flight.
					if (!(itinerary.getFlights().contains(flight))) {

						if (validTime(thisTime, tempIt.get(0)
								.getDepartureDate()
								+ " "
								+ tempIt.get(0).getDepartureTime())
								&& tempIt.get(0).getOrigin() == flight
										.getDestination()) {
							itinerary.add(flight);
						}
					}
				}

			}
			// returns the list when done adding
			return list;
		}
	}

	/**
	 * Checks if the Itinerary is valid by testing if there are same itinerary
	 * in the ItineraryList and if it involves round trip
	 * 
	 * @param tempIt
	 *            an Itinerary
	 * @return True if Itinerary is valid
	 */
	private boolean validItinerary(Itinerary tempIt) {

		int counter = 0;
		// Checks if a flight has traveled back to place already traveled
		for (Flight tempFlight : tempIt.getFlights()) {

			for (int i = counter + 1; i < tempIt.size(); i++) {
				if (tempFlight.getOrigin() == tempIt.getFlights().get(i)
						.getDestination())
					return false;

			}
			counter++;
		}

		// Checks if there are duplicate itinerary in list
		for (Itinerary tempIt2 : itineraries) {
			if (tempIt.getFlights().equals(tempIt2.getFlights()))
				return false;
		}
		return true;
	}

	/**
	 * Validates if two Time is in between 0 minute and 360 minutes(6 hours).
	 * 
	 * @param timeOne
	 *            String of time of first Flight
	 * @param timeTwo
	 *            String of time of second Flight
	 * 
	 * @return true/false if the time gap is valid.
	 */
	private boolean validTime(String timeOne, String timeTwo) {

		// Set the format for input Strings, checks for validation.
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm");

		Date d1 = null;
		Date d2 = null;

		// checks the input time and catch errors.
		try {
			d1 = format.parse(timeOne);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			d2 = format.parse(timeTwo);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Calculates the time different.
		long diff = d2.getTime() - d1.getTime();

		// If time in between 0 minute and 360 minute, returns true.
		if ((diff / (60 * 1000)) > 1 && (diff / (60 * 1000)) <= 360)
			return true;

		return false;
	}

	/**
	 * Return an Array of Itineraries given an ArrayList<Itineraries>
	 * 
	 * @return an an Array of Itineraries given an ArrayList<Itineraries>
	 */
	public Itinerary[] toArray() {
		Itinerary iArray[] = new Itinerary[this.getItineraries().size()];
		iArray = this.getItineraries().toArray(iArray);
		return iArray;
	}



	/**
	 * Return an ArrayList of Itineraries given an Array of Itineraries
	 * @param iArray An Array of Itineraries to convert to an 
	 *        ArrayList of Itineraries
	 * @return an ArrayList of Itineraries given an Array of Itineraries
	 */
	public ArrayList<Itinerary> toArrayList(Itinerary[] iArray) {
		ArrayList<Itinerary> iList = new ArrayList<Itinerary>(
				Arrays.asList(iArray));
		return iList;
	}

	/**
	 * Perform Merge sort on given Array. 
	 * @param iArray the Array to do Merge Sort on
	 * @param timeCost the specification to sort by time or cost
	 */
	public void mergesort(Itinerary iArray[], int timeCost) {
		array = iArray;
		length = iArray.length;
		tempMergArr = new Itinerary[length];
		doMergeSort(0, length - 1, timeCost);
	}

	/**
	 * The recursion part of Merge sort
	 * @param lowerIndex the lower index to sort left side
	 * @param higherIndex the lower index to sort right side
	 * @param timeCost the specification to sort by tim eor cost
	 */
	private void doMergeSort(int lowerIndex, int higherIndex, int timeCost) {

		if (lowerIndex < higherIndex) {
			int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
			// Sorts the left side of the array
			doMergeSort(lowerIndex, middle, timeCost);
			// Sorts the right side of the array
			doMergeSort(middle + 1, higherIndex, timeCost);
			// Merge both sides by cost
			if (timeCost == 1) {
				mergePartsCost(lowerIndex, middle, higherIndex);
			// Merge both sides by Time
			} else {
				mergePartsTime(lowerIndex, middle, higherIndex);
			}
		}
	}

	/**
	 * Merge the sub parts of the Array by cost
	 * @param lowerIndex the lower index to sort left side
	 * @param middle  the middle index of Array
	 * @param higherIndex  the higher index to sort right side
	 */
	private void mergePartsCost(int lowerIndex, int middle, int higherIndex) {

		for (int i = lowerIndex; i <= higherIndex; i++) {
			tempMergArr[i] = array[i];
		}
		int i = lowerIndex;
		int j = middle + 1;
		int k = lowerIndex;
		while (i <= middle && j <= higherIndex) {
			if (tempMergArr[i].getCost() <= tempMergArr[j].getCost()) {
				array[k] = tempMergArr[i];
				i++;
			} else {
				array[k] = tempMergArr[j];
				j++;
			}
			k++;
		}
		while (i <= middle) {
			array[k] = tempMergArr[i];
			k++;
			i++;
		}
	}

	/**
	 * Merge the sub parts of the Array by time
	 * @param lowerIndex the lower index to sort left side
	 * @param middle  the middle index of Array
	 * @param higherIndex  the higher index to sort right side
	 */
	private void mergePartsTime(int lowerIndex, int middle, int higherIndex) {

		for (int i = lowerIndex; i <= higherIndex; i++) {
			tempMergArr[i] = array[i];
		}
		int i = lowerIndex;
		int j = middle + 1;
		int k = lowerIndex;
		while (i <= middle && j <= higherIndex) {
			if (tempMergArr[i].getTravelTime() <= tempMergArr[j]
					.getTravelTime()) {
				array[k] = tempMergArr[i];
				i++;
			} else {
				array[k] = tempMergArr[j];
				j++;
			}
			k++;
		}
		while (i <= middle) {
			array[k] = tempMergArr[i];
			k++;
			i++;
		}
	}

	/**
	 * Represent String representation of the ItineraryMap
	 */
	@Override
	public String toString() {
		String s = "";
		ArrayList<Itinerary> itineraryList = this.itineraries;
		Iterator<Itinerary> it = itineraryList.iterator();
		while (it.hasNext()) {
			Itinerary i = it.next();
			s += i.toString() + "\n";
		}

		return s;
	}

}