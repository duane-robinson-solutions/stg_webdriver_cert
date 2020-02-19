package challenge3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class challenge3 {


    public WebDriver driver;
    public int itemCount;
    public int rowNumber;
    public String textOfCar;


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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


    }

    @AfterClass(alwaysRun = true)
    public void stopClass() {
        driver.quit();

    }

    @BeforeMethod()
    public void beforeMethod() throws Exception {
    }

    @AfterMethod()
    public void afterMethod() {
    }

    @Test(description = "Launches Browser to copart.com")
    public void goToCopart() throws Exception {
        driver.get("https://www.copart.com/");
        driver.manage().window().maximize();
    }

    @Test(description = "Verifies copart.com title")

    public void verifyCoPartTitle() throws Exception {
        //Assert that page loaded as expected
        Assert.assertEquals(driver.getTitle(), "Auto Auction - Copart USA - Salvage Cars for Sale in Online Car Auctions");

    }

    @Test(description = "copart.com - Lists popular car models from copart.com")
    public void listMostPopularItems() throws Exception {
        itemCount = 0;

        Thread.sleep(2000);

        //Loop to obtain the car and href information to print
        for (int colnum = 1; colnum < 5; colnum++) {

        List<WebElement> favoritesCols = driver.findElements(By.xpath("//div[@id='tabTrending']//div//div[2]//div[" + colnum + "]//a[1]"));
            for (WebElement favoritesCol : favoritesCols) {
               textOfCar = favoritesCol.getText();
               String hrefText = favoritesCol.getAttribute("href");
              System.out.println(textOfCar + " - " + hrefText );
              ++itemCount;
            }

        }
        //I know that the script would fail if it couldn't find an element.  I am going to assert a total of 20
        //in case the developers decide to change something
        Assert.assertTrue(itemCount == 20);
        System.out.println("There were " + itemCount + " autos listed as popular car models");

    }





}
