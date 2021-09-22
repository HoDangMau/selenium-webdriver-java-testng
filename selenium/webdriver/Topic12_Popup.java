package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic12_Popup {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");

		By loginPopup = By.cssSelector("div#modal-login-v1");

		// Verify login popup is not displayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

		// Click to login button
		driver.findElement(By.cssSelector("button.login_.icon-before")).click();
		sleepInSecond(2);

		// Verify login popup is displayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.error-login-panel")).getText(),
				"Tài khoản không tồn tại!");

	}

	//@Test
	public void TC_02_Random_Popup_In_Dom() {
		// Step 1
		driver.get("https://blog.testproject.io/");

		By mailcPopup = By.cssSelector("div.mailch-wrap");

		// Wait for page loaded success
		Assert.assertTrue(isPageLoadedSuccess(driver));

		// Step 2
		if (driver.findElement(mailcPopup).isDisplayed()) {
			System.out.println("-----------------------Popup displays and close-----------------");
			driver.findElement(By.cssSelector("div#close-mailch")).click();
			sleepInSecond(2);
			Assert.assertFalse(driver.findElement(mailcPopup).isDisplayed());
		}

		// Step 3
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();

		// Wait for page loaded success
		Assert.assertTrue(isPageLoadedSuccess(driver));

		// Step 4: Verify all post title contains Selenium
		List<WebElement> postTitles = driver.findElements(By.cssSelector("h3.post-title>a"));
		System.out.println("All post title = " + postTitles.size());

		for (WebElement postTitle : postTitles) {
			Assert.assertTrue(postTitle.getText().contains("Selenium"));

		}

	}

	@Test
	public void TC_03_Random_Popup_Not_In_Dom() {
		// Step 1
		driver.get("https://shopee.vn/");

		By shopeePopupBy = By.cssSelector("div.shopee-popup__container");

		// Step 2
		List<WebElement> shopeePopupElement = driver.findElements(shopeePopupBy);
		
		if (shopeePopupElement.size() > 0) {
			System.out.println("-----------------------Popup displays and close-----------------");
			driver.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
			sleepInSecond(2);
			Assert.assertEquals(driver.findElements(shopeePopupBy).size(), 0);
		}

		// Step 3
		driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("Macbook Pro");
		driver.findElement(By.cssSelector("button.btn-solid-primary")).click();
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

	public boolean isPageLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 120);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

}