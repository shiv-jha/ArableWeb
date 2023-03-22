package support;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    public static String browserRequired;
    public static String browserType;
    public static String browserHeadless;
    public static String environment;
    public static String webApplicationUrl;
    public static String applicationPassword;

    public static String testRailUrl;
    public static String TestRail_username;
    public static String TestRail_password;

    public static String apiSeverUrl;
    public static String apiVersion;


    public void loadProperties() throws IOException {
        Properties settings = new Properties();
        Properties env = new Properties();
        Properties data = new Properties();

        settings.load(new FileInputStream("config/settings.properties"));
        env.load(new FileInputStream("environment/env.properties"));
        data.load(new FileInputStream("data/commonData.properties"));

        // get the property value and set it to a global variable
        browserRequired = settings.getProperty("browser.required");
        browserType = settings.getProperty("browser.type");
        browserHeadless = settings.getProperty("browser.headless");
        environment = settings.getProperty("environment");

        webApplicationUrl = env.getProperty(environment + ".webUrl");
        applicationPassword = env.getProperty(environment + ".webPassword");

        testRailUrl = settings.getProperty("testrail.url");
        TestRail_username = settings.getProperty("testrail.username");
        TestRail_password = settings.getProperty("testrail.password");

        apiSeverUrl = env.getProperty(environment + ".apiServer");
        apiVersion = env.getProperty(environment + ".apiVersion");

    }

    public String loadProperties(String key) {
        Properties data = new Properties();
        try {
            data.load(new FileInputStream("data/commonData.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.getProperty(key);
    }
}
