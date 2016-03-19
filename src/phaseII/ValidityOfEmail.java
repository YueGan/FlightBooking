package phaseII;


/**
 * a class to check if a email address is valid
 * @author jeff
 *
 */
public class ValidityOfEmail {
		
	String emailAdress;
		
	/**
	 * 
	 * @param emailAdress
	 */
	public ValidityOfEmail(String emailAdress) {
		this.emailAdress = emailAdress;
	}
	/**
	 * a class that checks a email address
	 * @return a boolean checks if email matches the following condition
	 */
	public Boolean isValid() {
		return this.emailAdress.matches("[a-zA-Z0-9]+"
				                        + "(?:\\.[a-zA-Z0-9]+)*"
				                        + "@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+"
				                        + "[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?");
	        
	    }
	}
