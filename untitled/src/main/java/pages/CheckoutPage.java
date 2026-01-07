package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement zip;

    @FindBy(id = "continue")
    private WebElement continueBtn;

    @FindBy(className = "title")
    private WebElement title;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> overviewItems;

    public void fillDetails(String f, String l, String z) {
        helper.type(firstName, f);
        helper.type(lastName, l);
        helper.type(zip, z);
    }

    public void continueCheckout() {
        helper.click(continueBtn);
    }

    public boolean isOverviewPage(String expected) {
        return title.getText().equals(expected);
    }

    public boolean verifyProductsInOverview(List<String> expected) {
        for (String product : expected) {
            boolean found = overviewItems.stream()
                    .anyMatch(e -> e.getText().equals(product));
            if (!found) return false;
        }
        return true;
    }
}
