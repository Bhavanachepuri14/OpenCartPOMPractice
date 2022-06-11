package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By HomePageLogo = By.cssSelector("div#logo h1 a");
	private By EditAccntLink = By.linkText("Edit Account");
	private By AccountSectionHeader = By.cssSelector("div#content h2");
	private By LogoutLink = By.linkText("Logout");
	private By search = By.name("search");

	
	public String getAccntPageTitle() {
		String title = eleUtil.waitForTitleIs(Constants.HOMEPAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		System.out.println("Home/Accounts Page title is: "+title);
		return title;
	}
	
	public String getAccntPageUrl() {
		String url = eleUtil.waitForUrlContains(Constants.HOME_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
		System.out.println("Home/Accounts Page url is: "+url);
		return url;
	}
	
	public boolean isEditAccntLinkExists() {
		return eleUtil.waitForElementVisible(EditAccntLink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
		
	}
	
	public String homePageLogo() {
		return eleUtil.waitForElementVisible(HomePageLogo, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).getText();
	}
	
	public List<String> getaccntSectionHeaderList() {
		List <WebElement> accHeaderList=eleUtil.waitForElementsVisible(AccountSectionHeader, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT);
		List<String> Headerslist = eleUtil.getElementsTextList(AccountSectionHeader);
		System.out.println(Headerslist);
		return Headerslist;
		
		}
	
	public boolean isLogOutlinkExists() {
		return eleUtil.waitForElementVisible(LogoutLink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}
	
	public boolean isSearchFieldExists() {
		return eleUtil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();
	}
	
	public LoginPage clickOnLogOut() {
		if(isLogOutlinkExists()) {
			eleUtil.doClick(LogoutLink);
			return new LoginPage(driver);	
		}
		return null;
	}
	
	
	
	
	
}
