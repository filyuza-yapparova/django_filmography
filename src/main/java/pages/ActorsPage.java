package pages;

import constants.Constant.NotificationType;
import entities.Actor;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

public class ActorsPage extends BasePage {
    public ActorsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Actor field's locators
     */
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

    /**
     * Searching actor locator by actor's
     * @param fullname
     */
    public final By actorInList(String fullname) {
        return By.xpath("//table/tbody/tr/th/a[contains(text(),'" + fullname + "')]");
    }

    /**
     * Checkbox locators
     */
    public final By allActorCheckbox = By.xpath("//tr/th/div/span/input[@type=\"checkbox\"]");
    public final By actorCheckbox(Actor actor) {
        return By.xpath("//tbody/tr/th/a[contains (text(), \""
                + actor.getFullname()
                + "\")]/parent::*/parent::*/td/input [@type=\"checkbox\"]");
    }

    /**
     * Action buttons locators (that needs to delete item)
     */
    public final By actionList = By.xpath("//label/select[@name='action']");
    public final By deleteAction = By.xpath("//label/select/option[@value='delete_selected']");
    public final By goButton = By.xpath("//div/button[@type='submit']");


    public final By actorDeletedSuccessfullyNotification = By.xpath("//div[@class=\"content\"]/ul[@class=\"messagelist\"]/li[@class=\"success\"]");

    public final By submitDeletion = By.xpath("//div/input[@type='submit']");

    public final By actorsNumberInList = By.xpath("//div[contains(@class,'results')]" +
            "/following-sibling::*");

    public final By validationNotificationBelowForm = By.xpath("//div/form[@id=\"actor_form\"]" +
            "/div/p[@class=\"errornote\"][contains(text(),'Please correct the errors below.')]");

    public final By errorInSomeActorField = By.xpath("//div/ul[@class=\"errorlist\"]");

    /**
     * Filling actor fields
     */
    public ActorsPage fillActorFields(Actor actor) {
        driver.findElement(actorFirstNameTextField).sendKeys(actor.getFirstname());
        driver.findElement(actorLastNameTextField).sendKeys(actor.getLastname());
        driver.findElement(actorBirthdate).sendKeys(actor.getBirthdate().format(DateTimeFormatter.ISO_DATE));
        driver.findElement(genderList).click();
        driver.findElement(gender(actor.getGender())).click();
        return this;
    }

    /**
     * Clicking on Add new button with filling actor fields
     */
    public ActorsPage addNewActor(Actor actor) {
        driver.findElement(addNewEntryButton).click();
        fillActorFields(actor);
        return this;
    }

    /**
     * Fast actor creating with auto save action
     */
    public ActorsPage createActorAndSave(Actor actor) {
        driver.findElement(addNewEntryButton).click();
        fillActorFields(actor);
        driver.findElement(saveButton).click();
        return this;
    }

    /**
     * Save action
     */
    public ActorsPage saveActor() {
        driver.findElement(saveButton).click();
        return this;
    }

    /**
     * Checking is actor presents in table (looking only on fullname)
     * @param actor Takes first actor with similar fullname
     *              (we are working with table as there is only one actor with that fullname)
     */
    public ActorsPage isActorPresent(Actor actor) {
        waitElementIsVisible(driver.findElement(actorInList(actor.getFullname())));
        return this;
    }

    /**
     * Delete action (choosing to delete action with submitting action
     */
    public void deleteAction() {
        driver.findElement(actionList).click();
        driver.findElement(deleteAction).click();
        driver.findElement(goButton).click();
        driver.findElement(submitDeletion).click();
    }

    /**
     * Deleting one actor by his fullname
     * @param actor takes FIRST actor by his fullname
     *              (there can be more than one actor with similar fullname,
     *              but we are working with table as there is only one actor with this name)
     */
    public ActorsPage deleteSingleActor(Actor actor) {
        driver.findElement(actorCheckbox(actor)).click();
        deleteAction();
        return this;
    }

    /**
     * Deleting all actors without any checking before and after
     */
    public ActorsPage deleteAllActors() {
        driver.findElement(allActorCheckbox).click();
        deleteAction();
        return this;
    }

    /**
     * Deleting all actors with checking actors count
     * before (in table list)
     * and
     * after (in deleting notification)
     */
    public ActorsPage deleteAllActorsAndCheckDeletedCount() {
        var countOfActorsBeforeDeleting = watchActorList("before");
        deleteAllActors();
        var countOfDeletedActors = watchActorList("after");
        assertThat(countOfActorsBeforeDeleting).isEqualTo(countOfDeletedActors);
        return this;
    }

    /**
     * Default method for notification that shown after successfully adding or editing actor
     * @param notificationType takes on of the enums ADD, EDIT or DELETE (last is not working yet)
     * @param actor takes one actor which we are working with
     */
    public ActorsPage notificationIsShown(NotificationType notificationType, Actor actor) {
        notificationIsShown(notificationType, "actor", actor.getFullname());
        return this;
    }

    /**
     * Waiting for notification that shown after successfully deleting action
     */
    public ActorsPage actorDeletedSuccessfullyNotificationIsShown() {
        waitElementIsVisible(driver.findElement(actorDeletedSuccessfullyNotification));
        return this;
    }

    /**
     * Edit existing
     * @param actor to
     * @param updatedActor
     */
    public ActorsPage editActor(Actor actor, Actor updatedActor) {
        findActorInActorList(actor);
        clearActorFields();
        fillActorFields(updatedActor);
        saveActor();
        return this;
    }

    /**
     * Searching and opening existing
     * @param actor
     */
    public ActorsPage findActorInActorList(Actor actor) {
        driver.findElement(actorInList(actor.getFullname())).click();
        return this;
    }

    /**
     * Clear all actor fields (for example for editing)
     */
    public ActorsPage clearActorFields() {
        driver.findElement(actorFirstNameTextField).clear();
        driver.findElement(actorLastNameTextField).clear();
        driver.findElement(actorBirthdate).clear();
        return this;
    }

    /**
     * Checking count of actors before and after deleting
     * @param type selects locator where we are looking count - table bottom or notification
     */
    public Integer watchActorList(String type) {
        if (type.equals("before")) {
            return Integer.valueOf(driver.findElement(actorsNumberInList)
                    .getText().split(" ")[0]
            );
        } else if (type.equals("after")) {
            return Integer.valueOf(driver.findElement(actorDeletedSuccessfullyNotification)
                    .getText().split(" ")[2]
            );
        } else {
            System.out.println("INCORRECT STRING -> " + type);
            return 0;
        }
    }

    /**
     * Waiting for some validation error and notification
     */
    public ActorsPage fieldValidationIsShown() {
        waitElementIsVisible(driver.findElement(validationNotificationBelowForm));
        waitElementIsVisible(driver.findElement(errorInSomeActorField));
        return this;
    }
}
