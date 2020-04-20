package Exercises;

import base.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.copart;
import utils.GeneralUtilities;

import java.net.MalformedURLException;


public class Challenge5 {

    public GeneralUtilities generalUtilities;
    public copart copart;
    public TestBase testbase;
    public String numOfFibSeries;





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


    @Test(priority = 0)
    public void goToCopart() throws Exception {
        copart.loadGopartSite();

    }


    @Test(priority = 1)
    public void searchgopart()  {
        copart.searchForText("porsche");

    }

    @Test(priority = 2)
    public void changeNumOfRecsToView()  {
      copart.set100RecsPerPage();

    }
    @Test(priority = 3)
    public void showSearchedItems()  {
        try {
            copart.listMostPopularItems();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}

