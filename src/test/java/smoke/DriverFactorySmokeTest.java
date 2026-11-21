package smoke;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.DriverFactory;

/** Proves driver management works after the transfer: starts a headless session and opens a blank page. */
@Listeners(utils.TestNgListener.class)
public class DriverFactorySmokeTest {

    @Test(description = "DriverFactory starts a headless browser session")
    public void driverStartsHeadlessSession() {
        DriverFactory.InitializeDriver();
        Assert.assertNotNull(DriverFactory.getDriver(), "driver should be initialized");
        DriverFactory.getDriver().get("about:blank");
        Assert.assertEquals(DriverFactory.getDriver().getTitle(), "", "blank page should have an empty title");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
