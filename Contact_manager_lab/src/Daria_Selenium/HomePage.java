package Daria_Selenium;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	WebDriver driver = null;
		
	public HomePage (WebDriver driver){
		this.driver = driver;
		assertTrue (isPageReady()); // verify the last element on the page is displayed
	}
	
// Verify the last element on the page is ready
	public boolean isPageReady(){
			
		WebElement _ele = driver.findElement(By.id("addContactLink"));
		return _ele.isDisplayed();
	}	
	
// Press Add Contact Button and navigate to AddContact page. Returns an object of AddContactPage class.
	public AddContactPage clickAddContact (){
		
		driver.findElement(By.id("addContactLink")).click();
		return new AddContactPage(driver); 
	}
	
// Find the contact by First Name and Last Name. Returns row number if match found. Returns -1 if match not found.
/** 
 * @param sFirstName First Name to search for
 * @param sLastName Last Name to search for
 * @return Number of the row where the match was found. If not found returns -1
 */
	public int findContactRowByName (String sFirstName, String sLastName){
		
		//method will return -1 if the sought-for first name & last name aren't found
		int iPositionNumber = -1;
		//get number of rows in the table body
		int iRowsCount = driver.findElements(By.xpath("//table[@id='ContactsTable']/tbody/tr")).size();
	    //for each row
        for(int i=1; i<=iRowsCount; i++ ){
        	//get the value of First Name
        	String sCurrentFName  = driver.findElement(By.xpath("//table[@id='ContactsTable']/tbody/tr["+i+"]/td[3]")).getText();
        	//get the value of Last Name
        	String sCurrentLName  = driver.findElement(By.xpath("//table[@id='ContactsTable']/tbody/tr["+i+"]/td[4]")).getText();
        	//check if current First Name and Last Name match the sought-for FN & LN
        	if ((sCurrentFName.equals(sFirstName)) && (sCurrentLName.equals(sLastName))){
        		iPositionNumber = i; //if yes, return the row number
        	}
        }
        return iPositionNumber; 
	}
	
// Click "View" button for the selected position in the table. Returns an object of ViewContactPage class.
	public ViewContactPage clickViewContact(int iPositionNumber){
		driver.findElement(By.xpath("//table[@id='ContactsTable']/tbody/tr["+iPositionNumber+"]/td[1]")).click();
		return new ViewContactPage(driver);
	}
	
// Click "Delete" button for the selected position in the table
	public void deleteContact (int iPositionNumber){
		driver.findElement(By.xpath("//table[@id='ContactsTable']/tbody/tr["+iPositionNumber+"]/td[5]")).click();
	}
}
