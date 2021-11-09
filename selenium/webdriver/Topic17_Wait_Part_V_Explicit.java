package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic17_Wait_Part_V_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>button")).click();

		// Wait for loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

		// After click - take 5s to Hello work display
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());

		System.out.println(driver.findElement(By.cssSelector("div#finish>h4")).getText());

	}

	// @Test
	public void TC_02_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();

		// After click - take 5s to Hello work display
		// Wait for Hello work display
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());

		System.out.println(driver.findElement(By.cssSelector("div#finish>h4")).getText());

	}
	
	@Test
	public void TC_03_Date_Picker() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='25']")));
		
		driver.findElement(By.xpath("//a[text()='25']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id$='RadCalendar1']>div.raDiv"))); // $ lấy cuối
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='25']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='25']")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Thursday, November 25, 2021");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}