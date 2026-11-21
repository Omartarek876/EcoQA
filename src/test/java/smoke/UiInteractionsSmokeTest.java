package smoke;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.AlertActions;
import utils.BrowserUtils;
import utils.DriverFactory;
import utils.ElementActions;
import utils.Validations;
import utils.WaitUtils;

import java.io.File;

/**
 * Exercises the core UI utilities (BrowserUtils, ElementActions, WaitUtils,
 * Validations, AlertActions) against a local page, so no external site is needed.
 */
@Listeners(utils.TestNgListener.class)
public class UiInteractionsSmokeTest {

    private static final String SMOKE_PAGE_URL =
            new File("src/test/resources/smoke/smoke-page.html").toURI().toString();

    private static final By HEADING = By.id("heading");
    private static final By NAME_INPUT = By.id("nameInput");
    private static final By SUBMIT_BUTTON = By.id("submitBtn");
    private static final By RESULT = By.id("result");
    private static final By ALERT_BUTTON = By.id("alertBtn");
    private static final By AGREE_CHECKBOX = By.id("agreeCheckbox");
    private static final By COUNTRY_SELECT = By.id("countrySelect");

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.InitializeDriver();
        BrowserUtils.navigateToUrl(SMOKE_PAGE_URL);
    }

    @Test(description = "Navigate, type, click, wait for the result and validate its text")
    public void typeClickWaitAndValidate() {
        Validations.assertTextEquals(HEADING, "Framework Smoke");

        ElementActions.typeText(NAME_INPUT, "Framework");
        ElementActions.clickElement(SUBMIT_BUTTON);

        WaitUtils.waitForVisibility(RESULT);
        Assert.assertTrue(WaitUtils.waitForTextToBePresent(RESULT, "Hello,"), "greeting text should appear");
        Validations.assertTextEquals(RESULT, "Hello, Framework!");

        Assert.assertTrue(BrowserUtils.GetCurrentUrl().endsWith("smoke-page.html"),
                "current URL should point at the local smoke page");
    }

    @Test(description = "Dropdown selection, checkbox checking and visibility validation")
    public void formControls() {
        ElementActions.selectDropdownByValue(COUNTRY_SELECT, "de");
        Assert.assertEquals(ElementActions.getElement(COUNTRY_SELECT).getDomProperty("value"), "de",
                "dropdown should select Germany by value");

        ElementActions.checkCheckbox(AGREE_CHECKBOX);
        Assert.assertTrue(ElementActions.getElement(AGREE_CHECKBOX).isSelected(), "checkbox should be checked");

        Validations.assertElementIsVisible(HEADING);
        Validations.assertElementIsNotVisible(RESULT);
    }

    @Test(description = "Open a JavaScript alert, read its text and accept it")
    public void alertHandling() {
        ElementActions.clickElement(ALERT_BUTTON);
        String alertText = AlertActions.getAlertText();
        Assert.assertEquals(alertText, "Smoke alert", "alert text should match");
        AlertActions.acceptAlert();
        try {
            DriverFactory.getDriver().switchTo().alert();
            Assert.fail("alert should be gone after accept");
        } catch (org.openqa.selenium.NoAlertPresentException expected) {
            // Accepting the alert removed it, as expected.
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
