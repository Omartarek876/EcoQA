package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.ElementActions;
import utils.Validations;

/**
 * Ready product-details flows for any e-commerce site.
 * Fill the locators below for your site and the flows work as-is.
 */
public class ProductDetailsPage {

    // TODO: fill per site
    private final By productName = By.cssSelector("");
    private final By productPrice = By.cssSelector("");
    private final By quantityField = By.cssSelector("");
    private final By sizeDropdown = By.cssSelector("");
    private final By addToCartButton = By.cssSelector("");
    private final By addToWishlistButton = By.cssSelector("");

    @Step("Assert the product details page is loaded")
    public ProductDetailsPage assertProductDetailsLoaded() {
        Validations.assertElementIsVisible(productName);
        return this;
    }

    @Step("Get the product name")
    public String getProductName() {
        return ElementActions.getText(productName);
    }

    @Step("Get the product price")
    public String getProductPrice() {
        return ElementActions.getText(productPrice);
    }

    @Step("Set the quantity to [{quantity}]")
    public ProductDetailsPage selectQuantity(int quantity) {
        ElementActions.typeText(quantityField, String.valueOf(quantity));
        return this;
    }

    @Step("Select size [{size}]")
    public ProductDetailsPage selectSize(String size) {
        ElementActions.selectDropdownByVisibleText(sizeDropdown, size);
        return this;
    }

    @Step("Add the product to the cart")
    public ProductDetailsPage addToCart() {
        ElementActions.clickElement(addToCartButton);
        return this;
    }

    @Step("Add the product to the wishlist")
    public ProductDetailsPage addToWishlist() {
        ElementActions.clickElement(addToWishlistButton);
        return this;
    }
}
