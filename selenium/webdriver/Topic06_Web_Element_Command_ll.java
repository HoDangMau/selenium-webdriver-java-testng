package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic06_Web_Element_Command_ll {
	WebDriver driver;
	By emailTextbox = By.id("mail");
	By educationTextarea = By.id("edu");
	
	By passwordTextbox = By.id("password");
	By slider2 = By.id("slider-2");
	
	By ageOver18Radio = By.id("over_18");
	By developmentCheckbox = By.id("development");
	By javaCheckbox = By.id("java");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		// System.setProperty("webdriver.chrome.driver",".\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		// Open application(AUT/SUT)
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01_Is_Displayed() {
		// Email
		WebElement emailTextbox = driver.findElement(By.id("mail"));

		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation Testing");
			System.out.println("Mail Texbox is displayed");
		} else
			System.out.println("Mail Texbox is not displayed");

		// Age
		WebElement ageOver18Radio = driver.findElement(By.id("over_18"));

		if (ageOver18Radio.isDisplayed()) {
			ageOver18Radio.click();
			System.out.println("Age over 18 is displayed");
		} else
			System.out.println("Age over 18 is not displayed");

		// Edu
		WebElement educationTextarea = driver.findElement(By.id("edu"));

		if (educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys("Automation Testing");
			System.out.println("Education is displayed");
		} else
			System.out.println("Education is not displayed");
	}

	@Test
	public void TC_02_Is_Displayed_Refactor() {
		if (isElementDisplayed(emailTextbox)) {
			senKeysToElement(emailTextbox, "Automation Testing");			
		}
		
		if (isElementDisplayed(ageOver18Radio)) {
			clickToElement(ageOver18Radio);		
		}
		
		if (isElementDisplayed(educationTextarea)) {
			senKeysToElement(educationTextarea, "Automation Testing");			
		}
	}

	@Test
	public void TC_03_Is_Disabled() {
		//Expect 1 element enabled
		Assert.assertTrue(isElementEnabled(emailTextbox));
		Assert.assertTrue(isElementEnabled(ageOver18Radio));
		Assert.assertTrue(isElementEnabled(educationTextarea));

		//Expect 1 element disabled
		Assert.assertFalse(isElementEnabled(passwordTextbox));
		Assert.assertFalse(isElementEnabled(slider2));
	}
	
	@Test
	public void TC_04_Is_Selected() {
		clickToElement(ageOver18Radio);
		clickToElement(developmentCheckbox);
		
		Assert.assertTrue(isElementSelected(ageOver18Radio));
		Assert.assertTrue(isElementSelected(developmentCheckbox));
		Assert.assertFalse(isElementSelected(javaCheckbox));
		
		clickToElement(ageOver18Radio);
		clickToElement(developmentCheckbox);
		
		Assert.assertTrue(isElementSelected(ageOver18Radio));
		Assert.assertFalse(isElementSelected(developmentCheckbox));
		Assert.assertFalse(isElementSelected(javaCheckbox));	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element [" + by + "]is dissplayed");
			return true;
		} else
			System.out.println("Element [" + by + "]is not dissplayed");
		return false;
	}
	
	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element [" + by + "]is Enabled");
			return true;
		} else
			System.out.println("Element [" + by + "]is Disabled");
		return false;
	}
	
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element [" + by + "]is Selected");
			return true;
		} else
			System.out.println("Element [" + by + "]is Diselected");
		return false;
	}
	
	public void senKeysToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}
	
	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

}