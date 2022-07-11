package pages;

import entities.Actor;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.format.DateTimeFormatter;

public class ActorsPage extends BasePage {
    public ActorsPage(WebDriver driver) {
        super(driver);
    }


    public final By actorFirstNameTextField = By.name("firstname");
    public final By actorLastNameTextField = By.name("lastname");
    public final By actorBirthdate = By.name("date_of_birth");
    public final By genderList = By.name("gender");

    public final By gender(String value) {
        if (value.equals("Female")) {
            value = "F";
        } else if (value.equals("Male")) {
            value = "M";
        } else {
            Assertions.fail("INCORRECT GENDER NAME =>> '" + value + "'");
        }
        return By.xpath("//select/option[@value='" + value + "']");
    }

    public final By actorInList(String fullname) {
        return By.xpath("//table/tbody/tr/th/a[contains(text(),'" + fullname + "')]");
    }


    public final By actorCheckbox = By.xpath("//table/tbody/tr/td/input[@type='checkbox']");
    public final By actionList = By.xpath("//label/select[@name='action']");
    public final By deleteAction = By.xpath("//label/select/option[@value='delete_selected']");
    public final By goButton = By.xpath("//div/button[@type='submit']");

    public final By actorAddedSuccessfullyNotification(Actor actor) {
        return By.xpath("//div/ul/li[@class='success']/a[contains(text(),'" + actor.getFirstname() + " " + actor.getLastname() + "')]");
    }

    public final By actorDeletedSuccessfullyNotification(Actor actor) {
        return By.xpath("//div/ul/li[contains(text(),'Successfully deleted 1 actor.')]");
    }

    public final By submitDeletion = By.xpath("//div/input[@type='submit']");

    public void addNewActor(Actor actor) {
        driver.findElement(addNewEntryButton).click();
        driver.findElement(actorFirstNameTextField).sendKeys(actor.getFirstname());
        driver.findElement(actorLastNameTextField).sendKeys(actor.getLastname());
        driver.findElement(actorBirthdate).sendKeys(actor.getBirthdate().format(DateTimeFormatter.ISO_DATE));
        driver.findElement(genderList).click();
        driver.findElement(gender(actor.getGender())).click();
    }

    public void saveActor() {
        driver.findElement(saveButton).click();
    }

    public void isAddedActorPresent(Actor actor) {
        waitElementIsVisible(driver.findElement(actorInList(actor.getFirstname() + " " + actor.getLastname())));
    }

    public void deleteAddedActor() {
        driver.findElement(actorCheckbox).click();
        driver.findElement(actionList).click();
        driver.findElement(deleteAction).click();
        driver.findElement(goButton).click();
        driver.findElement(submitDeletion).click();
    }

    public void actorAddedSuccessfullyNotificationIsShown(Actor actor) {
        waitElementIsVisible(driver.findElement(actorAddedSuccessfullyNotification(actor)));
    }

    public void actorDeletedSuccessfullyNotificationIsShown(Actor actor) {
        waitElementIsVisible(driver.findElement(actorDeletedSuccessfullyNotification(actor)));
    }

}
