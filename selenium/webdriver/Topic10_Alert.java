package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic10_Alert {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, 15);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	//@Test
	public void TC_01_Acept_Alert() {
		driver.get("http://demo.guru99.com/v4/index.php");
		
		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(2);
		
		// wait for alert displays for 15s
		// wait + switch to alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Get text of alert: alert still displays
		Assert.assertEquals(alert.getText(), "User or Password is not valid");
		
		// switchTo: Alert/ Windows/ Tab/ Frame/ Iframe
		// switch to 1 alert
		// alert = driver.switchTo().alert();
		
		// Acept alert: alert will hide (OK)
		alert.accept();
		
		// Cancel alert: alert will hide (cancel)
		// alert.dismiss();
			
	}
	
	//@Test
	public void TC_02_Acept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(2);
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Get text of alert: alert still displays
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked an alert successfully");
	}	
	
	//@Test
	public void TC_03_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(2);
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Get text of alert: alert still displays
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked: Ok");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(2);
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Get text of alert: alert still displays
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		alert.dismiss();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked: Cancel");		
	}
	
	//@Test
	public void TC_04_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(2);
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Get text of alert: alert still displays
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		String addressName = "Ho Chi Minh";
		
		alert.sendKeys(addressName);
		sleepInSecond(4);
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You entered: " + addressName);
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(2);
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Get text of alert: alert still displays
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		alert.sendKeys(addressName);
		sleepInSecond(4);
		
		alert.dismiss();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You entered: null");
	}
	
	//@Test
	public void TC_05_Authentication_Alert() {
		String username = "admin";
		String password = "admin";
		String url = "http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
		
		driver.get(url);
		
		driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed();
	}
	
	@Test
	public void TC_06_Authentication_Alert() {
		String username = "admin";
		String password = "admin";
		
		driver.get("http://the-internet.herokuapp.com");
		
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()= 'Basic Auth']")).getAttribute("href");
		
		driver.get(getLinkByUserPass(basicAuthenLink, username, password));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	public String getLinkByUserPass(String link, String username, String password) {
		//http://the-internet.herokuapp.com/basic_auth
		String[] links = link.split("//");
		//links[0] = http:
		//links[1] = the-internet.herokuapp.com/basic_auth
		return links[0] + "//" + username + ":" + password + "@" + links[1];
		
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}