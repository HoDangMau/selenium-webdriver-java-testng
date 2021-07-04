package webdriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class Topic04_Xpath_Css {
	// Set variable
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Open Firefox browser
		// System.setProperty("webdriver.gecko.driver",".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Set timeout to find elements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Open application(AUT/SUT)
		driver.get("http://live.demoguru99.com/index.php");
	}

	@Test
	public void TC_01_Login_Empty_Email_And_Password() {
		// Click Account link
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Enter data in input field
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");

		// Click on Login button
		driver.findElement(By.id("send2")).click();

		// Get error message text of Email / Password => Verify
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		// Navigate to home page
		driver.get("http://live.demoguru99.com/index.php");

		// Click Account link
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Enter data in input field
		driver.findElement(By.id("email")).sendKeys("123@234.123");
		driver.findElement(By.id("pass")).sendKeys("12345678");

		// Click on Login button
		driver.findElement(By.id("send2")).click();

		// Get error message text of Email => Verify
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_Login_Invalid_Password() {
		// Navigate to home page
		driver.get("http://live.demoguru99.com/index.php");

		// Click Account link
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		// Enter data in input field
		driver.findElement(By.id("email")).sendKeys("hodangmau@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");

		// Click on Login button
		driver.findElement(By.id("send2")).click();

		// Get error message text of Email => Verify
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_04_Login_Incorrect_Email() {
		// Navigate to home page
		driver.get("http://live.demoguru99.com/index.php");
		
		// Click Account link
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Enter data in input field
		driver.findElement(By.id("email")).sendKeys("hodangmau4555434@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456789");
		
		// Click on Login button
		driver.findElement(By.id("send2")).click();
		
		// Get error message text of Email => Verify
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
