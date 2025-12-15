package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {

    @FindBy(id = "checkout")
    private WebElement checkoutBtn;

    // ✅ NO WAIT FOR cart_item
    public int getItemCount() {
        return driver.findElements(By.className("cart_item")).size();
    }

    public List<String> getProductNames() {
        return driver.findElements(By.className("cart_item"))
                .stream()
                .map(item -> item.findElement(By.className("inventory_item_name")).getText())
                .collect(Collectors.toList());
    }

    public void removeProduct(String productName) {
        for (WebElement item : driver.findElements(By.className("cart_item"))) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equalsIgnoreCase(productName)) {
                item.findElement(By.tagName("button")).click();
                break;
            }
        }
    }

    public void checkout() {
        helper.click(checkoutBtn);
    }
}
