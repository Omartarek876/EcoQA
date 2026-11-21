package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.BrowserUtils;
import utils.ElementActions;
import utils.PropertiesReader;
import utils.WaitUtils;

/**
 * Ready home-page navigation and product search for any e-commerce site.
 * Fill the locators below for your site and the flows work as-is.
 */
public class HomePage {

    // TODO: fill per site
    private final By logo = By.cssSelector("");
    private final By searchBox = By.cssSelector("");
    private final By searchButton = By.cssSelector("");
    private final By loginLink = By.cssSelector("");
    private final By productsLink = By.cssSelector("");
    private final By cartLink = By.cssSelector("");

    @Step("Open the store home page")
    public HomePage open() {
        PropertiesReader.loadProperties();
        BrowserUtils.navigateToUrl(System.getProperty("baseUrl"));
        return this;
    }

    @Step("Assert the home page is loaded")
    public boolean isHomePageLoaded() {
        return WaitUtils.waitForVisibility(logo).isDisplayed();
    }

    @Step("Search for product [{keyword}]")
    public ProductsPage searchForProduct(String keyword) {
        ElementActions.typeText(searchBox, keyword);
        ElementActions.clickElement(searchButton);
        return new ProductsPage();
    }

    @Step("Open the login page")
    public LoginPage openLoginPage() {
        ElementActions.clickElement(loginLink);
        return new LoginPage();
    }

    @Step("Open the products page")
    public ProductsPage openProductsPage() {
        ElementActions.clickElement(productsLink);
        return new ProductsPage();
    }

    @Step("Open the cart")
    public CartPage openCart() {
        ElementActions.clickElement(cartLink);
        return new CartPage();
    }
}
