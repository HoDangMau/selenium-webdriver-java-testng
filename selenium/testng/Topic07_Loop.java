package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic07_Loop {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		 driver.manage().window().maximize();

	}

	@Test(dataProvider = "register", invocationCount = 3)
	public void TC_01_Register_System(String emailAddr, String passWord) {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		driver.findElement(By.xpath("//a[@class='button']//span[text()='Create an Account']")).click();

		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("demo");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("of me");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddr);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(passWord);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(passWord);

		driver.findElement(By.xpath("//button[@title='Register']")).click();

		driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Log Out']")).click();

	}

	@DataProvider(name = "register")
	public Object[][] registerData() {

		return new Object[][] { { "demo" + getRandomNum() + "@mail.com", "123456" } };
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNum() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
}
