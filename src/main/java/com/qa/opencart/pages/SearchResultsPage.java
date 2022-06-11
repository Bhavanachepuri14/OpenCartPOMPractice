package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By ResultsPageHeader = By.cssSelector("div#content h1");
	
	public String getResultsPageHeader() {
		return eleUtil.doGetElementText(ResultsPageHeader);
	}
	
	public ProductInfoPage selectProductName(String mainProductName) {
		WebElement mainproductEle = eleUtil.waitForElementVisible(By.linkText(mainProductName), Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT);
		mainproductEle.click();
		return new ProductInfoPage(driver);
	}
}
