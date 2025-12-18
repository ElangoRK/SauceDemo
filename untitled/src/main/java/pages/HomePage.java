package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    public boolean isHomePage() {
        return pageTitle.getText().equals("Products");
    }

    public void addProduct(String productName) {

        if (helper.isAlertPresent()) {
            String msg = helper.getAlertText();
            helper.acceptAlert();
            System.out.println("Alert handled: " + msg);
        }

        WebElement addBtn = driver.findElement(
                By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button"));
        helper.click(addBtn);
    }

    public void openCart() {
        helper.click(cartIcon);
    }
}
