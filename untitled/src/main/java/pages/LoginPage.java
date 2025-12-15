package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public void login(String user, String pass) {
        helper.type(username, user);
        helper.type(password, pass);
        helper.click(loginBtn);

        // ✅ handle alert if any
        if (helper.isAlertPresent()) {
            helper.acceptAlert();
        }

        // ✅ wait until products page loads
        helper.waitForUrlContains("inventory");
    }
}
