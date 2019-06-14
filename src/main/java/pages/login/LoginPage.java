package pages.login;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	protected WebDriver driver;

	public static Logger log = Logger.getLogger(LoginPage.class.getName());

	// Initializing the Page Objects:
	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		log.debug("Initializing the Login Page Objects");
	}

	@FindBy(xpath = "//img[contains(@src,'login.png')]")
	WebElement imgLogo;
	
	@FindBy(xpath = "//span[@class='title'][contains(.,'HOME')]")
	WebElement lblTitleText;
	
	@FindBy(xpath = "//input[@id='email']")
	public WebElement txtEmail;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement txtPassword;

	// @FindBy(xpath = "//span[contains(text(),'Sign In')]")
	@FindBy(css = "#loginTest")
	public WebElement btnSignIn;

	@FindBy(xpath = "//a[contains(.,'Forgot Password?')]")
	public WebElement lnkForgotPassword;

	@FindBy(xpath = "//span[contains(.,'Invalid username or password')]")
	public WebElement msgTxtloginError;

	// Logout objects

	@FindBy(xpath = "//a[@aria-label='Open Profile Menu']")
	public WebElement lnkProfileMenu;

	@FindBy(xpath = "//span[contains(.,'Logout')]")
	public WebElement lnkLogout;

	@FindBy(xpath = "//span[contains(.,'User Logged Out Successfully.')]")
	public WebElement msgTxtUserLogout;
	
	

	public boolean verifyLogin(String username, String password) {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
	
		log.info("======== into verifyLogin() ========");

		try {
			
			wait.until(ExpectedConditions.visibilityOf(txtEmail));
			txtEmail.clear();
			txtEmail.sendKeys(username);
			log.info("======== Username: " + username + "========");

			wait.until(ExpectedConditions.visibilityOf(txtPassword));
			txtPassword.clear();
			txtPassword.sendKeys(password);
			log.info("======== Password: " + password + "========");

			btnSignIn.click();
			Thread.sleep(500);

		} catch (Exception e) {

			System.err.println(e);
			// e.printStackTrace();
		}
		if (driver.getCurrentUrl().endsWith("landingPage")) {

			log.info("======== Login Success ========");
		//	assertEquals(lblTitleText.getText(), "HOME");
			log.info("======== End verifyLogin() ========");
			return true;
		} else {
			log.debug("======== Login Unsuccessful ========");
			return false;
		}

	}

	public boolean invalidLogin(String username, String password) {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);

		log.info("======== into invalidLogin() ========");

		try {
			wait.until(ExpectedConditions.visibilityOf(txtEmail));

			txtEmail.clear();
			txtEmail.sendKeys(username);
			log.info("======== Username: " + username + "========");

			wait.until(ExpectedConditions.visibilityOf(txtPassword));

			txtPassword.clear();
			txtPassword.sendKeys(password);
			log.info("======== Password: " + password + "========");

			if (btnSignIn.isEnabled()) {

				btnSignIn.click();
				Thread.sleep(500);

			} else {
				log.debug("======== Button is disabled ========");

				return false;

			}
		} catch (Exception e) {

			System.err.println(e);
			// e.printStackTrace();
		}

		if ((!driver.getCurrentUrl().endsWith("/landingPage")) && msgTxtloginError.isDisplayed()) {
			log.info("======== invalidLogin() Fail ========");
			return false;
		} else {
			log.info("======== invalidLogin() Pass ========");
			return true;
		}

	}

	/*public SuperAdminLandingPage verifySuperAdminLogin(String username, String password) {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			
			wait.until(ExpectedConditions.visibilityOf(txtEmail));
			txtEmail.clear();
			txtEmail.sendKeys(username);

			wait.until(ExpectedConditions.visibilityOf(txtPassword));
			txtPassword.clear();
			txtPassword.sendKeys(password);

			btnSignIn.click();

			Thread.sleep(1000);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return new SuperAdminLandingPage(driver);
	}*/

/*	public EntityAdminLandingPage verifyEntityAdminLogin(String username, String password) {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			
			wait.until(ExpectedConditions.visibilityOf(txtEmail));
			txtEmail.clear();
			txtEmail.sendKeys(username);

			wait.until(ExpectedConditions.visibilityOf(txtPassword));
			txtPassword.clear();
			txtPassword.sendKeys(password);

			btnSignIn.click();
			
			Thread.sleep(1000);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return new EntityAdminLandingPage(driver);

	}*/

/*	public FrontDeskAdminLandingPage verifyFrontDeskAdminLogin(String username, String password) {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			
			wait.until(ExpectedConditions.visibilityOf(txtEmail));
			txtEmail.clear();
			txtEmail.sendKeys(username);

			wait.until(ExpectedConditions.visibilityOf(txtPassword));
			txtPassword.clear();
			txtPassword.sendKeys(password);

			btnSignIn.click();
			
			Thread.sleep(1000);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return new FrontDeskAdminLandingPage(driver);

	}*/

	public boolean userlogout() {

		try {
			if (lnkProfileMenu.isDisplayed()) {
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.visibilityOf(lnkProfileMenu));

				Thread.sleep(1000);
				lnkProfileMenu.click();

				Thread.sleep(1000);
				lnkLogout.click();

				Thread.sleep(1000);

				assertEquals(msgTxtUserLogout.getText(), "User Logged Out Successfully.");

				Thread.sleep(1000);
				
				return true;

			} else {
				return false;
			}
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		return false;

	}

}