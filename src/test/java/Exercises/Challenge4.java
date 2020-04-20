package Exercises;

import base.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.GeneralUtilities;
import pages.copart;


import java.io.Console;
import java.net.MalformedURLException;



public class Challenge4 {

    public GeneralUtilities generalUtilities;
    public copart copart;
    public TestBase testbase;
    public String numOfFibSeries;
    public int fibOrderNum;
    Console console = System.console();





    @BeforeSuite
    public void startSuite() throws Exception {
    }

    @AfterSuite
    public void stopSuite() throws Exception {
        System.out.println("All done!!!");
    }

  @BeforeClass
         public void initalizeClass() throws MalformedURLException {
            TestBase base = new TestBase();

            WebDriver driver = base.getDriver("chrome");
              copart = new copart(driver);
              generalUtilities = new GeneralUtilities();
              testbase = new TestBase();



    }

    @AfterClass(alwaysRun = true)
    public void classTearDown() {
        copart.closeGopartSite();

    }

    @BeforeMethod()
    public void beforeMethod() throws Exception {
    }

    @AfterMethod()
    public void afterMethod() {
    }


    @Test(description = "Launches Browser to copart.com")
    public void goToCopart() throws Exception {
        copart.loadGopartSite();

    }


    @Test
    public void testFibThird()  {

        generalUtilities.printFibonacci(7);


    }





}

