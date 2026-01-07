package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(className = "inventory_item")
    private List<WebElement> products;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(className = "title")
    private WebElement title;

    public boolean isOnProductsPage() {
        return title.getText().equals("Products");
    }

    public void addProducts(List<String> productNames) {
        helper.waitForVisibility(By.className("inventory_item"));

        for (String name : productNames) {
            addSingleProduct(name);
        }
    }

    private void addSingleProduct(String productName) {
        for (WebElement product : products) {
            String name = product
                    .findElement(By.className("inventory_item_name"))
                    .getText();

            if (name.equalsIgnoreCase(productName)) {
                product.findElement(By.tagName("button")).click();
                break;
            }
        }
    }

    public void goToCart() {
        helper.click(cartLink);
    }

    public int getCartCount() {
        return helper.isDisplayed(cartBadge)
                ? Integer.parseInt(cartBadge.getText())
                : 0;
    }
}
