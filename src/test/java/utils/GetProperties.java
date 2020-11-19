package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {

    private static final String TEST_PROPERTIES_FILE = "src/main/resources/properties/test.properties";
    private static final String PRIVATE_PROPERTIES_FILE="src/main/resources/properties/private.properties";
    private static final String NAVIGATION_PROPERTIES_FILE="src/main/resources/properties/navigation.properties";
    public static final String OUTPUT_FILE = getProperty("OUTPUT_FILE");



    public static final String REPORT_FILE = getProperty("REPORT_FILE");

    //Paths to Selenium Web Drivers
    public static final String CHROME_BINARY = getProperty("CHROME_BINARY");
    public static final String CHROME_DRIVER_PATH = getProperty("CHROME_DRIVER_PATH");
    public static final String FIREFOX_DRIVER_PATH = getProperty("FIREFOX_DRIVER_PATH");
    public static final String MS_DRIVER_PATH = getProperty("MS_DRIVER_PATH");
    public static final String IE_DRIVER_PATH = getProperty("IE_DRIVER_PATH");
    public static final String BASE_URL = getProperty("BASE_URL");





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
