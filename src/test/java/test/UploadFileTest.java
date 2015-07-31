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
public class UploadFileTest extends TestNgTestBase {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public LoginPage loginPage;
    public MainPage mainPage;

    @BeforeClass
    public void initPages (){
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
    }



    //Positive test for file uploading. User data is stored in \resources\filePath.data
    // (one row for test, path to file and filename are delimited by ';')
    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "filePath")
    public void UploadFile(String path, String fileName) {

        try {
            loginPage.openLoginPage()
                    .waitUntilLoginPageIsLoaded()
                    .login("jakoff+11@gmail.com", "Piterpan1234.com");
            mainPage.waitUntilMainPageIsLoaded();
            mainPage.uploadNewFile(path, fileName);
            assertTrue("File name is not found", mainPage.fileIsCreated(fileName));
            Reporter.log("File "+fileName+" is uploaded");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
