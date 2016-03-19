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
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import phaseII.Client;
import phaseII.InvalidInputException;

/**
 * A class that represent a Client store in Map. In the Map the key are String
 * that contains email. And the value is list of Client info that have the
 * 
 * @author
 */

public class ClientMap implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8375700853316496599L;
	/** Map that store the clients */
	private Map<String, Client> clientList = new HashMap<String, Client>();

	/**
	 * Creates an instance of ClientMap by reading the File at filepath where the
	 * file should be in format
	 * lastName,firstName,email,address,creaditCardNum,expiryDate
	 * 
	 * @param filepath
	 *            represent the path of the client where the client info are
	 *            stored
	 * @throws IOException
	 * @throws InvalidInputException
	 * @throws NumberFormatException+
	 */
	@SuppressWarnings("resource")
	public ClientMap(String filepath) throws IOException,
			NumberFormatException, InvalidInputException {
		// initializes an empty map
		this.clientList = new HashMap();
		loadClientFromFile(filepath);
		// helping variable that are used to generate ClientMap
		}

	/**
	 * Creates an instance of empty ClientMap with an new HashMap
	 */
	public ClientMap() {
		this.clientList = new HashMap();
	}

	/**
	 * adds Client from a csv file.
	 * @param filepath represent path of the csv from which users are
	 * being added
	 */
	public void loadClientFromFile(String filepath) throws IOException{
		String[] temp;
		String line;
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		line = br.readLine();
		// while the whole file is not read create line is read and create an
		// instance of client using the information in that line
		// the client is then added to the ClientMap
		while (line != null && line != "") {
			// split the line in piece of information
			temp = line.split(",");
			// create an instance of client
			Client tempClient = null;
			try {
				tempClient = new Client(temp[0], temp[1], temp[2], temp[3],
						temp[4], temp[5]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());

			} catch (java.lang.NumberFormatException e) {
				System.out.println("INVALID FORMAT OF DATE");

			} catch (java.lang.NullPointerException e){
				System.out.println("NO SUCH EMAIL");
			}

			addClient(tempClient);
			// reads the next line in the file
			line = br.readLine();
		}
	}
	
	/**
	 * serializes this.clientList to the file on the given path
	 * @param path represents the path of the file where this.clientList
	 * is being serializes
	 */
	public void serializeMap(String path){
		try {
			FileOutputStream fileOut = new FileOutputStream (path);
			ObjectOutputStream ou = new ObjectOutputStream(fileOut);
			ou.writeObject(this.clientList);
			ou.close();
			fileOut.close();
		}
		catch (IOException i){
			i.printStackTrace();
		}
	}

	/**
	 * deserializes this.clientList from the file at the given path
	 * @param path represnets the path of the file from which 
	 * this.clientList is being deserializes
	 */
	public void deserializeMap(String path){
		try {
		 	FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.clientList = (HashMap) in.readObject();
			in.close();
			fileIn.close();
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
	/*
	public void LoadSavedMap(String path){
		try {
			FileInputStream fileIn = new FileInputStream(path);
			System.out.println("File is working fine");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			System.out.println("Inputing object working fine");
			this.clientList = (HashMap) in.readObject();
			System.out.println("object gotten");
			in.close();
			fileIn.close();
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
	}*/
	/**
	 * Adds the given client1 to the ClientMap
	 * @param client1
	 *            represent client that is to be added in the ClientMap
	 */
	public void addClient(Client client1) {
		// get the basic search info key so the it is easier to check if the
		// key already exists in the ClientMap

		String key = client1.getEmail();

		this.clientList.put(key, client1);
	}

	/**
	 * Returns the client acquire by email in String.
	 * @param email
	 *            represent a String which the arguments passed in for searching
	 *            clients
	 * @return the client acquire by email in String.
	 */
	public String getClient(String email) throws java.lang.NullPointerException{
		if (clientList.containsKey(email) == false) {
			throw new java.lang.NullPointerException();
		}
		// Search client returns a list of client.
		Client readClients = getValue(email);
		// Since there is only one client, take the first and return.
		return readClients.toString();
	}

	/**
	 * Returns a value store the given key
	 * 
	 * @param key
	 *            represent the key the of value if asked
	 * @return represent value stored at the key
	 */
	public Client getValue(String key) {
		Client result = null;
		// get all the keys of the maps
		// if the passed in key exits then only the value is returned
		if (containKey(key)) {
			// find the actual key in the map and get the value at that key
			result = clientList.get(key);
		}
		return result;
	}
	
	public void setPassword(String username, String newPassword){
		getValue(username).setPassword(newPassword);
	}

	/**
	 * Returns true if the given key is one of the key in the Map
	 * 
	 * @param key
	 *            represent the key
	 * @return return true the given key exits in the maps otherwise return
	 *         false
	 */
	public boolean containKey(String key) {
		
		return clientList.containsKey(key);

	}

	/**
	 * Returns a set of key of the ClientMap
	 * 
	 * @return represent set of String  that are key of the ClientMap
	 */
	public Set<String> getKeys() {
		return this.clientList.keySet();
	}

	/**
	 * Represent String representation of the ClientMap
	 */
	@Override
	public String toString() {
		String result = "";
		for (String key : this.clientList.keySet()) {
			result += clientList.get(key) + "\n";
		}
		return result;
	}
}
