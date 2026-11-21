package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.ElementActions;
import utils.Validations;

/**
 * Ready checkout flows for any e-commerce site.
 * Fill the locators below for your site and the flows work as-is.
 */
public class CheckoutPage {

    // TODO: fill per site
    private final By addressField = By.cssSelector("");
    private final By cityField = By.cssSelector("");
    private final By postalCodeField = By.cssSelector("");
    private final By phoneField = By.cssSelector("");
    private final By paymentMethodOption = By.cssSelector("");
    private final By placeOrderButton = By.cssSelector("");
    private final By orderConfirmationMessage = By.cssSelector("");

    @Step("Fill the shipping address")
    public CheckoutPage fillShippingAddress(String address, String city, String postalCode, String phone) {
        ElementActions.typeText(addressField, address);
        ElementActions.typeText(cityField, city);
        ElementActions.typeText(postalCodeField, postalCode);
        ElementActions.typeText(phoneField, phone);
        return this;
    }

    @Step("Select the payment method")
    public CheckoutPage selectPaymentMethod() {
        ElementActions.clickElement(paymentMethodOption);
        return this;
    }

    @Step("Place the order")
    public CheckoutPage placeOrder() {
        ElementActions.clickElement(placeOrderButton);
        return this;
    }

    @Step("Get the order confirmation message")
    public String getOrderConfirmationMessage() {
        return ElementActions.getText(orderConfirmationMessage);
    }

    @Step("Assert the order is confirmed with message [{expectedMessage}]")
    public CheckoutPage assertOrderConfirmed(String expectedMessage) {
        Validations.assertTextEquals(orderConfirmationMessage, expectedMessage);
        return this;
    }
}
