package test.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.util.LogLog4j;

import java.io.IOException;

/**
 * Created by Iakov Volf on 30.07.2015.
 */
public class LoginPage extends Page {

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    // log in elements
    @FindBy(xpath = "//*[contains(text(),'Log in')]")
    WebElement loginTitle;
    @FindBy(name = "email")
    WebElement emailField;
    @FindBy(name = "password")
    WebElement passwordField;
    @FindBy(xpath = "//*[@id='regular-login-forms']/form[1]/div[3]/button[@type='submit']")
    WebElement loginButton;


    //error messages
    @FindBy(xpath = "//*[@class='error-message'][contains(.,'Invalid email or password')]")
    WebElement invalidEmailOrPasswordAlert;


    public LoginPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = baseUrl + "/login";
        PageFactory.initElements(driver, this);
    }

    public LoginPage openLoginPage() {
        Log.info("Opening login page");
        driver.get("http://www.dropbox.com");
        return this;
    }

    public LoginPage waitUntilLoginPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(loginTitle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }return this;
    }


    public boolean isOnLoginPage() {
        waitUntilLoginPageIsLoaded();
        return exists(loginTitle);
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
