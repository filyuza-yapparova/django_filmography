package tests.base;

import com.github.javafaker.Faker;
import common.CommonActions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.util.Random;

import static common.Config.CLEAR_COOKIES;
import static common.Config.HOLD_BROWSER_OPEN;
import static constants.Constant.Urls.MAIN_URL;

@Execution(ExecutionMode.CONCURRENT) //parallel test running
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected WebDriver driver = CommonActions.createDriver();
    protected BasePage basePage = new BasePage(driver);
    protected LoginPage loginPage = new LoginPage(driver);
    protected ActorsPage actorsPage = new ActorsPage(driver);
    protected CharactersPage charactersPage = new CharactersPage(driver);
    protected MoviesPage moviesPage = new MoviesPage(driver);
    protected Faker faker = new Faker();
    protected Random randomizer = new Random();

    @BeforeAll
    void openMainPage() {
        basePage.goToUrl(MAIN_URL);
    }

    @BeforeEach
    void login() {
        loginPage.login();
    }


    @AfterEach
    void clearCookiesAndLocalStorage() {
        if(CLEAR_COOKIES) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            javascriptExecutor.executeScript("window.sessionStorage.clear()");
        }
    }

    @AfterAll
    void close() {
        if(!HOLD_BROWSER_OPEN) {
            driver.close();
        }
    }
}
