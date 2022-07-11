package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.Constant.Variables.USER_LOGIN;
import static constants.Constant.Variables.USER_PASSWORD;

public class LoginPage extends BasePage{
    public final By loginTextField = By.id("id_username");
    public final By passwordTextField = By.id("id_password");
    public final By loginButton = By.xpath("//div/input[@type = 'submit'][@value = 'Log in']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login() {
        waitElementIsVisible(driver.findElement(loginTextField));
        driver.findElement(loginTextField).sendKeys(USER_LOGIN);
        driver.findElement(passwordTextField).sendKeys(USER_PASSWORD);
        driver.findElement(loginButton).click();
    }
}
