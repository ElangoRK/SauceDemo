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

        helper.waitForUrlContains("inventory");

        if (helper.isAlertPresent()) {
            helper.acceptAlert();
        }
    }
}
