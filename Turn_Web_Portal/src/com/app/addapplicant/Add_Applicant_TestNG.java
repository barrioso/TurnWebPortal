package com.app.addapplicant;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

public class Add_Applicant_TestNG {
	
    String baseURL = "https://partners.turn-stage.io";
	WebDriver driver;
	String actualResult = null;
	String expectedResult = null; 
	
	
	@BeforeTest
	/*This method is used to launch the Chrome's browser instance and it is used to log in to Turn
	 Web Portal page as well. This one annotation is executed just once before all the @Test included below.*/
	public void launchBrowserAndLogin() throws Exception {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\Chrome_Driver\\chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.get(baseURL);
		driver.manage().window().maximize();
		
		Thread.sleep(4000);
		WebElement txtUserName = driver.findElement(By.name("username"));
		txtUserName.sendKeys("candidate@turning.io");
		fnHighlightMe(driver,txtUserName, null);
					
		WebElement txtPassword = driver.findElement(By.name("password"));
		txtPassword.sendKeys("@9vioPd*rV7mXkOjTwHe");
		fnHighlightMe(driver,txtPassword, null);
		
		driver.findElement(By.className("auth0-label-submit")).click();
		Thread.sleep(4000);
	}
	
	@BeforeMethod
	/*This method is used to click on the "Add Candidate" button all and it is executed before to run 
	 all the @Test included below.*/
	public void turnAppLogIn() throws Exception { 
	driver.findElement(By.xpath("//button[@id='check_worker']")).click();
	
	}

	@AfterMethod
	/*This method is used to click on the "Cancel" button under the "Add Candidate" window 
	 and it is executed after to run all the @Test included below.*/
	public void closeAddApplicantWindow() throws Exception {
		driver.findElement(By.xpath("//button[@id='close_modal']")).click();
		Thread.sleep(1000);
	}	
	
	@AfterTest
	/*This method is used to close the Chrome's browser instance once all the @Test included below were run.*/
	public void tearDown(){
		driver.quit();
	}

/*++++++++++++++++++++++++++++TEST SCENARIOS++++++++++++++++++++++++++++*/
	/*This scenario validate the following:
	 Test 1 - GIVEN the user enters a voIP phone number under the ADD CANDIDATE window
	   		  WHEN  the user hits the NEXT button 
	   		  THEN  the "Oops! You entered a voip number. Please enter a valid mobile number" message is displayed 
	   		  AND   the user is unable to continue with the ADD CANDIDATE flow*/
	@Test(priority = 1)
	public void  voipPhoneNumber() throws Exception {

	driver.findElement(By.id("worker_firstname")).sendKeys("Test1");
	driver.findElement(By.id("worker_lastname")).sendKeys("Test");
	driver.findElement(By.id("worker_phone")).sendKeys("312-278-3565");
	driver.findElement(By.id("worker_email")).sendKeys("jobs@turning.io");
	driver.findElement(By.id("worker_ssn")).sendKeys("123456789");
	
	driver.findElement(By.xpath("//button[@id='confirm_data']")).click();
	Thread.sleep(4000);
		
	actualResult = "Oops! You entered a voip number. Please enter a valid mobile number.";
	expectedResult = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[1]/div/div[2]/div[3]")).getText();
	Assert.assertEquals(actualResult, expectedResult);
	
	}		
	/*This scenario validate the following:
	 Test 1 - GIVEN the user enters a landline phone number under the ADD CANDIDATE window
	   		  WHEN  the user hits the NEXT button 
	   		  THEN  the "Oops! You entered a landline number. Please enter a valid mobile number." message is displayed 
	   		  AND   the user is unable to continue with the ADD CANDIDATE flow*/
	@Test(priority = 2)
	public void  landLineNumber() throws Exception {

	driver.findElement(By.id("worker_firstname")).sendKeys("Test1");
	driver.findElement(By.id("worker_lastname")).sendKeys("Test");
	driver.findElement(By.id("worker_phone")).sendKeys("773-235-0227");
	driver.findElement(By.id("worker_email")).sendKeys("jobs@turning.io");
	driver.findElement(By.id("worker_ssn")).sendKeys("123456789");
	
	driver.findElement(By.xpath("//button[@id='confirm_data']")).click();
	Thread.sleep(4000);
		
	actualResult = "Oops! You entered a landline number. Please enter a valid mobile number.";
	expectedResult = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[1]/div/div[2]/div[3]")).getText();
	Assert.assertEquals(actualResult, expectedResult);
	
	}		
	/*This scenario validate the following:
	 Test 1 - GIVEN the user enters an undeliverable email account under the ADD CANDIDATE window
	   		  WHEN  the user hits the NEXT button 
	   		  THEN  the "This email is undeliverable" message is displayed 
	   		  AND   the user is unable to continue with the ADD CANDIDATE flow*/
	@Test(priority = 3)
	public void  emailUndeliverable() throws Exception {
		
	driver.findElement(By.id("worker_firstname")).sendKeys("Test1");
	driver.findElement(By.id("worker_lastname")).sendKeys("Test");
	driver.findElement(By.id("worker_phone")).sendKeys("312-203-6261");
	driver.findElement(By.id("worker_email")).sendKeys("candidate@turning.io");
	driver.findElement(By.id("worker_ssn")).sendKeys("123456789");

	driver.findElement(By.xpath("//button[@id='confirm_data']")).click();
	Thread.sleep(4000);
	
	actualResult = "This email is undeliverable";
	expectedResult = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[1]/div/div[3]/div[3]")).getText();
	Assert.assertEquals(actualResult, expectedResult);

}
	
