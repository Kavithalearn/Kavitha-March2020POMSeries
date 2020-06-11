package com.qa.hubspot.tests;

import org.testng.Assert;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspot.Listeners.ExtentReportListener;
import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


//@Listeners(ExtentReportListener.class)
@Epic("Epic - 101 : design login page with different features...")
@Story("US - 102 : design basic login page with singup, title and login form...")
public class LoginPageTest extends BaseTest {
	@Description("verify Login Page Title Test.....")
	@Test(priority = 2)
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitle() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is :" + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);

	}
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 1)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.verifySignUpLink(), "sign up link is not displayed......");
	}
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 3)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

}
