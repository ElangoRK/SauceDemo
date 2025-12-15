package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void fillDetails(String f, String l, String z) {
        helper.type(firstName, f);
        helper.type(lastName, l);
        helper.type(zip, z);
    }

    public void continueCheckout() {
        helper.click(continueBtn);
    }

    public boolean isOverviewPage() {
        return title.getText().equals("Checkout: Overview");
    }
}