	/*This scenario validate the following:
	 Test 1 - GIVEN the user enters an email account with errors under the ADD CANDIDATE window
	   		  WHEN  the user hits the NEXT button 
	   		  THEN  the "This email address is not formatted correctly" message is displayed 
	   		  AND   the user is unable to continue with the ADD CANDIDATE flow*/
	@Test(priority = 4)
	public void  emailFormatNotCorrect() throws Exception {
		
	driver.findElement(By.id("worker_firstname")).sendKeys("Test1");
	driver.findElement(By.id("worker_lastname")).sendKeys("Test");
	driver.findElement(By.id("worker_phone")).sendKeys("312-203-6261");
	driver.findElement(By.id("worker_email")).sendKeys("123turning.io");
	driver.findElement(By.id("worker_ssn")).sendKeys("123456789");

	driver.findElement(By.xpath("//button[@id='confirm_data']")).click();
	Thread.sleep(4000);
	
	actualResult = "This email address is not formatted correctly";
	expectedResult = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[1]/div/div[3]/div[3]")).getText();
	Assert.assertEquals(actualResult, expectedResult);

	}	
	
	/*This scenario validate the following:
	 Test 1 - GIVEN the user leaves the first name field empty under the ADD CANDIDATE window
	   		  WHEN  the user hits the NEXT button 
	   		  THEN  the "This field is required" message is displayed 
	   		  AND   the user is unable to continue with the ADD CANDIDATE flow*/
	@Test(priority = 5)
	public void  firstNameEmpty() throws Exception {
	
	driver.findElement(By.id("worker_firstname")).sendKeys(" ");
	driver.findElement(By.id("worker_lastname")).sendKeys("Test");
	driver.findElement(By.id("worker_phone")).sendKeys("312-203-6261");
	driver.findElement(By.id("worker_email")).sendKeys("jobs@turning.io");
	driver.findElement(By.id("worker_ssn")).sendKeys("123456789");

	driver.findElement(By.xpath("//button[@id='confirm_data']")).click();
	Thread.sleep(4000);

	actualResult = "This field is required";
	expectedResult = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[1]/div/div[1]/div[1]/div/div[2]")).getText();
	Assert.assertEquals(actualResult, expectedResult);

	}
	
