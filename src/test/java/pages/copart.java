package pages;

import utils.CountingSort;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;


public class copart {
    public String searchString;
    private WebDriver driver;
    public int itemCount;
    public String modelOfCar;
    public String damageText;
    public List<String> modelList = new ArrayList<String>(101);
    public List<String> damageList = new ArrayList<String>(101);
    public CountingSort countingSort;


    public copart(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        countingSort = new CountingSort();



    }

    @FindBy(xpath = "//button[@class='btn btn-lightblue marginleft15']")
    public WebElement searchBtn;

    @FindBy(xpath = "//input[@id='input-search']")
    public WebElement carSearchtxt;

    @FindBy(xpath = "//div[contains(@class,'inner-wrap')]//div[contains(@class,'top')]//select[1]//option[3]")
    public WebElement numRecsPerPageDropDwn;


    public void loadGopartSite() {
    driver.get("https://www.copart.com/");


    }

    public void searchForText(String searchString) {
        carSearchtxt.sendKeys(searchString);
        searchBtn.click();

    }

    public void set100RecsPerPage() {
        numRecsPerPageDropDwn.click();


    }

    public void closeGopartSite() {
        driver.quit();


    }

    public void listMostPopularItems() throws Exception {
        itemCount = 0;

        Thread.sleep(2000);

        //Loop to obtain the car and href information to print
        for (int rownum = 1; rownum <= 100; rownum++) {

            List<WebElement> damageCols = driver.findElements(By.xpath("//table[@id='serverSideDataTable']//tr[" + rownum + "]//td[12]//span[1]"));
            for (WebElement damageCol : damageCols) {
                damageText = damageCol.getText();
                damageList.add(damageCol.getText());
                System.out.println("Damage Column Text" + " - " + damageText);
                ++itemCount;
            }

        }


        for (int rownum = 1; rownum <= 100; rownum++) {

            List<WebElement> modelsCols = driver.findElements(By.xpath("//table[@id='serverSideDataTable']//tr[" + rownum + "]//td[6]//span[1]"));
            for (WebElement modelsCol : modelsCols) {
                modelOfCar = modelsCol.getText();
                modelList.add(modelsCol.getText());
                System.out.println("Model of Car" + " - " + modelOfCar);
            }

        }

        ArrayList<String> sortedList = new ArrayList<>();
        for (String s : modelList) {
            sortedList.add(s);
            Collections.sort(sortedList);
        }

    }


}
