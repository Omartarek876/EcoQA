package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.ElementActions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ready wishlist flows for any e-commerce site.
 * Fill the locators below for your site and the flows work as-is.
 */
public class WishlistPage {

    // TODO: fill per site
    private final By wishlistItems = By.cssSelector("");
    private final By wishlistItemNames = By.cssSelector("");
    private final By moveToCartButton = By.cssSelector("");
    private final By removeFromWishlistButton = By.cssSelector("");

    @Step("Get the names of the wishlist items")
    public List<String> getWishlistItemNames() {
        return ElementActions.getElements(wishlistItemNames).stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }

    @Step("Get the number of wishlist items")
    public int getWishlistCount() {
        return ElementActions.getElements(wishlistItems).size();
    }

    @Step("Check whether the wishlist is empty")
    public boolean isWishlistEmpty() {
        return ElementActions.getElements(wishlistItems).isEmpty();
    }

    @Step("Move product [{productName}] from the wishlist to the cart")
    public CartPage moveToCart(String productName) {
        ElementActions.clickElement(moveToCartButton);
        return new CartPage();
    }

    @Step("Remove product [{productName}] from the wishlist")
    public WishlistPage removeFromWishlist(String productName) {
        ElementActions.clickElement(removeFromWishlistButton);
        return this;
    }
}
