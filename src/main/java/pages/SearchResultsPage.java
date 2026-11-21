package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.ElementActions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ready search-results flows for any e-commerce site.
 * Fill the locators below for your site and the flows work as-is.
 */
public class SearchResultsPage {

    // TODO: fill per site
    private final By resultCards = By.cssSelector("");
    private final By resultNames = By.cssSelector("");
    private final By sortDropdown = By.cssSelector("");
    private final By resultAddToCartButton = By.cssSelector("");
    private final By resultDetailsLink = By.cssSelector("");

    @Step("Get the names of the search results")
    public List<String> getResultNames() {
        return ElementActions.getElements(resultNames).stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }

    @Step("Get the number of search results")
    public int getResultsCount() {
        return ElementActions.getElements(resultCards).size();
    }

    @Step("Sort the results by [{option}]")
    public SearchResultsPage sortResultsBy(String option) {
        ElementActions.selectDropdownByVisibleText(sortDropdown, option);
        return this;
    }

    @Step("Open the details of product [{productName}]")
    public ProductDetailsPage openProductDetails(String productName) {
        ElementActions.clickElement(resultDetailsLink);
        return new ProductDetailsPage();
    }

    @Step("Add product [{productName}] to the cart from the results")
    public SearchResultsPage addToCartFromResults(String productName) {
        ElementActions.clickElement(resultAddToCartButton);
        return this;
    }
}
