package phaseII;

@SuppressWarnings("serial")
/**
 * a class that checks for invalid input
 * @author jeff
 *
 */
public class InvalidInputException extends Exception{
	
	public InvalidInputException(){};

	/**
	 * 
	 * @param message
	 */
	public InvalidInputException(String message){
			super(message);
	
	}

}
