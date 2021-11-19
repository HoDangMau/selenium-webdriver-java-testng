package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic03_Group {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		Assert.assertTrue(false);
		//Trong trường hợp mà before mà fail thì tất cả các testcase / after sẽ bị skip
		// 1 bộ testcase cần run regression có 100 class
		// x3 browser = 300 class
		// Before mà fail hết = 300 browser đang mở chưa đóng ở task bar + 300 file executable trong task manager
		// => cần có @AfterClass(alwaysRun = true) => để lun chạy được thằng after và quit browser
	}
	
	@Test(groups = "user")
	public void TC_01() {
		System.out.println("Run Testcase 01");
	}

	@Test(groups = "user")
	public void TC_02() {
		System.out.println("Run Testcase 02");
	}

	@Test(groups = {"user", "admin"})
	public void TC_03() {
		System.out.println("Run Testcase 03");
	}
	
	@Test(groups = {"user", "super"})
	public void TC_04() {
		System.out.println("Run Testcase 04");
	}

	@Test(groups = "super")
	public void TC_05() {
		System.out.println("Run Testcase 05");
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}


}
