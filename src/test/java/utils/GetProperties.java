package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {

    private static final String TEST_PROPERTIES_FILE = "src/main/resources/properties/test.properties";
    private static final String PRIVATE_PROPERTIES_FILE="src/main/resources/properties/private.properties";
    private static final String NAVIGATION_PROPERTIES_FILE="src/main/resources/properties/navigation.properties";


    public static final String USER_EMAIL = getProperty("USER_EMAIL");
    public static final String OUTPUT_FILE = getProperty("OUTPUT_FILE");


    // CAC Database Properties
    public static final String DB_URL_ALPHA = getProperty("DB_URL_ALPHA");
    public static final String DB_URL_BETA = getProperty("DB_URL_BETA");
    public static final String DB_URL_PROD = getProperty("DB_URL_PROD");
    public static final String DB_DRIVER = getProperty("DB_DRIVER");
    public static final String DB_USER = getProperty("DB_USER");
    public static final String DB_PASSWORD = getProperty("DB_PASSWORD");
    public static final String REPORT_FILE = getProperty("REPORT_FILE");

    //Automation Schema Specific Properties
    public static final String USE_AUTOMATION_DB = getProperty("USE_AUTOMATION_DB");
    public static final String AUTO_SCHEMA_URL = getProperty("AUTO_SCHEMA_URL");
    public static final String AUTO_SCHEMA_USER = getProperty("AUTO_SCHEMA_USER");
    public static final String AUTO_SCHEMA_PASSWORD = getProperty("AUTO_SCHEMA_PASSWORD");

    //OCC Program ID Used in Testing, for example A to Z Building Blocks in Orem
    public static final String OCC_PROGRAM_ID = getProperty("OCC_PROGRAM_ID");

    // Navigation URLs
    public static final String ENVIRONMENT = getProperty("ENVIRONMENT");
    public static final String BASE_URL = getEnvironment() + getProperty("BASE_URL");

    //Paths to Selenium Web Drivers
    public static final String CHROME_BINARY = getProperty("CHROME_BINARY");
    public static final String CHROME_DRIVER_PATH = getProperty("CHROME_DRIVER_PATH");
    public static final String FIREFOX_DRIVER_PATH = getProperty("FIREFOX_DRIVER_PATH");
    public static final String MS_DRIVER_PATH = getProperty("MS_DRIVER_PATH");
    public static final String IE_DRIVER_PATH = getProperty("IE_DRIVER_PATH");



    private static String getEnvironment() {
        String env = getProperty("ENVIRONMENT");
        if (env.equalsIgnoreCase("prod")){
            return "https://";
        } else {
            return "https://" + env + ".";
        }
    }

    private static String getProperty(String property) {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream(TEST_PROPERTIES_FILE)) {
            props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return props.getProperty(property);
    }

    private static String getPrivateProperty(String property) {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream(PRIVATE_PROPERTIES_FILE)) {
            props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return props.getProperty(property);
    }

    private static String getNAVProperty(String property) {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream(NAVIGATION_PROPERTIES_FILE)) {
            props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return props.getProperty(property);
    }


}
