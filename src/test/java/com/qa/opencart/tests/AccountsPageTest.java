package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accntSetup() {
		accpage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		//accpage = new AccountsPage(driver);
	}
	
	
	@Test(priority = 1)
	public void accPageTitleTest() {
		String accnttitle = accpage.getAccntPageTitle();
		Assert.assertEquals(accnttitle, Constants.HOMEPAGE_TITLE);
	}
	
	@Test(priority = 2, enabled = false)
	public void accHomePagelogoTest() {
		Assert.assertEquals(accpage.homePageLogo(), Constants.HOMEPAGE_LOGO);
	}
	
	@Test(priority = 3)
	public void accntSectionHeaderTest() {
		List<String> actualAccSectionList = accpage.getaccntSectionHeaderList();
		System.out.println("Actual accout Page Headers: "+actualAccSectionList);
		Assert.assertEquals(actualAccSectionList, Constants.ACCOUNTS_PAGE_HEADER_LIST);
	}
	
	@Test(priority = 4)
	public void accPageUrlTest() {
		Assert.assertTrue(accpage.getAccntPageUrl().contains(Constants.HOME_PAGE_URL_FRACTION));
	}
	
	@Test(priority = 5)
	public void editAccntLinkTest() {
	Assert.assertTrue(accpage.isEditAccntLinkExists());
	}
	
	@Test(priority = 6)
	public void searchFieldTest() {
		Assert.assertTrue(accpage.isSearchFieldExists());
	}
	
	@Test(priority = 9, enabled= false)
	public void isUserLoggedOutTest() {
		loginpage = accpage.clickOnLogOut();
		Assert.assertEquals(loginpage.getLogOutMessage(), Constants.USER_LOGOUT_MESSAGE);
		
	}
	
	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] {
			{"MacBook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"}
		};
	}
	
	@Test(priority = 7, dataProvider ="getProductName")
	public void searchTest(String productName) {
		cmmnpage = new CommonsPage(driver);
		searchResultsPage = cmmnpage.doSearch(productName);
		String ResultsPageHeaderText = searchResultsPage.getResultsPageHeader();
		Assert.assertTrue(ResultsPageHeaderText.contains(productName));
		
	}
	
	@DataProvider
	public Object[][] getmainProductName() {
		return new Object[][] {
			{"MacBook","MacBook Pro"},
			{"MacBook", "MacBook Air"},
			{"Samsung", "Samsung SyncMaster 941BW"}
		};
	}
	
	@Test(priority = 8, dataProvider = "getmainProductName")
	public void productInfoTest(String productName, String mainProductName) {
		cmmnpage = new CommonsPage(driver);
		searchResultsPage = cmmnpage.doSearch(productName);		
		productInfoPage =searchResultsPage.selectProductName(mainProductName);
		String mainproductnameval = productInfoPage.getmainProductName();
		Assert.assertEquals(mainproductnameval, mainProductName);
	}
}
