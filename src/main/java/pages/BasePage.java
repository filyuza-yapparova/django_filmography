package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static constants.Constant.TimeoutVariables.EXPLICIT_WAIT;
import static constants.Constant.Variables.*;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    public final By addNewEntryButton = By.xpath("//div/ul/li/a [@class='addlink']");
    public final By actorsPageButton = By.xpath("//tr/th/a[contains(text(),'Actors')]");
    public final By charactersPageButton = By.xpath("//tr/th/a[contains(text(),'Characters')]");
    public final By directorsPageButton = By.xpath("//tr/th/a[contains(text(),'Directors')]");
    public final By moviesPageButton = By.xpath("//tr/th/a[contains(text(),'Movies')]");

    public final By pageTitle(String value) {
        By page = By.xpath("//tr/th/a[contains(text(),'" + value + "')]");
        return page;
    }

    public final By saveButton = By.name("_save");
    public final By saveAndContinueEditingButton = By.name("_continue");
    public final By saveAndAddAnotherButton = By.name("_addanother");


    /**
     * Method for navigating to a specific URL
     */
    public BasePage goToUrl(String url) {
        driver.get(url);
        return this;
    }

    /**
     * Wait for visibility element in DOM model
     */
    public WebElement waitElementIsVisible(WebElement element) {
        new WebDriverWait(driver, Duration.of(EXPLICIT_WAIT, ChronoUnit.SECONDS)).until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public BasePage isEntryAdditionButtonPresent() {
        waitElementIsVisible(driver.findElement(addNewEntryButton));
        return this;
    }

    public BasePage openPage(By element) {
        driver.findElement(element).click();
        return this;
    }

    public BasePage isPageTitleChanged(String page) {
        switch (page) {
            case "actors" -> page = ACTORS;
            case "characters" -> page = CHARACTERS;
            case "movies" -> page = MOVIES;
            case "directors" -> page = DIRECTORS;
            default -> Assertions.fail("INCORRECT PAGE NAME =>> '" + page + "'");
        }
        waitElementIsVisible(driver.findElement(pageTitle(page)));
        return this;
    }
}
