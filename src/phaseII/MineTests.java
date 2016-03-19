package phaseII;

import java.io.IOException;
import java.text.ParseException;

public class MineTests {
	public static void main (String [] args) throws NumberFormatException, IOException, InvalidInputException, ParseException{
		//BackEnd test1 = new BackEnd("mars11", "12344");
		/*ClientMap mutation test
		Client client0 = new Client("Gee", "Gavin", "abc@gmail.com", "happyStreet", "1234567890123456", "2015-03-08");
		Client client1 = new Client("apple", "juice", "abc2@gmail.com", "happyStreet", "1234567890123456", "2015-07-08");
		Client client2 = new Client("orange", "pie", "abc3@gmail.com", "happyStreet", "1234567890123456", "2015-07-08");
		ClientMap test2 = new ClientMap();
		test2.addClient(client0);
		test2.addClient(client1);
		test2.addClient(client2);
		
		System.out.println("1st");
		System.out.println(test2);

		Client temp  = test2.getValue("abc2@gmail.com");

		temp.setEmail("love");
		System.out.println(temp);

		System.out.println("2nd");
		System.out.println(test2);*/
		/*Testing admin and client storing and extracting in ClientMap successful idea
		Client client0 = new Client("Gee", "Gavin", "abc@gmail.com", "happyStreet", "1234567890123456", "2015-03-08");
		Client admin0 = new Admin("apple", "juice", "abc2@gmail.com", "happyStreet", "1234567890123456", "2015-07-08");

		ClientMap test3 = new ClientMap();
		test3.addClient(client0);
		test3.addClient(admin0);

		System.out.println(test3);

		Client temp  = test3.getValue("abc@gmail.com");
		if (temp instanceof Admin){
			System.out.println("Admin Found");
		}
		*/
		
		//BackEnd test1 = new BackEnd("abc1@gmail.com", "12344");
		//test1.loadClient("C:\\User\\Owner-pc\\Desktop\\phaseiii\\PhaseII\\src\\phaseII\\ClientTest.csv");

		//BackEnd test2 = new BackEnd("mars12","44321");
		
	}
}
