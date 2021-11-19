package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic01_Annotation {
  @Test
  public void TC_01() {
	  System.out.println("Run Testcase 01");
  }
  
  @Test
  public void TC_02() {
	  System.out.println("Run Testcase 02");
  }
  
  @Test
  public void TC_03() {
	  System.out.println("Run Testcase 03");
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Run BeforeMethod");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Run AfterMethod");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("Run BeforeClass");

  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Run AfterClass");

  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Run BeforeTest");

  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Run AfterTest");

  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("Run BeforeSuite");

  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("Run AfterSuite");

  }

}
