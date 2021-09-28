package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic14_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String pageUrl;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	// @Test
	public void TC_01_Window_2Tab() {
		// Tab A: Parent page
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Get ID of tab/window which is active
		String parentPageID = driver.getWindowHandle();
		System.out.println("Parent page ID = " + parentPageID);

		// Tab B: Google page
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);

		// Switch to Google Page
		switchToWindowByID(parentPageID);

		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());

		String googlePageID = driver.getWindowHandle();
		System.out.println("Google page ID = " + googlePageID);

		driver.findElement(By.name("q")).sendKeys("Selenium");
		sleepInSecond(2);
		driver.findElement(By.name("btnK")).click();

		switchToWindowByID(googlePageID);
	}

	//@Test
	public void TC_02_Title() {
		// Tab A: Parent page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		pageUrl = driver.getCurrentUrl();
		System.out.println("Parent url = " + pageUrl);

		// Tab B: Google page
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

		// Switch to tab google
		switchToWindowByPageTitle("Google");
		sleepInSecond(2);

		pageUrl = driver.getCurrentUrl();
		System.out.println("Google url = " + pageUrl);

		driver.findElement(By.name("q")).sendKeys("Selenium");
		sleepInSecond(2);
		driver.findElement(By.name("btnK")).click();

		// Back to tab parent
		switchToWindowByPageTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);

		pageUrl = driver.getCurrentUrl();
		System.out.println("Parent url = " + pageUrl);

		// Tab C: Facebook page
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

		// Switch to tab facebook
		switchToWindowByPageTitle("Facebook - Đăng nhập hoặc đăng ký");
		sleepInSecond(2);

		pageUrl = driver.getCurrentUrl();
		System.out.println("Facebook url = " + pageUrl);

		// Switch to parent
		switchToWindowByPageTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);

		pageUrl = driver.getCurrentUrl();
		System.out.println("Parent url = " + pageUrl);

		// Tab D: Tiki page
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();

		// Switch to tab Tiki
		switchToWindowByPageTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		sleepInSecond(2);

		pageUrl = driver.getCurrentUrl();
		System.out.println("Tiki url = " + pageUrl);

	}

	@Test
	public void TC_03_Kyna() {
		driver.get("https://kyna.vn/");
		String parenPageID = driver.getWindowHandle();

		By salePopupBy = By.cssSelector("div.fancybox-inner img");
		List<WebElement> salePopupElement = driver.findElements(salePopupBy);

		if (salePopupElement.size() > 0) {
			System.out.println("-----------------------Popup displays and close-----------------");
			driver.findElement(By.cssSelector("a.fancybox-close")).click();
			sleepInSecond(2);
		} else {
			System.out.println("-----------------------Popup doesn't display and go to next steps-----------------");
		}

		// Click vào Facebook link
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']")).click();
		
		switchToWindowByPageTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.findElement(By.cssSelector("h1#seo_h1_tag span")).getText(), "Kyna.vn");

		switchToWindowByPageTitle("Kyna.vn - Học online cùng chuyên gia");

		// Click on Youtube link
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='youtube']")).click();
		
		switchToWindowByPageTitle("Kyna.vn - Youtube");
		Assert.assertEquals(driver
				.findElement(By.cssSelector("div#contentContainer div#channel-header-container div#text-container #text"))
				.getText(), "Kyna.vn");
		
		closeAllWindowWithoutParrent(parenPageID);	
		
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

	public void switchToWindowByID(String windowPageID) {
		// Get all ID of tab/window
		Set<String> allWindows = driver.getWindowHandles();

		for (String window : allWindows) {
			if (!window.equals(windowPageID)) {
				driver.switchTo().window(window);
				break;
			}
		}
	}

	public void switchToWindowByPageTitle(String expectedPageTitle) {
		// Get all ID of tab/window
		Set<String> allWindows = driver.getWindowHandles();

		for (String window : allWindows) {
			// switch to all tabs previous
			driver.switchTo().window(window);
			sleepInSecond(2);

			String actualPageTitle = driver.getTitle().trim();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}

		}
	}
	
public void closeAllWindowWithoutParrent(String windowPageID) {
		// Get all ID of tab/window
		Set<String> allWindows = driver.getWindowHandles();
		
		for (String window : allWindows) {
			// if id != id input 
			if(!window.equals(windowPageID)) {
				// Switch
				driver.switchTo().window(window);
				sleepInSecond(1);
				
				// Close tab which is active
				driver.close(); // driver stay in last tab/window which closing
				
			}
		}
		
		// => switch to parent id
		driver.switchTo().window(windowPageID);
		sleepInSecond(1);
	}

}