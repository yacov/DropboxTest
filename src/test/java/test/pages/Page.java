package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {
  public String baseUrl;
  public String PAGE_URL;
  public String PAGE_TITLE;
  protected WebDriver driver;
  protected StringBuffer verificationErrors = new StringBuffer();
  HashMap<String, String> allElementsMap;
  /*
   * Constructor injecting the WebDriver interface
   *
   * @param webDriver
   */

  public Page(WebDriver driver) {
    this.driver=driver;
      this.allElementsMap = new HashMap<String, String>();
    baseUrl = "http://dropbox.com";
  }

  private WebElement getWebElement(String name) {
    return driver.findElement(By.xpath(allElementsMap.get(name)));
  }

  public Page clickWebElement(String name) {
    getWebElement(name).click();
    return this;
  }

  public Page setElementText(String name, String text) {
    WebElement element = getWebElement(name);
    element.clear();
    element.sendKeys(text);
    //  Assert.assertEquals(element.getAttribute("value"), text);
    return this;
  }

  public void goBackBrowserButton() {
    driver.navigate().back();
  }

  public void goForwardBrowserButton() {
    driver.navigate().forward();
  }

  public void reloadPage() {
    driver.navigate().refresh();
  }

  public void waitUntilIsLoaded(String name) {
    WebElement element = getWebElement(name);

    new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(element));
  }

  public boolean verifyTextBoolean(String name, String text) {
    WebElement element = getWebElement(name);
    return text.equals(element.getText());
  }



  public String getTitle() {
    return driver.getTitle();
  }

  public String getPageUrl() {
    return PAGE_URL;
  }

  public String getPageTitle() {
    return PAGE_TITLE;
  }

  public void loadPage() {
    driver.get(getPageUrl());
//  Assert.assertEquals(getTitle(), getPageTitle());
  }

  public void setElementText(WebElement element, String text) {
    element.click();
    element.clear();
    element.sendKeys(text);
    // Assert.assertEquals(element.getAttribute("value"), text);
  }


  public void clickElement(WebElement element) {
    element.click();
  }

  public void waitUntilIsLoaded(WebElement element) {
    new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element));
  }



  public String selectValueInDropdown(WebElement dropdown, String value) {
    Select select = new Select(dropdown);
    select.selectByValue(value);
    WebElement option = select.getFirstSelectedOption(); // Chooses label that fits the value
    return option.getText();
  }

  public boolean verifyElementIsPresent(WebElement element) {
    try {
      element.getTagName();
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public void verifyText(WebElement element, String text) {
    try {
      Assert.assertEquals(text, element.getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  public boolean verifyTextBoolean(WebElement element, String text) {
    return text.equals(element.getText());
  }

  public boolean exists(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (org.openqa.selenium.NoSuchElementException ignored) {
      return false;
    }
  }

  public void waitUntilElementIsLoaded(WebElement element, int time) {
    new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
  }

  public void waitForElement(WebDriverWait wait, String element) {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
  }

  protected boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (org.openqa.selenium.NoSuchElementException e) {
      return false;
    }
  }


}
