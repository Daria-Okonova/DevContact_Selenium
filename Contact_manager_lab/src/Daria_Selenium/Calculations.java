package Daria_Selenium;

import java.util.HashMap;
import java.util.Random;

// Class for additional calculations and data recording
public class Calculations {
	
	HashMap<Integer, String> StateMap = new HashMap<Integer, String>(); // hashmap for states
	static HashMap<String, String> NameMap = new HashMap<String, String>(); // hashmap for recording Last name and First Name
	
// Populate the hash table of values for drop down
	private void populateStateHM (){

		StateMap.put(1, "Alabama");
		StateMap.put(2, "Iowa");
		StateMap.put(3, "Washington");
		StateMap.put(4, "New Mexico");
		StateMap.put(5, "Texas");

	}
	
// Get a value from the hasj table by int ID
	public String getStateByID (int iState){
		
		populateStateHM();
		return StateMap.get(iState); 
	}
	
// Get random value within min and max
	public int getRandInt(int min, int max) {

	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
// Record Last Name and First Name to the hash map 
	public void recordNames (String sFirstName, String sLastName){
		NameMap.put("fn", sFirstName);
		NameMap.put("ln", sLastName);
	}
	
// Get a Name by string key (last name or first name)
	public String getName (String sKey){
		return NameMap.get(sKey);	
	}
}
