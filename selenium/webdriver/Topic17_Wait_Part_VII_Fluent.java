package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic17_Wait_Part_VII_Fluent {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentWaitDriver;
	FluentWait<WebElement> fluentWaitElement;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	// @Test
	public void TC_01() {
		driver.get("https://automationfc.github.io/fluent-wait/");

		WebElement countDownTime = driver.findElement(By.id("javascript_countdown_time"));

		// fluentWaitDriver = new FluentWait<WebDriver>(driver);
		fluentWaitElement = new FluentWait<WebElement>(countDownTime);

		// Wait với tổng thời gian là 15 second
		fluentWaitElement.withTimeout(Duration.ofSeconds(15))

				// Cơ chế tìm lại nếu chưa thỏa mãn điều kiện là 0.5 s tìm lại 1 lần
				.pollingEvery(Duration.ofMillis(500))

				// Nếu như trong thời gian tìm lại mà ko thấy element
				.ignoring(NoSuchElementException.class)

				// XỬ lý diều kiện
				.until(new Function<WebElement, Boolean>() {

					@Override // Điều kiện
					public Boolean apply(WebElement element) {
						String text = element.getText();
						System.out.println("Time = " + text);
						return text.endsWith("00");
					}
				});
	}

	// @Test
	public void TC_02() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>button")).click();

		fluentWaitDriver = new FluentWait<WebDriver>(driver);

		// Tổng thời gian < thời gian của element được hiểu thị
		fluentWaitDriver.withTimeout(Duration.ofSeconds(6))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);

		WebElement hellowordText = fluentWaitDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.cssSelector("div#finish>h4"));
			}
		});

		Assert.assertEquals(hellowordText.getText(), "Hello World!");
	}

	//@Test
	public void TC_03() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>button")).click();

		fluentWaitDriver = new FluentWait<WebDriver>(driver);

		// Tổng thời gian < thời gian của element được hiểu thị
		fluentWaitDriver.withTimeout(Duration.ofSeconds(6))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class)
				.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.cssSelector("div#finish>h4")).getText().equals("Hello World!");
			}
		});

	}
	
	//@Test
	public void TC_04() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		waitForElementAndClick(By.cssSelector("div#start>button"));
		
		Assert.assertEquals(getWebElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
		Assert.assertTrue(waitForElementAndDisplayed(By.xpath("//div[@id = 'finish']/h4[text() = 'Hello World!']")));
	}
	
	@Test
	public void TC_05() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		getWebElement(By.id("txtUsername")).sendKeys("Admin");
		getWebElement(By.id("txtPassword")).sendKeys("admin123");
		getWebElement(By.id("btnLogin")).click();
		
		Assert.assertTrue(isPageLoadedSuccess(driver));
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
	
	// findElement ( Custom)
	public WebElement getWebElement(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(locator);
			}
		});
		return element;
	}
	
	public void waitForElementAndClick(By locator) {
		WebElement element = getWebElement(locator);
		element.click();	
	}
	
	public Boolean waitForElementAndDisplayed(By locator) {
		WebElement element = getWebElement(locator);
		return element.isDisplayed();	
	}
	
	public boolean isPageLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 30);
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