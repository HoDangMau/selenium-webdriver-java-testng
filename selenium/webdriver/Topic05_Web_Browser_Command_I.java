package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic05_Web_Browser_Command_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		// System.setProperty("webdriver.chrome.driver",".\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Browser() {
		//Open url
		driver.get("https://www.messenger.com/"); //*
		
		//Close a active tab
		driver.close();
		
		//Close browser
		driver.quit(); //*
		
		//Get id of active window/tab
		String messengerID = driver.getWindowHandle();
		
		//Get all id of window/tab
		Set<String> allTabs = driver.getWindowHandles();
		
		//Switch to tab/window
		driver.switchTo().window(messengerID); //*
		
		//Find 1 element with 1 locator
		WebElement mailTextbox = driver.findElement(By.id("")); //*
		mailTextbox.clear();
		mailTextbox.sendKeys("");
		
		//Find all elements with 1 locator
		List<WebElement> buttons = driver.findElements(By.id("")); //*
		
		//Return current url
		String homePageURL = driver.getCurrentUrl(); //*
		
		//Return page source
		String homePageSource = driver.getPageSource();
		
		//Returm page title
		String pageTitle = driver.getTitle();
		
		//Get/delete cookies of page
		//Buld Framework: Share state of class
		//Get cookies after login => transmit class => reduce login time
		driver.manage().deleteAllCookies();
		
		//Get log of browser
		driver.manage().logs().getAvailableLogTypes();
		
		//Wait for finding element(s)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //*
		
		//Wait for page which loading successfully(Option)
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

		//Wait for 1 script which executing successfully
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);

		//Open full screen
		driver.manage().window().fullscreen();
		
		//Maximize screen
		driver.manage().window().maximize(); //*
		
		//Set browser position
		driver.manage().window().setPosition(new Point(0,0));
		
		//Get browser size
		driver.manage().window().getSize();
		driver.manage().window().setSize(new Dimension(1366,768));
		
		//Back to page
		driver.navigate().back();
		
		//Forward to page
		driver.navigate().forward();
		
		//Reresh page
		driver.navigate().refresh();
		
		//Like get but keep history well
		driver.navigate().to("");
		
		//Windows/Tab
		//Alert
		//Frame/Iframe
		driver.switchTo().alert(); //*
		driver.switchTo().window(""); //*
		driver.switchTo().frame(""); //*
		
		
	}

	@Test
	public void TC_02() {

	}

	@Test
	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}