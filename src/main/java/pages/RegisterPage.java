package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.ElementActions;
import utils.Validations;

/**
 * Ready registration flow for any e-commerce site.
 * Fill the locators below for your site and the flow works as-is.
 */
public class RegisterPage {

    // TODO: fill per site
    private final By nameField = By.cssSelector("");
    private final By emailField = By.cssSelector("");
    private final By passwordField = By.cssSelector("");
    private final By confirmPasswordField = By.cssSelector("");
    private final By registerButton = By.cssSelector("");
    private final By registrationErrorMessage = By.cssSelector("");

    @Step("Enter name [{name}]")
    public RegisterPage enterName(String name) {
        ElementActions.typeText(nameField, name);
        return this;
    }

    @Step("Enter email [{email}]")
    public RegisterPage enterEmail(String email) {
        ElementActions.typeText(emailField, email);
        return this;
    }

    @Step("Enter password")
    public RegisterPage enterPassword(String password) {
        ElementActions.typeText(passwordField, password);
        return this;
    }

    @Step("Confirm password")
    public RegisterPage confirmPassword(String password) {
        ElementActions.typeText(confirmPasswordField, password);
        return this;
    }

    @Step("Click the register button")
    public RegisterPage clickRegisterButton() {
        ElementActions.clickElement(registerButton);
        return this;
    }

    @Step("Register with name [{name}] and email [{email}]")
    public RegisterPage register(String name, String email, String password) {
        return enterName(name).enterEmail(email).enterPassword(password)
                .confirmPassword(password).clickRegisterButton();
    }

    @Step("Get the registration error message")
    public String getRegistrationErrorMessage() {
        return ElementActions.getText(registrationErrorMessage);
    }

    @Step("Assert the registration error message is [{expectedMessage}]")
    public RegisterPage assertOnRegistrationError(String expectedMessage) {
        Validations.assertTextEquals(registrationErrorMessage, expectedMessage);
        return this;
    }
}
