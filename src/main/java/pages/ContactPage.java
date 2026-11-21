package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.ElementActions;
import utils.Validations;

/**
 * Ready contact/support flows for any e-commerce site.
 * Fill the locators below for your site and the flows work as-is.
 */
public class ContactPage {

    // TODO: fill per site
    private final By nameField = By.cssSelector("");
    private final By emailField = By.cssSelector("");
    private final By subjectField = By.cssSelector("");
    private final By messageField = By.cssSelector("");
    private final By submitButton = By.cssSelector("");
    private final By successMessage = By.cssSelector("");

    @Step("Enter name [{name}]")
    public ContactPage enterName(String name) {
        ElementActions.typeText(nameField, name);
        return this;
    }

    @Step("Enter email [{email}]")
    public ContactPage enterEmail(String email) {
        ElementActions.typeText(emailField, email);
        return this;
    }

    @Step("Enter subject [{subject}]")
    public ContactPage enterSubject(String subject) {
        ElementActions.typeText(subjectField, subject);
        return this;
    }

    @Step("Enter message")
    public ContactPage enterMessage(String message) {
        ElementActions.typeText(messageField, message);
        return this;
    }

    @Step("Submit the contact form")
    public ContactPage submitForm() {
        ElementActions.clickElement(submitButton);
        return this;
    }

    @Step("Send a contact message from [{name}] with subject [{subject}]")
    public ContactPage sendMessage(String name, String email, String subject, String message) {
        return enterName(name).enterEmail(email).enterSubject(subject)
                .enterMessage(message).submitForm();
    }

    @Step("Get the success message")
    public String getSuccessMessage() {
        return ElementActions.getText(successMessage);
    }

    @Step("Assert the message was sent with confirmation [{expectedMessage}]")
    public ContactPage assertOnMessageSent(String expectedMessage) {
        Validations.assertTextEquals(successMessage, expectedMessage);
        return this;
    }
}