	/*This scenario validate the following:
	 Test 1 - GIVEN the user leaves the last name field empty under the ADD CANDIDATE window
	   		  WHEN  the user hits the NEXT button 
	   		  THEN  the "This field is required" message is displayed 
	   		  AND   the user is unable to continue with the ADD CANDIDATE flow*/
	@Test(priority = 6)
	public void  lastNameEmpty() throws Exception {
	
	driver.findElement(By.id("worker_firstname")).sendKeys("Test");
	driver.findElement(By.id("worker_lastname")).sendKeys(" ");
	driver.findElement(By.id("worker_phone")).sendKeys("312-203-6261");
	driver.findElement(By.id("worker_email")).sendKeys("jobs@turning.io");
	driver.findElement(By.id("worker_ssn")).sendKeys("123456789");

	driver.findElement(By.xpath("//button[@id='confirm_data']")).click();
	Thread.sleep(4000);

	actualResult = "This field is required";
	expectedResult = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[1]/div/div[1]/div[2]/div/div[2]")).getText();
	Assert.assertEquals(actualResult, expectedResult);
	
	}
	
	/*This scenario validate the following:
	 Test 1 - GIVEN the user enters an incorrect SSN under the ADD CANDIDATE window
	   		  WHEN  the user hits the NEXT button 
	   		  THEN  the "Please enter this candidate's SSN." message is displayed 
	   		  AND   the user is unable to continue with the ADD CANDIDATE flow*/
	@Test(priority = 7)
	public void  ssnInavlid() throws Exception {

	driver.findElement(By.id("worker_firstname")).sendKeys("Test");
	driver.findElement(By.id("worker_lastname")).sendKeys("Test");
	driver.findElement(By.id("worker_phone")).sendKeys("312-203-6261");
	driver.findElement(By.id("worker_email")).sendKeys("jobs@turning.io");
	driver.findElement(By.id("worker_ssn")).sendKeys("66668352");

	driver.findElement(By.xpath("//button[@id='confirm_data']")).click();
	Thread.sleep(4000);

	actualResult = "Please enter this candidate's SSN.";
	expectedResult = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[1]/div/div[4]/div[3]")).getText();
	Assert.assertEquals(actualResult, expectedResult);

	}	
	
	/*This scenario validate the following:
	 Test 1 - GIVEN the user enters all required information under the ADD CANDIDATE window
	   		  WHEN  the user hits the NEXT button is able to navigate to the Confirm Candidate Details page
	   		  WHEN  the user hits the SUBMIT  
	   		  THEN  the new candidate is registered and the message displayed is "he Candidate has been notified"*/
	@Test(priority = 8)
	public void  addCandidateHappyPathFlow() throws Exception {
	
	driver.findElement(By.id("worker_firstname")).sendKeys("Test1");
	driver.findElement(By.id("worker_lastname")).sendKeys("Test");
	driver.findElement(By.id("worker_phone")).sendKeys("312-203-6261");
	driver.findElement(By.id("worker_email")).sendKeys("jobs@turning.io");
	driver.findElement(By.id("worker_ssn")).sendKeys("123456789");
					
	driver.findElement(By.xpath("//button[@id='confirm_data']")).click();
	Thread.sleep(3000);
			
	driver.findElement(By.xpath("//button[@id='submit_modal']")).click();
	Thread.sleep(4000);
	
	actualResult = "The Candidate has been notified";
	expectedResult = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/h3")).getText();
	Assert.assertEquals(actualResult, expectedResult);
	
	}		
	
	/*This JAVASCRIPT function is used to highlight the background from the fields User & Password
	 within the Log In page*/			
public static void fnHighlightMe(WebDriver driver, WebElement txtUserName, WebElement txtPassword) throws Exception{
		   JavascriptExecutor js = (JavascriptExecutor)driver;
		   for (int iCnt = 0; iCnt < 3; iCnt++) {
			   try {
		         js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", txtUserName, txtPassword);
		         js.executeScript("arguments[0].style.border=''", txtUserName, txtPassword);
			   } catch (Exception e) {
				    throw new Exception("Class BaseClass | Method fnHighlightMe | Exception desc: Interrupted Exception", e);
			   }
		   }
		}
}



