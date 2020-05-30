package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	WebDriver driver;
	Properties prop;

	/**
	 * this method is used to initialize the WebDriver on the basis of browser
	 * 
	 * @param browserName
	 * @return driver
	 */

	public WebDriver init_driver(Properties prop)
	{
	
	String browserName = prop.getProperty("browser");
	
	
	
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
			
		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			 driver = new InternetExplorerDriver();
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
		return driver;

	}
	
	
	/**
	 * this method is used to initialize the properties from config properties file
	 * @return prop
	 */

	public Properties init_prop() {
		prop = new Properties();
		try {
			//FileInputStream ip = new FileInputStream(" D:/MyProgram/Selenium/WorkSpace/OnlineSession/OnlineSessionSelenium/March2020POMSeries/src/main/java/com/qa/hubspot/config/config.properties");
			System.out.println("Before Property Read");
			FileInputStream ip = new FileInputStream("./src/main/java/com/qa/hubspot/config/config.properties");
			System.out.println("After Property Read");
			//System.out.println(" ip : " + ip.
			prop.load(ip);
			System.out.println("Trace browser ::: " +  prop.getProperty("browser"));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		catch (IOException e) {

			e.printStackTrace();
		}
		return prop;

	}

}
