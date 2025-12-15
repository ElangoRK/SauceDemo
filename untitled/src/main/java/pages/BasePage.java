package pages;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.Helpers;

public abstract class BasePage {

    protected WebDriver driver;
    protected Helpers helper;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.helper = new Helpers(driver);
        PageFactory.initElements(driver, this);
    }
}
