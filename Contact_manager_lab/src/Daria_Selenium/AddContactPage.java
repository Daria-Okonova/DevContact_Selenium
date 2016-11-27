package Daria_Selenium;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddContactPage {
	WebDriver driver = null;
	
	public AddContactPage (WebDriver driver){
		this.driver = driver;
		assertTrue(isPageReady()); // verify the last element on the page is displayed
	}
	
// Verify the last element on the page is ready
	public boolean isPageReady(){
		
		WebElement _ele  = driver.findElement(By.xpath("//input[@type='submit']"));
		return _ele.isDisplayed();
	}

// Populate new contact and submit
	public HomePage addNewContact(String sFirstName, String sLastName, String sPhone, String sEmail, String sStreet1, String sStreet2, String sState, String sCity, String sZip){
		//populating fields
		driver.findElement(By.name("Contact.FirstName")).sendKeys(sFirstName);
		driver.findElement(By.name("Contact.LastName")).sendKeys(sLastName);
		driver.findElement(By.name("Contact.Phone")).sendKeys(sPhone);
		driver.findElement(By.name("Contact.Email")).sendKeys(sEmail);
		driver.findElement(By.name("Contact.Address.Street1")).sendKeys(sStreet1);
		driver.findElement(By.name("Contact.Address.Street2")).sendKeys(sStreet2);
		//drop down
		Select dropdown = new Select(driver.findElement(By.id("Contact_Address_State")));
		dropdown.selectByVisibleText(sState);
		
		driver.findElement(By.name("Contact.Address.City")).sendKeys(sCity);
		driver.findElement(By.name("Contact.Address.Zip")).sendKeys(sZip);
		
		//click Submit button
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		return new HomePage(driver);
		
	} 
	
	
}
