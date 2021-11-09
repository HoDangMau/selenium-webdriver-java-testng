package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic17_Wait_Part_lV_Dead {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void TC_01_Timeout_Less_Than_Element_Display() { 
		long start = System.currentTimeMillis();
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		long finish = System.currentTimeMillis();
		long totalTime = finish - start; 
		System.out.println("Total Time for page load - "+totalTime); 
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		sleepInSecond(3);
		
		// After click - take 5s to Hellowork display
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
		
		System.out.println(driver.findElement(By.cssSelector("div#finish>h4")).getText());
	}

	@Test
	public void TC_02_Timeout_Equal_Element_Display() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		sleepInSecond(5);
		
		// After click - take 5s to Hellowork display
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
		
		System.out.println(driver.findElement(By.cssSelector("div#finish>h4")).getText());

	}

	@Test
	public void TC_03_Timeout_Greater_Than_Element_Display() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		sleepInSecond(10);
		
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