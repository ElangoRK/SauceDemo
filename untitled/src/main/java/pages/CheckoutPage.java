package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutPage extends BasePage {

    /* =========================
       Checkout Information Page
       ========================= */

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement zipCode;

    @FindBy(id = "continue")
    private WebElement continueBtn;

    /* =========================
       Checkout Overview Page
       ========================= */

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> overviewProducts;

    /* =========================
       Actions
       ========================= */

    public void fillDetails(String first, String last, String zip) {
        helper.type(firstName, first);
        helper.type(lastName, last);
        helper.type(zipCode, zip);
    }

    public void continueCheckout() {
        helper.click(continueBtn);
    }

    /* =========================
       Validations
       ========================= */

    public boolean isOverviewPage() {
        return pageTitle.getText().equalsIgnoreCase("Checkout: Overview");
    }

    public boolean verifyProductsInOverview(List<String> expectedProducts) {
        List<String> actualProducts = overviewProducts
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        return actualProducts.containsAll(expectedProducts);
    }
}
