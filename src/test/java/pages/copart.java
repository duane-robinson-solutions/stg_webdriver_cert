package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CountingSort;
import utils.GeneralUtilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class copart {

    private WebDriver driver;
    public int itemCount;
    public int arrayPosition = 0;
    public int linkTextLen = 0;
    public int attributTextLen = 0;
    public GeneralUtilities generalUtilities;
    public List<String> listOfModels = new ArrayList<String>();
    @FindBy(xpath = "//a[contains(text(),'Model')]")
    public WebElement modelExpandBtn;
    @FindBy(xpath = "//div[@id='collapseinside4']//input[@placeholder='Search']")
    public WebElement searchModelTxt;
    private int rearEndCount = 0;
    public String modelOfCar;
    public String damageText;
    public String attributeText;
    public String linkText;
    private int frondEndCount = 0;
    public List<String> modelList = new ArrayList<String>(101);
    public List<String> damageList = new ArrayList<String>(101);
    public List<String> nameurls = new ArrayList<String>(101);


    public String[][] namesURL = new String[200][200];
    private int minorCount = 0;

    public CountingSort countingSort;
    private int underCarriageCount = 0;

    @FindBy(xpath = "//button[@class='btn btn-lightblue marginleft15']")
    public WebElement searchBtn;

    @FindBy(xpath = "//input[@id='input-search']")
    public WebElement carSearchtxt;

    @FindBy(xpath = "//div[contains(@class,'inner-wrap')]//div[contains(@class,'top')]//select[1]//option[3]")
    public WebElement numRecsPerPageDropDwn;
    private int miscCount = 0;

    public copart(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        countingSort = new CountingSort();
        generalUtilities = new GeneralUtilities();


    }

    public void loadGopartSite() {
        driver.get("https://www.copart.com/");


    }

    public void searchForText(String searchString) {
        carSearchtxt.sendKeys(searchString);
        searchBtn.click();

    }


    public void searchForModelText(String modelSearchString) {
        searchModelTxt.sendKeys(modelSearchString);


    }


    public void set100RecsPerPage() {
        numRecsPerPageDropDwn.click();


    }

    public void expandModels() {
        modelExpandBtn.click();


    }


    public void verifysearchModel(String modelSearchString) throws Exception {

        try {
            boolean isPresent = driver.findElements(By.id("lot_model_desc" + modelSearchString.toUpperCase())).size() > 0;
            if (isPresent == true){
                driver.findElement(By.id("lot_model_desc" + modelSearchString.toUpperCase())).click();
                System.out.println("Found checkbox for model " + modelSearchString);
            }else{
                System.out.println("CHECKBOX NOT FOUND FOR MODEL " + modelSearchString);
            }


        } catch (Exception e) {
            generalUtilities.takeSnapShot(driver, "c://tmp//test.png");
            ;
            e.printStackTrace();
        }

    }


    public void closeGopartSite() {
        driver.quit();


    }

    public void listPorscheModels() throws Exception {
        itemCount = 0;

        Thread.sleep(2000);

        for (int rownum = 1; rownum <= 100; rownum++) {
            List<WebElement> modelsCols = driver.findElements(By.xpath("//table[@id='serverSideDataTable']//tr[" + rownum + "]//td[6]//span[1]"));
            for (WebElement modelsCol : modelsCols) {
                modelOfCar = modelsCol.getText();
                modelList.add(modelsCol.getText());
            }


        }
        List<String> modelListNoDups = modelList.stream().distinct().collect(Collectors.toList());
        Collections.sort(modelListNoDups);
        System.out.println("The following is a Distinct list of Porsche Models listed Alphetically ");
        for (String m : modelListNoDups) {
            System.out.println(" - Porsche: " + m);

        }


    }


    public void checkForSpecialModel(String modeltoSearch) throws Exception {
        Thread.sleep(2000);

    }


    public void listDamages() throws Exception {

        //Loop to obtain the car and href information to print
        for (int rownum = 1; rownum <= 100; rownum++) {

            List<WebElement> damageCols = driver.findElements(By.xpath("//table[@id='serverSideDataTable']//tr[" + rownum + "]//td[12]//span[1]"));
            for (WebElement damageCol : damageCols) {
                damageText = damageCol.getText();
                damageList.add(damageCol.getText());

                switch (damageText) {
                    case "REAR END":
                        rearEndCount++;
                        break;
                    case "FRONT END":
                        frondEndCount++;
                        break;
                    case "MINOR DENT/SCRATCHES":
                        minorCount++;
                        break;
                    case "UNDERCARRIAGE":
                        underCarriageCount++;
                        break;
                    default:
                        miscCount++;
                        break;

                }

            }

        }
        System.out.println("The total number of cars with REAR END damage is: " + rearEndCount);
        System.out.println("The total number of cars with FRONT END damage is: " + frondEndCount);
        System.out.println("The total number of cars with MINOR DENT/SCRATCHES damage is: " + minorCount);
        System.out.println("The total number of cars with UNDERCARRIAGE damage is: " + underCarriageCount);
        System.out.println("The total number of cars with MISCELLANEOUS/OTHER damage is: " + miscCount);
    }


    public void listmakesWithURL() throws Exception {

        WebElement modelsList = driver.findElement(By.xpath("//div[@id='tabTrending']"));

        List<WebElement> allLinks = modelsList.findElements(By.tagName("a"));

        //Traversing through the list and printing its text along with link address
        for (WebElement link : allLinks) {

            linkText = link.getText();
            attributeText = (link.getAttribute("href"));
            //namesURL = new String [linkText][attributeText];
            System.out.println(linkText + " - " + " " + attributeText);

        }


    }

}













