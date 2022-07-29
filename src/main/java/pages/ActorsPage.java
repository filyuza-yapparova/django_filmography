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



    public final By actorInList = By.xpath("");
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


    public final By allActorCheckbox = By.xpath("//tr/th/div/span/input[@type=\"checkbox\"]");

    public final By actorCheckbox(Actor actor) {
        return By.xpath("//tbody/tr/th/a[contains (text(), \""
                + actor.getFirstname() + " " + actor.getLastname()
                + "\")]/parent::*/parent::*/td/input [@type=\"checkbox\"]");
    }

    public final By actionList = By.xpath("//label/select[@name='action']");
    public final By deleteAction = By.xpath("//label/select/option[@value='delete_selected']");
    public final By goButton = By.xpath("//div/button[@type='submit']");

    public final By actorAddedSuccessfullyNotification(Actor actor) {
        return By.xpath("//div/div/ul/li[@class=\"success\"]" +
                "[contains(text(),'The actor')]/a[contains(text(),'"
                + actor.getFirstname() + " " + actor.getLastname()
                + "')]/following-sibling::text()[contains(., 'was added successfully.')]");
    }

    public final By actorEditedSuccessfullyNotification(Actor actor) {
        return By.xpath("//div/div/ul/li[@class=\"success\"]" +
                "[contains(text(),'The actor')]/a[contains(text(),'"
                + actor.getFirstname() + " " + actor.getLastname()
                + "')]/following-sibling::text()[contains(., 'was changed successfully.')]");
    }

    public final By actorDeletedSuccessfullyNotification(Actor actor) {
        return By.xpath("//div/ul/li[contains(text(),'Successfully deleted 1 actor.')]");
    }

    public final By submitDeletion = By.xpath("//div/input[@type='submit']");

    public final By actorsNumberInList = By.xpath("//div[contains(@class,'results')]" +
                "/following-sibling::*");

    public ActorsPage fillActorFields(Actor actor) {
        driver.findElement(actorFirstNameTextField).sendKeys(actor.getFirstname());
        driver.findElement(actorLastNameTextField).sendKeys(actor.getLastname());
        driver.findElement(actorBirthdate).sendKeys(actor.getBirthdate().format(DateTimeFormatter.ISO_DATE));
        driver.findElement(genderList).click();
        driver.findElement(gender(actor.getGender())).click();
        return this;
    }

    public ActorsPage addNewActor(Actor actor) {
        driver.findElement(addNewEntryButton).click();
        fillActorFields(actor);
        return this;
    }

    public ActorsPage saveActor() {
        driver.findElement(saveButton).click();
        return this;
    }

    public ActorsPage isAddedActorPresent(Actor actor) {
        waitElementIsVisible(driver.findElement(actorInList(actor.getFirstname() + " " + actor.getLastname())));
        return this;
    }

    public void deleteAction() {
        driver.findElement(actionList).click();
        driver.findElement(deleteAction).click();
        driver.findElement(goButton).click();
        driver.findElement(submitDeletion).click();
    }

    public ActorsPage deleteAddedActor(Actor actor) {
        driver.findElement(actorCheckbox(actor)).click();
        deleteAction();
        return this;
    }

    public ActorsPage deleteAllActors() {
        driver.findElement(allActorCheckbox).click();
        deleteAction();
        return this;
    }

    public ActorsPage actorAddedSuccessfullyNotificationIsShown(Actor actor) {
        waitElementIsVisible(driver.findElement(actorAddedSuccessfullyNotification(actor)));
        return this;
    }

    public ActorsPage actorDeletedSuccessfullyNotificationIsShown(Actor actor) {
        waitElementIsVisible(driver.findElement(actorDeletedSuccessfullyNotification(actor)));
        return this;
    }

    public ActorsPage editActor(Actor actor, Actor updatedActor) {
        findActorInActorList(actor.getFirstname(), actor.getLastname());
        fillActorFields(updatedActor);
        return this;
    }

    public ActorsPage findActorInActorList(String firstname, String lastname) {
        driver.findElement(actorInList).click();
        return this;
    }

    public ActorsPage actorEditedSuccessfullyNotificationIsShown(Actor actor) {
        waitElementIsVisible(driver.findElement(actorEditedSuccessfullyNotification(actor)));
        return this;
    }

    public void watchActorList() {

    }
}
