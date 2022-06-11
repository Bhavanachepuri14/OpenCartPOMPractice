package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By Emailid = By.id("input-email");
	private By Password = By.id("input-password");
	private By Loginbtn = By.xpath("//input[@type='submit']");
	private By ForgotPwd = By.linkText("Forgotten Password");
	private By Register = By.linkText("Register");
	
	private By SearchField = By.name("search");
	private By NewsLetter = By.linkText("Newsletter");
	private By AccntLogOutText = By.cssSelector("div#content h1");
	
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(Constants.LOGINPAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		System.out.println("Page title is: "+title);
		return title;
	}
	
	public String getLoginPageUrl() {
		String url = eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
		System.out.println("login page url is: "+url);
		return url;
	}
	
	public boolean isForgotPwdLinkExists() {
		return eleUtil.waitForElementVisible(ForgotPwd, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}
	
	public void clickOnFrgtPwd() {
		if(isForgotPwdLinkExists()) {
			driver.findElement(ForgotPwd).click();
		}
	}
	
	public boolean isRegisterlinkExists() {
		return eleUtil.waitForElementVisible(Register, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}
	
	public RegistrationPage navigateToRegistrationPage() {
		eleUtil.doClick(Register);
		return new RegistrationPage(driver);
	}
	
	public boolean isSearchFieldPresent() {
		return eleUtil.waitForElementVisible(SearchField, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}
	
	
	public boolean isNewsLetterLinkExists() {
		return eleUtil.waitForElementVisible(NewsLetter, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}
	
	public AccountsPage doLogin(String username, String pwd) {
		eleUtil.waitForElementVisible(Emailid, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(Password, pwd);
		eleUtil.doClick(Loginbtn);
		
		return new AccountsPage(driver);
	}
	
	public String getLogOutMessage() {
	String LogOutMsg = eleUtil.waitForElementVisible(AccntLogOutText, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).getText();
		System.out.println("Logout is successfull " + LogOutMsg);
		return LogOutMsg;
	}
	
}
