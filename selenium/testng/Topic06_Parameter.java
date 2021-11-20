package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic06_Parameter {
	WebDriver driver;
	String firstName, lastName, emailAddress, password, day, month, year, company;
	Select select;
	String projectPath = System.getProperty("user.dir");

	@Parameters({"browser", "environment"})
	@BeforeClass
	public void beforeClass(String browserName, @Optional("LOCAL") String environmentName) {
		System.out.println(environmentName);
		
		switch (browserName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;		
		case "firefox":
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;

		default:
			throw new RuntimeException("Browser not supported");
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Parameters("url")
	@Test
	public void TC_01_NopCommerce(String url) {
		driver.get(url);

		firstName = "David";
		lastName = "Beckham";
		emailAddress = "davidbeckham" + getRandomNumber() + "@gmail.com";
		day = "10";
		month = "August";
		year = "1990";
		company = "Rode VN";
		password = "123456";

		By genderMaleBy = By.id("gender-male");
		By firtNameBy = By.id("FirstName");
		By lastNameBy = By.id("LastName");
		By dateDropdownBy = By.name("DateOfBirthDay");
		By monthDropdownBy = By.name("DateOfBirthMonth");
		By yearDropdownBy = By.name("DateOfBirthYear");
		By emailBy = By.id("Email");
		By companyBy = By.id("Company");

		driver.findElement(By.cssSelector(".ico-register")).click();
		driver.findElement(genderMaleBy).click();
		driver.findElement(firtNameBy).sendKeys(firstName);
		driver.findElement(lastNameBy).sendKeys(lastName);

		select = new Select(driver.findElement(dateDropdownBy));

		// Select 1 item A
		select.selectByVisibleText(day);

		// Check dropdown => is multiple select
		Assert.assertFalse(select.isMultiple());

		// Check that selecting item a
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

		// Count items in dropdown
		Assert.assertEquals(select.getOptions().size(), 32);

		select = new Select(driver.findElement(monthDropdownBy));
		select.selectByVisibleText(month);

		select = new Select(driver.findElement(yearDropdownBy));
		select.selectByVisibleText(year);

		driver.findElement(emailBy).sendKeys(emailAddress);
		driver.findElement(companyBy).sendKeys(company);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector(".result")).getText(), "Your registration completed");

		driver.findElement(By.cssSelector("a.ico-account")).click();

		Assert.assertTrue(driver.findElement(genderMaleBy).isSelected());
		Assert.assertEquals(driver.findElement(firtNameBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameBy).getAttribute("value"), lastName);

		select = new Select(driver.findElement(dateDropdownBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

		select = new Select(driver.findElement(monthDropdownBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		select = new Select(driver.findElement(yearDropdownBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(companyBy).getAttribute("value"), company);
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}