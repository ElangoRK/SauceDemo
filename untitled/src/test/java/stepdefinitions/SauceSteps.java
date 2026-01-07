package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.*;

public class SauceSteps {

    LoginPage login = new LoginPage();
    ProductPage products = new ProductPage();
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
        Assert.assertTrue(products.isOnProductsPage());
    }


    @When("I add the following products to the cart:")
    public void addProducts(DataTable table) {
        products.addProducts(table.asList());
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
        Assert.assertTrue(cart.hasProducts(table.asList()));
    }

    @When("I remove the following products from the cart:")
    public void removeProducts(DataTable table) {
        cart.removeProducts(table.asList());
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
        Assert.assertTrue(checkout.isOverviewPage(page));
    }

    @Then("I should see the following products in the checkout overview:")
    public void validateCheckoutOverviewProducts(DataTable table) {
        Assert.assertTrue(checkout.verifyProductsInOverview(table.asList()));
    }
}
