package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.ElementActions;
import utils.Validations;

/**
 * Ready order-confirmation flows for any e-commerce site.
 * Fill the locators below for your site and the flows work as-is.
 */
public class OrderConfirmationPage {

    // TODO: fill per site
    private final By confirmationMessage = By.cssSelector("");
    private final By orderNumber = By.cssSelector("");
    private final By continueShoppingButton = By.cssSelector("");

    @Step("Get the order confirmation message")
    public String getConfirmationMessage() {
        return ElementActions.getText(confirmationMessage);
    }

    @Step("Get the order number")
    public String getOrderNumber() {
        return ElementActions.getText(orderNumber);
    }

    @Step("Assert the order is confirmed with message [{expectedMessage}]")
    public OrderConfirmationPage assertOrderConfirmed(String expectedMessage) {
        Validations.assertTextEquals(confirmationMessage, expectedMessage);
        return this;
    }

    @Step("Continue shopping")
    public HomePage continueShopping() {
        ElementActions.clickElement(continueShoppingButton);
        return new HomePage();
    }
}
