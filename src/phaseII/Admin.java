package phaseII;

import java.text.ParseException;
import java.util.List;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

import Managers.ClientMap;
import Managers.FlightMap;

/**
 * a class represents admin.
 * @author 
 *
 */

public class Admin extends Client{
	//creates a new FlightMap storedFlight
	private FlightMap storedFlight;
	//creates a new ClientMap storedClient
	private ClientMap storedClient;
	
	/**
	 * creates a new admin with the same information client has.
	 * @param lastName
	 * @param firstName
	 * @param email
	 * @param address
	 * @param creditCardNum
	 * @param expiryDate
	 * @throws InvalidInputException
	 * @throws ParseException
	 */
	public Admin(String lastName, String firstName, String email,
				 String address, String creditCardNum, String expiryDate) 
					throws InvalidInputException, ParseException{
		
		super(lastName, firstName, email, address, creditCardNum, expiryDate);
	}

	/**
	 * takes a path and convert the .csv file into a flight map.
	 * @param filepath
	 * @throws NumberFormatException
	 * @throws IOException
	 * @throws InvalidInputException
	 */
	public void uploadFlight(String filepath,FlightMap storedFlight2) 
			throws NumberFormatException, IOException, InvalidInputException{
		storedFlight = storedFlight2;
		storedFlight.loadFlightFromFile(filepath);
	}
	
	/**
	 * takes a path and voncert the .csv file into a flight map.
	 * @param filepath
	 * @param storedClient2 
	 * @throws IOException
	 */
	public void uploadClient(String filepath, ClientMap storedClient2) throws IOException{
		storedClient = storedClient2;
		storedClient.loadClientFromFile(filepath);
	}
	
	/**
	 * takes a email and get the info of the client with this email.
	 * @param email
	 * @return
	 */
	public Client getClientInfo(String email){
		
		return (storedClient.getValue(email));
		
	}
	
	
	/**
	 * takes a dep time, an origin,, a destination, a flight number and
	 * get the flight with these info.
	 * @param dep
	 * @param origin
	 * @param destination
	 * @param flightNum
	 * @return
	 */
	private Flight getFlightInfo(String dep, String origin,
								 String destination, String flightNum){
		return storedFlight.getFlight(dep, origin, destination, flightNum);
		
	}
	
	/**
	 * takes a emaill to track the client, and change the information 
	 * of that client.
	 * @param email: email of the client.
	 * @param change: the information wants to change.
	 * @param edit: the new info wants to be saved. 
	 */
	public void editClientInfo(String email, String change, String edit){
		
		if (change == "LastName"){
			getClientInfo(email).setLastName(edit);	
		}
		
		if (change == "FirstName"){
			getClientInfo(email).setFirstName(edit);	

		}
		
		if (change == "Email"){
			getClientInfo(email).setEmail(edit);	

		}
		
		if (change == "Address"){
			getClientInfo(email).setAddress(edit);	

		}
		
		if (change == "CreditCardNumber"){
			getClientInfo(email).setCreditCardNum(edit);	

		}
		
		if (change == "ExpiryDate"){
			getClientInfo(email).setExpiryDate(edit);
		}
	}
	
	/**
	 *changes information of the flight.
	 * @param dep
	 * @param origin
	 * @param des
	 * @param flightNum
	 * @param change
	 * @param edit
	 */
	public void editFlightInfo(String dep, String origin,
							   String des, String flightNum,
							   String change, String edit){

		if (change == "FlightNumber"){
			getFlightInfo(dep, origin,
						  des, flightNum).setFlightNum(edit);
			
		}
		if (change == "DepartureDateTime"){
			getFlightInfo(dep, origin,
						  des, flightNum).setDepartureInfo(edit);
			
		}
		if (change == "ArrivalDateTime"){
			getFlightInfo(dep, origin,
						  des, flightNum).setArrivalInfo(edit);
			
		}
		if (change == "Airline"){
			getFlightInfo(dep, origin,
						  des, flightNum).setAirline(edit);
			
		}
		if (change == "Origin"){
			getFlightInfo(dep, origin,
						  des, flightNum).setOrigin(edit);
			
		}
		if (change == "Destination"){
			getFlightInfo(dep, origin,
						  des, flightNum).setDestination(edit);	
		}

		if (change == "Price"){
			getFlightInfo(dep, origin,
						  des, flightNum).setPrice(Double.parseDouble(edit));	
		}
	}
		
		
	/**
	 * writes client info to system.
	 * @param lastName
	 * @param firstName
	 * @param email
	 * @param address
	 * @param creditCard
	 * @param expiryDate
	 * @param filePath
	 */
	
	public void uploadClientInfo(String lastName, String firstName, String email,
			String address, String creditCard, String expiryDate, String filePath) 
					throws InvalidInputException, ParseException{
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath)));
			writer.write(lastName +"," + firstName + "," + email +
					"," + address + "," + creditCard + "," + expiryDate);
			writer.close();
			ValidityOfEmail emailAdressCheck = new ValidityOfEmail(email);
			ValidityOfExpiryDate expiryDateCheck = new ValidityOfExpiryDate(
					expiryDate.substring(0, 4), 
					expiryDate.substring(5, 7), expiryDate.substring(8));
			if (!emailAdressCheck.isValid() || !lastName.matches("[a-zA-Z]+")
					|| !firstName.matches("[a-zA-Z]+")
					|| !address.matches("[a-zA-Z0-9 ]+")
					|| !creditCard.matches("[0-9]+")
					|| !expiryDateCheck.isValid()
					|| !expiryDate.matches("[0-9]+[-]+[0-9]+[-][0-9]+")
					|| (expiryDate.length() != 10)) {
				throw new InvalidInputException("INVALID INPUT OF CLINET INFO");
			}
		} catch (IOException e){	
			e.printStackTrace();
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		} catch (java.lang.NumberFormatException e) {
			System.out.println("INVALID FORMAT OF DATE");
		}		
	}
	
	/**
	 * writes flight info to system.
	 * @param number
	 * @param departureDateTime
	 * @param arrivalDateTime
	 * @param airline
	 * @param origin
	 * @param destination
	 * @param price
	 * @param numSeats
	 * @param filePath
	 */
	public void uploadFlightInfo(String number, String departureDateTime,
			String arrivalDateTime, String airline, String origin, 
			String destination, String price, String numSeats, String filePath){
		
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath)));
			writer.write(number + "," + departureDateTime + "," + arrivalDateTime +
					"," + airline + "," + origin + "," + destination + "," +
					price + "," + numSeats);
			writer.close();
		} catch (IOException e){	
			e.printStackTrace();
		}
	}
	




}