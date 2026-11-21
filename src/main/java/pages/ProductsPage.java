package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.ElementActions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ready catalog/search-results flows for any e-commerce site.
 * Fill the locators below for your site and the flows work as-is.
 */
public class ProductsPage {

    // TODO: fill per site
    private final By productCards = By.cssSelector("");
    private final By productNames = By.cssSelector("");
    private final By addToCartButton = By.cssSelector("");
    private final By viewProductLink = By.cssSelector("");

    @Step("Get the names of the listed products")
    public List<String> getProductNames() {
        return ElementActions.getElements(productNames).stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }

    @Step("Get the number of listed products")
    public int getSearchResultsCount() {
        return ElementActions.getElements(productCards).size();
    }

    @Step("Open the details of product [{productName}]")
    public ProductsPage openProductDetails(String productName) {
        ElementActions.clickElement(viewProductLink);
        return this;
    }

    @Step("Add product [{productName}] to the cart")
    public ProductsPage addProductToCart(String productName) {
        ElementActions.clickElement(addToCartButton);
        return this;
    }
}
