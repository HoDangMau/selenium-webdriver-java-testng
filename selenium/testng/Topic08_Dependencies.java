package testng;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listener.ExtendReport;

@Listeners(ExtendReport.class)
public class Topic08_Dependencies {
	
	@Test
	public void User_01_Create_New_User() {
		System.out.println("Run Testcase 01");
		Assert.assertTrue(false);
	}

	@Test(dependsOnMethods = "User_01_Create_New_User")
	public void User_02_View_User() {
		System.out.println("Run Testcase 02");
	}

	@Test(dependsOnMethods = "User_01_Create_New_User")
	public void User_03_Edit_User() {
		System.out.println("Run Testcase 03");
	}
	
	@Test(dependsOnMethods = "User_01_Create_New_User")
	public void User_04_Move_User() {
		System.out.println("Run Testcase 04");
	}

	@Test(dependsOnMethods = "User_01_Create_New_User")
	public void User_05_Delete_User() {
		System.out.println("Run Testcase 05");
	}

}
