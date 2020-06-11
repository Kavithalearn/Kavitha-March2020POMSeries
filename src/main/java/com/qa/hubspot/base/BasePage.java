package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.qa.hubspot.utils.ElementUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	WebDriver driver;
	public ElementUtil elementUtil;
	public Properties prop;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * this method is used to initialize the WebDriver on the basis of browser
	 * 
	 * @param browserName
	 * @return driver
	 */

	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());

		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			// driver = new InternetExplorerDriver();
			tlDriver.set(new InternetExplorerDriver());
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		// getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	/**
	 * this method is used to initialize the properties file  from config properties file on the basis of given env variable
	 * 
	 * @return prop
	 */

	public Properties init_prop() {
		prop = new Properties();
		String path = null;
		String env = null;

		try {
				env = System.getProperty("env");
				System.out.println("Executing env Values ::: " + env);
				if (env == null || env.trim().length() <= 0) 
				{
					System.out.println("Executing env is null");
					path = "./src/main/java/com/qa/hubspot/config/config.properties";
				} 
				else 
				{
					switch (env) 
					{
						case "qa":
							path = "./src/main/java/com/qa/hubspot/config/qa.config.properties";
							break;
						case "dev":
							path = "./src/main/java/com/qa/hubspot/config/dev.config.properties";
							break;
						case "stage":
							path = "./src/main/java/com/qa/hubspot/config/stage.config.properties";
							break;
						default:
							System.out.println("Please pass the correct env value-------->" + env);
							break;
	
					}
					
				}
			
			System.out.println("Executing env path value ::: " +path);

			// FileInputStream ip = new FileInputStream("
			// D:/MyProgram/Selenium/WorkSpace/OnlineSession/OnlineSessionSelenium/March2020POMSeries/src/main/java/com/qa/hubspot/config/config.properties");
			System.out.println("Before Property Read");
			FileInputStream ip = new FileInputStream(path);
			System.out.println("After Property Read");
			// System.out.println(" ip : " + ip.
			prop.load(ip);
			System.out.println("Trace browser ::: " + prop.getProperty("browser"));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		catch (IOException e) {

			e.printStackTrace();
		}
		return prop;

	}

	/**
	 * this method will take the screenshot
	 */
	public String getScreenshot() {

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;

	}
}
