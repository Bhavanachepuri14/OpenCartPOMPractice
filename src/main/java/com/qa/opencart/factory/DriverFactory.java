package com.qa.opencart.factory;

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

import com.qa.opencart.customExceptions.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	 WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/* This method is to initialize the browser by passing the browsername parameter*/
	
	public WebDriver init_driver(Properties prop) {
		String browsername = prop.getProperty("browser").trim();
		System.out.println("Browser name is: "+browsername);
		optionsManager = new OptionsManager(prop);
		
		switch (browsername) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		default:
			System.out.println("Please pass the right browser name: " +browsername);
			break;
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
		
	public Properties init_prop() {
		FileInputStream ip = null;
		prop = new Properties();
		
		//mvn commandline arg: mvn clean install -Denv = "qa"
		
		String envName = System.getProperty("env");
		System.out.println("Running tests in the environment "+ envName);
		if(envName==null) {
			System.out.println("No environment is provided, hence running in QA ");
		try {
		 ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		}
		else {
			try {
			switch (envName.toLowerCase()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "prod":
				ip = new FileInputStream("./src/test/resources/config/config.properties");
				break;
			case "uat":
				ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;
			default:
				System.out.println("Please pass the right environment value: " +envName);
				throw new FrameworkException("No environment is found");
				
			}
			}catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (FrameworkException e) {
				e.printStackTrace();
			} catch (Exception e) {
			e.printStackTrace();
		}	
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public String getScreenshot() {
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = "./screenshot/"+System.currentTimeMillis()+".png";
		try {
			FileUtils.copyFile(src, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	
	
}
