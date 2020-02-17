package challenge2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class challenge2 {


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

    @Test(description = "copart.com - Verifies that Porche is listed when exotic searched")

    public void verifyPorcheListed() throws Exception {
        itemCount = 0; rowNumber = 0;

        WebElement srchinput = driver.findElement(By.id("input-search"));

        //Assert input box exists
        WebElement srchClick = driver.findElement(By.xpath("//button[@class='btn btn-lightblue marginleft15']"));

        srchinput.sendKeys("exotics");
        srchClick.click();


        //Set the list to display 100
        WebElement display100Mnu = driver.findElement(By.xpath("//div[contains(@class,'inner-wrap')]//div[contains(@class,'top')]//select[1]//option[3]"));
        display100Mnu.click();


        Thread.sleep(2000);
        WebElement numOfResultsTxt = driver.findElement(By.xpath("//h1[@id='searchResultsHeader']//span[2]"));
        String numOfResultsTxtStr = numOfResultsTxt.getText();

        String totalSearchResults = numOfResultsTxtStr.replaceAll("\\D+", "");
        int totalSearchRows = Integer.parseInt(totalSearchResults);

        //div[@id='serverSideDataTable_info']
        List<WebElement> colFiveDatas = driver.findElements(By.xpath("//table//tbody//td[5]//span[1]"));

        for (WebElement colFiveData : colFiveDatas) {
            ++rowNumber;
             textOfCar = colFiveData.getText();

            if (textOfCar.contains("PORSCHE")) {
                System.out.println(textOfCar + " Is listed on row " + rowNumber);
                ++itemCount;

                boolean clickNext = rowNumber % 100 == 0;
                if (clickNext) {
                    clickNextLink();
                }
                textOfCar = "";

            }


        }
        Assert.assertTrue(itemCount > 0);
        System.out.println("PORSCHE" + " Is listed a total number of " + itemCount + " times.");
    }

    public void clickNextLink(){

        WebElement nextLink = driver.findElement(By.xpath("//li[@id='serverSideDataTable_next']//a[contains(text(),'Next')]"));
        nextLink.click();

    }



}