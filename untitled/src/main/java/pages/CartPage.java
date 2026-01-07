package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutBtn;

    public int getItemCount() {
        helper.waitForVisibility(By.className("cart_item"));
        return cartItems.size();
    }

    public boolean hasProducts(List<String> expectedProducts) {
        List<String> actualProducts = cartItems.stream()
                .map(i -> i.findElement(By.className("inventory_item_name")).getText())
                .collect(Collectors.toList());

        return actualProducts.containsAll(expectedProducts);
    }

    public void removeProducts(List<String> products) {
        for (String product : products) {
            removeSingleProduct(product);
        }
    }

    private void removeSingleProduct(String productName) {
        for (WebElement item : cartItems) {
            if (item.getText().contains(productName)) {
                item.findElement(By.tagName("button")).click();
                break;
            }
        }
    }

    public void checkout() {
        helper.click(checkoutBtn);
    }
}
