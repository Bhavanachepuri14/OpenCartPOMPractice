package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	public RegistrationPage(WebDriver driver) {
		
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By FirstName = By.id("input-firstname");
	private By LastName = By.id("input-lastname");
	private By Email = By.id("input-email");
	private By TelePhone = By.id("input-telephone");
	private By Pwd = By.id("input-password");
	private By ConfirmPwd = By.id("input-confirm");
	
	private By SubscribeYes = By.xpath("(//label[@class='radio-inline']/input[@type='radio'])[position()=1]");
	private By SubscribeNo = By.xpath("(//label[@class='radio-inline']/input[@type='radio'])[position()=2]");
	
	private By CheckBox = By.xpath("//div[@class='pull-right']/input[@type='checkbox']");
	private By ContnueBtn = By.xpath("//input[@type='submit']");
	private By SuccessMsg = By.cssSelector("div#content h1");
	
	private By LogOutlink = By.linkText("Logout");
	private By Registerlink = By.linkText("Register");
	
	
	public boolean userRegister(String firstName, String lastName, String email, String telephone,
			String Password, String subscribe) {
		
		eleUtil.doSendKeys(FirstName, firstName);
		eleUtil.doSendKeys(LastName, lastName);
		eleUtil.doSendKeys(Email, email);
		eleUtil.doSendKeys(TelePhone, telephone);
		eleUtil.doSendKeys(Pwd, Password);
		eleUtil.doSendKeys(ConfirmPwd, Password);
		
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(SubscribeYes);	
		} else {
			eleUtil.doClick(SubscribeNo);
		}
		
		eleUtil.doClick(CheckBox);
		eleUtil.doClick(ContnueBtn);
		
		String Successmsg = eleUtil.waitForElementVisible(SuccessMsg, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).getText();
		System.out.println(Successmsg);
		
		if(Successmsg.contains(Constants.REGISTRATION_SUCCESS_MSG)) {
			eleUtil.doClick(LogOutlink);
			eleUtil.doClick(Registerlink);
			return true;		
		}else {
			return false;
		}
	}


}
