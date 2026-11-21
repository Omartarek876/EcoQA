package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.ElementActions;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ready order-history flows for any e-commerce site.
 * Fill the locators below for your site and the flows work as-is.
 */
public class OrdersPage {

    // TODO: fill per site
    private final By orderRows = By.cssSelector("");
    private final By orderNumbers = By.cssSelector("");
    private final By orderStatuses = By.cssSelector("");
    private final By orderDetailsLink = By.cssSelector("");
    private final By reorderButton = By.cssSelector("");

    @Step("Get the number of past orders")
    public int getOrdersCount() {
        return ElementActions.getElements(orderRows).size();
    }

    @Step("Get the order numbers")
    public List<String> getOrderNumbers() {
        return ElementActions.getElements(orderNumbers).stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }

    @Step("Get the order statuses")
    public List<String> getOrderStatuses() {
        return ElementActions.getElements(orderStatuses).stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }

    @Step("Open the details of order [{orderNumber}]")
    public OrdersPage openOrderDetails(String orderNumber) {
        ElementActions.clickElement(orderDetailsLink);
        return this;
    }

    @Step("Reorder the items of order [{orderNumber}]")
    public CartPage reorderItems(String orderNumber) {
        ElementActions.clickElement(reorderButton);
        return new CartPage();
    }
}
