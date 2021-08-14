package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic08_Custom_Dropdown_List_I {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		//Driver ID
		
		// wait to apply statuses of element (visible/ invisible/ presence/ clickable)
		explicitWait = new WebDriverWait(driver, 15);
		
		// ép kiểu tường minh (Reference casting)
		jsExecutor = (JavascriptExecutor) driver;
		
		//wait to find element (findElement / findElements)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	//@Test
	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		By parent = By.id("number-button");
		By child = By.cssSelector("ul#number-menu div");
		
		selectItemInDropdown(parent, child, "5");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text() = '5']")));
		
		selectItemInDropdown(parent, child, "10");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text() = '10']")));

	} 
	
	//@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		By parent = By.cssSelector("i.dropdown.icon");
		By child = By.cssSelector("div[role='option']>span");
		
		selectItemInDropdown(parent, child, "Stevie Feliciano");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text() = 'Stevie Feliciano']")));
		
		selectItemInDropdown(parent, child, "Matt");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text() = 'Matt']")));
		
	}	
	
	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		By parent = By.cssSelector("li.dropdown-toggle");
		By child = By.cssSelector("ul.dropdown-menu a");
		
		selectItemInDropdown(parent, child, "Second Option");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and contains(text(), 'Second Option')]")));
		
		selectItemInDropdown(parent, child, "Third Option");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and contains(text(), 'Third Option')]")));
		
		
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void selectItemInDropdown(By parentBy, By childBy, String expectedTextItem) {		
		// 1 - Click 1 element to expand all items
		driver.findElement(parentBy).click();
		
		// 2 - Wait all elements which loading(in HTML page/DOM)
		//presence
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		
		//Store all elements
		List<WebElement> allItems = driver.findElements(childBy);
		
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				if (item.isDisplayed()) {		// 3 - If item need to select in view ( can see) => click
					item.click();				
				}else {		// 4 - If item need to select (can't see) => scroll down => click
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
				}
				
			}
			
		}	
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
}