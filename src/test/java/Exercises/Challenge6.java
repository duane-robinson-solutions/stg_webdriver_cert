package Exercises;

import base.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.copart;
import utils.GeneralUtilities;

import java.net.MalformedURLException;

//NOTE:  This challenge involves returning an exception
public class Challenge6 {

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
    public void searchgopart() {
        copart.searchForText("nissan");

    }

    @Test(priority = 2)
    public void changeNumOfRecsToView() {
        copart.set100RecsPerPage();

    }

    @Test(priority = 3)
    public void expandModelstest() {
        copart.expandModels();

    }


    @Test(priority = 4)
    public void SearchforSpecificModel() {
        try {
            copart.searchForModelText("skyline");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(priority = 5)
    public void verifyModelExist() throws Exception {
    //NOTE: This test MAY return an exception..the catch captures the screen where the exception occurred
        copart.verifysearchModel("skyline");

    }


}

