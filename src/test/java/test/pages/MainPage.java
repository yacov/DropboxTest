package test.pages;

/**
 * Created by Iakov Volf on 30/07/2015.
 */

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import test.util.LogLog4j;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MainPage extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    //log out elements
    @FindBy(xpath = "//*[@id='header-account-menu']/a")
    WebElement upperMenu;

    @FindBy(xpath = "//*[@id='header-account-menu']/div/ul/li[4]/a")
    WebElement logOutButton;

    //new folder elements
    @FindBy(id = "new_folder_button")
    WebElement createNewFolderButton;

    @FindBy(xpath = "//*[@id='null']/input")
    WebElement newFolderNameField;


    //file upload elements
    @FindBy(id = "upload_button")
    WebElement uploadFileButton;

    @FindBy(id = "choose-button")
    WebElement chooseFileButton;

    @FindBy(id = "done-button")
    WebElement uploadIsDoneButton;

    @FindBy(xpath = "//ol[@id='browse-files']/li[1]//*[@class='filename-link']")
    WebElement newFolderName;


    //another elements
    @FindBy(id = "browse-location")
    WebElement dropboxName;

//page constructor
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "http://dropbox.com/home";
        PropertyConfigurator.configure("log4j.properties");
    }

//open page method
    public MainPage openMainPage() {
        driver.get(PAGE_URL);
        return this;
    }



    // Waits until drpboxname element appears on the screen
    public MainPage waitUntilMainPageIsLoaded() {
            Log.info("Waiting for Main page to load");
            waitUntilIsLoaded(upperMenu);
        return this;
    }

    //Log out method
    public MainPage logOut() {
        Log.info("Logging out");
        clickElement(upperMenu);
        clickElement(logOutButton);
        return this;
    }

    //file upload methods
    public MainPage clickOnUploadFileButton() {
        Log.info("Clicking on 'Upload File' button");
        clickElement(uploadFileButton);
        return this;
    }

    public MainPage clickOnChooseFileButton() {
        Log.info("Clicking on 'Choose File' button");
        clickElement(chooseFileButton);
        return this;
    }
    public MainPage clickOnDoneButton() {
        Log.info("Clicking on 'Choose File' button");
        clickElement(uploadIsDoneButton);
        return this;
    }

    public void uploadNewFile(String path, String filename) throws AWTException, IOException, InterruptedException {
        Log.info("Starting upload new file");
        clickOnUploadFileButton();
        clickOnChooseFileButton();
        String fullPath = path+filename;
        Log.info("Sending Path to file");
        StringSelection ss = new StringSelection(fullPath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Log.info("waiting for file uploading");
        waitUntilIsLoaded(uploadIsDoneButton);
        Log.info("clicking on Done button");
        clickOnDoneButton();

    }

    //Folder creation methods
    public MainPage clickOnCreateFolderButton() {
        Log.info("Clicking on 'Create new Folder' button");
        clickElement(createNewFolderButton);
        return this;
    }

    public MainPage enterFolderName(String name) throws AWTException, InterruptedException {
        Log.info("Entering new folder name "+name);
        setElementText(newFolderNameField, name);
        //Press Enter after entering name
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        //Wait until folder created
        Thread.sleep(2000);
        return this;
    }

    public MainPage checkifNewFolderNameisCorrect (String name) {
        Log.info("Getting new folder name from main page");
        String nameOnPage = newFolderName.getText();
        Log.info("New folder name from main page is "+nameOnPage);
        Log.info("Checking, that new folder name is correct");
        Assert.assertEquals(nameOnPage,name,"New folder name isn't correct");
        return this;
    }
//methods, checking that elements exists

    public boolean isOnMainPage() {
        return exists(chooseFileButton);
    }


    public boolean isLoggedIn(){
        return exists(chooseFileButton);
    }

    public boolean fileIsCreated (String filename) {
       WebElement element =  driver.findElement(By.xpath("//*[contains(.,'"+filename+"')]"));
        return exists(element);
    }


}