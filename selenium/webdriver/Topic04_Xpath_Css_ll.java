package webdriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class Topic04_Xpath_Css_ll {
	WebDriver driver;
	String name, email, password, phone;
	
	//Action
	By nameTextbox = By.id("txtFirstname");
	By emailTextbox = By.id("txtEmail");
	By confirmEmailTextbox = By.id("txtCEmail");
	By passwordTextbox = By.id("txtPassword");
	By confirmPasswordTextbox = By.id("txtCPassword");
	By phoneTextbox = By.id("txtPhone");
	By registerButton = By.xpath("//form[@id='frmLogin']//button");
	
	//Error
	By nameErrorMsg = By.id("txtFirstname-error");
	By emailErrorMsg = By.id("txtEmail-error");
	By confirmEmailErrorMsg = By.id("txtCEmail-error");
	By passwordErrorMsg = By.id("txtPassword-error");
	By confirmPasswordErrorMsg = By.id("txtCPassword-error");
	By phoneErrorMsg = By.id("txtPhone-error");

	

	@BeforeClass
	public void beforeClass() {
		// Open Firefox browser
		System.setProperty("webdriver.gecko.driver",".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Set timeout to find elements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//set data
		name = "Mau Ho";
		email = "hodangmau@gmail.com";
		password = "12345678";
		phone = "0939303082";
	}
	
	@BeforeMethod
	public void beforeMethod() {
		// Open application(AUT/SUT)
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		driver.findElement(registerButton).click();
		
		//Verify
		Assert.assertEquals(driver.findElement(nameErrorMsg).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErrorMsg).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsg).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordErrorMsg).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsg).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneErrorMsg).getText(), "Vui lòng nhập số điện thoại.");	
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(emailTextbox).sendKeys("123.123@1@23");
		driver.findElement(confirmEmailTextbox).sendKeys("123.123@1@23");
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(confirmPasswordTextbox).sendKeys(password);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(registerButton).click();
		Assert.assertTrue(driver.findElement(emailErrorMsg).isDisplayed());
		Assert.assertTrue(driver.findElement(confirmEmailErrorMsg).isDisplayed());		
	}
	
	@Test
	public void TC_03_Register_Incorrect_Confirm_Email() {
		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(confirmEmailTextbox).sendKeys("abc@gmail.com");
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(confirmPasswordTextbox).sendKeys(password);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(registerButton).click();
		Assert.assertTrue(driver.findElement(confirmEmailErrorMsg).isDisplayed());	
	}
	
	@Test
	public void TC_04_Register_Password_Less_Than_6_Characters() {
		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(confirmEmailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys("123");
		driver.findElement(confirmPasswordTextbox).sendKeys("123");
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(registerButton).click();
		Assert.assertEquals(driver.findElement(passwordErrorMsg).getText(), "Mật khẩu phải có ít nhất 6 ký tự");	
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsg).getText(), "Mật khẩu phải có ít nhất 6 ký tự");			
	}
	
	@Test
	public void TC_05_Register_Incorrect_Confirm_Pwds() {
		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(confirmEmailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(confirmPasswordTextbox).sendKeys("12356888");
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(registerButton).click();
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsg).getText(), "Mật khẩu bạn nhập không khớp");			
	}
	
	@Test
	public void TC_06_Register_Incorrect_Phone() {
		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(confirmEmailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(confirmPasswordTextbox).sendKeys(password);
		driver.findElement(phoneTextbox).sendKeys(email);
		driver.findElement(registerButton).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsg).getText(), "Vui lòng nhập con số");
		
		//Clear data and enter data again
		driver.findElement(phoneTextbox).clear();
		driver.findElement(phoneTextbox).sendKeys("0342");
		driver.findElement(registerButton).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsg).getText(), "Số điện thoại phải từ 10-11 số.");
		
		//Clear data and enter data again
		driver.findElement(phoneTextbox).clear();
		driver.findElement(phoneTextbox).sendKeys("23444");
		driver.findElement(registerButton).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsg).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
