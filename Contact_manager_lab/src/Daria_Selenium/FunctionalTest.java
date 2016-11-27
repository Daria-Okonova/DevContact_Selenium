package Daria_Selenium;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// Main Test Class
public class FunctionalTest {
	
// variables declarations
	public static WebDriver driver;
	public static String homePage = "http://devcontactmanager.devlab.qualit.co.nz/";
	public int iPositionNumber; // position number is used to locate the found contact
// radnomizing contact details for adding a contact by using current Time to Milliseconds -can be done through something like math.random*999999.toString
	public String sFirstName = "test_firstname-"+ UUID.randomUUID().toString().substring(0, 7);
	public String sLastName = "test_lastname-"+ UUID.randomUUID().toString().substring(0, 7);
	public String sPhone = UUID.randomUUID().toString().substring(0, 7);
	public String sEmail =  UUID.randomUUID().toString().substring(0, 7) +"@qq.ru";
	public String sAddress1 = "test_address1 "+ UUID.randomUUID().toString().substring(0, 7);
	public String sAddress2 = "test_address2 "+ UUID.randomUUID().toString().substring(0, 7);
	public String sCity =  UUID.randomUUID().toString().substring(0, 7);
	public String sZip =  UUID.randomUUID().toString().substring(0, 4);
	Calculations calc = new Calculations();
	public String sState = calc.getStateByID(calc.getRandInt(1, 5)); //randomly get one of 5 states // possibly shorten to one method
	
// Open the page
	@Before // to be executed before every test
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");   
		driver = new ChromeDriver();
		driver.get(homePage);	
	}
	
//Test - add contact + view contact
	@Test
	public void addContact(){
		HomePage homePage = new HomePage (driver); // initialize homepage + assert the last element on the page is displayed
		AddContactPage addContactPage =  homePage.clickAddContact(); // click "Add Contact" button
		homePage = addContactPage.addNewContact(sFirstName,sLastName,sPhone,sEmail,sAddress1,sAddress2,sState,sCity,sZip); //populate randomized data and add new Contact
		calc.recordNames(sFirstName, sLastName); // record Last Name and First Name in a hash map for further use
		//Search for the firstname && lastname. Method returns the position number in the table. If the contact is not found, method returns -1. 
		//Assumption is made that the combination of First Name & Last Name is unique - the method does not cover the case with >1 match.
		//This also covers View contact test case
		iPositionNumber = homePage.findContactRowByName(sFirstName,sLastName); // row number in the table is returned
		assertTrue(iPositionNumber>0); //verify the .findContact did not return -1.
		ViewContactPage viewContactPage = homePage.clickViewContact(iPositionNumber); //click "View" button for the selected position number
		//assertTrue(viewContactPage.compareContact(sFirstName,sLastName,sPhone,sEmail,sAddress1,sAddress2,sState,sCity,sZip)); //verify every field in the opened contact is as per the input
		assertEquals(sFirstName,viewContactPage.getFirstName());
		assertEquals(sLastName,viewContactPage.getLastName());
		assertEquals(sPhone,viewContactPage.getPhone());
		assertEquals(sEmail,viewContactPage.getEmail());
		assertEquals(sAddress1,viewContactPage.getAddr1());
		assertEquals(sAddress2,viewContactPage.getAddr2());
		assertEquals(sState,viewContactPage.getState());
		assertEquals(sCity,viewContactPage.getCity());
		assertEquals(sZip,viewContactPage.getZip());
		homePage  = viewContactPage.backToContacts(); //navigate back to home page
	}
	
// Test - delete the contact that was previously added
	@Test 
	public void deleteContact(){
		HomePage homePage = new HomePage (driver); // initialize homepage + assert the last element on the page is displayed
		String sFirstNameToDelete = calc.getName("fn"); // get recorded values from the addContact method
		String sLastNameToDelete = calc.getName("ln");
		iPositionNumber = homePage.findContactRowByName(sFirstNameToDelete, sLastNameToDelete); //search the same contact that was added above
		assertTrue(iPositionNumber>0); // assert the contact exists (.findContact did not return -1.)
		homePage.deleteContact(iPositionNumber); // click Delete for the selected contact
		iPositionNumber = homePage.findContactRowByName(sFirstNameToDelete, sLastNameToDelete); //searching for the deleted contact again
		assertTrue(iPositionNumber==-1); // assert the contact is not found
	}
	
// Remove cookies and close the browser - after every test
	@After
	public void cleanUp(){
		driver.manage().deleteAllCookies();
		driver.close();
	}
		
}

