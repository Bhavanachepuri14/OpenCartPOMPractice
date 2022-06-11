package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage{
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By mainProductName = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productDescription = By.cssSelector("div#tab-description");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPricingData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getmainProductName() {
		return eleUtil.doGetElementText(mainProductName);
	}
	
	public int getProductImagesCount() {
		return eleUtil.waitForElementsVisible(productImages, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).size();
	}
	
	public String getProductDescription() {
		return eleUtil.doGetElementText(productDescription);
	}
	
	public Map<String, String> getProductMetaData() {
		
		//Map<String, String> productInfoMap = new HashMap<String,String>();
		Map<String, String> productInfoMap = new LinkedHashMap<String,String>();
		productInfoMap.put("name", getmainProductName());
		
		List <WebElement> metaList = eleUtil.getElements(productMetaData);
		for(WebElement e: metaList) {
			String MetaData = e.getText();
			
			String MetaKey = MetaData.split(":")[0].trim();
			String MetaVal = MetaData.split(":")[1].trim();
			
			productInfoMap.put(MetaKey, MetaVal);
		}
		
		//For price data
		List<WebElement> pricingList = eleUtil.getElements(productPricingData);
		String price = pricingList.get(0).getText().trim();
		String exTaxPrice = pricingList.get(1).getText().trim();
		
		productInfoMap.put("price", price);
		productInfoMap.put("extaxprice", exTaxPrice);
		
		return productInfoMap;
	}

}
