package smoke;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertiesReader;

/** Proves the framework configuration files load correctly after the transfer. */
public class FrameworkConfigSmokeTest {

    @Test(description = "Execution and environment configuration load with the expected defaults")
    public void configurationLoads() {
        PropertiesReader.loadProperties();
        Assert.assertEquals(System.getProperty("browser"), "chrome", "browser should default to chrome");
        Assert.assertEquals(System.getProperty("headless"), "true", "headless should default to true");
        Assert.assertNotNull(System.getProperty("waitTimeOutSeconds"), "waitTimeOutSeconds should be configured");
        Assert.assertNotNull(System.getProperty("baseUrl"), "baseUrl key should exist (value may be empty until set)");
    }
}
