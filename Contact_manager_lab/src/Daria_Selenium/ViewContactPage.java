package Daria_Selenium;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ViewContactPage{
	WebDriver driver = null;
	
	public ViewContactPage (WebDriver driver){
		this.driver = driver;
		assertTrue(isPageReady()); // verify the last element on the page is displayed
	}
		
// Verify the last element on the page is displayed
	public boolean isPageReady(){
	
		WebElement _ele  = driver.findElement(By.partialLinkText("Back to contacts"));
		return _ele.isDisplayed();
	}
		
/*
	// Compare displayed contact with the input values
	public boolean compareContact(String sFirstName, String sLastName,String sPhone, String sEmail, String sStreet1, String sStreet2, String sState, String sCity, String sZip){
		//get values for the contact
		String sCurrentFName = driver.findElement(By.xpath("//div[@id='main']/ul/li[1]/label[@class='value']")).getText();
		String sCurrentLName = driver.findElement(By.xpath("//div[@id='main']/ul/li[2]/label[@class='value']")).getText();
		String sCurrentPhone = driver.findElement(By.xpath("//div[@id='main']/ul/li[3]/label[@class='value']")).getText();
		String sCurrentEmail = driver.findElement(By.xpath("//div[@id='main']/ul/li[4]/label[@class='value']")).getText();
		String sCurrentAddr1 = driver.findElement(By.xpath("//div[@id='main']/ul/li[5]/label[@class='value']")).getText();
		String sCurrentAddr2 = driver.findElement(By.xpath("//div[@id='main']/ul/li[6]/label[@class='value']")).getText();
		String sCurrentState = driver.findElement(By.xpath("//div[@id='main']/ul/li[7]/label[@class='value']")).getText();
		String sCurrentCity  = driver.findElement(By.xpath("//div[@id='main']/ul/li[8]/label[@class='value']")).getText();
		String sCurrentZip   = driver.findElement(By.xpath("//div[@id='main']/ul/li[9]/label[@class='value']")).getText();
				
		//compare the actual values with expected values and return boolean (if one of the conditions does not match the whole result will be false)
		return (sFirstName.equals(sCurrentFName)) && 
			   (sCurrentLName.equals(sLastName)) &&
			   (sCurrentPhone.equals(sPhone)) &&
			   (sCurrentEmail.equals(sEmail)) &&
			   (sCurrentAddr1.equals(sStreet1)) &&
			   (sCurrentAddr2.equals(sStreet2)) &&
			   (sCurrentState.equals(sState)) &&
			   (sCurrentCity.equals(sCity)) &&
			   (sCurrentZip.equals(sZip));
		} 
	*/
	
	// Block of methods returning values from the fields for a contact.
	/** 
	 * @return String First Name
	 */
	public String getFirstName (){
		return driver.findElement(By.xpath("//div[@id='main']/ul/li[1]/label[@class='value']")).getText();
	}
	
	public String getLastName (){
		return driver.findElement(By.xpath("//div[@id='main']/ul/li[2]/label[@class='value']")).getText();
	}
	
	public String getPhone (){
		return driver.findElement(By.xpath("//div[@id='main']/ul/li[3]/label[@class='value']")).getText();
	}
	
	public String getEmail (){
		return driver.findElement(By.xpath("//div[@id='main']/ul/li[4]/label[@class='value']")).getText();
	}
	
	public String getAddr1 (){
		return driver.findElement(By.xpath("//div[@id='main']/ul/li[5]/label[@class='value']")).getText();
	}
	
	public String getAddr2 (){
		return driver.findElement(By.xpath("//div[@id='main']/ul/li[6]/label[@class='value']")).getText();
	}
	
	public String getState (){
		return driver.findElement(By.xpath("//div[@id='main']/ul/li[7]/label[@class='value']")).getText();
	}
	
	public String getCity (){
		return driver.findElement(By.xpath("//div[@id='main']/ul/li[8]/label[@class='value']")).getText();
	}
	
	public String getZip (){
		return driver.findElement(By.xpath("//div[@id='main']/ul/li[9]/label[@class='value']")).getText();
	}
	// end of block
		
// Click "back to contacts", returns homepage
	public HomePage backToContacts (){
		driver.findElement(By.partialLinkText("Back to contacts")).click();
		return new HomePage (driver);
	}
}
