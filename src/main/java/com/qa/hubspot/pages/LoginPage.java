package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Step;

 

public class LoginPage extends BasePage 
{
	
	private WebDriver driver;
	
	//1. By locators---OR
	
	By username = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	
	//2. Create constructor of page class
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	//3. page actions
	@Step("Get Login Page Title.....")
	public String getLoginPageTitle()
	{
		//return driver.getTitle();
		return elementUtil.waitForTitleToBePresent(Constants.LOGIN_PAGE_TITLE,10);
	}
	@Step("Verify Signup Link on login page.....")
	public boolean verifySignUpLink()
	{
		//return driver.findElement(signUpLink).isDisplayed();
		return elementUtil.doIsDisplayed(signUpLink);
		
	}
	@Step("Login to app with username: {0} and password: {1}")
	public HomePage doLogin(String username, String password)
	{
		//driver.findElement(this.username).sendKeys(username);
		//driver.findElement(this.password).sendKeys(password);
		//driver.findElement(this.loginButton).click();
		elementUtil.waitForElementToBeVisible(this.username, 10);
		elementUtil.doSendKeys(this.username, username);
		elementUtil.doSendKeys(this.password,password);
		elementUtil.doClick(this.loginButton);
		return new HomePage(driver);
	}
	
	
}
