package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.ElementActions;
import utils.Validations;

/**
 * Ready login flow for any e-commerce site.
 * Fill the locators below for your site and the flow works as-is.
 */
public class LoginPage {

    // TODO: fill per site
    private final By emailField = By.cssSelector("");
    private final By passwordField = By.cssSelector("");
    private final By loginButton = By.cssSelector("");
    private final By loginErrorMessage = By.cssSelector("");

    @Step("Enter email [{email}]")
    public LoginPage enterEmail(String email) {
        ElementActions.typeText(emailField, email);
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        ElementActions.typeText(passwordField, password);
        return this;
    }

    @Step("Click the login button")
    public LoginPage clickLoginButton() {
        ElementActions.clickElement(loginButton);
        return this;
    }

    @Step("Login with email [{email}]")
    public LoginPage login(String email, String password) {
        return enterEmail(email).enterPassword(password).clickLoginButton();
    }

    @Step("Get the login error message")
    public String getLoginErrorMessage() {
        return ElementActions.getText(loginErrorMessage);
    }

    @Step("Assert the login error message is [{expectedMessage}]")
    public LoginPage assertOnLoginError(String expectedMessage) {
        Validations.assertTextEquals(loginErrorMessage, expectedMessage);
        return this;
    }
}
