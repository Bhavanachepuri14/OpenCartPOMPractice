package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class CommonsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	
	private By Search = By.name("search");
	private By Searchbtn = By.cssSelector("div#search button");
	private By AllFooterLink = By.xpath("//div[@class = 'col-sm-3']/ul[@class='list-unstyled']/li");
	
	public CommonsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public SearchResultsPage doSearch(String ProductName) {
		WebElement searchEle = eleUtil.waitForElementVisible(Search, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT);
		    searchEle.clear();
		searchEle.sendKeys(ProductName);
		eleUtil.doClick(Searchbtn);
		return new SearchResultsPage(driver);
		
	}
	public List<String> allFooterLinkText() {
		List<String> Footerlinklist = eleUtil.getElementsTextList(AllFooterLink);
		System.out.println(Footerlinklist);
		return Footerlinklist;
	}
	
	
}
