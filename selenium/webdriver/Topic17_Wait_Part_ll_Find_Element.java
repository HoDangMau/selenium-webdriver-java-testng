package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic17_Wait_Part_ll_Find_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		// driver.get("https://www.facebook.com/");
		// driver.get("http://live.techpanda.org/");
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Find_Element() {
		WebElement element = null;

		// 1 - No elements found
		// element = driver.findElement(By.id("selenium"));
		// Khi 1 step mà fail thì sẽ fail cả testcase - ko chạy các step còn lại nữa

		// 2- 1 element found
		// element = driver.findElement(By.id("email"));
		// element.sendKeys("abc@gmail.com");

		// 3 - More than 1 element found
		// Find first element
		// Don't care element visible / invisible
		element = driver.findElement(By.xpath("//a[@title='My Account']"));
		element.click();

	}

	// @Test
	public void TC_02_Find_Elements() {
		List<WebElement> links = null;

		// 1 - No elements found
		// links = driver.findElements(By.tagName("selenium"));
		// System.out.println(links.size());

		// 2- 1 element found
		// links = driver.findElements(By.id("email"));
		// System.out.println("Element size:" + links.size());
		// links.get(0).sendKeys("abc@gmail.com");

		// 3 - More than 1 element found
		// get all elements => store to list
		links = driver.findElements(By.xpath("//a[@title='My Account']"));
		System.out.println("Element size:" + links.size());

		for (WebElement link : links) {
			System.out.println(link.getText());
			System.out.println(link.getAttribute("href"));
		}

	}

	@Test
	public void TC_03_Find_Element() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.findElement(By.id("email")).sendKeys("abc@gmail.com");

	}

	@Test
	public void TC_04_Find_Element() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("send2")).click();

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}