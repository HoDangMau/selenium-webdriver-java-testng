package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic16_Upload_File_I {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String dalatName = "DaLat.jpg";
	String hueName = "Hue.jpg";
	String dalatPath = projectPath + "\\uploadFiles\\" + dalatName;
	String huePath = projectPath + "\\uploadFiles\\" + hueName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 20);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Sendkeys_Single_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Load file
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(dalatPath);
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(huePath);
		sleepInSecond(1);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + dalatName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hueName + "']")).isDisplayed());

		List<WebElement> uploadButtons = driver.findElements(By.cssSelector("tr.template-upload button.start"));

		for (WebElement uploadButton : uploadButtons) {
			uploadButton.click();
			sleepInSecond(1);

		}

		// Verify upload file success
		Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + dalatName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + hueName + "']")).isDisplayed());

	}

	//@Test
	public void TC_02_Sendkeys_Multiple_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Load file
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(dalatPath + "\n" + huePath);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + dalatName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hueName + "']")).isDisplayed());

		List<WebElement> uploadButtons = driver.findElements(By.cssSelector("tr.template-upload button.start"));

		for (WebElement uploadButton : uploadButtons) {
			uploadButton.click();
			sleepInSecond(1);

		}

		// Verify upload file success
		Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + dalatName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + hueName + "']")).isDisplayed());

	}

	@Test
	public void TC_03_GoFile() {
		driver.get("https://gofile.io/uploadFiles");

		// Load file
		driver.findElement(By.xpath("//input[@id='uploadFile-Input']")).sendKeys(dalatPath + "\n" + huePath);
		
		// wait for files loading
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
		
		// wait for spinner loading
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#mainContent i.fa-spinner")));
		
		// wait for text displays
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'Your files have been successfully uploaded')]")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//h5[contains(text(),'Your files have been successfully uploaded')]")).isDisplayed());
		
		driver.get(driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).getAttribute("href"));
		
		// Verify upload file success
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()= '" +dalatName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()= '" +hueName+"']")).isDisplayed());


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