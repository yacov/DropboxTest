package test;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.pages.LoginPage;
import test.pages.MainPage;
import test.util.DataProviders;
import test.util.LogLog4j;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Iakov Volf on 30.07.2015.
 */
public class LoginTest extends TestNgTestBase {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public LoginPage loginPage;
    public MainPage mainPage;

@BeforeClass
    public void setup() {

        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
    }


    //Positive login test. User data is stored in \resources\existingUser.data
    // (one row for test, email and password delimited by ';')
    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "existingUser")
    public void LoginSuccess(String email, String pass) {

        Log.info("Filling data and loggin in");
        try {
            loginPage
                    .openLoginPage()
                    .fillEmailField(email)
                    .fillPasswordField(pass)
                    .clickOnLogin();
            Log.info("Logged in");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Negative login test. User data is stored in \resources\Data\existingUserNegative.data
    // (one row for test, email and password delimited by ';')
    @Test(groups = {"negative"}, dataProviderClass = DataProviders.class, dataProvider = "existingUserNegative")
    public void LoginWithWrongUserdata (String email, String pass) {
        Log.info("Negative test - login with wrong credentials");
        try {
        loginPage
                .openLoginPage()
                .fillEmailField(email)
                .fillPasswordField(pass)
                .clickOnLogin();
            Log.info("Checking, that alert message 'Invalid Email or password' appears");
            assertTrue("Alert Message is not appeared", loginPage.alertMessageInvalidEmailOrPassword());
            Log.info("Assert passed - Alert message appears");
            Reporter.log("Alert message appears");
            Log.info("Checking, that user is still on login page");
            assertTrue("User is not remaining on Login page", loginPage.isOnLoginPage());
            Log.info("Assert passed - User remaining on login page");
            Reporter.log("User is still on login page");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //Positive login-logout test. User data is stored in \resources\Data\existingUser.data
    // (one row for test, email and password delimited by ';')
    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "existingUser")
    public void Logout (String email, String pass) {
        Log.info("Loggin in");
        try {
            loginPage
                    .openLoginPage()
                    .fillEmailField(email)
                    .fillPasswordField(pass)
                    .clickOnLogin();
            mainPage.waitUntilMainPageIsLoaded();
//            assertTrue("Main Page is not opened", mainPage.isOnMainPage());
            mainPage.logOut();
            loginPage.waitUntilLoginPageIsLoaded();
            assertTrue("User is still on Main Page", loginPage.isOnLoginPage());
            Reporter.log("Logout is successful");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
