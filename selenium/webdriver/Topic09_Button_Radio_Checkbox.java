package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

	// @Test
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

		// Verify login button with background color = RED
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

	// @Test
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

	// @Test
	public void TC_03_Checkbox_Default() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");

		checkToCheckbox(rearSideCheckbox);
		sleepInSecond(2);

		// Verify Rear Side checkbox selected
		Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());

		By luggageCheckbox = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");

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

	// @Test
	public void TC_04_Radio_Custom() {
		driver.get("https://material.angular.io/components/radio/examples");

		// 1 - input card can't click - can verify (custom)
		//By winterRadioButton = By.xpath("//input[@value='Winter']");


		//driver.findElement(winterRadioButton).click();
		//sleepInSecond(2);

		//Assert.assertTrue(driver.findElement(winterRadioButton).isSelected());
		
		// 2 - span card => can click but can't verify
		//By winterRadioButton = By.xpath("//input[@value='Winter']/preceding-sibling::span[contains(@class,'outer')]");


		//driver.findElement(winterRadioButton).click();
		//sleepInSecond(8);

		//Assert.assertTrue(driver.findElement(winterRadioButton).isSelected());	
		
		// 3 - span card => click
		// input card => verify
		//By winterRadioSpan = By.xpath("//input[@value='Winter']/preceding-sibling::span[contains(@class,'mat-radio-outer-circle')]");
		//By winterRadioInput = By.xpath("//input[@value='Winter']");
		
		//driver.findElement(winterRadioSpan).click();
		//sleepInSecond(2);

		//Assert.assertTrue(driver.findElement(winterRadioInput).isSelected());	
		// Reality project
		// Maintain => take time
		// 1 element => have to define locator for 2 times (input/span) 
		
		// 4 - Use input card (click = JS vs Verify)
		By winterRadioInput = By.xpath("//input[@value='Winter']");
		
		checkToRadioCheckboxByJS(winterRadioInput);
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(winterRadioInput).isSelected());
		
	}
	
	//@Test
	public void TC_05_Checkbox_Custom() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		By checkedCheckbox = By.xpath("//span[text() = 'Checked']/preceding-sibling::span/input");
		By indeterminateCheckbox = By.xpath("//span[text() = 'Indeterminate']/preceding-sibling::span/input");
		
		checkToRadioCheckboxByJS(checkedCheckbox);
		checkToRadioCheckboxByJS(indeterminateCheckbox);
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());
		
		uncheckToCheckboxByJS(checkedCheckbox);
		uncheckToCheckboxByJS(indeterminateCheckbox);
		sleepInSecond(2);
		
		Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());

	}
	
	//@Test
	public void TC_06_Radio_Checkbox_Google_Docs() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By canthoRadio = By.cssSelector("div[aria-label='Cần Thơ']");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label = 'Cần Thơ' and @aria-checked='false']")).isDisplayed());
		Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"), "false");

		
		checkToCheckbox(canthoRadio);
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"), "true");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label = 'Cần Thơ' and @aria-checked='true']")).isDisplayed());
		
		List<WebElement> checkboxes = driver.findElements(By.xpath("//div[@role='checkbox']"));
		
		// click all
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
			sleepInSecond(2);
		}
		
		// verify all
		for (WebElement checkbox : checkboxes) {
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
		}			
	}
	
	@Test
	public void TC_07_Live_Guru() {
		driver.get("https://live.demoguru99.com/index.php/backendlogin");
		
		driver.findElement(By.id("username")).sendKeys("user01");
		driver.findElement(By.id("login")).sendKeys("guru99com");
		driver.findElement(By.cssSelector("input[title='Login']")).click();
		
		sleepInSecond(5);
		//Popup
		//WebDriverWait
		
		clickToCheckboxByCustomerName("Automation VN");		
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
	public void clickToCheckboxByCustomerName(String customerName) {
		WebElement customerNameCheckbox = driver.findElement(By.xpath("//td[contains(text(), '" +customerName+"')]/preceding-sibling::td/input"));
		if(!customerNameCheckbox.isSelected()) {
			customerNameCheckbox.click();
		}
	}
	
	public void clickToElementJs(By by) {
		jsExcuetor.executeScript("arguments[0].click();", driver.findElement(by));
	}
	
	public void checkToRadioCheckboxByJS(By by) {
		if (!driver.findElement(by).isSelected()) {
			jsExcuetor.executeScript("arguments[0].click();", driver.findElement(by));
		}
	}
	
	public void uncheckToCheckboxByJS(By by) {
		if (driver.findElement(by).isSelected()) {
			jsExcuetor.executeScript("arguments[0].click();", driver.findElement(by));
		}
	}

	public void checkToCheckbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}

	public void uncheckToCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
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