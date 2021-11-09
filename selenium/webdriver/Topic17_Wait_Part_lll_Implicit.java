package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic17_Wait_Part_lll_Implicit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void TC_01_Timeout_Less_Than_Element_Display() { 
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// After click - take 5s to Hellowork display
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
		
		System.out.println(driver.findElement(By.cssSelector("div#finish>h4")).getText());
	}

	@Test
	public void TC_02_Timeout_Equal_Element_Display() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// After click - take 5s to Hellowork display
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
		
		// driver.findElement(By.cssSelector("div#finish>h4"))
		// Nó ko quan tâm element hiển thị / ko hiển thị -> có trong DOM
		
		 //isDisplayed() -> hiển thị / ko hiển thị
		 //true: Render ra trên UI
		 //false: Chưa render ra trên UI
		 //Ko liên quan gì đến implycitWait / findElement
		
		System.out.println(driver.findElement(By.cssSelector("div#finish>h4")).getText());

	}

	@Test
	public void TC_03_Timeout_Greater_Than_Element_Display() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// After click - take 5s to Hellowork display
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
		
		System.out.println(driver.findElement(By.cssSelector("div#finish>h4")).getText());

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