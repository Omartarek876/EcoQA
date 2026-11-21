package templates;

import org.testng.annotations.*;
import pages.*;
import utils.JsonFileManager;

/**
 * Template showing how a test class consumes the per-page data files.
 *
 * Pattern (same as production suites):
 * - Each page has a folder src/test/resources/<Page>Page_Data/ holding three JSON files:
 *   <Page>Messages.json  (expected assertion messages, kept separate for readability)
 *   <Page>Data_EN.json   (English test data)
 *   <Page>Data_AR.json   (Arabic test data, for localized sites)
 * - Data files are loaded in @BeforeClass through JsonFileManager. The language
 *   suffix is selected with the "language" system property (-Dlanguage=AR),
 *   defaulting to EN.
 * - Inside tests, values are read with getTestData("$.jsonPath") and passed into
 *   the fluent page methods; expected messages come from the messages file.
 *
 * This class is a template: its name does not match the surefire test includes,
 * and the sample test is disabled. Copy it per test class, fill the locators and
 * the data files, then enable the tests.
 */
public class PageTestTemplate {

    JsonFileManager loginMessages;
    JsonFileManager loginData;

    @BeforeClass
    public void setup() {
        loginMessages = new JsonFileManager("src/test/resources/LoginPage_Data/LoginMessages.json");
        loginData = new JsonFileManager("src/test/resources/LoginPage_Data/LoginData_"
                + System.getProperty("language", "EN") + ".json");
    }

    @Test(description = "Sample: login with invalid credentials shows the expected error", enabled = false)
    public void loginWithInvalidCredentials() {
        new LoginPage()
                .enterEmail(loginData.getTestData("$.credentials.invalidEmail"))
                .enterPassword(loginData.getTestData("$.credentials.invalidPassword"))
                .clickLoginButton()
                .assertOnLoginError(loginMessages.getTestData("$.invalidCredentialsMessage"));
    }

    @Test(description = "Sample: valid login lands on the home page greeting", enabled = false)
    public void loginWithValidCredentials() {
        new LoginPage()
                .login(loginData.getTestData("$.credentials.validEmail"),
                        loginData.getTestData("$.credentials.validPassword"));
        // TODO: assert on the logged-in state, e.g.
        // new HomePage().assertOnUserGreeting(loginMessages.getTestData("$.loggedInUserGreeting"));
    }
}
