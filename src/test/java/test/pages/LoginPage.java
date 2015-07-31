package test.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.util.LogLog4j;

/**
 * Created by Iakov Volf on 30.07.2015.
 */
public class LoginPage extends Page {

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    // log in elements
    @FindBy(xpath = "//*[contains(text(),'Log in')]")
    WebElement loginTitle;
    @FindBy(xpath = "//input[@name='login_email']")
    WebElement emailField;
    @FindBy(xpath = "//input[@name='login_password']")
    WebElement passwordField;
    @FindBy(xpath = "//*[@id='regular-login-forms']/form[1]/div[3]/button[@type='submit']")
    WebElement loginButton;


    //error messages
    @FindBy(xpath = "//*[@class='error-message'][contains(.,'Invalid email or password')]")
    WebElement invalidEmailOrPasswordAlert;


    public LoginPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "http://dropbox.com/login";
        PageFactory.initElements(driver, this);
    }

    public LoginPage openLoginPage() {
        Log.info("Opening login page");
        driver.get(PAGE_URL);
        return this;
    }

    public LoginPage waitUntilLoginPageIsLoaded() {
        waitUntilElementIsLoaded(loginButton, 45);
        return this;
    }


    public boolean isOnLoginPage() {
        waitUntilLoginPageIsLoaded();
        return exists(loginButton);
    }

    public LoginPage fillEmailField(String email) {
        Log.info("entering email: " + email + " ");
        setElementText(emailField, email);
        return this;
    }

    public LoginPage fillPasswordField(String password) {
        Log.info("entering password: " + password + " ");
        setElementText(passwordField, password);
        return this;
    }

    public LoginPage clickOnLogin() {
        clickElement(loginButton);
        return this;
    }



    public LoginPage login(String email, String password) {
        openLoginPage();
        waitUntilLoginPageIsLoaded();
        fillEmailField(email);
        fillPasswordField(password);
        clickOnLogin();
        return this;
    }


    public boolean alertMessageInvalidEmailOrPassword() {
        return exists(invalidEmailOrPasswordAlert);
    }


}
