package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.utils.DescriptionConstants;

public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetup() {
		cmmnpage = new CommonsPage(driver);
		ProductInfoPage productInfoPage = new ProductInfoPage(driver);
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"MacBook","MacBook Pro", 4},
			{"MacBook", "MacBook Air", 4},
			{"Samsung", "Samsung SyncMaster 941BW", 1}
		};
	}
	
	@Test(dataProvider="getProductData")
	public void productImagesCountTest(String ProductName, String MainProductName, int ImagesCount ) {
		
		searchResultsPage = cmmnpage.doSearch(ProductName);		
		productInfoPage =searchResultsPage.selectProductName(MainProductName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), ImagesCount);
	}
	
	@Test
	public void productDescriptionTest() {
		searchResultsPage = cmmnpage.doSearch("MacBook");		
		productInfoPage =searchResultsPage.selectProductName("MacBook Air");
		String prodDesc = productInfoPage.getProductDescription();
		
		softAssert.assertTrue(prodDesc!=null);
		softAssert.assertFalse(prodDesc.isEmpty());
		softAssert.assertTrue(prodDesc.contains("MacBook Air"));
		softAssert.assertTrue(prodDesc.contains(DescriptionConstants.MACBOOK_AIR_DESCRIPTION));
		softAssert.assertAll();
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage = cmmnpage.doSearch("MacBook");		
		productInfoPage =searchResultsPage.selectProductName("MacBook Air");
		Map<String, String> productMetaMap = productInfoPage.getProductMetaData();
		productMetaMap.forEach((k,v) -> System.out.println(k + " : " +v));
		
		softAssert.assertEquals(productMetaMap.get("Brand"), "Apple");
		softAssert.assertEquals(productMetaMap.get("Reward Points"), "700");
		softAssert.assertAll();
		
		/*name : MacBook Air
		Brand : Apple
		Product Code : Product 17
		Reward Points : 700
		Availability : In Stock
		price : $1,202.00
		extaxprice : Ex Tax: $1,000.00*/
	}

}
