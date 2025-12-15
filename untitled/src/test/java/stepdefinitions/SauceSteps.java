package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.*;

import java.util.List;

public class SauceSteps {

    LoginPage login = new LoginPage();
    ProductsPage products = new ProductsPage();
    CartPage cart = new CartPage();
    CheckoutPage checkout = new CheckoutPage();

    @Given("I am on the login page")
    public void openLogin() {
        login.open();
    }

    @When("I login with the following credentials:")
    public void login(DataTable table) {
        login.login(
                table.asMap().get("Username"),
                table.asMap().get("Password")
        );
    }

    @Then("I should be navigated to the homepage")
    public void validateHome() {
        Assert.assertTrue(products.getCartCount() >= 0);
    }

    @When("I add the following products to the cart:")
    public void addProducts(DataTable table) {
        for (String product : table.asList()) {
            products.addProduct(product);
        }
    }

    @When("I navigate to the cart page")
    public void openCart() {
        products.goToCart();
    }

    @Then("I should see {string} products in the cart")
    public void validateCount(String count) {
        Assert.assertEquals(Integer.parseInt(count), cart.getItemCount());
    }

    @Then("I should see the following products in the cart:")
    public void validateProducts(DataTable table) {
        List<String> actual = cart.getProductNames();
        for (String expected : table.asList()) {
            Assert.assertTrue(actual.contains(expected));
        }
    }

    @When("I remove the following products from the cart:")
    public void removeProducts(DataTable table) {
        for (String product : table.asList()) {
            cart.removeProduct(product);
        }
    }

    @When("I click on the checkout button")
    public void checkout() {
        cart.checkout();
    }

    @When("I enter the following checkout details:")
    public void enterDetails(DataTable table) {
        checkout.fillDetails(
                table.asMap().get("First Name"),
                table.asMap().get("Last Name"),
                table.asMap().get("Zip/Postal Code")
        );
    }

    @When("I click on the Continue button")
    public void continueBtn() {
        checkout.continueCheckout();
    }

    @Then("I should be on the {string} page")
    public void validateOverview(String page) {
        Assert.assertTrue(checkout.isOverviewPage());
    }

    @Then("I should see the following products in the checkout overview:")
    public void validateOverviewProducts(DataTable table) {
        for (String product : table.asList()) {
            Assert.assertTrue(cart.getProductNames().contains(product));
        }
    }
}
