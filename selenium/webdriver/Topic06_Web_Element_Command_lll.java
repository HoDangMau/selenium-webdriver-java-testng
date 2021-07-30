package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic06_Web_Element_Command_lll {
	WebDriver driver;
	String firstName, lastName, fullName, email, password;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		// System.setProperty("webdriver.chrome.driver",".\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();
		driver = new FirefoxDriver();

		firstName = "John";
		lastName = "Wick";
		fullName = firstName + " " + lastName;
		email = "john" + getRandomNumber() + "@gmail.com";
		password = "123456";

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_Is_Displayed() {
		driver.get("https://login.mailchimp.com/signup/");

		driver.findElement(By.id("email")).sendKeys("hodangmau@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("hodangmau");

		WebElement passwordTextbox = driver.findElement(By.id("new_password"));
		WebElement signUpButton = driver.findElement(By.id("create-account"));
		WebElement newsletterCheckbox = driver.findElement(By.id("marketing_newsletter"));
		newsletterCheckbox.click();

		// Lowercase
		passwordTextbox.sendKeys("auto");

		// Verify label of lowercase > update
		Assert.assertTrue(driver
				.findElement(By.xpath("//li[contains(@class,'lowercase-char') and text()='One lowercase character']"))
				.isDisplayed());
		Assert.assertFalse(signUpButton.isEnabled());

		// Uppercase
		passwordTextbox.clear();
		passwordTextbox.sendKeys("AUTO");

		Assert.assertTrue(driver
				.findElement(By.xpath("//li[contains(@class,'uppercase-char') and text()='One uppercase character']"))
				.isDisplayed());
		Assert.assertFalse(signUpButton.isEnabled());

		// Number
		passwordTextbox.clear();
		passwordTextbox.sendKeys("123456");

		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'number-char') and text()='One number']"))
				.isDisplayed());
		Assert.assertFalse(signUpButton.isEnabled());

		// Special
		passwordTextbox.clear();
		passwordTextbox.sendKeys("###$$$");

		Assert.assertTrue(
				driver.findElement(By.xpath("//li[contains(@class,'special-char') and text()='One special character']"))
						.isDisplayed());
		Assert.assertFalse(signUpButton.isEnabled());

		// >= 8 characters
		passwordTextbox.clear();
		passwordTextbox.sendKeys("12345678");

		Assert.assertTrue(
				driver.findElement(By.xpath("//li[contains(@class,'8-char') and text()='8 characters minimum']"))
						.isDisplayed());
		Assert.assertFalse(signUpButton.isEnabled());

		// Full val data
		passwordTextbox.clear();
		passwordTextbox.sendKeys("Auto12345!!!");

		Assert.assertFalse(driver
				.findElement(By.xpath("//li[contains(@class,'lowercase-char') and text()='One lowercase character']"))
				.isDisplayed());
		Assert.assertFalse(driver
				.findElement(By.xpath("//li[contains(@class,'uppercase-char') and text()='One uppercase character']"))
				.isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[contains(@class,'number-char') and text()='One number']"))
				.isDisplayed());
		Assert.assertFalse(
				driver.findElement(By.xpath("//li[contains(@class,'special-char') and text()='One special character']"))
						.isDisplayed());
		Assert.assertFalse(
				driver.findElement(By.xpath("//li[contains(@class,'8-char') and text()='8 characters minimum']"))
						.isDisplayed());
		Assert.assertTrue(signUpButton.isEnabled());

		// Check newsletter checkbox is selected
		Assert.assertTrue(newsletterCheckbox.isSelected());
	}

	@Test
	public void TC_02_LiveGuru_Register() {
		driver.get("http://live.guru99.com/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);

		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath(
				"//li[@class='success-msg']//span[text()=\"Thank you for registering with Main Website Store.\"]"))
				.isDisplayed());

		// 1st way
		String confirmation = driver.findElement(By
				.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p"))
				.getText();
		Assert.assertTrue(confirmation.contains(fullName));
		Assert.assertTrue(confirmation.contains(email));

		// 2nd way
		Assert.assertTrue(driver.findElement(By.xpath(
				"//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(string(),'"+fullName+"')]"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(
				"//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(string(),'"+email+"')]"))
				.isDisplayed());
		
		driver.findElement(By.xpath("//a/span[text()='Account']")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
	}
	
	@Test
	public void TC_03_LiveGuru_Login() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys(email);  
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		String confirmation = driver.findElement(By
				.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p"))
				.getText();
		Assert.assertTrue(confirmation.contains(fullName));
		Assert.assertTrue(confirmation.contains(email));
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}