package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.ElementActions;
import utils.Validations;

/**
 * Ready payment flows for any e-commerce site.
 * Fill the locators below for your site and the flows work as-is.
 */
public class PaymentPage {

    // TODO: fill per site
    private final By cardHolderField = By.cssSelector("");
    private final By cardNumberField = By.cssSelector("");
    private final By expiryField = By.cssSelector("");
    private final By cvvField = By.cssSelector("");
    private final By payButton = By.cssSelector("");
    private final By paymentErrorMessage = By.cssSelector("");

    @Step("Fill the card details")
    public PaymentPage fillCardDetails(String holderName, String cardNumber, String expiry, String cvv) {
        ElementActions.typeText(cardHolderField, holderName);
        ElementActions.typeText(cardNumberField, cardNumber);
        ElementActions.typeText(expiryField, expiry);
        ElementActions.typeText(cvvField, cvv);
        return this;
    }

    @Step("Confirm the payment")
    public OrderConfirmationPage confirmPayment() {
        ElementActions.clickElement(payButton);
        return new OrderConfirmationPage();
    }

    @Step("Get the payment error message")
    public String getPaymentErrorMessage() {
        return ElementActions.getText(paymentErrorMessage);
    }

    @Step("Assert the payment error message is [{expectedMessage}]")
    public PaymentPage assertOnPaymentError(String expectedMessage) {
        Validations.assertTextEquals(paymentErrorMessage, expectedMessage);
        return this;
    }
}
