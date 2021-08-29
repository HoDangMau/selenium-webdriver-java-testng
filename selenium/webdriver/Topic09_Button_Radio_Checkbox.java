package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic09_Button_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExcuetor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		jsExcuetor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");

		By loginButton = By.cssSelector("button.fhs-btn-login");

		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

		// Verify login button is disabled
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());

		driver.findElement(By.cssSelector("input#login_username")).sendKeys("hodangmau@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
		sleepInSecond(1);

		// Verify login button is enabled
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());

		driver.navigate().refresh();

		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

		// Remove disabled attribute of login button
		jsExcuetor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(loginButton));
		sleepInSecond(2);

		//Verify login button with background color = RED
		String rgbacolor = driver.findElement(loginButton).getCssValue("background-color");
		System.out.println("RGBA:" + rgbacolor);
		
		String hexaColor = Color.fromString(rgbacolor).asHex().toUpperCase();
		
		Assert.assertEquals(hexaColor, "#C92127");
		
		driver.findElement(loginButton).click();
		
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='popup-login-content']//label[contains(text(),'Số điện thoại/Email')]/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='popup-login-content']//label[contains(text(),'Mật khẩu')]/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");

	}

	//@Test
	public void TC_02_Radio_Checkbox_Default() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		
		By petrolTwo = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		
		// Verify petrol 2.0 deselectd
		Assert.assertFalse(driver.findElement(petrolTwo).isSelected());	
		
		driver.findElement(petrolTwo).click();
		sleepInSecond(2);
		
		// Verify petrol 2.0 selected
		Assert.assertTrue(driver.findElement(petrolTwo).isSelected());
		
		driver.findElement(petrolTwo).click();
		sleepInSecond(2);
		
		// Verify petrol 2.0 selected
		Assert.assertTrue(driver.findElement(petrolTwo).isSelected());
		
		By dieselTwo = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");
		
		driver.findElement(dieselTwo).click();
		sleepInSecond(2);
		
		// Verify petrol 2.0 deselectd
		Assert.assertFalse(driver.findElement(petrolTwo).isSelected());	
		
		// Verify diesel 2.0 selected
		Assert.assertTrue(driver.findElement(dieselTwo).isSelected());
		
		By petrolThree = By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::input");
		
		// Verify petrol 2.0 disabled
		Assert.assertFalse(driver.findElement(petrolThree).isEnabled());
	}

	@Test
	public void TC_03_Checkbox_Default() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		By rearSideCheckbox =  By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
		
		checkToCheckbox(rearSideCheckbox);
		sleepInSecond(2);
		
		// Verify Rear Side checkbox selected
	    Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());
		
		By luggageCheckbox =  By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
		
		// Verify luggage checkbox deselected
		Assert.assertFalse(driver.findElement(luggageCheckbox).isSelected());
		
		checkToCheckbox(luggageCheckbox);
		sleepInSecond(2);
		
		// Verify luggage checkbox selected
	    Assert.assertTrue(driver.findElement(luggageCheckbox).isSelected());
	    
	    uncheckToCheckbox(luggageCheckbox);
		sleepInSecond(2);
		
		// Verify luggage checkbox deselected
	    Assert.assertFalse(driver.findElement(luggageCheckbox).isSelected());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void checkToCheckbox(By by) {
		if(!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void uncheckToCheckbox(By by) {
		if(driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}