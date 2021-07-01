package webdriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class Topic03_Selenium_Locator {
	// Set variable
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Open Firefox browser
		// System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Set timeout to find elements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Open application(AUT/SUT)
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	public void TC_01_FindElement() {

		// Single element: WebElement
		WebElement loginButton = driver.findElement(By.className(""));
		loginButton.click();

		// findElement
		// By.xxx with locator
		// Action: click /getText / sendKeys

		// Multiple element: List<WebElement>
		List<WebElement> buttons = driver.findElements(By.className(""));
		buttons.get(0).click();
	}

	@Test
	public void TC_02_ID() {

		// selenium locator
		driver.findElement(By.id("send2")).click();

		// Verify email error message shows up
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
	}

	@Test
	public void TC_03_Class() {
		driver.navigate().refresh();
		
		driver.findElement(By.className("validate-password")).sendKeys("12345678");
	}

	@Test
	public void TC_04_Name() {
		driver.navigate().refresh();
		
		driver.findElement(By.name("send")).click();
		
		// Verify email error message shows up
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
	}

	@Test
	public void TC_05_Tagname() {
		// Show up all links on this screen => get text
		List<WebElement> loginPageLinks = driver.findElements(By.tagName("a"));
		
		for (WebElement webElement : loginPageLinks) {
			System.out.println(webElement.getText());		
		}

	}

	@Test
	public void TC_06_LinkText() {
		driver.navigate().refresh();
		
		driver.findElement(By.linkText("Forgot Your Password?")).click();
		
		// Verify email address displays
		Assert.assertTrue(driver.findElement(By.id("email_address")).isDisplayed());
	}

	@Test
	public void TC_07_PartialLinkText() {
		driver.findElement(By.partialLinkText("Back to")).click();
		
		// Verify email address displays
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());

	}

	@Test
	public void TC_08_Css() {
		driver.navigate().refresh();
		driver.findElement(By.cssSelector("#email")).sendKeys("hodangmau@gmail.com");
		driver.findElement(By.cssSelector("input[name='login[password]']")).sendKeys("12345678");
	}

	@Test
	public void TC_09_Xpath() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hodangmau@gmail.com");
		driver.findElement(By.xpath("//label[contains(text(),'Password')]/following-sibling::div/input")).sendKeys("12345678");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
