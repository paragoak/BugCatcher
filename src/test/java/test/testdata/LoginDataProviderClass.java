package test.testdata;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class LoginDataProviderClass {

	@DataProvider(name = "credentials")
	public static Object[][] credentialsData(Method method) {

		Object[][] result = null;

		if (method.getName().equals("verifyValidLogin")) {
			result = new Object[][] { { "hms_admin1@mailinator.com", "Admin$123" } };
		} else if (method.getName().equals("verifyInValidUsername")) {
			result = new Object[][] { { "invalid@mailinator.com", "Admin$123" } };
		} else if (method.getName().equals("verifyInValidPassword")) {
			result = new Object[][] { { "hms_entity_admin1@mailinator.com", "invalid$123" } };
		} else if (method.getName().equals("verifyBlankUsername")) {
			result = new Object[][] { { "", "Admin$123" } };
		} else if (method.getName().equals("verifyBlankPassword")) {
			result = new Object[][] { { "test@test.com", "" } };
		}

		return result;

	}

	@DataProvider(name = "AdminLogins")
	public static Object[][] loginData(Method method) {

		Object[][] result = null;

		if (method.getName().equals("verifySuperAdminLogin")) {
			result = new Object[][] { { "hms_admin1@mailinator.com", "Admin$123" } };
		} else if (method.getName().equals("verifyEnityAdminLogin")) {
			result = new Object[][] { { "hms_entity_admin1@mailinator.com", "Admin$123" } };
		} else if (method.getName().equals("verifyFrontDeskAdminLogin")) {
			result = new Object[][] { { "hms_frontdesk1@mailinator.com", "Admin$123" } };
		}

		return result;

	}

	@DataProvider(name = "forgotDetails")
	public static Object[][] forgotData(Method method) {

		Object[][] result = null;

		if (method.getName().equals("verifyValidforgotpasswordTest")) {
			result = new Object[][] { { "valid_forgot_password@mailinator.com" } };
		} else if (method.getName().equals("verifyInvalidForgotPasswordTest")) {
			result = new Object[][] { { "invalid_forgot_password@mailinator.com" } };
		}

		return result;

	}

}
