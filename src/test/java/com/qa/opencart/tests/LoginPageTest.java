package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest {
	
	@Test(priority =1)
	public void loginPageTitleTest() {
		String actualTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, Constants.LOGINPAGE_TITLE);
	}
	
	@Test(priority =2)
	public void loginPageUrlTest() {
		String Url = loginpage.getLoginPageUrl();
		Assert.assertTrue(Url.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Test(priority =3)
	public void forgotPwdTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExists());
	}
	
	@Test(priority =4)
	public void registerLinkTest() {
		Assert.assertTrue(loginpage.isRegisterlinkExists());
	}
	
	@Test(priority =5)
	public void searchFieldTest() {
		Assert.assertTrue(loginpage.isSearchFieldPresent());
	}
	
	@Test(priority =6)
	public void newsLetterLinkTest() {
		Assert.assertTrue(loginpage.isNewsLetterLinkExists());
	}
	
	@Test(priority =7)
	public void doLoginTest() {
		AccountsPage accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		String accPageTitle = accPage.getAccntPageTitle();
		Assert.assertEquals(accPageTitle, Constants.HOMEPAGE_TITLE);
		
	}

}
