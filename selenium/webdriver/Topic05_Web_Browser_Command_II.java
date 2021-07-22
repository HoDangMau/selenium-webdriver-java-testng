package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Topic05_Web_Browser_Command_II {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@BeforeMethod
	public void beforeMethod() {
		// Open application(AUT/SUT)
		driver.get("http://live.demoguru99.com");
	}
	
	@Test
	public void TC_01_Verify_URL() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl,"http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl,"http://live.demoguru99.com/index.php/customer/account/create/");
		
	}
	
	@Test
	public void TC_02_Verify_Title() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle,"Customer Login");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle,"Create New Customer Account");
		
	}	
	
	@Test
	public void TC_03_Navigation() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
		
		driver.navigate().back();
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.navigate().forward();
		String registerPageForwardUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageForwardUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
		
	}
	
	@Test
	public void TC_04_Page_Source() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		String myAccountPageSource = driver.getPageSource();
		Assert.assertTrue(myAccountPageSource.contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Create an Account"));
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}