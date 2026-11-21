package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.ElementActions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ready cart flows for any e-commerce site.
 * Fill the locators below for your site and the flows work as-is.
 */
public class CartPage {

    // TODO: fill per site
    private final By cartItems = By.cssSelector("");
    private final By cartItemNames = By.cssSelector("");
    private final By quantityField = By.cssSelector("");
    private final By removeItemButton = By.cssSelector("");
    private final By cartTotal = By.cssSelector("");
    private final By checkoutButton = By.cssSelector("");

    @Step("Get the names of the items in the cart")
    public List<String> getCartItemNames() {
        return ElementActions.getElements(cartItemNames).stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }

    @Step("Update the quantity of product [{productName}] to [{quantity}]")
    public CartPage updateQuantity(String productName, int quantity) {
        ElementActions.typeText(quantityField, String.valueOf(quantity));
        return this;
    }

    @Step("Remove product [{productName}] from the cart")
    public CartPage removeProductFromCart(String productName) {
        ElementActions.clickElement(removeItemButton);
        return this;
    }

    @Step("Get the cart total")
    public String getCartTotal() {
        return ElementActions.getText(cartTotal);
    }

    @Step("Proceed to checkout")
    public CheckoutPage proceedToCheckout() {
        ElementActions.clickElement(checkoutButton);
        return new CheckoutPage();
    }
}
