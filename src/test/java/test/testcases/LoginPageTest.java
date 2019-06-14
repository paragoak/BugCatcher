package test.testcases;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import test.testdata.LoginDataProviderClass;
import pages.login.LoginPage;
import test.testbase.TestBase;

public class LoginPageTest extends TestBase {

	public static Logger log = Logger.getLogger(LoginPageTest.class.getName());

	@BeforeMethod
	public void pageSetup() {
		loginPage = new LoginPage(driver);
	}

	@Test(priority = 0, description = "Verify LoginPage UI", groups = { "Smoke" })
	public void verifyLoginPageTitle() {

		TestBase.parentTest = TestBase.extent.createTest("Login Page Test");
		TestBase.parentTest.assignCategory("Smoke Test");
		TestBase.childTest = TestBase.parentTest.createNode("Verify Login Page");

		TestBase.childTest.log(Status.INFO, "Verifying LoginPage Title");

		assertTrue(driver.getTitle().contains("HMS"));
		

	}

	@Test(priority = 0, description = "Verify LoginPage UI", groups = { "Smoke" })
	public void verifyLoginPageUI() {

		System.out.println("-----START : Verify Login Page UI-----");

		TestBase.parentTest.assignCategory("Smoke Test");
		TestBase.childTest = TestBase.parentTest.createNode("Verify Login Page UI");

		TestBase.childTest.log(Status.INFO, "Verifying LoginPage UI");

		TestBase.childTest.log(Status.INFO, "Verifying Email textfield");
		assertTrue(loginPage.txtEmail.isDisplayed());

		TestBase.childTest.log(Status.INFO, "Verifying Password textfield");
		assertTrue(loginPage.txtPassword.isDisplayed());

		TestBase.childTest.log(Status.INFO, "Verifying SignIn button");
		assertTrue(loginPage.btnSignIn.isDisplayed());

		TestBase.childTest.log(Status.INFO, "Verifying ForgotPassword link");
		assertTrue(loginPage.lnkForgotPassword.isDisplayed());

		
	
	}

	@Test(priority = 2, description = "Verify valid login functionality", groups = {
			"Smoke" }, dataProvider = "credentials", dataProviderClass = LoginDataProviderClass.class)
	public void verifyValidLogin(String username, String password) throws Exception {

		TestBase.parentTest.assignCategory("Smoke Test");
		TestBase.childTest = TestBase.parentTest.createNode("Verify Valid Login");

		TestBase.childTest.log(Status.INFO, "Email : " + username);
		TestBase.childTest.log(Status.INFO, "Password : " + password);

		assertTrue(loginPage.verifyLogin(username, password), "Login funtionality failed with valid credentials");

		assertTrue(loginPage.userlogout(), "User still login");
		
		System.out.println("-----Verify Valid Login END-----");
	}

	@Test(priority = 3, description = "Verify login functionality with invalid Username", groups = "Sanity", dataProvider = "credentials", dataProviderClass = LoginDataProviderClass.class)
	public void verifyInValidUsername(String username, String password) throws Exception {

		TestBase.parentTest.assignCategory("Sanity Test");
		TestBase.childTest = TestBase.parentTest.createNode("Verify InValid Username Login");

		TestBase.childTest.log(Status.INFO, "Email : " + username);
		TestBase.childTest.log(Status.INFO, "Password : " + password);

		assertFalse(loginPage.invalidLogin(username, password), "Login successful with invalid data credentials");
		
	
	}
	
	@Test(priority = 4, description = "Verify login functionality with invalid Password", groups = "Sanity", dataProvider = "credentials", dataProviderClass = LoginDataProviderClass.class)
	public void verifyInValidPassword(String username, String password) throws Exception {

		TestBase.parentTest.assignCategory("Sanity Test");
		TestBase.childTest = TestBase.parentTest.createNode("Verify InValid Password Login");

		TestBase.childTest.log(Status.INFO, "Email : " + username);
		TestBase.childTest.log(Status.INFO, "Password : " + password);

		assertFalse(loginPage.invalidLogin(username, password), "Login successful with invalid data credentials");

		
	}

	@Test(priority = 5, description = "Verify login functionality with blank Username", groups = "Sanity", dataProvider = "credentials", dataProviderClass = LoginDataProviderClass.class)
	public void verifyBlankUsername(String username, String password) throws Exception {
		
		TestBase.parentTest.assignCategory("Sanity Test");
		TestBase.childTest = TestBase.parentTest.createNode("Verify Blank Username Login");

		TestBase.childTest.log(Status.INFO, "Email : " + username);
		TestBase.childTest.log(Status.INFO, "Password : " + password);

		assertFalse(loginPage.invalidLogin(username, password), "Login successful with invalid data credentials");
		
	}

	@Test(priority = 6, description = "Verify login functionality with blank Password", groups = "Sanity", dataProvider = "credentials", dataProviderClass = LoginDataProviderClass.class)
	public void verifyBlankPassword(String username, String password) throws Exception {

		TestBase.parentTest.assignCategory("Sanity Test");
		TestBase.childTest = TestBase.parentTest.createNode("Verify Blank Password Login");

		TestBase.childTest.log(Status.INFO, "Email : " + username);
		TestBase.childTest.log(Status.INFO, "Password : " + password);

		assertFalse(loginPage.invalidLogin(username, password), "Login successful with invalid data credentials");
		
	}


}
