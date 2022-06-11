package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	private WebDriver driver;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	public void doSendKeys(By Locator, String Value) {
		getElement(Locator).sendKeys(Value);
	}
	
	public By getBy(String LocatorType, String Locatorvalue) {
		By Locator = null;
		
		switch (LocatorType.toLowerCase()) {
		case "id":
			Locator = By.id(Locatorvalue);
			break;
		case "name":
			Locator = By.name(Locatorvalue);
			break;
        case "classname":
			Locator = By.className(Locatorvalue);
			break;
		case "xpath":
			Locator = By.xpath(Locatorvalue);
			break;
		case "cssSelector":
			Locator = By.cssSelector(Locatorvalue);
			break;
		case "linkText":
			Locator = By.linkText(Locatorvalue);
			break;
		case "partiallinkText":
			Locator = By.partialLinkText(Locatorvalue);
			break;
		case "tagname":
			Locator = By.tagName(Locatorvalue);
			break;
		default:
			System.out.println("please pass the right locator....");
			break;
		}
		return Locator;
	}
	
	public void doClick(By Locator) {
		getElement(Locator).click();
	}
	
	public String doGetElementText(By Locator) {
		return getElement(Locator).getText();
	}
	public String doGetAttributeValue(By Locator, String AttributeName) {
		return getElement(Locator).getAttribute(AttributeName);
	}
	
	public List<WebElement> getElements(By Locator) {
		return driver.findElements(Locator);
	}
	
	
	public boolean doIsDisplayed(By Locator) {
		return getElement(Locator).isDisplayed();
	}
	
	public WebElement getElement(By Locator) {
		return driver.findElement(Locator);
	}
	
	//======================Utilities for selection and print from a list================
	public void ClickonElementFromSection(By Locator, String value) {
		List<WebElement>elelist = getElements(Locator);
		System.out.println(elelist.size());
		for(WebElement e: elelist) {
			String text = e.getText();
			System.out.println("------"+text+"--------");
			if(text.equals(value)) {
				e.click();
				break;
			}
		}
		
	}
	public void printAllElementsText(By Locator) {
		List<WebElement>elelist = getElements(Locator);
		 for(WebElement e: elelist) {
			String text = e.getText();
			System.out.println("------"+text+"--------");
	}
	}
	
	public int getElementsListCount(By Locator) {
		return getElements(Locator).size();
	}
	
	
	
	public List<String> getElementsTextList(By Locator) {
	List<String>eleTextList = new ArrayList<String>();
		List<WebElement>elelist = getElements(Locator);
		 for(WebElement e: elelist) {
			String text = e.getText();
			eleTextList.add(text);
	}
		 return eleTextList;
}

	//=============utilities for dropdown with and without select class============
	
	public void doselectdropdownByIndex(By Locator, int indexvalue) {
		Select select = new Select(getElement(Locator));
		select.selectByIndex(indexvalue);
	}
	
    public void doselectdropdownByVisibleText(By Locator, String visibleText) {
    	Select select = new Select(getElement(Locator));
    	select.selectByVisibleText(visibleText);
	}
	
	public void doselectdropdownByValue(By Locator, String value) {
		Select select = new Select(getElement(Locator));
		select.selectByValue(value);
	}
	
	public void selectValueFromSelectDropDown(By Locator, String value) {
		Select select = new Select(getElement(Locator));
		List<WebElement> dropdownoptions = select.getOptions();
		//System.out.println(dropdownoptions.size());
		for(WebElement e:dropdownoptions) {
			String text = e.getText();
			System.out.println(text);
			if(text.contains(value)) {
				e.click();
			     break;
			}
		}
	}
	
	public void printSelectDropDownValues(By Locator) {
		Select select = new Select(getElement(Locator));
		List<WebElement> dropdownlist = select.getOptions();
		System.out.println(dropdownlist.size());
		for(WebElement e:dropdownlist) {
			String text = e.getText();
			System.out.println(text);
	}
	
	}
	public int getSelectDropDownValueCount(By Locator) {
		return getElements(Locator).size();
	}
	
	public List<String> getSelectDropdownValueList(By Locator) {
		List<String>dropdownvaluelist = new ArrayList<String>();
		Select select = new Select(getElement(Locator));
		List<WebElement> dropdownlist = select.getOptions();
		for(WebElement e:dropdownlist) {
			String text = e.getText();
			dropdownvaluelist.add(text);	
	}
		return dropdownvaluelist;
	}
	public void selectdropdownvthoutselectclass(By Locator, String value) {
		List<WebElement> dropcategorylist = getElements(Locator);
		//System.out.println(dropcategorylist.size());
		for(WebElement e: dropcategorylist) {
			String categoryText = e.getText();
			System.out.println(categoryText);
			if(categoryText.contains(value)) {
				e.click();
				break;
			}
		}
	}
	
	//================Utility for Browser WindowHandler================
	
	public void doGetWindowHandles() {
		Set<String> handles = driver.getWindowHandles();
        Iterator<String> it= handles.iterator();
        String parentwindowid = it.next();
        System.out.println(parentwindowid);
        String childwindowid = it.next();
        System.out.println(childwindowid);
        driver.switchTo().window(childwindowid);
        String childtitle = driver.getTitle();
        System.out.println(childtitle);
        driver.close();
        driver.switchTo().window(parentwindowid);
        String parenttitle = driver.getTitle();
        System.out.println(parenttitle);
	}
	
	//===============Utilities for Actions class====================
	
	public void doActionsSendKeys(By Locator, String value) {
		 Actions act = new Actions(driver);
		 act.sendKeys(getElement(Locator), value).perform();	
	}
	
	public void doActionsClick(By Locator) {
		Actions act = new Actions(driver);
		act.click(getElement(Locator)).perform();
	}
	
	//============Xpath WebTable preceding and following utilities============
	
	public void selectcheckboxByXpath(String name) {
		By selectcheckboxxpath = By.xpath("//a[text()='"+name+"']/parent::td/preceding-sibling::td/input");
		getElement(selectcheckboxxpath).click();
	}
	
	public List<String> followingXpathgetdata(String name) {
		System.out.println("user name is: "+name);
		List<String> colElelist = new ArrayList<String>();
	List<WebElement> followingselectxpath = driver.findElements(By.xpath("//a[text()='"+name+"']/parent::td/following-sibling::td"));
	System.out.println(followingselectxpath.size());
	for(WebElement e: followingselectxpath) {
		String text = e.getText();
		colElelist.add(text);
		//System.out.println(text);
	}
	return colElelist;	
	}
	
	// ******************************wait utils******************************//

		/**
		 * An expectation for checking that the title contains a case-sensitive
		 * substring
		 * 
		 * @param titleFraction
		 * @param timeOut
		 * @return
		 */
		public String waitForTitleContains(String titleFraction, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
				return driver.getTitle();
			}
			return null;
		}
		
		/**
		 * An expectation for checking the title of a page.
		 * 
		 * @param titleValue
		 * @param timeOut
		 * @return
		 */
		public String waitForTitleIs(String titleValue, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if (wait.until(ExpectedConditions.titleIs(titleValue))) {
				return driver.getTitle();
			}
			return null;
		}
		
		public String waitForUrlContains(String urlFraction, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
				return driver.getCurrentUrl();
			}
			return null;
		}
		
		public String waitForUrlIs(String urlValue, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if (wait.until(ExpectedConditions.urlToBe(urlValue))) {
				return driver.getCurrentUrl();
			}
			return null;
		}
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page.
		 * This does not necessarily mean that the element is visible.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementPresent(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page
		 * and visible. Visibility means that the element is not only displayed but also
		 * has a height and width that is greater than 0.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		
		
		public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}

	
}	
	
