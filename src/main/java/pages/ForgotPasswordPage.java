package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.ElementActions;
import utils.Validations;

/**
 * Ready password-reset flows for any e-commerce site.
 * Fill the locators below for your site and the flows work as-is.
 */
public class ForgotPasswordPage {

    // TODO: fill per site
    private final By emailField = By.cssSelector("");
    private final By resetButton = By.cssSelector("");
    private final By confirmationMessage = By.cssSelector("");

    @Step("Enter email [{email}]")
    public ForgotPasswordPage enterEmail(String email) {
        ElementActions.typeText(emailField, email);
        return this;
    }

    @Step("Submit the reset request")
    public ForgotPasswordPage submitResetRequest() {
        ElementActions.clickElement(resetButton);
        return this;
    }

    @Step("Request a password reset for email [{email}]")
    public ForgotPasswordPage requestPasswordReset(String email) {
        return enterEmail(email).submitResetRequest();
    }

    @Step("Get the reset confirmation message")
    public String getConfirmationMessage() {
        return ElementActions.getText(confirmationMessage);
    }

    @Step("Assert the reset confirmation message is [{expectedMessage}]")
    public ForgotPasswordPage assertOnResetConfirmation(String expectedMessage) {
        Validations.assertTextEquals(confirmationMessage, expectedMessage);
        return this;
    }
}
