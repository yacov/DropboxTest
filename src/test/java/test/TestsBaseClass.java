package test;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.selenium.factory.WebDriverFactory;
import ru.stqa.selenium.factory.WebDriverFactoryMode;
import test.util.PropertyLoader;

import java.io.IOException;

/**
 * Base class for TestNG-based test classes
 */
public class TestsBaseClass {

  protected static String gridHubUrl;
  protected static String baseUrl;
  protected static Capabilities capabilities;

  protected WebDriver driver;

  @BeforeSuite
  public void initTestSuite() throws IOException {
    baseUrl = PropertyLoader.loadProperty("site.url");

    capabilities = PropertyLoader.loadCapabilities();
    WebDriverFactory.setMode(WebDriverFactoryMode.THREADLOCAL_SINGLETON);
  }

  @BeforeMethod
  public void initWebDriver() {
    driver = WebDriverFactory.getDriver(capabilities);
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    WebDriverFactory.dismissAll();
  }
}
