package phaseII;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A class that represents a Client.
 * 
 * @author jeff
 */

public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7612366885988154164L;
	/** Email of the Client. */
	private String email;
	/** LastName of the Client. */
	private String lastName;
	/** FirstName of the Client. */
	private String firstName;
	/** Address of the Client. */
	private String address;
	/** CreditCardNum of the Client. */
	private String creditCardNum;
	/** expiryDate of Credit Card of of the Client. */
	private String expiryDate;
	/** expiryYear of Credit Card of of the Client. */
	private String expiryYear;
	/** expiryMonth of Credit Card of of the Client. */
	private String expiryMonth;
	/** ExpiryDay of Credit Card of the Client. */
	private String expiryDay;
	private String password;
	private List<Itinerary> bookedInt = new ArrayList<Itinerary>();

	// format of the String input of the expiryDate
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Calendar currentdate = Calendar.getInstance();

	/**
	 * Creates a new client with the given info (lastName,
	 *
	 * firstName,email,address,creaditCardNum,expiryDate).
	 * 
	 * @param lastName
	 *            : represents the last name of the client.
	 * @param firstName
	 *            : represents the first name of the client.
	 * @param email
	 *            : represents the email of the client.
	 * @param address
	 *            : represents the address of the client.
	 * @param creditCardNum
	 *            : represents CreaditCardNumber of the client.
	 * @param expiryDate
	 *            : represents the expiryDate of the credit card .
	 * @throws InvalidInputException.
	 * @throws ParseException.
	 */
	public Client(String lastName, String firstName, String email,
			String address, String creditCardNum, String expiryDate)
			throws InvalidInputException, ParseException {

		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.address = address;
		this.creditCardNum = creditCardNum;
		this.expiryDate = expiryDate;
		expiryYear = expiryDate.substring(0, 4);
		expiryMonth = expiryDate.substring(5, 7);
		expiryDay = expiryDate.substring(8);

		// creates new email and expiryDate check.
		ValidityOfEmail emailAdressCheck = new ValidityOfEmail(email);
		ValidityOfExpiryDate expiryDateCheck = new ValidityOfExpiryDate(
				expiryYear, expiryMonth, expiryDay);
		if (!emailAdressCheck.isValid() || !lastName.matches("[a-zA-Z]+")
				|| !firstName.matches("[a-zA-Z]+")
				|| !address.matches("[a-zA-Z0-9 ]+")
				|| !creditCardNum.matches("[0-9]+")
				|| !expiryDateCheck.isValid()
				|| !expiryDate.matches("[0-9]+[-]+[0-9]+[-][0-9]+")
				|| (expiryDate.length() != 10)) {
			throw new InvalidInputException("INVALID INPUT OF CLINET INFO");
		}
	}

	// setters and getters

	/**
	 * @return the email represents the email of client.
	 *
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set represents the email of the client.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void bookInt (Itinerary itinerary1){
		this.bookedInt.add(itinerary1);
	} 
	/**
	 * @return the lastName represents the last name of the client.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set represents the last name of the client.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName represents the first name of the client.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set represents the first name of the client.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the address represents the address of the client.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set the Address of Client.
	 * 
	 * @param address
	 *            the address to set represents the address of the client.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the creditCardNum represents the CreditCardNumber of the client.
	 */
	public String getCreditCardNum() {
		return creditCardNum;
	}

	/**
	 * @param creditCardNum
	 *            the creditCardNum to set represents the CreditCardNumber of
	 *            the client.
	 */
	public void setCreditCardNum(String creditCardNum) {
		this.creditCardNum = creditCardNum;
	}

	/**
	 * @return the expiryDate represents the ExpiryDate of the client.
	 */
	public String getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set represents the ExpiryDate of the client.
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * return all the personal information in the right format.
	 */
	@Override
	public String toString() {
		return lastName + "," + firstName + "," + email + "," + address + ","
				+ creditCardNum + "," + expiryDate;
	}

}