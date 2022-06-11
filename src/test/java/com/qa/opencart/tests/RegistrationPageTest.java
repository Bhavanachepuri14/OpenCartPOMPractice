package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void regSetup() {
		regPage = loginpage.navigateToRegistrationPage();
	}
	
	@DataProvider
	public Object[][] getUserRegisterTestData() {
	Object regData[][]=	ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	return regData;
	
	}
	
	@Test (dataProvider ="getUserRegisterTestData")
	public void userRegisterTest(String firstname, String lastname, String email, 
			String telephone, String password, String subscribe) {
		Assert.assertTrue(regPage.userRegister(firstname, lastname, email, telephone, password, subscribe));
	}

}
