package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {

    private static final String TEST_PROPERTIES_FILE = "src/main/resources/properties/test.properties";
    private static final String PRIVATE_PROPERTIES_FILE="src/main/resources/properties/private.properties";
    private static final String NAVIGATION_PROPERTIES_FILE="src/main/resources/properties/navigation.properties";

    public static final String ADMIN_EMAIL = getPrivateProperty("ADMIN_EMAIL");
    public static final String ADMIN_PASSWORD = getPrivateProperty("ADMIN_PASSWORD");
    public static final String USER_NAME = getPrivateProperty("USER_NAME");
    public static final String PASSWORD = getPrivateProperty("PASSWORD");
    public static final String USER_PATH = getPrivateProperty("USER_PATH");
    public static final String NAME = getProperty("NAME");
    public static final String EMAIL = getProperty("EMAIL");
    public static final String CATEGORY = getProperty("CATEGORY");
    public static final String PROGRAM_NAME = getProperty("PROGRAM_NAME");
    public static final String CITY = getProperty("CITY");
    public static final String WAGE = getProperty("WAGE");
    public static final String FIRST_NAME = getProperty("FIRST_NAME");
    public static final String LAST_NAME = getProperty("LAST_NAME");
    public static final String USER_EMAIL = getProperty("USER_EMAIL");
    public static final String OUTPUT_FILE = getProperty("OUTPUT_FILE");
    public static final String UNREGISTER_SQL = getProperty("UNREGISTER_SQL");
    public static final String LEAVE_USER_BUT_CLEAR_DATA = getProperty("LEAVE_USER_BUT_CLEAR_DATA");
    public static final String USER_2_COOKIE = getProperty("USER_2_COOKIE");

    //Instructor Properties

    public static final String INSTRUCTOR_EDIT_URL = getEnvironment() + getProperty("INSTRUCTOR_EDIT_URL");
    public static final String INSTRUCTOR_URL = getEnvironment() + getProperty("INSTRUCTOR_URL");
    public static final String INSTRUCTOR_USER_NAME = getProperty("INSTRUCTOR_USER_NAME");

    //CCQA Properties
    public static final String CCQS_RATE_APP = getEnvironment() + getProperty("CCQS_RATE_APP");

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
    public static final String SIGNON_URL = getEnvironment() + getProperty("SIGNON_URL");
    public static final String WELCOME_NAV = getEnvironment() + getNAVProperty("WELCOME_NAV");
    public static final String PROFILE_EDIT = getEnvironment() + getNAVProperty("PROFILE_EDIT");
    public static final String ABOUT_ME_EDIT = getEnvironment() + getNAVProperty("ABOUT_ME_EDIT");
    public static final String PHOTO_PROCESS = getEnvironment() + getNAVProperty("PHOTO_PROCESS");
    public static final String ABOUT_ME_PROCESS = getEnvironment() + getNAVProperty("ABOUT_ME_PROCESS");
    public static final String NAV_USER_PROFILE = getEnvironment() + getNAVProperty("NAV_USER_PROFILE");
    public static final String PHOTO_PROCESS_EDIT = getEnvironment() + getNAVProperty("PHOTO_PROCESS_EDIT");
    public static final String WORK_HISTORY= getEnvironment() + getNAVProperty("WORK_HISTORY");

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
