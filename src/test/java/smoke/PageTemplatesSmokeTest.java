package smoke;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ContactPage;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderConfirmationPage;
import pages.OrdersPage;
import pages.PaymentPage;
import pages.ProductDetailsPage;
import pages.ProductsPage;
import pages.RegisterPage;
import pages.SearchResultsPage;
import pages.WishlistPage;

/** Proves every e-commerce page template constructs cleanly with its empty locators. */
public class PageTemplatesSmokeTest {

    @Test(description = "Every page template instantiates and is ready for its locators to be filled")
    public void pageTemplatesInstantiate() {
        Assert.assertNotNull(new LoginPage(), "LoginPage should instantiate");
        Assert.assertNotNull(new RegisterPage(), "RegisterPage should instantiate");
        Assert.assertNotNull(new HomePage(), "HomePage should instantiate");
        Assert.assertNotNull(new ProductsPage(), "ProductsPage should instantiate");
        Assert.assertNotNull(new SearchResultsPage(), "SearchResultsPage should instantiate");
        Assert.assertNotNull(new ProductDetailsPage(), "ProductDetailsPage should instantiate");
        Assert.assertNotNull(new CartPage(), "CartPage should instantiate");
        Assert.assertNotNull(new CheckoutPage(), "CheckoutPage should instantiate");
        Assert.assertNotNull(new PaymentPage(), "PaymentPage should instantiate");
        Assert.assertNotNull(new OrderConfirmationPage(), "OrderConfirmationPage should instantiate");
        Assert.assertNotNull(new OrdersPage(), "OrdersPage should instantiate");
        Assert.assertNotNull(new AccountPage(), "AccountPage should instantiate");
        Assert.assertNotNull(new WishlistPage(), "WishlistPage should instantiate");
        Assert.assertNotNull(new ForgotPasswordPage(), "ForgotPasswordPage should instantiate");
        Assert.assertNotNull(new ContactPage(), "ContactPage should instantiate");
    }
}
