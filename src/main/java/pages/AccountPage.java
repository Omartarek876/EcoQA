package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.ElementActions;
import utils.Validations;

/**
 * Ready account/profile flows for any e-commerce site.
 * Fill the locators below for your site and the flows work as-is.
 */
public class AccountPage {

    // TODO: fill per site
    private final By profileName = By.cssSelector("");
    private final By nameField = By.cssSelector("");
    private final By phoneField = By.cssSelector("");
    private final By addressField = By.cssSelector("");
    private final By cityField = By.cssSelector("");
    private final By postalCodeField = By.cssSelector("");
    private final By saveButton = By.cssSelector("");
    private final By ordersLink = By.cssSelector("");
    private final By wishlistLink = By.cssSelector("");
    private final By successMessage = By.cssSelector("");

    @Step("Get the profile name")
    public String getProfileName() {
        return ElementActions.getText(profileName);
    }

    @Step("Update the profile with name [{name}] and phone [{phone}]")
    public AccountPage updateProfile(String name, String phone) {
        ElementActions.typeText(nameField, name);
        ElementActions.typeText(phoneField, phone);
        ElementActions.clickElement(saveButton);
        return this;
    }

    @Step("Add a new address")
    public AccountPage addAddress(String address, String city, String postalCode) {
        ElementActions.typeText(addressField, address);
        ElementActions.typeText(cityField, city);
        ElementActions.typeText(postalCodeField, postalCode);
        ElementActions.clickElement(saveButton);
        return this;
    }

    @Step("Open the order history")
    public OrdersPage openOrders() {
        ElementActions.clickElement(ordersLink);
        return new OrdersPage();
    }

    @Step("Open the wishlist")
    public WishlistPage openWishlist() {
        ElementActions.clickElement(wishlistLink);
        return new WishlistPage();
    }

    @Step("Assert the account success message is [{expectedMessage}]")
    public AccountPage assertOnAccountUpdate(String expectedMessage) {
        Validations.assertTextEquals(successMessage, expectedMessage);
        return this;
    }
}
