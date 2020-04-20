package Exercises;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class challenge1 {

    public WebDriver driver;


    @BeforeSuite
    public void startSuite() throws Exception {
    }

    @AfterSuite
    public void stopSuite() throws Exception {
        System.out.println("All done!!!");
    }

    @BeforeClass
    public void startClass() throws Exception {

        System.setProperty("webdriver.chrome.driver", "./bin/chromedriver.exe");
        driver = new ChromeDriver();


    }

    @AfterClass
    public void stopClass() {
        driver.quit();

    }

    @BeforeMethod()
    public void beforeMethod() throws Exception {
    }

    @AfterMethod()
    public void afterMethod() {
    }

    @Test()
    public void goToGoogle() throws Exception {
        driver.get("https://www.google.com");
    }

    @Test()
    public void verifyGoogleTitle() throws Exception {


        Assert.assertEquals(driver.getTitle(), "Google");

    }


}
