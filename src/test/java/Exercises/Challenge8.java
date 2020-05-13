package Exercises;

import base.TestBase;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.copart;
import utils.GeneralUtilities;
import org.json.JSONArray;
import org.json.JSONException;

import java.net.MalformedURLException;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.post;

public class Challenge8 {

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


    @Test
    public void httpPost() throws JSONException, InterruptedException {

        //Initializing Rest API's URL
        String APIUrl = "https://www.copart.com/public/lots/search";

        //Initializing payload or API body
        String APIBody = "{\"query\":\"toyota camry\"}";

        // Building request using requestSpecBuilder
        RequestSpecBuilder builder = new RequestSpecBuilder();

        //Setting API's body
        builder.setBody(APIBody);

        //Setting content type as application/json or application/xml
        builder.setContentType("application/json; charset=UTF-8");

        RequestSpecification requestSpec = builder.build();

        //Making post request with authentication, leave blank in case there are no credentials- basic("","")
        Response response = given().authentication().preemptive().basic("", "")
                .spec(requestSpec).when().post(APIUrl);
        JSONObject JSONResponseBody = new JSONObject(response.body().asString());

        //Fetching the desired value of a parameter

        String result = JSONResponseBody.getString("{totalElements}");

        //Asserting that result of Norway is Oslo
        Assert.assertEquals(result, "{expectedValue}");

    }

}

