package utils;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class TestHelpers<Public> {

    private final WebDriver driver;
    private final FileUtilities fu;
    private final Queries query;


    public TestHelpers(WebDriver driver) {
        this.driver = driver;
        fu = new FileUtilities();
        query = new Queries();
    }

    public void selectComboBoxOption(WebElement element, String option) {
        Select selector = new Select(element);
        selector.selectByVisibleText(option);
    }

    public void clearText(WebElement element, WebDriver driver) {
        Actions action = new Actions(driver);
        try {
            action.clickAndHold(element);
//            element.click();
            action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            action.sendKeys(Keys.DELETE).perform();
            action.release(element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void pauseThread(int milliseconds ){
        try {
            sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public int getRandomNumberBetween(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public void restoreImplicitWaitTime(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void setImplicitWaitTime(WebDriver driver, int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }


    public String getTodaysDatePlusDays(int days) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        Date newDate = calendar.getTime();

        return dateFormat.format(newDate);
    }

    public static String getTodaysDateMinusDays(int days) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        Date newDate = calendar.getTime();

        return dateFormat.format(newDate);
    }


    public String getTodaysDatePlusYears(int years) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.YEAR, years);
        Date newDate = calendar.getTime();

        return dateFormat.format(newDate);
    }

    public String getXPathTextFromElement(WebElement element) {
        String xpath = "";
        String elemString = element.toString();
        String regex = "(\\/\\/.*'\\)\\]|\\]^]])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(elemString);
        if (matcher.find())
            xpath = matcher.group(0);
        return xpath;
    }






    public void deleteAllCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();

    }

}
